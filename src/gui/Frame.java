package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame extends JFrame
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JSplitPane jSplitPane = null;

	private JTable jTable1 = null;
	private static Frame f;
	
	public static void main(String[] args)
	{
//		javax.swing.SwingUtilities.invokeLater(new Runnable() 
//    	{
//    		public void run() 
//    		{
//    			f = new Frame();
//    		}
//    	});
		
	}
	/**
	 * This is the default constructor
	 */
	public Frame()
	{
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		setSize(531, 437);
		setContentPane(getJContentPane());
		setTitle("JFrame");
		pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		addWindowListener(new WindowAdapter() {
//
//			public void windowClosed(WindowEvent e) {
//				System.exit(0);
//			}
//		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJSplitPane(), BorderLayout.WEST);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane()
	{
		if (jSplitPane == null)
		{
			jSplitPane = new JSplitPane();
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane.setTopComponent(getJTable1());
			jSplitPane.setDividerLocation(150);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable1()
	{
		if (jTable1 == null)
		{
			jTable1 = new JTable();
			jTable1.setModel(new DefaultTableModel());
		}
		return jTable1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
