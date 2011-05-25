package InputConsole;

//import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class OutputConsole extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    JScrollPane test = null;
    
    Connection con;
	Statement stmt;
	String query;
	PreparedStatement pstmt = null;
	ResultSet rs;
    
    public OutputConsole(){
    	
    	super();
		setPreferredSize(new Dimension(1000, 220));
		initialize();
    }
    
    private void initialize() {
		//this.setSize(300, 300);
		this.setLayout(null);
		
		test = createScrollPane();
		test.setBounds(new Rectangle(0, 0, 1000, 220));
		this.add(test, null);

	}

        public JScrollPane createScrollPane() {
        //Create the content-pane-to-be.
        //JPanel contentPane = new JPanel(null);
        //contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(1000,220);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        //contentPane.add(scrollPane, 0,0);
        //if(MainWindow.display_flag == 1)
        test();

        return scrollPane;
    }

    /*public void createPopupMenu() {
        JMenuItem menuItem;

        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("A popup menu item");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Another popup menu item");
        menuItem.addActionListener(this);
        popup.add(menuItem);

        //Add listener to the text area so the popup menu can come up.
        MouseListener popupListener = new PopupListener(popup);
        output.addMouseListener(popupListener);
    }*/
        
    public void test(){
    	
    	//String s = "Hello";
    	float l = doUpdate("strip_beam","length","object_id");
    	String s = Float.toString(l); 
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    public void actionPerformed(ActionEvent e) {
        //JMenuItem source = (JMenuItem)(e.getSource());
        /*String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")";*/
    	
    	float l = doUpdate("strip_beam","length","object_id");
    	String s = Float.toString(l); 
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }
    
    private float doUpdate(String table,String column,String primary_key) {
		
		float l=0;
    	try {
			//float tempVal = Float.parseFloat(value);
			doConnect();
			query = "Select "+column+" from "+table+" where "+primary_key+" = 1";
			rs=stmt.executeQuery(query);
			rs.next();
			l=rs.getFloat(1);
			stmt.close();
			con.close();
			
		}
		catch(SQLException ee)
		{
			JOptionPane one = new JOptionPane();
			JOptionPane.showMessageDialog(one,ee.getMessage());	
		}
		return l;
		
	}

    /*public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }*/

    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    public boolean doConnect()
    {
            boolean flag;
			try
            {
            		String dbUrl = "jdbc:mysql://localhost:3306/mavdts";
            		String dbClass ="com.mysql.jdbc.Driver";
            		Class.forName(dbClass);
         			con = DriverManager.getConnection (dbUrl,"root","");
         			stmt = con.createStatement();
         			flag=true;
            }
            catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error in Connection:"+ex.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                    flag=false;
            }
            return(flag);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Output Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        //frame.setJMenuBar(demo.createMenuBar());
        //frame.setContentPane(demo.createContentPane());
        frame.add(new OutputConsole());

        //Create and set up the popup menu.
        //demo.createPopupMenu();

        //Display the window.
        frame.pack();
        frame.setSize(1000, 220);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
}