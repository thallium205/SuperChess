package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class King extends Piece
{       
    Piece originalPiece, replacedPiece;
    
    // Used for determinging if castling is allowed
    private Boolean hasMoved;
    
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
       this.hasMoved = false;
    }
    
    public ArrayList<String> getPotentialMoves(BoardType board)
    {
        ArrayList<String> potentialMoves = new ArrayList<String>();  
        
        if (this.isWhite)
        {    
            
            // A king can move NORTH if either nothing is in front of it or there is an enemy piece
            if (row != 0)            
                if (board.getBoard().get(row - 1).get(col).getPieceType().contains("Empty") || !board.getBoard().get(row - 1).get(col).isWhite)
                    potentialMoves.add((row - 1) + "," + col);            
            
            // A king can move NORTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && col != 7)            
                if (board.getBoard().get(row - 1).get(col + 1).getPieceType().contains("Empty") || !board.getBoard().get(row - 1).get(col + 1).isWhite)
                    potentialMoves.add((row - 1) + "," + (col + 1));            
            
            // A king can move EAST if either nothing is in front of it or there is an enemy piece
            if (col != 7)            
                if (board.getBoard().get(row).get(col + 1).getPieceType().contains("Empty") || !board.getBoard().get(row).get(col + 1).isWhite)
                    potentialMoves.add(row + "," + (col + 1));            
            
            // A king can move SOUTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && col != 7)            
                if (board.getBoard().get(row + 1).get(col + 1).getPieceType().contains("Empty") || !board.getBoard().get(row + 1).get(col + 1).isWhite)
                    potentialMoves.add((row + 1) + "," + (col + 1));             
            
            // A king can move SOUTH if either nothing is in front of it or there is an enemy piece
            if (row != 7)            
                if (board.getBoard().get(row + 1).get(col).getPieceType().contains("Empty") || !board.getBoard().get(row + 1).get(col).isWhite)
                    potentialMoves.add((row + 1) + "," + col);            
            
            // A king can move SOUTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && col != 0)            
                if (board.getBoard().get(row + 1).get(col - 1).getPieceType().contains("Empty") || !board.getBoard().get(row + 1).get(col - 1).isWhite)
                    potentialMoves.add((row + 1) + "," + (col - 1));             
            
           // A king can move WEST if either nothing is in front of it or there is an enemy piece
            if (col != 0)            
                if (board.getBoard().get(row).get(col - 1).getPieceType().contains("Empty") || !board.getBoard().get(row).get(col - 1).isWhite)
                    potentialMoves.add((row) + "," + (col - 1));             
            
            // A king can move NORTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && col != 0)            
                if (board.getBoard().get(row - 1).get(col - 1).getPieceType().contains("Empty") || !board.getBoard().get(row - 1).get(col - 1).isWhite)
                    potentialMoves.add((row - 1) + "," + (col - 1));
            
            // Castling - A king can castle if 
            // 1) it hasnt moved yet. 
            // 2) if it is not in check. 
            // 3) there are no pieces in its way 
            // 4) a rook exists and that it hasn't moved yet    
            // 5) if it does not cross into check on its way to movement
            
                // The king cannot have moved yet
                if (!this.hasMoved)
                {
                    // The king cannot be in check at the moment
                    if (!isThereAThreat(board, (this.row + "," + this.col), this.row, this.col, this))
                    {
                        // To caste king's side for white, there cannot be a bishop or knight in the way
                        if (board.getBoard().get(7).get(5).getPieceType().contains("Empty") && board.getBoard().get(7).get(6).getPieceType().contains("Empty"))
                        {
                            // There must be a rook at the edge and it cannot have moved yet either
                            if (board.getBoard().get(7).get(7).getPieceType().contains("Rook") && !((Rook) board.getBoard().get(7).get(7)).getHasMoved())
                            {
                                // It cannot cross passed F1 if there is a potential check there
                                if (!isThereAThreat(board, "7,5", this.row, this.col, this))
                                {
                                    potentialMoves.add("7,6");
                                }                       
                            }
                        }
                        
                        // To caslte queen's side for white, there cannot be a queen, bishop, or knight in the way
                        else if (board.getBoard().get(7).get(3).getPieceType().contains("Empty") && board.getBoard().get(7).get(2).getPieceType().contains("Empty") && board.getBoard().get(7).get(1).getPieceType().contains("Empty"))
                        {
                           // There must be a rook at the edge and it cannot have moved yet either
                            if (board.getBoard().get(7).get(0).getPieceType().contains("Rook") && !((Rook) board.getBoard().get(7).get(0)).getHasMoved())
                            {
                                // It cannot cross passed D1 if there is a potential check there
                                if (!isThereAThreat(board, "7,3", this.row, this.col, this))
                                {
                                   potentialMoves.add("7,2"); 
                                }
                                
                            }
                        }                  
                }     
            }          
            
           return removeIllegalMoves(row, col, potentialMoves, board);         
        }
        
        else if(!this.isWhite)
        {
            
            // A king can move NORTH if either nothing is in front of it or there is an enemy piece
            if (row != 0)            
                if (board.getBoard().get(row - 1).get(col).getPieceType().contains("Empty") || board.getBoard().get(row - 1).get(col).isWhite)
                    potentialMoves.add((row - 1) + "," + col);            
            
            // A king can move NORTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && col != 7)          
                if (board.getBoard().get(row - 1).get(col + 1).getPieceType().contains("Empty") || board.getBoard().get(row - 1).get(col + 1).isWhite)                
                    potentialMoves.add((row - 1) + "," + (col + 1));              
            
            // A king can move EAST if either nothing is in front of it or there is an enemy piece
            if (col != 7)              
                if (board.getBoard().get(row).get(col + 1).getPieceType().contains("Empty") || board.getBoard().get(row).get(col + 1).isWhite)
                    potentialMoves.add(row + "," + (col + 1));             
            
            // A king can move SOUTH-EAST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && col != 7)            
                if (board.getBoard().get(row + 1).get(col + 1).getPieceType().contains("Empty") || board.getBoard().get(row + 1).get(col + 1).isWhite())
                    potentialMoves.add((row + 1) + "," + (col + 1));             
            
            // A king can move SOUTH if either nothing is in front of it or there is an enemy piece
            if (row != 7)            
                if (board.getBoard().get(row + 1).get(col).getPieceType().contains("Empty") || board.getBoard().get(row + 1).get(col).isWhite)
                    potentialMoves.add((row + 1) + "," + col);            
            
            // A king can move SOUTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 7 && col != 0)            
                if (board.getBoard().get(row + 1).get(col - 1).getPieceType().contains("Empty") || board.getBoard().get(row + 1).get(col - 1).isWhite)
                    potentialMoves.add((row + 1) + "," + (col - 1));               
            
           // A king can move WEST if either nothing is in front of it or there is an enemy piece
            if (col != 0)            
                if (board.getBoard().get(row).get(col - 1).getPieceType().contains("Empty") || board.getBoard().get(row).get(col - 1).isWhite)
                    potentialMoves.add((row) + "," + (col - 1));             
            
            // A king can move NORTH-WEST if either nothing is in front of it or there is an enemy piece
            if (row != 0 && col != 0)            
                if (board.getBoard().get(row - 1).get(col - 1).getPieceType().contains("Empty") || board.getBoard().get(row - 1).get(col - 1).isWhite)
                    potentialMoves.add((row - 1) + "," + (col - 1)); 
            
            // The king cannot have moved yet
                if (!this.hasMoved)
                {
                    // The king cannot be in check at the moment TODO
                   if (!isThereAThreat(board, (this.row + "," + this.col), this.row, this.col, this))
                   {
                        // To caste king's side for black, there cannot be a bishop or knight in the way
                        if (board.getBoard().get(0).get(5).getPieceType().contains("Empty") && board.getBoard().get(0).get(6).getPieceType().contains("Empty"))
                        {
                            // There must be a rook at the edge and it cannot have moved yet either
                            if (board.getBoard().get(0).get(7).getPieceType().contains("Rook") && !((Rook) board.getBoard().get(0).get(7)).getHasMoved())
                            {
                                // It cannot cross passed F1 if there is a potential check there
                                if (!isThereAThreat(board, "0,5", this.row, this.col, this))
                                {
                                    potentialMoves.add("0,6");
                                }                                 
                            }
                        }
                        
                        // To caslte queen's side for black, there cannot be a queen, bishop, or knight in the way
                        else if (board.getBoard().get(0).get(3).getPieceType().contains("Empty") && board.getBoard().get(0).get(2).getPieceType().contains("Empty") && board.getBoard().get(0).get(1).getPieceType().contains("Empty"))
                        {
                           // There must be a rook at the edge and it cannot have moved yet either
                            if (board.getBoard().get(0).get(0).getPieceType().contains("Rook") && !((Rook) board.getBoard().get(0).get(0)).getHasMoved())
                            {
                                if (!isThereAThreat(board, "0,3", this.row, this.col, this))
                                {
                                    potentialMoves.add("0,2");
                                }                                
                            }
                        }                       
                    }                            
                }
            
           return removeIllegalMoves(row, col, potentialMoves, board); 
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
    public ArrayList<String> removeIllegalMoves(int currentPieceRow, int currentPiececol, ArrayList<String> potentialMoves, BoardType board)
    {    
        LinkedList tmpLinkedList = new LinkedList(potentialMoves);
        
        for (Iterator iter = tmpLinkedList.iterator(); iter.hasNext();)
        {
            String move = (String) iter.next();
             
            int newPieceRow = Integer.parseInt((move).split(",")[0]);
            int newPiececol = Integer.parseInt((move).split(",")[1]);      

            // We get a board structure of what it might potentially look like given the potential moves
            potentialMove(board, currentPieceRow, currentPiececol, newPieceRow, newPiececol);    

            // Now we evaluate the king's predicamate given this new board structure.  
            // If there is a problem with it, will will remove this current potential move from the potentialMoves array list+
            // TODO - if the king itself is moving, getPotentialMoves may need to overload this somehow.. ill cross that when i get there
            if(isThereAThreat(board, move, this.row, this.col, originalPiece))
                iter.remove();           

            potentialMoveBack(board, currentPieceRow, currentPiececol, newPieceRow, newPiececol);   
            
        }
        
        potentialMoves.clear();
        potentialMoves.addAll(tmpLinkedList);
        return potentialMoves;
        
        /*
        int size = potentialMoves.size();
        for (int i = 0; i < size; i++)
        {            
            int newPieceRow = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
            int newPiececol = Integer.parseInt(potentialMoves.get(i).split(",")[1]);      
            
            // We get a board structure of what it might potentially look like given the potential moves
            potentialMove(board, currentPieceRow, currentPiececol, newPieceRow, newPiececol);    
            
            // Now we evaluate the king's predicamate given this new board structure.  
            // If there is a problem with it, will will remove this current potential move from the potentialMoves array list+
            // TODO - if the king itself is moving, getPotentialMoves may need to overload this somehow.. ill cross that when i get there
            if(isThereAThreat(board, potentialMoves.get(i), this.row, this.col))
                potentialMoves.remove(potentialMoves.get(i));             
            
            potentialMoveBack(board, currentPieceRow, currentPiececol, newPieceRow, newPiececol);            
        }      
         * */
         
        
        
       
        
       // return potentialMoves;
    }
   
    private void potentialMove(BoardType board, int currentRow, int currentcol, int newRow, int newcol)
    {
        // we get the piece in the original location
        originalPiece = board.getBoard().get(currentRow).get(currentcol);
        
        // We get the piece in the new location 
        replacedPiece = board.getBoard().get(newRow).get(newcol);
        
        // we then replace the old positiion with an empty square
        // TODO - What about castling??  A separate function will have to be made to handle that
        // -> this wont work! board.getBoard().get(currentRow).set(currentcol, board.getBoard().get(newRow).get(newcol));      
        board.getBoard().get(currentRow).set(currentcol, new Empty(assetManager, true, currentRow, currentcol));
        
        // we now replace the new position with the original piece
        board.getBoard().get(newRow).set(newcol, originalPiece);             
    }
    
    private void potentialMoveBack(BoardType board, int currentRow, int currentcol, int newRow, int newcol)
    {
        // Time to move the pieces back
        board.getBoard().get(currentRow).set(currentcol, originalPiece);
        board.getBoard().get(newRow).set(newcol, replacedPiece);
        
        /*
        Piece newPiece = board.getBoard().get(currentRow).get(currentcol);
        
        // Replace the current with an empty 
        board.getBoard().get(currentRow).set(currentcol, originalPiece);
        
        // Put the piece back
        */
        
    }
    
    private boolean isThereAThreat(BoardType board, String move, int kingRow, int kingCol, Piece origPiece)
    {       
        int moveRow = Integer.parseInt(move.split(",")[0]);
        int moveCol = Integer.parseInt(move.split(",")[1]);    
        int pawnRow, pawnCol, knightRow, knightCol, j;
        
        // If the king is wanting to move itself, then the moveRow and the moveCol become the kingRow and the kingCol
        if (origPiece.getPieceType().contains("King"))
        {
            kingRow = moveRow;
            kingCol = moveCol;
        }
        
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
            // THe pawn has to be black and the king has to be white for this to be a threat!
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn") && !board.getBoard().get(pawnRow).get(pawnCol).isWhite())
            {
                if (this.isWhite)
                {
                    return true;
                }
            }
        }
        
        // Check for bishops and queens     
        j = kingCol + 1;
        for (int i = kingRow - 1; i >= 0; i--)
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
            // The pawn has to be white and the king has to be black for this to be a threat
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn") && board.getBoard().get(pawnRow).get(pawnCol).isWhite())
            {
                if (!this.isWhite)
                {
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
            // The pawn has to be white and the king has to be black for this to be a threat
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn") && board.getBoard().get(pawnRow).get(pawnCol).isWhite())
            {
                if (!this.isWhite)
                {
                    return true;
                }
            }
        }
        
        // Check for bishops and queens      
        j = kingCol - 1;
        for (int i = kingRow + 1; i < 8; i++)
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
        for (int i = kingCol - 1; i >= 0; i--)
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
            // The pawn has to be black and the king has to be white for this to be a threat!
            if (board.getBoard().get(pawnRow).get(pawnCol).getPieceType().contains("Pawn") && !board.getBoard().get(pawnRow).get(pawnCol).isWhite())
            {
                if (this.isWhite)
                {
                    return true;
                }
            }
        }
        
        // Check for bishops and queens  
        j = kingCol - 1;
        for (int i = kingRow - 1; i >= 0; i--)
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
    
    // If a king has moved, it needs to set hasMoved to true for castle logic
    @Override
    public void setRow(int row)
    {
        hasMoved = true;
        this.row = row;      
    }
    
    @Override
    public void setColumn(int col)
    {
        hasMoved = true;
        this.col = col;
    }
}
