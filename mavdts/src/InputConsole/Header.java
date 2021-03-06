package InputConsole;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;


public class Header extends JPanel implements ActionListener{
	
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    JMenuBar crap = null;
    
    public Header() {
		super();
		setPreferredSize(new Dimension(1000, 30));
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//this.setSize(300, 300);
		this.setLayout(null);
		crap = createMenuBar();
		crap.setBounds(new Rectangle(0, 0, 1000, 30));
		this.add(crap, null);

	}

    public JMenuBar createMenuBar() {
    	
    	JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        //JRadioButtonMenuItem rbMenuItem;
        //JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("New", KeyEvent.VK_N);    
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();

        //ImageIcon icon = createImageIcon("images/middle.gif");
        menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Save As", KeyEvent.VK_A);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        
        //Build second menu in the menu bar.
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        //menu.getAccessibleContext().setAccessibleDescription(
        //        "This menu does nothing");
        menuBar.add(menu);
        
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Help Contents", KeyEvent.VK_H);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        menuItem = new JMenuItem("About MAVDTS", KeyEvent.VK_A);
        menuItem.addActionListener(this);
        menu.add(menuItem);


        return menuBar;
    }

    /*public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }*/

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

    public void actionPerformed(ActionEvent e) {
        /*JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")";
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());*/
    }

    public void itemStateChanged(ItemEvent e) {
        /*JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());*/
    }

    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    /*protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Header.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Header Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create/set menu bar and content pane.
        /*Header demo = new Header();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());*/

        //Create and set up the popup menu.
        //demo.createPopupMenu();

        frame.add(new Header());

        //Display the window.
        frame.pack();
        frame.setSize(1000, 30);
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

    /*class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }*/
}
