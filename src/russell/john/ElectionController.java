package russell.john;

import com.jme3.asset.AssetManager;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.event.TouchEvent;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.xml.xpp3.Attributes;
import java.util.Properties;

public class ElectionController extends SuperChess implements ScreenController, Controller, TouchListener
{
    BoardType board;
    AssetManager assetManager;
    Node boardNode;
    Nifty nifty;
    Piece startPiece;
    Piece endPiece;
    
    final private String ESCAPE_EVENT = "TouchEscape";
    final private String MENU_EVENT = "TouchMenu";
    
    public ElectionController()
    {
        
    }
    
    public ElectionController(BoardType board, AssetManager assetManager, Node boardNode, Nifty nifty, Piece endPiece)
    { /** constructor */ 
        this.board = board;
        this.assetManager = assetManager;
        this.boardNode = boardNode;
        this.nifty = nifty;
        this.endPiece = endPiece;
    }    
    
   @Override
    public void bind(Nifty nifty, Screen screen) 
   {
       
    }
   
   @Override
    public void bind(Nifty nifty, Screen screen, Element elmnt, Properties prprts, Attributes atrbts) {
   
       
    }
    
    // methods for Controller
    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    public void onStartScreen() 
    {
         System.out.println("THE SCREEN HAS BEEN STARTED");
    }
    
    public void onEndScreen() 
    {
        
    }   
    
    @Override
    public void init(Properties prprts, Attributes atrbts) {
        
    }
    
    public void onFocus(boolean getFocus) 
    {
    }   
    
    // user defined functions
    public void getQueen()
    {
        // Create the end piece
        startPiece = new Queen(assetManager, endPiece.isWhite, endPiece.getRow(), endPiece.getColumn());
        if (startPiece.isWhite())
            startPiece.getSpatial().setMaterial(BoardControls.whiteMaterial);
        else
            startPiece.getSpatial().setMaterial(BoardControls.blackMaterial);
        startPiece.setLastMoved(true);

        int endRow = endPiece.getRow();
        int endCol = endPiece.getColumn();   
        Piece piece;

        board.getBoard().get(endRow).set(endCol, startPiece);

        boardNode.detachChild(endPiece.getSpatial());       

        boardNode.attachChild(board.getBoard().get(endRow).get(endCol).getSpatial());
        startPiece.getSpatial().setLocalTranslation(BoardConstants.vectors.get(endRow).get(endCol)); 
        
       //  super.nifty.removeScreen("electionMenu");
        // super.nifty.exit();
         
      //   nifty.exit();
        nifty.removeScreen("electionMenu");
    }
    
    public void getBishop()
    {
                // Create the end piece
        startPiece = new Bishop(assetManager, endPiece.isWhite, endPiece.getRow(), endPiece.getColumn());
        if (startPiece.isWhite())
            startPiece.getSpatial().setMaterial(BoardControls.whiteMaterial);
        else
            startPiece.getSpatial().setMaterial(BoardControls.blackMaterial);
        startPiece.setLastMoved(true);

        int endRow = endPiece.getRow();
        int endCol = endPiece.getColumn();   
        Piece piece;

        board.getBoard().get(endRow).set(endCol, startPiece);

        boardNode.detachChild(endPiece.getSpatial());       

        boardNode.attachChild(board.getBoard().get(endRow).get(endCol).getSpatial());
        startPiece.getSpatial().setLocalTranslation(BoardConstants.vectors.get(endRow).get(endCol)); 
        
       //  super.nifty.removeScreen("electionMenu");
        // super.nifty.exit();
         
      //   nifty.exit();
        nifty.removeScreen("electionMenu");
        
    }
    
    public void getKnight()
    {
                // Create the end piece
        startPiece = new Knight(assetManager, endPiece.isWhite, endPiece.getRow(), endPiece.getColumn());
        if (startPiece.isWhite())
            startPiece.getSpatial().setMaterial(BoardControls.whiteMaterial);
        else
            startPiece.getSpatial().setMaterial(BoardControls.blackMaterial);
        startPiece.setLastMoved(true);

        int endRow = endPiece.getRow();
        int endCol = endPiece.getColumn();   
        Piece piece;

        board.getBoard().get(endRow).set(endCol, startPiece);

        boardNode.detachChild(endPiece.getSpatial());       

        boardNode.attachChild(board.getBoard().get(endRow).get(endCol).getSpatial());
        startPiece.getSpatial().setLocalTranslation(BoardConstants.vectors.get(endRow).get(endCol)); 
        
       //  super.nifty.removeScreen("electionMenu");
        // super.nifty.exit();
         
      //   nifty.exit();
        nifty.removeScreen("electionMenu");
        
    }
    
    public void getRook()
    {
                // Create the end piece
        startPiece = new Rook(assetManager, endPiece.isWhite, endPiece.getRow(), endPiece.getColumn());
        if (startPiece.isWhite())
            startPiece.getSpatial().setMaterial(BoardControls.whiteMaterial);
        else
            startPiece.getSpatial().setMaterial(BoardControls.blackMaterial);
        startPiece.setLastMoved(true);

        int endRow = endPiece.getRow();
        int endCol = endPiece.getColumn();   
        Piece piece;

        board.getBoard().get(endRow).set(endCol, startPiece);

        boardNode.detachChild(endPiece.getSpatial());       

        boardNode.attachChild(board.getBoard().get(endRow).get(endCol).getSpatial());
        startPiece.getSpatial().setLocalTranslation(BoardConstants.vectors.get(endRow).get(endCol)); 
        
       //  super.nifty.removeScreen("electionMenu");
        // super.nifty.exit();
         
      //   nifty.exit();
        nifty.removeScreen("electionMenu");
        
    }
    
     public void onTouch(String name, TouchEvent event, float tpf) {
        if (name.equals(ESCAPE_EVENT)) {
            switch (event.getType()) {
                case KEY_UP:
                    event.setConsumed();
                    break;
                default:
                    break;
            }
        } else if (name.equals(MENU_EVENT)) {
            switch (event.getType()) {
                case KEY_UP:
                    nifty.removeScreen("electionMenu");
                    break;
                default:
                    break;
            }
        }
    }
    
   
        
     
}