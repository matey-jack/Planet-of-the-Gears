package gui;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import backend.ElementType;
import backend.GearScheme;
import backend.PlanetElement;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * table has the planet elements as columns 
 * (aligned with the planet elements in the drawing)
 * and three rows for the measure of ring, planet, and sun respectively.
 */

public class MeasuresTableModel extends AbstractTableModel implements TableModel
{
	private static final long serialVersionUID = 2048271888352168647L;
	static NumberFormat num_formatter;
	
	public static boolean almost_equal(double a, double b)
	{
		return Math.abs(a-b) < Math.pow(10, -4);
	}
	
	public static String format_number(double x)
	{
		if (num_formatter == null)
		{
			num_formatter = NumberFormat.getInstance(Locale.ENGLISH); 
			// num_formatter.setMaximumFractionDigits(4);
		}
		if (almost_equal(x,  1)) return "1.0";
		if (almost_equal(x, -1)) return "-1.0";
		if (almost_equal(x,  0)) return "0.0";
		return num_formatter.format(x);
	}
	
	@Override
	public int getRowCount()
	{
		return 3; // ring, planet, sun
		// headings are done by the TableControl
	}

	@Override
	public int getColumnCount()
	{
		// one column for the row headings
		return 1 + GearScheme.the_scheme().planet_elements.size();
	}

	@Override
	public String getColumnName(int i)
	{
		if (i==0) return ""; // column of row headings
		else return Integer.toString(i);
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return (col!=0);
	}

	public ElementType row_to_element_type(int row)
	{
		switch (row)
		{
		case 0: return ElementType.RING;
		case 1: return ElementType.PLANET;
		case 2: return ElementType.SUN;
		default: throw new NotImplementedException();
		}
	}
	
	@Override
	public Object getValueAt(int row, int col)
	{
		
		if (col==0) // row titles
			return row_to_element_type(row).toString();
		else
			return format_number(GearScheme.get_element(col-1)
								.measure(row_to_element_type(row)));
	}

	@Override
	public void setValueAt(Object val, int row, int col)
	{
		if (col==0)
			throw new IllegalArgumentException();
		if ( ! (val instanceof String) )
			throw new IllegalArgumentException();
		PlanetElement pe = GearScheme.get_element(col-1);
		pe.set_measure(row_to_element_type(row), (String) val);
		update_element(col); 
	}
	
	private void update_element(int col)
	{
		for (int r = 0; r < 3; r++)
		{
			fireTableCellUpdated(r, col);
			
		}
	}

}
