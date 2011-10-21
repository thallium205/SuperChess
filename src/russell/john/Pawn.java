package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class Pawn extends Piece
{       
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
            int row = this.row;
            int column = this.col;
            
            // A pawn can move forward only if nothing is infront of it
            if (board.getBoard().get(row - 1).get(column).getPieceType().contains("Empty"))  
            {
                // Since there is no piece in front of it, it can move forward
                potentialMoves.add((row - 1) + "," + column);

                // A pawn can move two spaces forward at the starting position if there is nothing 2 spaces in front of it
                if (row == 6 && board.getBoard().get(row - 2).get(column).getPieceType().contains("Empty"))
                    potentialMoves.add((row - 2) + "," + column);                           
            }
            
            // A pawn can move diagonally if there is a black piece diagonally from it            
            // Can it move left?     
            int upperLeftRow = row - 1;
            int upperLeftColumn = column - 1;
            
            if (upperLeftRow >= 0 && upperLeftColumn >= 0)
            {
                Piece upperLeftPiece = board.getBoard().get(upperLeftRow).get(upperLeftColumn);                 
                if (!upperLeftPiece.getPieceType().contains("Empty") && !upperLeftPiece.isWhite())            
                    potentialMoves.add(upperLeftRow + "," + upperLeftColumn);
            }
            
            // Can it move right?
            int upperRightRow = row - 1;
            int upperRightColumn = column + 1;
            
            if (upperRightRow <= 7 && upperRightColumn <= 7)
            {
                Piece upperRightPiece = board.getBoard().get(upperRightRow).get(upperRightColumn);
                if (!upperRightPiece.getPieceType().contains("Empty") && !upperRightPiece.isWhite())            
                    potentialMoves.add(upperRightRow + "," + upperRightColumn);
            }   
            
            return board.getKing(true).removeIllegalMoves(row, column, potentialMoves, board);
            
            
        }
        
        else if (!this.isWhite)
        {
            int row = this.row;
            int column = this.col; 
            
            // A pawn can move forward only if nothing is infront of it
            if (board.getBoard().get(row + 1).get(column).getPieceType().contains("Empty"))  
            {
                // Since there is no piece in front of it, it can move forward
                potentialMoves.add((row + 1) + "," + column);

                // A pawn can move two spaces forward at the starting position if there is nothing 2 spaces in front of it
                if (row == 1 && board.getBoard().get(row + 2).get(column).getPieceType().contains("Empty"))
                    potentialMoves.add((row + 2) + "," + column);                           
            }
            
            // A pawn can move diagonally if there is a black piece diagonally from it            
            // Can it move left?     
            int upperLeftRow = row + 1;
            int upperLeftColumn = column - 1;
            
            if (upperLeftRow >= 0 && upperLeftColumn >= 0)
            {
                Piece upperLeftPiece = board.getBoard().get(upperLeftRow).get(upperLeftColumn);                 
                if (!upperLeftPiece.getPieceType().contains("Empty") && !upperLeftPiece.isWhite())            
                    potentialMoves.add(upperLeftRow + "," + upperLeftColumn);
            }
            
            // Can it move right?
            int upperRightRow = row + 1;
            int upperRightColumn = column + 1;
            
            if (upperRightRow <= 7 && upperRightColumn <= 7)
            {
                Piece upperRightPiece = board.getBoard().get(upperRightRow).get(upperRightColumn);
                if (!upperRightPiece.getPieceType().contains("Empty") && !upperRightPiece.isWhite())            
                    potentialMoves.add(upperRightRow + "," + upperRightColumn);
            }   
            
            // Check for placing own king in check!
            return board.getKing(false).removeIllegalMoves(row, column, potentialMoves, board);
            
        }        
        return null;   
    }     
}
