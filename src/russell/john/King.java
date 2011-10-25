package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class King extends Piece
{       
    Piece originalPiece;
    public King()
    {
    
    }
    
    public King(AssetManager assetManager, Boolean isWhite, int row, int col)
    {  
       this.assetManager = assetManager;
       this.isWhite = isWhite;
       this.row = row;
       this.col = col;         
        
       this.piece = this.assetManager.loadModel("Models/King.j3o");  
       this.piece.setName("King," + this.isWhite + "," + this.row + "," + this.col);
       this.pieceType = "King";
    }
    
    public ArrayList<String> getPotentialMoves(BoardType board)
    {
        ArrayList<String> potentialMoves = new ArrayList<String>();  
        
        if (this.isWhite)
        {    
            int row = this.row;
            int column = this.col;
            
            // A king can move NORTH if either nothing is in front of it or there is an enemy piece
            if (row != 0)
                if (board.getBoard().get(row - 1).get(column).getPieceType().contains("Empty") || !board.getBoard().get(row - 1).get(column).isWhite)
                    potentialMoves.add((row - 1) + "," + column);
            // A king can move NORTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && column != 7)
                if (board.getBoard().get(row - 1).get(column + 1).getPieceType().contains("Empty") || !board.getBoard().get(row - 1).get(column + 1).isWhite);
                    potentialMoves.add((row - 1) + "," + (column + 1));
            // A king can move EAST if either nothing is in front of it or there is an enemy piece
            if (column != 7)
                if (board.getBoard().get(row).get(column + 1).getPieceType().contains("Empty") || !board.getBoard().get(row).get(column + 1).isWhite)
                    potentialMoves.add(row + "," + (column + 1));        
            // A king can move SOUTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && column != 7)
                if (board.getBoard().get(row + 1).get(column + 1).getPieceType().contains("Empty") || !board.getBoard().get(row + 1).get(column + 1).isWhite);
                    potentialMoves.add((row + 1) + "," + (column + 1)); 
            // A king can move SOUTH if either nothing is in front of it or there is an enemy piece
            if (row != 7)
                if (board.getBoard().get(row + 1).get(column).getPieceType().contains("Empty") || !board.getBoard().get(row + 1).get(column).isWhite)
                    potentialMoves.add((row + 1) + "," + column);
            // A king can move SOUTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && column != 0)
                if (board.getBoard().get(row + 1).get(column - 1).getPieceType().contains("Empty") || !board.getBoard().get(row + 1).get(column - 1).isWhite);
                    potentialMoves.add((row + 1) + "," + (column - 1));                     
           // A king can move WEST if either nothing is in front of it or there is an enemy piece
            if (column != 0)
                if (board.getBoard().get(row).get(column - 1).getPieceType().contains("Empty") || !board.getBoard().get(row).get(column - 1).isWhite)
                    potentialMoves.add((row - 1) + "," + (column + 1)); 
            // A king can move NORTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && column != 0)
                if (board.getBoard().get(row - 1).get(column - 1).getPieceType().contains("Empty") || !board.getBoard().get(row - 1).get(column - 1).isWhite);
                    potentialMoves.add((row - 1) + "," + (column - 1));                    
           return removeIllegalMoves(row, column, potentialMoves, board);         
        }
        
        else if(!this.isWhite)
        {
            int row = this.row;
            int column = this.col;
            
            // A king can move NORTH if either nothing is in front of it or there is an enemy piece
            if (row != 0)
                if (board.getBoard().get(row - 1).get(column).getPieceType().contains("Empty") || board.getBoard().get(row - 1).get(column).isWhite)
                    potentialMoves.add((row - 1) + "," + column);
            // A king can move NORTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && column != 7)
                if (board.getBoard().get(row - 1).get(column + 1).getPieceType().contains("Empty") || board.getBoard().get(row - 1).get(column + 1).isWhite);
                    potentialMoves.add((row - 1) + "," + (column + 1));
            // A king can move EAST if either nothing is in front of it or there is an enemy piece
            if (column != 7)
                if (board.getBoard().get(row).get(column + 1).getPieceType().contains("Empty") || board.getBoard().get(row).get(column + 1).isWhite)
                    potentialMoves.add(row + "," + (column + 1));        
            // A king can move SOUTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && column != 7)
                if (board.getBoard().get(row + 1).get(column + 1).getPieceType().contains("Empty") || board.getBoard().get(row + 1).get(column + 1).isWhite);
                    potentialMoves.add((row + 1) + "," + (column + 1)); 
            // A king can move SOUTH if either nothing is in front of it or there is an enemy piece
            if (row != 7)
                if (board.getBoard().get(row + 1).get(column).getPieceType().contains("Empty") || board.getBoard().get(row + 1).get(column).isWhite)
                    potentialMoves.add((row + 1) + "," + column);
            // A king can move SOUTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && column != 0)
                if (board.getBoard().get(row + 1).get(column - 1).getPieceType().contains("Empty") || board.getBoard().get(row + 1).get(column - 1).isWhite);
                    potentialMoves.add((row + 1) + "," + (column - 1));                     
           // A king can move WEST if either nothing is in front of it or there is an enemy piece
            if (column != 0)
                if (board.getBoard().get(row).get(column - 1).getPieceType().contains("Empty") || board.getBoard().get(row).get(column - 1).isWhite)
                    potentialMoves.add((row - 1) + "," + (column + 1)); 
            // A king can move NORTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && column != 0)
                if (board.getBoard().get(row - 1).get(column - 1).getPieceType().contains("Empty") || board.getBoard().get(row - 1).get(column - 1).isWhite);
                    potentialMoves.add((row - 1) + "," + (column - 1));                    
           return removeIllegalMoves(row, column, potentialMoves, board); 
        }
        
        // something bad happened
        return null;
        
    }

    /**
     * This function will check potential moves from pieces to make sure the king is not in check
     * @param currentPiece
     * @param potentialMoves
     * @param board
     * @return 
     */
    public ArrayList<String> removeIllegalMoves(int currentPieceRow, int currentPieceColumn, ArrayList<String> potentialMoves, BoardType board)
    {        
        for (int i = 0; i < potentialMoves.size(); i++)
        {            
            int newPieceRow = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
            int newPieceColumn = Integer.parseInt(potentialMoves.get(i).split(",")[1]);      
            
            // We get a board structure of what it might potentially look like given the potential moves
            potentialMove(board, currentPieceRow, currentPieceColumn, newPieceRow, newPieceColumn);    
            
            // Now we evaluate the king's predicamate given this new board structure.  
            // If there is a problem with it, will will remove this current potential move from the potentialMoves array list+
            // TODO - if the king itself is moving, getPotentialMoves may need to overload this somehow.. ill cross that when i get there
            if(isThereAThreat(board, potentialMoves.get(i), this.row, this.col))
                potentialMoves.remove(i);
            
            potentialMoveBack(board, currentPieceRow, currentPieceColumn, newPieceRow, newPieceColumn);            
        }      
        
        return potentialMoves;
    }
   
    private void potentialMove(BoardType board, int currentRow, int currentColumn, int newRow, int newColumn)
    {
        // we get the piece in the original location
        originalPiece = board.getBoard().get(currentRow).get(currentColumn);
        
        // we then replace it with an empty square
        // TODO - FUCK, what about castling??  A separate function will have to be made to handle that
        board.getBoard().get(currentRow).set(currentColumn, board.getBoard().get(newRow).get(newColumn));
        
        // we now replace the new position with the current piece
        board.getBoard().get(newRow).set(newColumn, originalPiece);             
    }
    
    private void potentialMoveBack(BoardType board, int currentRow, int currentColumn, int newRow, int newColumn)
    {
        // Time to move the piece back
        Piece newPiece = board.getBoard().get(currentRow).get(currentColumn);
        
        // Replace the current with an empty 
        board.getBoard().get(currentRow).set(currentColumn, originalPiece);
        
        // Put the piece back
        board.getBoard().get(newRow).set(newColumn, newPiece);
        
    }
    
    private boolean isThereAThreat(BoardType board, String move, int kingRow, int kingCol)
    {
        int moveRow = Integer.parseInt(move.split(",")[0]);
        int moveCol = Integer.parseInt(move.split(",")[1]);    
        int pawnRow, pawnCol, knightRow, knightCol, j;
        
        // We are going to check all the immediate pieces surrounding the king
        
        // ********** Get all squares NORTH where the last square is either the edge of the board or an enemy piece **********
        for (int i = kingRow - 1; i > 0; i--)
        {
            // We only care about the closest piece to the king
            if (!board.getBoard().get(i).get(kingCol).getPieceType().contains("Empty"))
            {
                // We are going to check for enemy rooks and queens
                if (board.getBoard().get(i).get(kingCol).getPieceType().contains("Rook") || board.getBoard().get(i).get(kingCol).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(i).get(kingCol).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(i).get(kingCol).isWhite())
                            return true;
                    }
                }
                
                // we now break out of the loop because we dont care about anything behind this piece
                break;
            }
        }
        
        
        // ********** Get all squares NORTH-EAST where the last square is either the edge of the board or an enemy piece **********    
        // We check for enemy pawn before checking for bishops and queens
        pawnRow = kingRow - 1;
        pawnCol = kingCol + 1;
        if (pawnRow >= 0 && pawnRow <= 7 && pawnCol >= 0 && pawnCol <=7)
        {
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
            }
        }
        
        // Check for bishops and queens     
        j = kingCol + 1;
        for (int i = kingRow - 1; i > 0; i--)
        {      
            // We only care about the closest non-empty diagonal piece to the king
            if (j <=7 && !board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
            {                   
                // We are going to check for enemy bishops and queens
                if (board.getBoard().get(i).get(j).getPieceType().contains("Bishop") || board.getBoard().get(i).get(j).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }
                }                   

                // we now break out of the loops because we dont care about anything behind this piece
                break;
            }      
            j++;
        }
        
        // ********** Get all squares EAST where the last square is either the edge of the board or an enemy piece **********
        for (int i = kingCol + 1; i < 8; i++)
        {
            // We only care about the closest piece to the king
            if (!board.getBoard().get(kingRow).get(i).getPieceType().contains("Empty"))
            {
                // We are going to check for enemy rooks and queens
                if (board.getBoard().get(kingRow).get(i).getPieceType().contains("Rook") || board.getBoard().get(kingRow).get(i).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(kingRow).get(i).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(kingRow).get(i).isWhite())
                            return true;
                    }
                }                
                
                // we now break out of the loop because we dont care about anything behind this piece
                break;
            }
        }
        
        
        // ********** Get all squares SOUTH-EAST where the last square is either the edge of the board or an enemy piece **********
        // We check for enemy pawn before checking for bishops and queens 
        pawnRow = kingRow + 1;
        pawnCol = kingCol + 1;
        if (pawnRow >= 0 && pawnRow <= 7 && pawnCol >= 0 && pawnCol <=7)
        {
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
            }
        }
        
        // Check for bishops and queens    
        j = kingCol + 1;
        for (int i = kingRow + 1; i < 8; i++)
        {
            // We only care about the closest non-empty diagonal piece to the king
            if (j <= 7 && !board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
            {                   
                // We are going to check for enemy bishops and queens
                if (board.getBoard().get(i).get(j).getPieceType().contains("Bishop") || board.getBoard().get(i).get(j).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }
                }

                // we now break out of the loops because we dont care about anything behind this piece
                break;
            }
            j++;
        }
        
        // ********** Get all squares SOUTH where the last square is either the edge of the board or an enemy piece **********        
        for (int i = kingRow + 1; i < 8; i++)
        {
            // We only care about the closest piece to the king
            if (!board.getBoard().get(i).get(kingCol).getPieceType().contains("Empty"))
            {
                // We are going to check for enemy rooks and queens
                if (board.getBoard().get(i).get(kingCol).getPieceType().contains("Rook") || board.getBoard().get(i).get(kingCol).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(i).get(kingCol).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(i).get(kingCol).isWhite())
                            return true;
                    }
                }                
                
                // we now break out of the loop because we dont care about anything behind this piece
                break;
            }
        }
        
        // ********** Get all squares SOUTH-WEST where the last square is either the edge of the board or an enemy piece **********
        // We check for enemy pawn before checking for bishops and queens 
        pawnRow = kingRow + 1;
        pawnCol = kingCol - 1;
        if (pawnRow >= 0 && pawnRow <= 7 && pawnCol >= 0 && pawnCol <=7)
        {
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
            }
        }
        
        // Check for bishops and queens      
        j = kingCol - 1;
        for (int i = kingRow + 1; i < 0; i++)
        {           
            // We only care about the closest non-empty diagonal piece to the king
            if (j >= 0 && !board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
            {                   
                // We are going to check for enemy bishops and queens
                if (board.getBoard().get(i).get(j).getPieceType().contains("Bishop") || board.getBoard().get(i).get(j).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }
                }

                // we now break out of the loops because we dont care about anything behind this piece
                break;
            }
            j--;            
        }
        
        // ********** Get all squares WEST where the last square is either the edge of the board or an enemy piece **********
        for (int i = kingCol - 1; i > 0; i--)
        {
            // We only care about the closest piece to the king
            if (!board.getBoard().get(kingRow).get(i).getPieceType().contains("Empty"))
            {
                // We are going to check for enemy rooks and queens
                if (board.getBoard().get(kingRow).get(i).getPieceType().contains("Rook") || board.getBoard().get(kingRow).get(i).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(kingRow).get(i).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(kingRow).get(i).isWhite())
                            return true;
                    }
                }                
                
                // we now break out of the loop because we dont care about anything behind this piece
                break;
            }
        }
        
        // ********** Get all squares NORTH-WEST where the last square is either the edge of the board or an enemy piece **********
        // We check for enemy pawn before checking for bishops and queens 
        pawnRow = kingRow - 1;
        pawnCol = kingCol - 1;
        if (pawnRow >= 0 && pawnRow <= 7 && pawnCol >= 0 && pawnCol <=7)
        {
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(pawnRow).get(pawnCol).isWhite())
                        return true;
                }
            }
        }
        
        // Check for bishops and queens  
        j = kingCol - 1;
        for (int i = kingRow - 1; i > 0; i--)
        {
            // We only care about the closest non-empty diagonal piece to the king
            if (j >= 0 && !board.getBoard().get(i).get(j).getPieceType().contains("Empty"))
            {                   
                // We are going to check for enemy bishops and queens
                if (board.getBoard().get(i).get(j).getPieceType().contains("Bishop") || board.getBoard().get(i).get(j).getPieceType().contains("Queen"))
                {
                    if (this.isWhite)
                    {
                        if (!board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }     

                    else if (!this.isWhite)
                    {
                        if (board.getBoard().get(i).get(j).isWhite())
                            return true;
                    }
                }

                // we now break out of the loops because we dont care about anything behind this piece
                break;                
            }
            j--;
        }
        
        // ********** Get Knight 1 where the knight is X **********
        //     X
        //    |
        //    |
        //    K
        knightRow = kingRow - 2;
        knightCol = kingCol + 1;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }
        
        
        
        // ********** Get Knight 2 where the knight is X **********
        //   
        //     - X
        //    |
        //    K
        knightRow = kingRow - 1;
        knightCol = kingCol + 2;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }
        
        // ********** Get Knight 3 where the knight is X **********
        //    K
        //    |
        //     - X
        knightRow = kingRow + 1;
        knightCol = kingCol + 2;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }
        
        // ********** Get Knight 4 where the knight is X **********
        //    K
        //    |
        //    |
        //     X
        knightRow = kingRow + 2;
        knightCol = kingCol + 1;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }
        
        // ********** Get Knight 5 where the knight is X **********
        //    K
        //    |
        //    |
        //   X
        knightRow = kingRow + 2;
        knightCol = kingCol - 1;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }
        
        // ********** Get Knight 6 where the knight is X **********
        //    K
        //    |
        // X -
        knightRow = kingRow + 1;
        knightCol = kingCol - 2;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }
        
        
        // ********** Get Knight 7 where the knight is X **********
        //   
        // X -
        //    |
        //    K
        knightRow = kingRow - 1;
        knightCol = kingCol - 2;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }
        
        // ********** Get Knight 8 where the knight is X **********
        //   X
        //    |
        //    |
        //    K
        knightRow = kingRow - 2;
        knightCol = kingCol - 1;
        if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
        {
            if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Knight"))
            {
                if (this.isWhite)
                {
                    if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
                else if (!this.isWhite)
                {
                    if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                        return true;
                }
            }
        }            
       
        // there are no threats to the king given this particular move
        return false;        
    }   
}
