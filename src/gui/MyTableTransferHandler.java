package gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.JTable.DropLocation;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import backend.GearScheme;

public class MyTableTransferHandler extends TransferHandler
{
	// The transfer handler contains the code for DnD, no data.
	// It handles both the source and destination part of the transfer.

	// Since our application is so specific (source and dest are the same control
	// and we talk directly to the Model of the control anyway)
	// we can do import and export in the same routine.
	
	AbstractTableModel table_model;
	// invariant: all transfers must originate from and be destined to a table which uses 
	// this model
	final static private String MAGIC_STRING = "PathsTable";
	private MyTableTransferHandler() {}
	
	public MyTableTransferHandler(AbstractTableModel table_model)
	{
		this.table_model = table_model;
	}
	
	public int getSourceActions(JComponent c) { return MOVE; }

	protected Transferable createTransferable(JComponent c) 
	{
		JTable t = (JTable)c;
		return new StringSelection(MAGIC_STRING+","+Integer.toString(t.getSelectedRow())
								               +","+Integer.toString(t.getSelectedColumn())
		                          );
	}

	public String get_data(TransferSupport sup)
	{
		try { return (String)sup.getTransferable().getTransferData(DataFlavor.stringFlavor); }
		catch (Exception e) { return ""; }
	}

	public boolean canImport(TransferSupport sup) 
	{
		// extract the data to see if it comes from ourselves
		Loc source = new Loc(get_data(sup));
		JTable.DropLocation dest = (JTable.DropLocation) sup.getDropLocation();
		return can_import(source, dest);
	}

	boolean can_import(Loc source, JTable.DropLocation dest)
	{
		return source.is_valid && source.col>0 
		    && dest.getColumn() > 0
		    && source.row == dest.getRow();
	}

	public boolean importData(TransferSupport sup) 
	{
		Loc source = new Loc(get_data(sup));
		JTable.DropLocation dest = (JTable.DropLocation) sup.getDropLocation();
		if ( ! sup.isDrop() || ! can_import(source, dest) ) 
			return false;
		
		GearScheme.get_path(source.row).swap_shafts(source.col-1, dest.getColumn()-1);
		table_model.fireTableDataChanged();
		return true;
	}

	final class Loc
	{
		public final boolean is_valid;
		public final int row, col;
		//@SuppressWarnings(value = { "FieldNotInitialized" })
		Loc(String transfer)
		{
			String[] parts = transfer.split(",");
			if ( (parts.length != 3) || (! parts[0].equals(MAGIC_STRING) ) )
			{
				is_valid = false;
				row = 0; // actually undefined, just put something for the compiler here
				col = 0;
			}
			else
			{
				row = Integer.parseInt(parts[1]);
				col = Integer.parseInt(parts[2]);
				is_valid = true;
			}
		}
	}
}
