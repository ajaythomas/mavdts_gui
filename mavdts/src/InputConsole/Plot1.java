package InputConsole;

import java.applet.Applet;

import java.awt.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.ColoringAttributes;
import com.sun.j3d.utils.applet.MainFrame;
import java.io.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;

public class Plot1 extends Java3dApplet{

	private static final long serialVersionUID = 1L;
	private static int Plot1_m_kWidth = 1350;
	private static int Plot1_m_kHeight = 700;
	float Plot1_xc,Plot1_yc,Plot1_zc,Plot1_mx,Plot1_my,Plot1_mz;
	float Plot1_pretwist;
	 int Plot1_nodeno=1; 
	  //constructor
	  public Plot1() {
		  Plot1_initJava3d();
	  }
	  public Point3d Plot1_rotate(double x,double y, double z,int flag)
	  {double theta=((Math.abs(x)/(Plot1_xc))*Plot1_pretwist)*3.1416/90;
	  double yc1=y*Math.cos(theta)-(z*Math.sin(theta));
	  double zc1=(y*Math.sin(theta))+(z*Math.cos(theta));
	  Point3d pt=new Point3d(x,yc1,zc1);
	 
	  try{ 
	  if(flag==1)
	   {BufferedWriter out = new BufferedWriter(new FileWriter("deformed_coordinates.txt",true));
	    out.write((Plot1_nodeno++)+"("+x+","+yc1+","+zc1+")\t");
	    out.close();
	   }
	  }
	  catch(Exception e)
	  {}
	  
	  return pt;
	  }
	  //for left wing
	  public Point3d Plot1_rotate2(double x,double y, double z,int flag)
	  {double theta=((Math.abs(Plot1_xc-x)/(Plot1_xc))*Plot1_pretwist)*3.1416/90;
	  double yc1=y*Math.cos(theta)-(z*Math.sin(theta));
	  double zc1=(y*Math.sin(theta))+(z*Math.cos(theta));
	  Point3d pt=new Point3d(x,yc1,zc1);
	  return pt;
	  }

	  protected Background Plot1_createBackground() {
			 Background back = new Background(new Color3f(0.7f, 0.7f, 0.7f));
			 m_ApplicationBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
				        100.0);
			 back.setApplicationBounds(m_ApplicationBounds);
		 	 return back;
		  }

	  protected double Plot1_getScale() {
	    return 0.1;
	  }
	  
	  //read input from geomerty.txt
	protected void Plot1_readdata()
	{String arr[]=new String[10];
	int i=0;
	try {
	    BufferedReader in = new BufferedReader(new FileReader("geometry.txt"));
	    String str;
	    while ((str = in.readLine()) != null) {
	        arr[i++]=str;
	    }
	    in.close();
	} catch (IOException e) {
	}
	Plot1_xc=Float.parseFloat(arr[0]);
	Plot1_yc=Float.parseFloat(arr[1]);
	Plot1_zc=Float.parseFloat(arr[2]);
	Plot1_pretwist=Float.parseFloat(arr[3]);
	Plot1_mx=100;
    Plot1_my=Plot1_yc/Plot1_xc*100;
    Plot1_mz=Plot1_zc/Plot1_xc*100;
	}
	
	protected BranchGroup Plot1_createSceneBranchGroup() {
	    BranchGroup objRoot = super.Plot1_createSceneBranchGroup();
	    Plot1_nodes();
	    if(Plot1_xc>75)
	    { 	
	    Plot1_yc=(Plot1_yc)/(Plot1_xc/75);
	    Plot1_xc=75;
	    }
	    if(Plot1_zc<4)
	    Plot1_zc=4;
	    
	    TransformGroup zoomTg = new TransformGroup();
	    OrderedGroup og=new OrderedGroup();
	    zoomTg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
	    zoomTg.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
	    TransformGroup tg=new TransformGroup();
	    Transform3D t3g=new Transform3D();
	    t3g.setTranslation(new Vector3f(-Plot1_xc,0,0));
	    tg.setTransform(t3g);
	    Appearance app = new Appearance();
	    ColoringAttributes ca = new ColoringAttributes(new Color3f(0.0f,0.0f,0.0f),ColoringAttributes.SHADE_FLAT);
	   LineAttributes la=new LineAttributes();
	   la.setLineWidth(5);
	   la.setLineAntialiasingEnable(true);
	    app.setLineAttributes(la);
	    app.setColoringAttributes(ca);
	    //outline (wings)
	    Point3d[] cuboid=new Point3d[24];
	    cuboid[0]=Plot1_rotate(Plot1_xc,0,Plot1_zc,0);
	    cuboid[1]=Plot1_rotate(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    
	    cuboid[2]=Plot1_rotate(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid[3]=Plot1_rotate(0,Plot1_yc,Plot1_zc,0);
	    
	    cuboid[4]=Plot1_rotate(0,Plot1_yc,Plot1_zc,0);
	    cuboid[5]=Plot1_rotate(0,0,Plot1_zc,0);
	    
	    cuboid[6]=Plot1_rotate(0,0,Plot1_zc,0);
	    cuboid[7]=Plot1_rotate(Plot1_xc,0,Plot1_zc,0);
	    
	    cuboid[8]=Plot1_rotate(Plot1_xc,0,0,0);
	    cuboid[9]=Plot1_rotate(Plot1_xc,Plot1_yc,0,0);
	    
	    cuboid[10]=Plot1_rotate(Plot1_xc,Plot1_yc,0,0);
	    cuboid[11]=Plot1_rotate(0,Plot1_yc,0,0);
	    
	    cuboid[12]=Plot1_rotate(0,Plot1_yc,0,0);
	    cuboid[13]=Plot1_rotate(0,0,0,0);
	    
	    cuboid[14]=Plot1_rotate(0,0,0,0);
	    cuboid[15]=Plot1_rotate(Plot1_xc,0,0,0);
	    
	    cuboid[16]=Plot1_rotate(Plot1_xc,0,Plot1_zc,0);
	    cuboid[17]=Plot1_rotate(Plot1_xc,0,0,0);
	  
	    cuboid[18]=Plot1_rotate(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid[19]=Plot1_rotate(Plot1_xc,Plot1_yc,0,0);
	    
	    cuboid[20]=Plot1_rotate(0,Plot1_yc,Plot1_zc,0);
	    cuboid[21]=Plot1_rotate(0,Plot1_yc,0,0);

	    cuboid[22]=Plot1_rotate(0,0,Plot1_zc,0);
	    cuboid[23]=Plot1_rotate(0,0,0,0);
	    
	    LineArray cub = new LineArray(24, LineArray.COORDINATES);
	    cub.setCoordinates(0, cuboid);
	    Shape3D plShape1 = new Shape3D(cub, app);
	    
	    
	    Point3d[] cuboid2=new Point3d[24];
	    cuboid2[0]=Plot1_rotate2(Plot1_xc,0,Plot1_zc,0);
	    cuboid2[1]=Plot1_rotate2(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    
	    cuboid2[2]=Plot1_rotate2(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid2[3]=Plot1_rotate2(0,Plot1_yc,Plot1_zc,0);
	    
	    cuboid2[4]=Plot1_rotate2(0,Plot1_yc,Plot1_zc,0);
	    cuboid2[5]=Plot1_rotate2(0,0,Plot1_zc,0);
	    
	    cuboid2[6]=Plot1_rotate2(0,0,Plot1_zc,0);
	    cuboid2[7]=Plot1_rotate2(Plot1_xc,0,Plot1_zc,0);
	    
	    cuboid2[8]=Plot1_rotate2(Plot1_xc,0,0,0);
	    cuboid2[9]=Plot1_rotate2(Plot1_xc,Plot1_yc,0,0);
	    
	    cuboid2[10]=Plot1_rotate2(Plot1_xc,Plot1_yc,0,0);
	    cuboid2[11]=Plot1_rotate2(0,Plot1_yc,0,0);
	    
	    cuboid2[12]=Plot1_rotate2(0,Plot1_yc,0,0);
	    cuboid2[13]=Plot1_rotate2(0,0,0,0);
	    
	    cuboid2[14]=Plot1_rotate2(0,0,0,0);
	    cuboid2[15]=Plot1_rotate2(Plot1_xc,0,0,0);
	    
	    cuboid2[16]=Plot1_rotate2(Plot1_xc,0,Plot1_zc,0);
	    cuboid2[17]=Plot1_rotate2(Plot1_xc,0,0,0);
	  
	    cuboid2[18]=Plot1_rotate2(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid2[19]=Plot1_rotate2(Plot1_xc,Plot1_yc,0,0);
	    
	    cuboid2[20]=Plot1_rotate2(0,Plot1_yc,Plot1_zc,0);
	    cuboid2[21]=Plot1_rotate2(0,Plot1_yc,0,0);

	    cuboid2[22]=Plot1_rotate2(0,0,Plot1_zc,0);
	    cuboid2[23]=Plot1_rotate2(0,0,0,0);
	    
	    LineArray cub2 = new LineArray(24, LineArray.COORDINATES);
	    cub2.setCoordinates(0, cuboid2);
	    Shape3D plShape2 = new Shape3D(cub2, app);
	    //zoomTg.addChild(plShape1);
	    tg.addChild(plShape2);
	    Plot1_xc-=0.3;
	    Plot1_yc-=0.3;
	    Plot1_zc-=0.3;

	    Appearance app2 = new Appearance();
	    ColoringAttributes ca2 = new ColoringAttributes(new Color3f(1.0f,0.0f,0.0f),ColoringAttributes.SHADE_FLAT);
	    app2.setColoringAttributes(ca2);
	    //Plot1_zc+=10;
	    Point3d[] cuboid3=new Point3d[16];
	    cuboid3[0]=Plot1_rotate(Plot1_xc,0,Plot1_zc,0);
	    cuboid3[1]=Plot1_rotate(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid3[2]=Plot1_rotate(0,Plot1_yc,Plot1_zc,0);
	    cuboid3[3]=Plot1_rotate(0,0,Plot1_zc,0);
	    
	    cuboid3[4]=Plot1_rotate(Plot1_xc,0,0,0);
	    cuboid3[5]=Plot1_rotate(Plot1_xc,Plot1_yc,0,0);
	    cuboid3[6]=Plot1_rotate(0,Plot1_yc,0,0);
	    cuboid3[7]=Plot1_rotate(0,0,0,0);
	   
	    cuboid3[8]=Plot1_rotate(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid3[9]=Plot1_rotate(Plot1_xc,Plot1_yc,0,0);
	    cuboid3[10]=Plot1_rotate(0,Plot1_yc,0,0);
	    cuboid3[11]=Plot1_rotate(0,Plot1_yc,Plot1_zc,0);
	   
	    cuboid3[12]=Plot1_rotate(Plot1_xc,0,Plot1_zc,0);
	    cuboid3[13]=Plot1_rotate(Plot1_xc,0,0,0);
	    cuboid3[14]=Plot1_rotate(Plot1_xc,Plot1_yc,0,0);
	    cuboid3[15]=Plot1_rotate(Plot1_xc,Plot1_yc,Plot1_zc,0);
	  
	    
	    QuadArray cub3 = new QuadArray(16, QuadArray.COORDINATES);
	    cub3.setCoordinates(0, cuboid3);
	    Shape3D plShape3 = new Shape3D(cub3, app2);
	    og.addChild(plShape3);
	    og.addChild(plShape1);
	    Point3d[] cuboid4=new Point3d[16];
	    cuboid4[0]=Plot1_rotate2(Plot1_xc,0,Plot1_zc,0);
	    cuboid4[1]=Plot1_rotate2(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid4[2]=Plot1_rotate2(0,Plot1_yc,Plot1_zc,0);
	    cuboid4[3]=Plot1_rotate2(0,0,Plot1_zc,0);
	    
	    cuboid4[4]=Plot1_rotate2(Plot1_xc,0,0,0);
	    cuboid4[5]=Plot1_rotate2(Plot1_xc,Plot1_yc,0,0);
	    cuboid4[6]=Plot1_rotate2(0,Plot1_yc,0,0);
	    cuboid4[7]=Plot1_rotate2(0,0,0,0);
	   
	    cuboid4[8]=Plot1_rotate2(Plot1_xc,Plot1_yc,Plot1_zc,0);
	    cuboid4[9]=Plot1_rotate2(Plot1_xc,Plot1_yc,0,0);
	    cuboid4[10]=Plot1_rotate2(0,Plot1_yc,0,0);
	    cuboid4[11]=Plot1_rotate2(0,Plot1_yc,Plot1_zc,0);
	   
	    cuboid4[12]=Plot1_rotate2(Plot1_xc,0,Plot1_zc,0);
	    cuboid4[13]=Plot1_rotate2(Plot1_xc,0,0,0);
	    cuboid4[14]=Plot1_rotate2(Plot1_xc,Plot1_yc,0,0);
	    cuboid4[15]=Plot1_rotate2(Plot1_xc,Plot1_yc,Plot1_zc,0);
	  
	    
	    QuadArray cub4 = new QuadArray(16, QuadArray.COORDINATES);
	    cub4.setCoordinates(0, cuboid4);
	    Shape3D plShape4 = new Shape3D(cub4, app2);
	    tg.addChild(plShape4);
	    objRoot.addChild(tg);
	//body
	    TransformGroup tg2=new TransformGroup();
	    Transform3D t3d=new Transform3D();
	    t3d.setTranslation(new Vector3f(-20,-20,-20));
	    tg2.setTransform(t3d);
	    objRoot.addChild(tg2);
	    //outline

	    Appearance appb2 = new Appearance();
	    ColoringAttributes cab2 = new ColoringAttributes(new Color3f(0.0f,0.0f,0.0f),ColoringAttributes.SHADE_FLAT);
	   LineAttributes la2=new LineAttributes();
	   la2.setLineWidth(3);
	   la2.setLineAntialiasingEnable(true);
	   TransparencyAttributes TA2 = new TransparencyAttributes(TransparencyAttributes.SCREEN_DOOR, 0.5f);
	   appb2.setTransparencyAttributes(TA2);
	     appb2.setLineAttributes(la2);
	    appb2.setColoringAttributes(cab2);

	  Point3d[] bdy=new Point3d[24];
	    
	    bdy[0]=new Point3d(40,40,40);
	    bdy[1]=new Point3d(40,0,40);
	    
	    bdy[2]=new Point3d(40,0,40);
	    bdy[3]=new Point3d(0,0,40);

	    bdy[4]=new Point3d(0,0,40);
	    bdy[5]=new Point3d(0,40,40);
	        
	    bdy[6]=new Point3d(0,40,40);
	    bdy[7]=new Point3d(40,40,40);
	    
	    
	    bdy[8]=new Point3d(40,40,-40);
	    bdy[9]=new Point3d(40,0,-40);
	    
	    bdy[10]=new Point3d(40,0,-40);
	    bdy[11]=new Point3d(0,0,-40);
	    
	    bdy[12]=new Point3d(0,0,-40);
	    bdy[13]=new Point3d(0,40,-40);
	    
	    
	    bdy[14]=new Point3d(0,40,-40);
	    bdy[15]=new Point3d(40,40,-40);
	    
	    bdy[16]=new Point3d(40,40,40);
	    bdy[17]=new Point3d(40,40,-40);
	    
	    bdy[18]=new Point3d(40,0,40);
	    bdy[19]=new Point3d(40,0,-40);
	    
	    bdy[20]=new Point3d(0,0,40);
	    bdy[21]=new Point3d(0,0,-40);
	    
	    bdy[22]=new Point3d(0,40,40);
	    bdy[23]=new Point3d(0,40,-40);
	    
	    LineArray mb2 = new LineArray(24, LineArray.COORDINATES);
	    mb2.setCoordinates(0,bdy);
	    Shape3D shape2 = new Shape3D(mb2, appb2);
	    tg2.addChild(shape2);
	KeyNavigatorBehavior key = new KeyNavigatorBehavior( zoomTg );
	key.setSchedulingBounds(Plot1_createApplicationBounds() );
	key.setEnable(true);
	objRoot.addChild(key);
objRoot.addChild(og);
	objRoot.addChild(zoomTg);
	return objRoot;
	  
	  }
	
	public void Plot1_nodes()
	  {Plot1_readdata();
	  //original nodes
	  	
	  float xi=Plot1_xc/Plot1_mx;
	  float yi=Plot1_yc/Plot1_my;
	  float zi=Plot1_zc/Plot1_mz;
	  int nodeno=1;
		try {

	    //xy plane
	    for(float i=0;i<Plot1_xc;i+=xi)
	    	for(float j=0;j<Plot1_yc;j+=yi)
	    	{
	  
	    		    BufferedWriter out = new BufferedWriter(new FileWriter("undeformed_coordinates.txt",true));
	    		    out.write((nodeno++)+"("+i+","+j+","+"0)\t"+(nodeno++)+"("+i+","+j+","+Plot1_zc+")\t");
	    		    out.close();
	    		    Point3d dummy=Plot1_rotate(i,j,0,1);
	    		    dummy=Plot1_rotate(i,j,Plot1_zc,1);
	    		    
	    		} 
	    //yz plane
	    for(float i=0;i<Plot1_yc;i+=yi)
	    for(float j=0;j<Plot1_zc;j+=zi)
	    {BufferedWriter out = new BufferedWriter(new FileWriter("undeformed_coordinates.txt",true));
	    out.write((nodeno++)+"(0"+","+i+","+j+")\t"+(nodeno++)+"("+Plot1_xc+","+i+","+j+")\t");
	    out.close();
	    Point3d dummy=Plot1_rotate(0,i,j,1);
	    dummy=Plot1_rotate(Plot1_xc,i,j,1);
	    
	} 
	  //zx plane
	    for(float i=0;i<Plot1_zc;i+=zi)
	    for(float j=0;j<Plot1_xc;j+=xi)
	    {BufferedWriter out = new BufferedWriter(new FileWriter("undeformed_coordinates.txt",true));
	    out.write((nodeno++)+"("+j+","+0+","+i+")\t"+(nodeno++)+"("+j+","+Plot1_yc+","+i+")\t");
	    out.close();
	    Point3d dummy=Plot1_rotate(j,0,i,1);
	    dummy=Plot1_rotate(i,Plot1_yc,j,1);
	    
	} 
	       
	    
		}
	    catch (IOException e) {
	    		}
	    		}
	    
	  
	  
	  public static void main(String[] args) {
	    Plot1 Test = new Plot1();
	   new MainFrame(Test, Plot1_m_kWidth, Plot1_m_kHeight);
	  }
	}

	abstract class Java3dApplet extends Applet {
	  public static int Plot1_m_kWidth = 1350;
	  public static int Plot1_m_kHeight = 700;
	  protected String[] m_szCommandLineArray = null;
	  protected VirtualUniverse m_Universe = null;
	  protected BranchGroup m_SceneBranchGroup = null;
	  protected Bounds m_ApplicationBounds = null;
	  protected Bounds Plot1_getApplicationBounds() {
	    if (m_ApplicationBounds == null)
	      m_ApplicationBounds = Plot1_createApplicationBounds();

	    return m_ApplicationBounds;
	  }

	  protected Bounds Plot1_createApplicationBounds() {
	    m_ApplicationBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
	        100.0);
	    return m_ApplicationBounds;
	  }

	  protected Background Plot1_createBackground() {
	    Background back = new Background(new Color3f(0.9f, 0.9f, 0.9f));
	    back.setApplicationBounds(Plot1_createApplicationBounds());
	    return back;
	  }

	  public void Plot1_initJava3d() {
	    m_Universe = Plot1_createVirtualUniverse();
	    Locale locale = Plot1_createLocale(m_Universe);
	    BranchGroup sceneBranchGroup = Plot1_createSceneBranchGroup();
	    ViewPlatform vp = Plot1_createViewPlatform();
	    BranchGroup viewBranchGroup = Plot1_createViewBranchGroup(
	        getViewTransformGroupArray(), vp);
	    Plot1_createView(vp);
	    Background background = Plot1_createBackground();
	    if (background != null)
	    	sceneBranchGroup.addChild(background);
	    locale.addBranchGraph(sceneBranchGroup);
	    Plot1_addViewBranchGroup(locale, viewBranchGroup);

	      }
	  protected double Plot1_getScale() {
	    return 1.0;
	  }
	  public TransformGroup[] getViewTransformGroupArray() {
	    TransformGroup[] tgArray = new TransformGroup[1];
	    tgArray[0] = new TransformGroup();
	    // move the camera BACK a little
	    // invert the matrix as
	    // we are moving the viewer
	    Transform3D t3d = new Transform3D();
	    t3d.setScale(Plot1_getScale());
	    t3d.setTranslation(new Vector3d(0.0, 0.0, -20.0));
	    t3d.invert();
	    tgArray[0].setTransform(t3d);
	    return tgArray;
	  }

	  protected void Plot1_addViewBranchGroup(Locale locale, BranchGroup bg) {
	    locale.addBranchGraph(bg);
	  }

	  protected Locale Plot1_createLocale(VirtualUniverse u) {
	    return new Locale(u);
	  }

	  protected BranchGroup Plot1_createSceneBranchGroup() {
	    m_SceneBranchGroup = new BranchGroup();
	    return m_SceneBranchGroup;
	  }

	  protected View Plot1_createView(ViewPlatform vp) {
	    View view = new View();

	    PhysicalBody pb = Plot1_createPhysicalBody();
	    PhysicalEnvironment pe = Plot1_createPhysicalEnvironment();

	    view.setPhysicalEnvironment(pe);
	    view.setPhysicalBody(pb);

	    if (vp != null)
	      view.attachViewPlatform(vp);

	    view.setBackClipDistance(Plot1_getBackClipDistance());
	    view.setFrontClipDistance(Plot1_getFrontClipDistance());

	    Canvas3D c3d = Plot1_createCanvas3D();
	    view.addCanvas3D(c3d);
	    Plot1_addCanvas3D(c3d);

	    return view;
	  }
	  protected PhysicalBody Plot1_createPhysicalBody() {
	    return new PhysicalBody();
	  }

	  protected PhysicalEnvironment Plot1_createPhysicalEnvironment() {
	    return new PhysicalEnvironment();
	  }

	  protected float Plot1_getViewPlatformActivationRadius() {
	    return 100;
	  }

	  protected ViewPlatform Plot1_createViewPlatform() {
	    ViewPlatform vp = new ViewPlatform();
	    vp.setViewAttachPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
	    vp.setActivationRadius(Plot1_getViewPlatformActivationRadius());

	    return vp;
	  }

	  protected Canvas3D Plot1_createCanvas3D() {
	    GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
	    gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
	    GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
	        .getScreenDevices();

	    Canvas3D c3d = new Canvas3D(gd[0].getBestConfiguration(gc3D));
	    c3d.setSize(Plot1_getCanvas3dWidth(c3d), Plot1_getCanvas3dHeight(c3d));

	    return c3d;
	  }

	  protected int Plot1_getCanvas3dWidth(Canvas3D c3d) {
	    return Plot1_m_kWidth;
	  }

	  protected int Plot1_getCanvas3dHeight(Canvas3D c3d) {
	    return Plot1_m_kHeight;
	  }

	  protected double Plot1_getBackClipDistance() {
	    return 100.0;
	  }

	  protected double Plot1_getFrontClipDistance() {
	    return 1.0;
	  }
	  

	  protected BranchGroup Plot1_createViewBranchGroup(TransformGroup[] tgArray,
	      ViewPlatform vp) {
	    BranchGroup vpBranchGroup = new BranchGroup();

	    if (tgArray != null && tgArray.length > 0) {
	      Group parentGroup = vpBranchGroup;
	      TransformGroup curTg = null;

	      for (int n = 0; n < tgArray.length; n++) {
	        curTg = tgArray[n];
	        parentGroup.addChild(curTg);
	        parentGroup = curTg;
	      }

	      tgArray[tgArray.length - 1].addChild(vp);
	    } else
	      vpBranchGroup.addChild(vp);

	    return vpBranchGroup;
	  }

	  protected void Plot1_addCanvas3D(Canvas3D c3d) {
	    setLayout(new BorderLayout());
	    add(c3d, BorderLayout.CENTER);
	    doLayout();
	  }

	  protected VirtualUniverse Plot1_createVirtualUniverse() {
	    return new VirtualUniverse();
	  }
	}
	



