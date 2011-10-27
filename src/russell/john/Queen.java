package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class Queen extends Piece
{       
    public Queen()
    {
    
    }
    
    public Queen(AssetManager assetManager, Boolean isWhite, int row, int col)
    {  
       this.assetManager = assetManager;
       this.isWhite = isWhite;
       this.row = row;
       this.col = col;          
        
       this.piece = this.assetManager.loadModel("Models/Queen.j3o");  
       this.piece.setName("Queen," + this.isWhite + "," + this.row + "," + this.col);
       this.pieceType = "Queen";
    }   
    
    public ArrayList<String> getPotentialMoves(BoardType board)
    {
        ArrayList<String> potentialMoves = new ArrayList<String>();  

        int row = this.row;
        int column = this.col;
        
        int j = 0;

        // The rook can move north, north-east, east, south-east, south, south-west, west, north-west          
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

            // Move EAST  
            if (column != 7)                
                for (int  i = column + 1; i <= 7; i++)
                {                    
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(row + "," + i);

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


            // Move WEST
            if (column != 0)
                for (int  i = column - 1; i >= 0; i--)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(row + "," + i);

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

            // Move EAST
            if (column != 7) 
                for (int  i = column + 1; i <= 7; i++)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(row + "," + i);

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

            // Move WEST
            if (column != 0) 
                for (int  i = column - 1; i >= 0; i--)
                {
                    // If there is nothing in the way, it can move there
                    if (board.getBoard().get(row).get(i).getPieceType().contains("Empty"))
                        potentialMoves.add(row + "," + i);

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

            // Given the move set, ask the king for permission to move there
            return board.getKing(false).removeIllegalMoves(row, column, potentialMoves, board);
        }            

        // Something bad happens
        return null;
    }
}
