package InputConsole;
//import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.tree.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import javax.swing.JOptionPane;

public class InputTree extends JPanel implements TreeSelectionListener{

	private static final long serialVersionUID = 1L;
	private JTree inputTree_Tree = null;
	private JScrollPane inputTree_TreeView = null;
	DefaultMutableTreeNode inputTree_Tree_Top = new DefaultMutableTreeNode("Input Attributes");  //  @jve:decl-index=0:
	
	Connection con;
	Statement stmt;
	String query;
	PreparedStatement pstmt = null;

	/**
	 * This is the default constructor
	 */
	public InputTree() {
		super();
		setPreferredSize(new Dimension(300, 550));
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	
	private void initialize() {
		//this.setSize(300, 300);
		this.setLayout(null);
		
		inputTree_TreeView = new JScrollPane(getInputTree_Tree());
		inputTree_TreeView.setBounds(new Rectangle(0, 0, 300, 550));
		this.add(inputTree_TreeView, null);

	}

	/**
	 * This method initializes inputTree_Tree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getInputTree_Tree() {
		if (inputTree_Tree == null) {
			
			 //Create a tree that allows one selection at a time.
			inputTree_Tree = new JTree(inputTree_Tree_Top);
			inputTree_Tree.setBounds(new Rectangle(0, 0, 300, 550));
			inputTree_Tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			
			//Create the nodes.
			createNodes(inputTree_Tree_Top);
		}
		inputTree_Tree.addTreeSelectionListener(this);
		return inputTree_Tree;
	}
	
	public void valueChanged(TreeSelectionEvent e) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
        inputTree_Tree.getLastSelectedPathComponent();
		//int objID=1;
		if (node == null) return;
		
		if (node.isLeaf()) {
		
			Object nodeInfo = node.getUserObject();
			String str = nodeInfo.toString();
			String value = null,table = null,column =null,primary_key=null;
			float tempVal=0;

			if(str.equals("Length"))
			{
				value=JOptionPane.showInputDialog(InputTree.this,"Enter length", "Length", JOptionPane.QUESTION_MESSAGE);
				table="strip_beam";
				column="length";
				primary_key="object_id";
				
				
			}
			else if(str.equals("Breadth"))
			{				
				value=JOptionPane.showInputDialog(InputTree.this,"Enter breadth", "Breadth", JOptionPane.QUESTION_MESSAGE);
				table="strip_beam";
				column="breadth";
				primary_key="object_id";
			}
			else if(str.equals("Thickness"))
			{				
				value=JOptionPane.showInputDialog(InputTree.this,"Enter thickness", "Thickness", JOptionPane.QUESTION_MESSAGE);
				table="strip_beam";
				column="thickness";
				primary_key="object_id";
			}
			else if(str.equals("Pretwist"))
			{				
				value=JOptionPane.showInputDialog(InputTree.this,"Enter pretwist", "Pretwist", JOptionPane.QUESTION_MESSAGE);
				table="strip_beam";
				column="pretwist";
				primary_key="object_id";
			}
			else if(str.equals("Young's Modulus"))
			{				
				value=JOptionPane.showInputDialog(InputTree.this,"Enter Young's Modulus", "Young's Modulus", JOptionPane.QUESTION_MESSAGE);
				table="isotropic";
				column="youngs_modulus";
				primary_key="material_id";
			}
			else if(str.equals("Shear Modulus"))
			{				
				value=JOptionPane.showInputDialog(InputTree.this,"Enter Shear Modulus", "Shear Modulus", JOptionPane.QUESTION_MESSAGE);
				table="isotropic";
				column="shear_modulus";
				primary_key="material_id";
			}
			else if(str.equals("Poisson Ratio"))
			{				
				value=JOptionPane.showInputDialog(InputTree.this,"Enter Poisson Ratio", "Poisson Ratio", JOptionPane.QUESTION_MESSAGE);
				table="isotropic";
				column="poisson_ratio";
				primary_key="material_id";
			}
			
			else if(str.equals("Mesh the Object")){
				
				//System.out.println("Test");
				MainWindow.change_mesh_flag();
			}
			else{}
			
			try {
				tempVal = Float.parseFloat(value);
				doUpdate(tempVal,table,column,primary_key);
				OutputConsole.display(table,column,primary_key);
			}
			catch(NumberFormatException ee) {
				JOptionPane one = new JOptionPane();
				JOptionPane.showMessageDialog(one,"\""+value+"\""+" is not Float Value");
			}

		}
		
	}
	
	private void doUpdate(float value,String table,String column,String primary_key) {
		
		try {
			//float tempVal = Float.parseFloat(value);
			doConnect();
			query="Update "+table+" set "+column+"="+value+" where "+primary_key+"=1";
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
		}
		catch(SQLException ee)
		{
			JOptionPane one = new JOptionPane();
			JOptionPane.showMessageDialog(one,ee.getMessage());	
		}

		
	}
	private void createNodes(DefaultMutableTreeNode top) {
		
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode geometry = null;
        DefaultMutableTreeNode strip = null;
        DefaultMutableTreeNode material = null;
        DefaultMutableTreeNode isotropic = null;
        DefaultMutableTreeNode anisotropic = null;
        DefaultMutableTreeNode composite = null;
        DefaultMutableTreeNode meshing = null;
        DefaultMutableTreeNode mesh_object = null;
        DefaultMutableTreeNode mesh_import = null;
        DefaultMutableTreeNode boundary_conditions = null;
        DefaultMutableTreeNode mesh_force = null;
        DefaultMutableTreeNode electrical_force = null;
        DefaultMutableTreeNode calculate = null;

        category = new DefaultMutableTreeNode("Geometry");
        top.add(category);
        
        geometry = new DefaultMutableTreeNode("Strip-like beams");
        category.add(geometry);

        strip = new DefaultMutableTreeNode("Length");
        geometry.add(strip);

        strip = new DefaultMutableTreeNode("Breadth");
        geometry.add(strip);

        strip = new DefaultMutableTreeNode("Thickness");
        geometry.add(strip);

        strip = new DefaultMutableTreeNode("Pretwist");
        geometry.add(strip);
            
        geometry = new DefaultMutableTreeNode("Thin-walled beams");
        category.add(geometry);

    	geometry = new DefaultMutableTreeNode("Other beams");
        category.add(geometry);

    	category = new DefaultMutableTreeNode("Material");
        top.add(category);   

    	material = new DefaultMutableTreeNode("Isotropic");
        category.add(material);   

    	isotropic = new DefaultMutableTreeNode("Young's Modulus");
        material.add(isotropic); 

    	isotropic = new DefaultMutableTreeNode("Shear Modulus");
        material.add(isotropic); 

    	isotropic = new DefaultMutableTreeNode("Poisson Ratio");
        material.add(isotropic); 

        material = new DefaultMutableTreeNode("Anisotropic");
        category.add(material);

    	anisotropic = new DefaultMutableTreeNode("Laminated Composite");
        material.add(anisotropic); 

    	composite = new DefaultMutableTreeNode("Fiber stiffness matrix");
        anisotropic.add(composite); 

    	composite = new DefaultMutableTreeNode("Matrix stiffness matrix ");
        anisotropic.add(composite);   
        
        meshing = new DefaultMutableTreeNode("Meshing");
        top.add(meshing);
        
        mesh_object = new DefaultMutableTreeNode("Mesh the Object");
        meshing.add(mesh_object);
        
        mesh_import = new DefaultMutableTreeNode("Import mesh");
        meshing.add(mesh_import);
        
        boundary_conditions = new DefaultMutableTreeNode("Boundary Conditions");
        top.add(boundary_conditions);
        
        mesh_force = new DefaultMutableTreeNode("Mesh Forces boundary conditions");
        boundary_conditions.add(mesh_force);
        
        electrical_force = new DefaultMutableTreeNode("Electrical Forces boundary conditions");
        boundary_conditions.add(electrical_force);
        
        calculate = new DefaultMutableTreeNode("Calculate");
        top.add(calculate);
  
    }
	
	private static void createAndShowGUI() {
        
        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        
        frame.setSize(300, 300);
        

        //Add content to the window.
        frame.add(new InputTree());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	 
	public static void main(String args[])
	{
		    javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}

