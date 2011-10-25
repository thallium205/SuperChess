package russell.john;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node; 
import com.jme3.scene.Spatial;

public class Board 
{
    Node boardNode;
    public static AssetManager assetManager;
    Material whiteMaterial, blackMaterial, boardMaterial, emptyMaterial, selectedPieceMaterial, suggestedMovementMaterial;
    InputManager inputManager;
    Camera camera;
    BoardControls boardControls;
    
    
    
    
    public Board (Node boardNode, AssetManager assetManager, InputManager inputManager, Camera camera)
    {
        this.boardNode = boardNode;
        this.assetManager = assetManager;
        this.inputManager = inputManager;
        this.camera = camera;
        
        // Set Materials
        buildMaterials();
        
        // Draw and attach the board        
        buildBoard();
        
        // Draw and attach the pieces
        setBoard();  
        
        // Creates a click listener
        setListener();
        
    }
    
    private void buildMaterials()
    {
        // TODO - set textures
        boardMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        // boardMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        // boardMaterial.setColor("Color", ColorRGBA.BlackNoAlpha);
        whiteMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");   
        whiteMaterial.setFloat("Shininess", 5f);
        
        blackMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"); 
        blackMaterial.setTexture("DiffuseMap", assetManager.loadTexture("Textures/gray.png"));
        blackMaterial.setFloat("Shininess", 120f);

        emptyMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        emptyMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        emptyMaterial.setColor("Color", ColorRGBA.BlackNoAlpha);
        
        selectedPieceMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        selectedPieceMaterial.setColor("Color", ColorRGBA.Green);
        selectedPieceMaterial.setColor("GlowColor", ColorRGBA.Green);
       // FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
       // BloomFilter bloom= new BloomFilter(BloomFilter.GlowMode.Objects);        
       // fpp.addFilter(bloom);
       // viewPort.addProcessor(fpp);
        
        suggestedMovementMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        suggestedMovementMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        suggestedMovementMaterial.setColor("Color", ColorRGBA.BlackNoAlpha);
        
    }
    
    private void buildBoard()
    {
        Spatial boardModel = assetManager.loadModel("Models/Board.j3o");    
        // board.setMaterial(boardMaterial);        
        boardNode.attachChild(boardModel);
    }
    
    private void setBoard()
    {  
        boardControls = new BoardControls(assetManager, boardNode, emptyMaterial, whiteMaterial, blackMaterial, selectedPieceMaterial, suggestedMovementMaterial);
        boardControls.setBoard();       
    }     
    
    private void setListener()
    {
         /** Declaring the "Shoot" action and mapping to its triggers. */
        inputManager.addMapping("pick target", new MouseButtonTrigger(MouseInput.BUTTON_LEFT)); // trigger 2: left-button click
        inputManager.addListener(actionListener, "pick target");              
    }    
    
   private ActionListener actionListener = new ActionListener() 
   {      
       
    public void onAction(String name, boolean keyPressed, float tpf) 
    {
      if (name.equals("pick target") && keyPressed) 
      {
        // Reset results list.
        CollisionResults results = new CollisionResults();
        
        // Convert screen click to 3d position        
        Vector2f click2d = inputManager.getCursorPosition();
        Vector3f click3d = camera.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
        Vector3f dir = camera.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d);
        
        // Aim the ray from the clicked spot forwards.
        Ray ray = new Ray(click3d, dir);
        
        // Collect intersections between ray and all nodes in results list.
        boardNode.collideWith(ray, results);  
        
        // (Print the results so we see what is going on:)
        /*
        for (int i = 0; i < results.size(); i++) 
        {
          // (For each “hit”, we know distance, impact point, geometry.)
          float dist = results.getCollision(i).getDistance();
          Vector3f pt = results.getCollision(i).getContactPoint();
          String target = results.getCollision(i).getGeometry().getName();
          System.out.println("Selection #" + i + ": " + target + " at " + pt + ", " + dist + " WU away.");
        }
         * 
         */
        
        // Use the results -- we rotate the selected geometry.
        if (results.size() > 0) 
        {
          // The closest result is the target that the player picked:
          Geometry target = results.getClosestCollision().getGeometry();
          
          // Here comes the action:
          boardControls.select(target.getName());
        }
      } 
    }
  };
    }

