package russell.john;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.ArrayList;

public class BoardControls 
{
    
    AssetManager assetManager;
    Node boardNode;
    Material whiteMaterial, blackMaterial, boardMaterial, emptyMaterial, selectedPieceMaterial, suggestedMovementMaterial;
    BoardType board;
    Boolean selected = false;
    
    
    
    /**
     * Initializes the board by setting all pieces to blank
     */
    public BoardControls(AssetManager assetManager, Node boardNode, Material emptyMaterial, Material whiteMaterial, Material blackMaterial, Material selectedPieceMaterial, Material suggestedMovementMaterial)
    {
        this.assetManager = assetManager;
        this.boardNode = boardNode;
        this.emptyMaterial = emptyMaterial;
        this.whiteMaterial = whiteMaterial;
        this.blackMaterial = blackMaterial;
        this.selectedPieceMaterial = selectedPieceMaterial;
        this.suggestedMovementMaterial = suggestedMovementMaterial;
        board = new BoardType();
    }
    
    public void select(String name)
    {
       // if (!selected)
      //  {
            selected = true;
           Piece piece = board.getPiece(name);       
          piece.getSpatial().setMaterial(selectedPieceMaterial);
           getLegalMoves(piece);
      //  }
     //   else
     //   {
     //       selected = false;
    //        Piece piece = board.getPiece(name);       
    //        piece.getSpatial().setMaterial(emptyMaterial);
            
    //    }
            
    }
    
    private void getLegalMoves(Piece piece)
    {        
        ArrayList<String> potentialMoves = new ArrayList<String>();
        int row, col;
        Piece p;
        
        if (piece.getPieceType().contains("Pawn"))
        {         
            System.out.println("Pawn can move:\n");
            Pawn pawn = (Pawn) piece;
            potentialMoves = pawn.getPotentialMoves(board);
            for (int i = 0; i < potentialMoves.size(); i++)
            {
                row = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(potentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }
                          
        }
        
        else if (piece.getPieceType().contains("Rook"))
        {
            System.out.println("Rook can move:\n");
            Rook rook = (Rook) piece;
            potentialMoves = rook.getPotentialMoves(board);
            for (int i = 0; i < potentialMoves.size(); i++)
            {
                row = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(potentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }
            
        }
        
        else if (piece.getPieceType().contains("Knight"))
        {
            System.out.println("Knight can move:\n");
            Knight knight = (Knight) piece;
            potentialMoves = knight.getPotentialMoves(board);
            for (int i = 0; i < potentialMoves.size(); i++)
            {
                row = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(potentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            } 
            
        }
        
        else if (piece.getPieceType().contains("Bishop"))
        {
            System.out.println("Bishop can move:\n");
            Bishop bishop = (Bishop) piece;
            potentialMoves = bishop.getPotentialMoves(board);
            for (int i = 0; i < potentialMoves.size(); i++)
            {
                row = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(potentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }           
        }
        
        else if (piece.getPieceType().contains("Queen"))
        {
            System.out.println("Queen can move:\n");
            Queen queen = (Queen) piece;
            potentialMoves = queen.getPotentialMoves(board);
            for (int i = 0; i < potentialMoves.size(); i++)
            {
                row = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(potentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }
            
        }
        
        else
        {
            // probably clikced on an empty square.  this function should never have to deal with the "legal" "moves" of empty squares.  ERROR
        }
            
            
        
    }
    
    private void deselect(String name)
    {
        
    }
    
    private void move(Piece piece)
    {
        // check for if a pawn is moved to the end of the board
        
    }
    
    public void setBoard()
    {
        BoardConstants.CreateBoard();
        ArrayList<Piece> temp;        
        // Set the entire board with empty pieces
        for (int i = 0; i < 8; i++)
        {
            temp = new ArrayList<Piece>();
            for (int j = 0; j < 8; j++)
            {                
                temp.add(new Empty(assetManager, true, i, j));
                temp.get(j).getSpatial().setMaterial(emptyMaterial);
                temp.get(j).getSpatial().setLocalTranslation(BoardConstants.vectors.get(i).get(j));
                boardNode.attachChild(temp.get(j).getSpatial());
            }
            board.getBoard().add(temp);
        }
        
         // set white pawns
        /*
        for (int i = 0; i < 8; i++)
        {
            board.getBoard().get(6).set(i, new Pawn(assetManager, true, 6, i));
            if (board.getBoard().get(6).get(i).getSpatial() != null)
            {               
                board.getBoard().get(6).get(i).getSpatial().setLocalTranslation(BoardConstants.vectors.get(6).get(i));
                board.getBoard().get(6).get(i).getSpatial().setMaterial(whiteMaterial);
                boardNode.detachChildNamed("Empty,true," + "6," + i);
                boardNode.attachChild(board.getBoard().get(6).get(i).getSpatial());
            }
        }
         * *
         */
        
        // set black pawns
        for (int i = 0; i < 8; i++)
        {
            board.getBoard().get(1).set(i, new Pawn(assetManager, false, 1, i));
            if (board.getBoard().get(1).get(i).getSpatial() != null)
            {
               board.getBoard().get(1).get(i).getSpatial().setLocalTranslation(BoardConstants.vectors.get(1).get(i));
               board.getBoard().get(1).get(i).getSpatial().setMaterial(blackMaterial);
               boardNode.detachChildNamed("Empty,true," + "1," + i);
               boardNode.attachChild(board.getBoard().get(1).get(i).getSpatial());
            }
        }
        
        
        // set white roocks    
        board.getBoard().get(7).set(0, new Rook(assetManager, true, 7, 0));
        board.getBoard().get(7).get(0).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(0));
        board.getBoard().get(7).get(0).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,0");
        boardNode.attachChild(board.getBoard().get(7).get(0).getSpatial());
        
        board.getBoard().get(7).set(7, new Rook(assetManager, true, 7, 7));
        board.getBoard().get(7).get(7).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(7));
        board.getBoard().get(7).get(7).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,7");
        boardNode.attachChild(board.getBoard().get(7).get(7).getSpatial());
        
        // set black roocks
        board.getBoard().get(0).set(0, new Rook(assetManager, false, 0, 0));
        board.getBoard().get(0).get(0).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(0));
        board.getBoard().get(0).get(0).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,0");
        boardNode.attachChild(board.getBoard().get(0).get(0).getSpatial());
        
        board.getBoard().get(0).set(7, new Rook(assetManager, false, 0, 7));
        board.getBoard().get(0).get(7).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(7));
        board.getBoard().get(0).get(7).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,7");
        boardNode.attachChild(board.getBoard().get(0).get(7).getSpatial());
        
        
        // set white knights        
        board.getBoard().get(7).set(1, new Knight(assetManager, true, 7, 1));
        board.getBoard().get(7).get(1).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(1));
        board.getBoard().get(7).get(1).getSpatial().setMaterial(whiteMaterial);        
        boardNode.detachChildNamed("Empty,true,7,1");
        boardNode.attachChild(board.getBoard().get(7).get(1).getSpatial());
        
        board.getBoard().get(7).set(6, new Knight(assetManager, true, 7, 6));
        board.getBoard().get(7).get(6).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(6));
        board.getBoard().get(7).get(6).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,6");
        boardNode.attachChild(board.getBoard().get(7).get(6).getSpatial());
        
        // set black knights
        Quaternion YAW180 = new Quaternion().fromAngleAxis(FastMath.PI, new Vector3f(0,1,0));
        board.getBoard().get(0).set(1, new Knight(assetManager, false, 0, 1));
        board.getBoard().get(0).get(1).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(1));
        board.getBoard().get(0).get(1).getSpatial().setLocalRotation(YAW180);
        board.getBoard().get(0).get(1).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,1");
        boardNode.attachChild(board.getBoard().get(0).get(1).getSpatial());
        
        board.getBoard().get(0).set(6, new Knight(assetManager, false, 0, 6));
        board.getBoard().get(0).get(6).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(6));
        board.getBoard().get(0).get(6).getSpatial().setLocalRotation(YAW180);
        board.getBoard().get(0).get(6).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,6");
        boardNode.attachChild(board.getBoard().get(0).get(6).getSpatial());       
        
        // set white bishops
        board.getBoard().get(7).set(2, new Bishop(assetManager, true, 7, 2));
        board.getBoard().get(7).get(2).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(2));
        board.getBoard().get(7).get(2).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,2");
        boardNode.attachChild(board.getBoard().get(7).get(2).getSpatial());
        
        board.getBoard().get(7).set(5, new Bishop(assetManager, true, 7, 5));
        board.getBoard().get(7).get(5).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(5));
        board.getBoard().get(7).get(5).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,5");
        boardNode.attachChild(board.getBoard().get(7).get(5).getSpatial());
        
        // set black bishops
        board.getBoard().get(0).set(2, new Bishop(assetManager, false, 0, 2));
        board.getBoard().get(0).get(2).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(2));
        board.getBoard().get(0).get(2).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,2");
        boardNode.attachChild(board.getBoard().get(0).get(2).getSpatial());
        
        board.getBoard().get(0).set(5, new Bishop(assetManager, false, 0, 5));
        board.getBoard().get(0).get(5).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(5));
        board.getBoard().get(0).get(5).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,5");
        boardNode.attachChild(board.getBoard().get(0).get(5).getSpatial());         
        
        
        // set white queen
        board.getBoard().get(7).set(3, new Queen(assetManager, true, 7, 3));
        board.getBoard().get(7).get(3).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(3));
        board.getBoard().get(7).get(3).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,3");
        boardNode.attachChild(board.getBoard().get(7).get(3).getSpatial());
        
        // set black queen
        board.getBoard().get(0).set(3, new Queen(assetManager, false, 0, 3));
        board.getBoard().get(0).get(3).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(3));
        board.getBoard().get(0).get(3).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,3");
        boardNode.attachChild(board.getBoard().get(0).get(3).getSpatial());
        
        // set white king
        board.getBoard().get(7).set(4, new King(assetManager, true, 7, 4));
        board.getBoard().get(7).get(4).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(4));
        board.getBoard().get(7).get(4).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,4");
        boardNode.attachChild(board.getBoard().get(7).get(4).getSpatial());
        
        // set black kings
        board.getBoard().get(0).set(4, new King(assetManager, false, 0, 4));
        board.getBoard().get(0).get(4).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(4));
        board.getBoard().get(0).get(4).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,4");
        boardNode.attachChild(board.getBoard().get(0).get(4).getSpatial());         
        
    }
    
}
