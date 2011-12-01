package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class Pawn extends Piece
{       
    private boolean enPassant;
    public Pawn()
    {
    
    }
    
    public Pawn(AssetManager assetManager, Boolean isWhite, int row, int col)
    {  
       this.assetManager = assetManager;
       this.isWhite = isWhite;
       this.row = row;
       this.col = col;         
        
       this.piece = this.assetManager.loadModel("Models/Pawn.j3o");  
       this.piece.setName("Pawn," + this.isWhite + "," + this.row + "," + this.col);
       this.pieceType = "Pawn";
       this.enPassant = false;
    }   
    
    /**
     * Given a coordinate (like A2), it will return all potential moves.  
     * @param coordinate
     * @return 
     */
    
    
    // TODO - a last move constructor may be needed for en passant!
    public ArrayList<String> getPotentialMoves(BoardType board)
    {
        ArrayList<String> potentialMoves = new ArrayList<String>();  
        
        if (this.isWhite)
        {         
            // A white pawn can move NORTH only if nothing is infront of it
            if (row != 0)
                if (board.getBoard().get(row - 1).get(col).getPieceType().contains("Empty"))  
                {
                    // Since there is no piece in front of it, it can move forward
                    potentialMoves.add((row - 1) + "," + col);

                    // A pawn can move two spaces forward at the starting position if there is nothing 2 spaces in front of it
                    if (row == 6 && board.getBoard().get(row - 2).get(col).getPieceType().contains("Empty"))
                    {                        
                        // If there an enemy pawn to the EAST, we will let that enemy pawn know that it can  do enpassant
                        if (col != 7)
                            if (board.getBoard().get(row - 2).get(col + 1).getPieceType().contains("Pawn") && !board.getBoard().get(row - 2).get(col + 1).isWhite)
                                ((Pawn) board.getBoard().get(row - 2).get(col + 1)).setEnPassant(true);    
                       
                        // If there an enemy pawn to the WEST, we will let that enemy pawn know that it can  do enpassant
                        if (col != 0)
                            if (board.getBoard().get(row - 2).get(col - 1).getPieceType().contains("Pawn") && !board.getBoard().get(row - 2).get(col - 1).isWhite)
                                ((Pawn) board.getBoard().get(row - 2).get(col - 1)).setEnPassant(true);
                        
                        // Now just set the potential piece movement
                        potentialMoves.add((row - 2) + "," + col);   
                    }                                                
                }
            
            // A white pawn can move NORTH-WEST if there is a black piece diagonally from it       
            if (row != 0 && col != 0)                        
                if (!board.getBoard().get(row - 1).get(col - 1).getPieceType().contains("Empty") && !board.getBoard().get(row - 1).get(col - 1).isWhite())            
                    potentialMoves.add((row - 1) + "," + (col - 1));
            
            
            // Can it move NORTH-EAST?            
            if (row != 0 && col != 7)      
                if (!board.getBoard().get(row - 1).get(col + 1).getPieceType().contains("Empty") && !board.getBoard().get(row - 1).get(col + 1).isWhite())            
                    potentialMoves.add((row - 1) + "," + (col + 1));             
            
            // Are we allowed to do en passant?
            if (this.enPassant)
            {
                // An enemy pawn has let us know that we are allowed to do en passant
                // Was it the enemy pawn to our EAST?
                if (col != 7)
                    if (board.getBoard().get(row).get(col + 1).getPieceType().contains("Pawn"))
                    {
                      Pawn pawn = (Pawn) board.getBoard().get(row).get(col + 1);
                      if (pawn.isLastMoved())
                      {
                         potentialMoves.add((row -1) + "," + (col + 1));
                      }
                    }
                
                // Was it the enemy pawn to our WEST?
                 if (col != 7)
                     if (board.getBoard().get(row).get(col - 1).getPieceType().contains("Pawn"))
                     {
                         Pawn pawn = (Pawn) board.getBoard().get(row).get(col - 1);
                         if (pawn.isLastMoved())
                         {
                             potentialMoves.add((row - 1) + "," + (col - 1));
                         }
                    }
            }
            return board.getKing(true).removeIllegalMoves(row, col, potentialMoves, board);                        
        }
        
        else if (!this.isWhite)
        {            
            // A black pawn can move SOUTH only if nothing is infront of it
            if (row != 7)
                if (board.getBoard().get(row + 1).get(col).getPieceType().contains("Empty"))  
                {
                    // Since there is no piece in front of it, it can move forward
                    potentialMoves.add((row + 1) + "," + col);

                    // A pawn can move two spaces SOUTH at the starting position if there is nothing 2 spaces in front of it
                    if (row == 1 && board.getBoard().get(row + 2).get(col).getPieceType().contains("Empty"))
                    {
                        // Since this pawn moved two spaces, it needs to alert any enemy pawns EAST and WEST of it that they can perform en passant
                        // Is there an enemy pawn to the EAST?
                        if (col != 7)
                            if (board.getBoard().get(row + 2).get(col + 1).getPieceType().contains("Pawn") && !board.getBoard().get(row + 2).get(col + 1).isWhite)
                                ((Pawn) board.getBoard().get(row - 2).get(col + 1)).setEnPassant(true);    
                       
                        // Is there an emeny pawn to the WEST?
                        if (col != 0)
                            if (board.getBoard().get(row + 2).get(col - 1).getPieceType().contains("Pawn") && !board.getBoard().get(row + 2).get(col - 1).isWhite)
                                ((Pawn) board.getBoard().get(row + 2).get(col - 1)).setEnPassant(true);  
                        
                        potentialMoves.add((row + 2) + "," + col);        
                    }
                }
            
            // A pawn can move diagonally if there is a black piece diagonally from it            
            // Can it move SOUTH-WEST               
            if (row != 7 && col != 0)                          
                if (!board.getBoard().get(row + 1).get(col - 1).getPieceType().contains("Empty") && board.getBoard().get(row + 1).get(col - 1).isWhite())            
                    potentialMoves.add((row + 1) + "," + (col - 1));            
            
            // Can it move SOUTH-EAST?            
            if (row != 7 && col != 7)      
            {
                if (!board.getBoard().get(row + 1).get(col + 1).getPieceType().contains("Empty") && board.getBoard().get(row + 1).get(col + 1).isWhite())            
                    potentialMoves.add((row + 1) + "," + (col + 1));
            }   
            
             // Are we allowed to do en passant?
            if (this.enPassant)
            {
                // An enemy pawn has let us know that we are allowed to do en passant
                // Was it the enemy pawn to our EAST?
                if (col != 7)
                    if (board.getBoard().get(row).get(col + 1).getPieceType().contains("Pawn"))
                    {
                      Pawn pawn = (Pawn) board.getBoard().get(row).get(col + 1);
                      if (pawn.isLastMoved())
                      {
                         potentialMoves.add((row + 1) + "," + (col + 1));
                      }
                    }
                
                // Was it the enemy pawn to our WEST?
                 if (col != 0)
                     if (board.getBoard().get(row).get(col - 1).getPieceType().contains("Pawn"))
                     {
                         Pawn pawn = (Pawn) board.getBoard().get(row).get(col - 1);
                         if (pawn.isLastMoved())
                         {
                             potentialMoves.add((row + 1) + "," + (col - 1));
                         }
                    }
            }
            
            // Check for placing own king in check!
            return board.getKing(false).removeIllegalMoves(row, col, potentialMoves, board);
            
        }        
        return null;   
    }     
    
    public void setEnPassant(Boolean enPassant)
    {
        this.enPassant = enPassant;        
        // A listener needs to be created for this! TODO
    }
    
    public boolean getEnPassant()
    {
        return enPassant;
    }
    
}
