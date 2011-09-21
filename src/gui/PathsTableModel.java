package gui;

import javax.swing.table.AbstractTableModel;

import backend.GearScheme;

public class PathsTableModel extends AbstractTableModel
{
	@Override
	public int getColumnCount()
	{
		// one for the index plus the number of shafts
		return 1 + GearScheme.the_scheme().all_shafts().size();
	}

	@Override
	public int getRowCount()
	{
		return GearScheme.the_scheme().paths.size();
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		if (col==0) 
			return row+1;
		else
			return MeasuresTableModel.format_number(
					GearScheme.get_path(row).shaft_speed(GearScheme.get_shaft(col-1))
					);
	}
	
	@Override
	public String getColumnName(int i)
	{
		if (i==0) return "path"; 
		else return GearScheme.get_shaft(i-1).toString();
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}

	public void update_path(int row)
	{
		for (int col = 1; col < getColumnCount(); col++)
			fireTableCellUpdated(row, col);
	}
}
