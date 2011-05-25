package InputConsole;

//import InputConsole.InputTree;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class MainWindow extends JFrame{
	
	private JPanel jContentPane = null;
	private JPanel InputPanel = null;
	private JPanel OutputPanel = null;
	private JPanel HeaderPanel = null;
	
	//static int display_flag = 0;
	
	public MainWindow()
	{
		super();
		setPreferredSize(new Dimension(800, 800));
		initialize();
	}
	
	public void initialize()
	{
		//this.setLayout(null);
		this.setSize(1000, 800);
		this.setContentPane(getJContentPane());
		this.setTitle("MAVDTS");
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBounds(new Rectangle(0, 0,1000, 800));
			jContentPane.setLayout(null);
			jContentPane.add(getInputPanel(), null);
			jContentPane.add(getOutputPanel(), null);
			jContentPane.add(getHeader(), null);
		}
		return jContentPane;
	}


	private JPanel getInputPanel() {
		if (InputPanel == null) {
			InputPanel = new InputTree();
			InputPanel.setBounds(new Rectangle(0, 30, 300, 550));
			/*Border one = BorderFactory.createEtchedBorder(  );
		    Border two = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue);
		    InputPanel.setBorder(BorderFactory.createCompoundBorder(one, two));*/
		}
		return InputPanel;
	}
	
	private JPanel getOutputPanel() {
		if (OutputPanel == null) {
			OutputPanel = new OutputConsole();
			OutputPanel.setBounds(new Rectangle(0, 580, 1000, 220));
		}
		return OutputPanel;
	}
	
	private JPanel getHeader(){
		
		if(HeaderPanel == null){
			
			HeaderPanel = new Header();
			HeaderPanel.setBounds(new Rectangle(0,0,1000,30));
		}
		return HeaderPanel;
	}
	
	
	/*
	private static void createAndShowGUI() {
        
        //Create and set up the window.
        JFrame frame = new JFrame("MAVDTS");
        
        frame.setSize(1000, 800);
        //frame.setBounds(new Rectangle(0, 0, 1000, 800));
        //frame.add(, null);
        

        //Add content to the window.
        frame.add(new MainWindow());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public static void main(String args[])
	{
		    javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}*/
}
