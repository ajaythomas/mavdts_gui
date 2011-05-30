package InputConsole;

//import InputConsole.InputTree;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import javax.swing.*;
//import javax.swing.border.*;
import java.awt.*;

//import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
//import java.awt.event.KeyEvent;


public class MainWindow extends JFrame{
	
	private static JPanel jContentPane = null;
	private JPanel InputPanel = null;
	private JPanel OutputPanel = null;
	private JPanel HeaderPanel = null;
	private Java3dApplet GraphicsWindow = null;
	private static Java3dApplet MeshWindow = null;
	public static int mesh_flag = 0;

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
		Color c = new Color(0,0,90);
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(c);
			jContentPane.setBounds(new Rectangle(0, 0,1000, 800));
			jContentPane.setLayout(null);
			jContentPane.add(getInputPanel(), null);
			jContentPane.add(getOutputPanel(), null);
			jContentPane.add(getHeader(), null);
			jContentPane.add(getGraphicsWindow(), null);
			
		}
		return jContentPane;
	}
	
	public static void change_mesh_flag()
	{
		//System.out.println(mesh_flag);
		mesh_flag=1;
		//jContentPane.add(getMeshWindow(), null);
		//initialize();
		//System.out.println(mesh_flag);
	}


	private JPanel getInputPanel() {
		if (InputPanel == null) {
			InputPanel = new InputTree();
			InputPanel.setBounds(new Rectangle(0, 30, 295, 545));
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
	
	private Java3dApplet getGraphicsWindow() {
		if (GraphicsWindow == null) {
			GraphicsWindow = new Plot1();
			GraphicsWindow.setBounds(new Rectangle(300, 30, 697, 545));
		}
		return GraphicsWindow;
	}
	
	private static Java3dApplet getMeshWindow() {
		if (MeshWindow == null) {
			MeshWindow = new Final1();
			MeshWindow.setBounds(new Rectangle(300, 30, 697, 545));
		}
		return MeshWindow;
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
