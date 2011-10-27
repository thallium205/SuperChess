package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class Bishop extends Piece
{       
    public Bishop()
    {
    
    }
    
    public Bishop(AssetManager assetManager, Boolean isWhite, int row, int col)
    {  
       this.assetManager = assetManager;
       this.isWhite = isWhite;
       this.row = row;
       this.col = col;
        
       this.piece = this.assetManager.loadModel("Models/Bishop.j3o");  
       this.piece.setName("Bishop," + this.isWhite + "," + this.row + "," + this.col);
       this.pieceType = "Bishop";
    }   
    
    public ArrayList<String> getPotentialMoves(BoardType board)
    {
        ArrayList<String> potentialMoves = new ArrayList<String>(); 

        int row = this.row;
        int column = this.col;
        int j = 0;
        
        
        if (this.isWhite)
        {            
            // Move NORTH-EAST      
            j = column + 1;
            for (int i = row - 1; i > 0; i--)
            {  
                // If there is nothing in the way, it can move there
                if (j <= 7 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a black piece in the way, it can move there but not any further
                else if (j <= 7 && !board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }      

                else                
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                

                  j++;  
            }   
                
            // Move SOUTH-EAST            
            j = column + 1;
            for (int i = row + 1; i < 8; i++)
            {  
                // If there is nothing in the way, it can move there
                if (j <= 7 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a black piece in the way, it can move there but not any further
                else if (j <= 7 && !board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }

                else
                {
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                }
                j++;  
            }
            
            // Move SOUTH-WEST
            j = column - 1;
            for (int i = row + 1; i < 8; i++)
            {                  
                // If there is nothing in the way, it can move there
                if (j >= 0 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a black piece in the way, it can move there but not any further
                else if (j >= 0 && !board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }

                else
                {
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                }

                  j--;  
            }  
                
            
            // Move NORTH-WEST
            j = column - 1;
            for (int i = row - 1; i > 0; i--)
            {                
                // If there is nothing in the way, it can move there
                if (j >= 0 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a black piece in the way, it can move there but not any further
                else if (j >= 0 && !board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }

                else
                {
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                }

                  j--;  
            }
            // Ask the king for permission to move there
            return board.getKing(true).removeIllegalMoves(row, column, potentialMoves, board);
        }
        
        // If it is black
        else if (!this.isWhite)
        {
            // Move NORTH-EAST      
            j = column + 1;
            for (int i = row - 1; i > 0; i--)
            {      
                // If there is nothing in the way, it can move there
                if (j <= 7 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a white piece in the way, it can move there but not any further
                else if (j <= 7 && board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }

                else
                {
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                }

                  j++;  
            }   
                
            // Move SOUTH-EAST            
            j = column + 1;
            for (int i = row + 1; i < 8; i++)
            {      
                // If there is nothing in the way, it can move there
                if (j <= 7 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a white piece in the way, it can move there but not any further
                else if (j <= 7 && board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }

                else
                {
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                }
                j++;  
            }
            
            // Move SOUTH-WEST
            j = column - 1;
            for (int i = row + 1; i < 8; i++)
            {      
                // If there is nothing in the way, it can move there
                if (j >= 0 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a white piece in the way, it can move there but not any further
                else if (j >= 0 && board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }

                else
                {
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                }

                  j--;  
            }  
                
            
            // Move NORTH-WEST
            j = column - 1;
            for (int i = row - 1; i > 0; i--)
            {      
                // If there is nothing in the way, it can move there
                if (j >= 0 && board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
                {                   
                    potentialMoves.add(i + "," + j);
                }        

                // If there is a white piece in the way, it can move there but not any further
                else if (j >= 0 && board.getBoard().get(i).get(j).isWhite())
                {
                    potentialMoves.add(i + "," + j);
                    break;
                }

                else
                {
                    // we now break out of the loops because we dont care about anything behind this piece
                    break;
                }

                  j--;  
            }
            // Ask the king for permission to move there
            return board.getKing(false).removeIllegalMoves(row, column, potentialMoves, board);  
        }
        
        // Something bad happened
        return null;
    }
}
