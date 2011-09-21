package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import backend.GearScheme;
import backend.Shaft;
import backend.ShaftType;

public class MyFrame extends JFrame
{

	private static final long serialVersionUID = 1L;

	/**
	 * Structure:
	 * main pane (vertical box)
	 *  - file pane (horizontal box)
	 *    - file name pane (vertical box)
	 *      - file name
	 *      - file open drop down
	 *    - file ctrls pane (vertical box)
	 *      - "Automatically saved"
	 *      - "New Gear Scheme (copies current)"
	 *  - content pane (horizontal box)
	 *    - measures pane (vertical box)
	 *      - measures scroll pane
	 *        - measures table
	 *      - "Add Planet Element"
	 *    - paths pane (horizontal box)
	 *      - paths scroll pane
	 *        - paths table
	 *      - "Add transmission path"
	 *      - legend text "Drag cells to assign shafts"
	 *      - legend colors pane (horizontal)
	 *        - (anonymous labels)
	 *    - speeds scroll pane (NYI)
	 *      - speeds table 
	 */
	JPanel main_pane = null;
	JPanel file_pane = null;
	JPanel file_name_pane = null;
	JLabel file_name_label = null;
	JComboBox file_open_box = null;
	JPanel file_ctrls_pane = null;
	JLinkLabel new_scheme_label = null;
	
	JPanel content_pane = null;
	JPanel measures_pane = null;
	JScrollPane measures_scroll_pane = null;
	AbstractTableModel measures_table_model = null;
	JTable measures_table = null;
	JLinkLabel add_element_label = null;
	
	JPanel paths_pane = null;
	JScrollPane paths_scroll_pane = null;
	AbstractTableModel paths_table_model = null;
	JTable paths_table = null;
	JLinkLabel add_path_label = null;
	JPanel legend_colors_pane = null;
	
	/**
	 * This is the default constructor
	 */
	public MyFrame()
	{
		super();
		initialize();
	}

	void initialize()
	{
		this.setContentPane(get_main_pane());
		this.setTitle("Welcome to the Planet of the Gears");
		this.setSize(750, 300);
		this.setBackground(Color.white);
	}

	JPanel get_main_pane()
	{
		if ( main_pane == null )
		{
			main_pane = new JPanel();
			main_pane.setLayout(new BoxLayout(main_pane, BoxLayout.Y_AXIS));
			main_pane.add(Box.createRigidArea(new Dimension(0, 5)));
			main_pane.add(get_file_pane());
			main_pane.add(Box.createRigidArea(new Dimension(0, 5)));
			main_pane.add(get_content_pane());
			main_pane.setBackground(Color.white);
		}
		return main_pane;
	}
	
	JPanel get_file_pane()
	{
		if ( file_pane == null )
		{
			file_pane = new JPanel();
			file_pane.setLayout(new BoxLayout(file_pane, BoxLayout.X_AXIS));
			file_pane.add(get_file_name_pane());
			file_pane.add(Box.createRigidArea(new Dimension(10, 0)));
			file_pane.add(get_file_ctrls_pane());
			file_pane.setBackground(Color.white);
		}
		return file_pane;
	}

	JPanel get_file_name_pane()
	{
		if ( file_name_pane == null )
		{
			file_name_pane = new JPanel();
			file_name_pane.setLayout(new BoxLayout(file_name_pane, BoxLayout.Y_AXIS));
			file_name_pane.add(get_file_name_label());
			file_name_pane.add(get_file_open_box());
			file_name_pane.setBackground(Color.white);
		}
		return file_name_pane;
	}
	
	JLabel get_file_name_label()
	{
		if ( file_name_label == null )
		{
			file_name_label = new JLabel("Example Gear Scheme");
			file_name_label.setBorder
				(BorderFactory.createCompoundBorder
						(BorderFactory.createCompoundBorder
								( BorderFactory.createEmptyBorder(5, 5, 5, 5)
								, BorderFactory.createLineBorder(Color.black)
								)
						, BorderFactory.createEmptyBorder(5, 5, 5, 5)
						)
				);
		}
		return file_name_label;
	}

	JComboBox get_file_open_box()
	{
		if ( file_open_box == null )
		{
			file_open_box = new JComboBox(new String[] {"Open Scheme..."});
			file_open_box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			file_open_box.setBackground(Color.white);
		}
		return file_open_box;
	}

	JPanel get_file_ctrls_pane()
	{
		if ( file_ctrls_pane == null )
		{
			file_ctrls_pane = new JPanel();
			file_ctrls_pane.setLayout(new BoxLayout(file_ctrls_pane, BoxLayout.Y_AXIS));
			JLabel auto_l = new JLabel("Automatically Saved");
			auto_l.setFont(auto_l.getFont().deriveFont(Font.PLAIN));
			auto_l.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			file_ctrls_pane.add(auto_l);
			file_ctrls_pane.add(get_new_scheme_label());
			file_ctrls_pane.setBackground(Color.white);
		}
		return file_ctrls_pane;
	}
	
	JLinkLabel get_new_scheme_label()
	{
		if ( new_scheme_label == null )
		{
			new_scheme_label = new JLinkLabel("New Gear Scheme (copies current)");
			new_scheme_label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		}
		return new_scheme_label;
	}
	
	JPanel get_content_pane()
	{
		if (content_pane == null)
		{
			content_pane = new JPanel();
			content_pane.setLayout(new BorderLayout());
			content_pane.add(get_measures_pane(), BorderLayout.CENTER);
			content_pane.add(get_paths_pane(), BorderLayout.LINE_END);
			content_pane.setBackground(Color.white);
		}
		return content_pane;
	}

	JPanel get_measures_pane()
	{
		if ( measures_pane == null )
		{
			measures_pane = new JPanel();
			measures_pane.setLayout(new BoxLayout(measures_pane, BoxLayout.Y_AXIS));
			measures_pane.add(new JLabel("Measures of the Gears (cog wheels)"));
			measures_pane.add(get_measures_scroll_pane());
			measures_pane.add(get_add_element_label());
			measures_pane.setBackground(Color.white);
		}
		return measures_pane;
	}
	
	JScrollPane get_measures_scroll_pane()
	{
		if (measures_scroll_pane == null)
		{
			measures_scroll_pane = new JScrollPane(get_measures_table());
			measures_scroll_pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//	measures_scroll_pane.setBackground(Color.white);
		}
		return measures_scroll_pane;
	}

	JTable get_measures_table()
	{
		if (measures_table == null)
		{
			measures_table = new JTable(get_measures_table_model());
			measures_table.setCellSelectionEnabled(false);
//			Enumeration<TableColumn> cols = measures_table.getColumnModel().getColumns();
//			TableColumn c;
//			while ( cols.hasMoreElements() )
//			{
//				c = cols.nextElement();
//				c.setPreferredWidth(20);
//			}
		}
		return measures_table;
	}

	AbstractTableModel get_measures_table_model()
	{
		if ( measures_table_model == null)
		{
			measures_table_model = new MeasuresTableModel();
			measures_table_model.addTableModelListener(new TableModelListener()
			{
				public void tableChanged(TableModelEvent e)
				{
					((PathsTableModel)get_paths_table().getModel()).fireTableDataChanged();
				}
			});
		}
		return measures_table_model;
	}

	JLinkLabel get_add_element_label()
	{
		if ( add_element_label == null )
		{
			add_element_label = new JLinkLabel("Add Planet Element");
			add_element_label.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					GearScheme.the_scheme().add_planet_element();
					measures_table_model.fireTableStructureChanged();
					paths_table_model.fireTableStructureChanged();
				}
			});
		}
		return add_element_label;
	}
	
	JPanel get_paths_pane()
	{
		if ( paths_pane == null )
		{
			paths_pane = new JPanel();
			paths_pane.setLayout(new BoxLayout(paths_pane, BoxLayout.Y_AXIS));
			paths_pane.add(new JLabel("Transmission Paths"));
			paths_pane.add(get_paths_scroll_pane());
			paths_pane.add(get_add_path_label());
			JLabel legend_l = new JLabel("Drag cells in table to assign shafts."
										//+"\nRight-click for direct drive."
					);
			legend_l.setFont(legend_l.getFont().deriveFont(Font.PLAIN));
			paths_pane.add(legend_l);
			paths_pane.add(get_legend_colors_pane());
			paths_pane.setBackground(Color.white);
		}
		return paths_pane;
	}
	
	JScrollPane get_paths_scroll_pane()
	{
		if (paths_scroll_pane == null)
		{
			paths_scroll_pane = new JScrollPane(get_paths_table());
			paths_scroll_pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//	paths_scroll_pane.setBackground(Color.white);
		}
		return paths_scroll_pane;
	}

	AbstractTableModel get_paths_table_model()
	{
		if ( paths_table_model == null )
		{
			paths_table_model = new PathsTableModel();
		}
		return paths_table_model;
	}
	
	JTable get_paths_table()
	{
		if (paths_table == null)
		{
			paths_table = new JTable(get_paths_table_model());
			paths_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			paths_table.setCellSelectionEnabled(true);
			paths_table.setDragEnabled(true);  // automatic with setting Handler?
			paths_table.setDropMode(DropMode.ON);
			paths_table.setTransferHandler(new MyTableTransferHandler(get_paths_table_model()));
			paths_table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
			{
				// only to change background color
				public Component getTableCellRendererComponent
				(JTable table, Object val, boolean sel, boolean foc, int row, int col) 
				{
					JLabel cell = (JLabel) super.getTableCellRendererComponent
												(table, val, sel, foc, row, col);
					if ( col > 0 )
					{
						Shaft s = GearScheme.get_shaft(col-1);
						ShaftType t = GearScheme.get_path(row).get_shaft_type(s);
						cell.setBackground(t.get_color());
					}
					else // needed since JLabel is reused for first col !
						cell.setBackground(ShaftType.TRANSPARENT_COLOR);
					return cell;
				}
			});
		}
		return paths_table;
	}
	
	JLinkLabel get_add_path_label()
	{
		if ( add_path_label == null )
		{
			add_path_label = new JLinkLabel("Add transmission path");
			add_path_label.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					GearScheme.the_scheme().add_path();
					measures_table_model.fireTableStructureChanged();
					paths_table_model.fireTableStructureChanged();
				}
			});
		}
		return add_path_label;
	}

	JLabel new_legend_label(ShaftType t)
	{
		JLabel lab = new JLabel(t.toString());
		lab.setOpaque(true);
		lab.setBackground(t.get_color());
		lab.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return lab;
	}
	
	JPanel get_legend_colors_pane()
	{
		if ( legend_colors_pane == null )
		{
			legend_colors_pane = new JPanel();
			legend_colors_pane.setLayout(new BoxLayout(legend_colors_pane, BoxLayout.X_AXIS));
			JLabel l = new JLabel("Color code: ");
			l.setFont(l.getFont().deriveFont(Font.PLAIN));
			legend_colors_pane.add(l);
			legend_colors_pane.add(new_legend_label(ShaftType.INPUT));
			legend_colors_pane.add(new_legend_label(ShaftType.OUTPUT));
			legend_colors_pane.add(new_legend_label(ShaftType.GROUND));
			legend_colors_pane.setBackground(Color.white);
		}
		return legend_colors_pane; 
	}
}
