package russell.john;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;

public class SuperChess extends SimpleApplication 
{
    Quaternion rotate = new Quaternion();
    Vector3f rotateVector = new Vector3f(0,1f,1f);
    PointLight light;

    public static void main(String[] args) 
    {
        SuperChess app = new SuperChess();
        app.start();
    }
    
    private Node boardNode;

    @Override
    public void simpleInitApp() 
    {    
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);
        
        // launch the main menu
       //  launchMainMenu();
        
        // set the camera and scene
        buildCamera();  
        
        // Set lighting
        buildLighting();
        
        // build the board
        buildBoard();  
    }

    @Override
    public void simpleUpdate(float tpf) 
    {
        // rotate.fromAngleAxis(FastMath.HALF_PI * (tpf/9f), rotateVector);
        // boardNode.rotate(rotate);
    }

    @Override
    public void simpleRender(RenderManager rm) 
    {
        //TODO: add render code
    }
    
    
    private void launchMainMenu()
    {
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        /** Create a new NiftyGUI object */
        Nifty nifty = niftyDisplay.getNifty();
        /** Read your XML and initialize your custom ScreenController */
        nifty.fromXml("Interface/MainMenu.xml", "start", new MainMenuController());
        // attach the Nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);
        // disable the fly cam
        flyCam.setDragToRotate(true);
        
        // nifty.exit();
    }

    
    private void buildCamera()
    {      
        cam.setLocation(new Vector3f(0f, 40f, 15f));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);  
        flyCam.setEnabled(false);
        inputManager.setCursorVisible(true);
    }
    
    private void buildLighting()
    {
        // Add a light to make the model visible
        light = new PointLight();
        light.setColor(ColorRGBA.White.mult(1.5f));
        light.setRadius(80f);
        light.setPosition(new Vector3f(0f, 40f, 15f));
        rootNode.addLight(light);
    }
       
    
    private void buildBoard()
    {
        // Create a pivot node at (0,0,0) and attach it to the root node
        boardNode = new Node("boardNode");
        rootNode.attachChild(boardNode); // put this node in the scene
               
        // Instantiate Board object and pass in the node and asset manager
        Board board = new Board(boardNode, assetManager, inputManager, cam);  
    }    
}
