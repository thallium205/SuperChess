package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class Rook extends Piece
{       
    public Rook()
    {
    
    }
    
    public Rook(AssetManager assetManager, Boolean isWhite, int row, int col)
    {  
       this.assetManager = assetManager;
       this.isWhite = isWhite;
       this.row = row;
       this.col = col;         
        
       this.piece = this.assetManager.loadModel("Models/Rook.j3o");  
       this.piece.setName("Rook," + this.isWhite + "," + this.row + "," + this.col);
       this.pieceType = "Rook";
    }   
    
    public ArrayList<String> getPotentialMoves(BoardType board)
    {
        ArrayList<String> potentialMoves = new ArrayList<String>();  

        int row = this.row;
        int column = this.col;

        // The rook can move north, south, east, west            
        if (this.isWhite)
        {
            // Move NORTH
            if (row != 0)                
                for (int i = row - 1; i >= 0; i--)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(i).get(column).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is black, it can move there and not any further                        
                    else if (!board.getBoard().get(i).get(column).isWhite())
                    {
                        potentialMoves.add(i + "," + column);
                        break;
                    }

                    else
                    {
                        break;
                    }
                }                

            // Move EAST  
            if (column != 7)                
                for (int  i = column + 1; i <= 7; i++)
                {                    
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is black, it can move there and not any further                        
                    else if (!board.getBoard().get(row).get(i).isWhite())
                    {
                        potentialMoves.add(row + "," + i);
                        break;
                    } 

                    else
                    {
                        break;
                    }
                }


            // Move SOUTH
            if (row != 7)                
                for (int i = row + 1; i <= 7; i++)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(i).get(column).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is black, it can move there and not any further                        
                    else if (!board.getBoard().get(i).get(column).isWhite())
                    {
                        potentialMoves.add(i + "," + column);
                        break;
                    }

                    else
                    {
                        break;
                    }
                }


            // Move WEST
            if (column != 0)
                for (int  i = column - 1; i >= 0; i--)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is black, it can move there and not any further                        
                    else if (!board.getBoard().get(row).get(i).isWhite())
                    {
                        potentialMoves.add(row + "," + i);
                        break;
                    }  

                    else
                    {
                        break;
                    }
                }

            // Given the move set, ask the king for permission to move there
            return board.getKing(true).removeIllegalMoves(row, column, potentialMoves, board);                
        }

        // It is a black piece
        else if (!this.isWhite)
        {
            // Move NORTH
            if (row != 0)  
                for (int i = row - 1; i >= 0; i--)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(i).get(column).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is white, it can move there and not any further                        
                    else if (board.getBoard().get(i).get(column).isWhite())
                    {
                        potentialMoves.add(i + "," + column);
                        break;
                    }

                    else
                    {
                        break;
                    }
                }

            // Move EAST
            if (column != 7) 
                for (int  i = column + 1; i <= 7; i++)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is white, it can move there and not any further                        
                    else if (board.getBoard().get(row).get(i).isWhite())
                    {
                        potentialMoves.add(row + "," + i);
                        break;
                    }  

                    else
                    {
                        break;
                    }
                }

            // Move SOUTH
            if (row != 7)                
                for (int i = row + 1; i <= 7; i++)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(i).get(column).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is white, it can move there and not any further                        
                    else if (board.getBoard().get(i).get(column).isWhite())
                    {
                        potentialMoves.add(i + "," + column);
                        break;
                    }

                    else
                    {
                        break;
                    }
                }

            // Move WEST
            if (column != 0) 
                for (int  i = column - 1; i >= 0; i--)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(i + "," + column);

                    // Else if there is a piece that is white, it can move there and not any further                        
                    else if (board.getBoard().get(row).get(i).isWhite())
                    {
                        potentialMoves.add(row + "," + i);
                        break;
                    }  

                    else
                    {
                        break;
                    }
                }

            // Given the move set, ask the king for permission to move there
            return board.getKing(false).removeIllegalMoves(row, column, potentialMoves, board);
        }            

        // Something bad happens
        return null;
    }
}
