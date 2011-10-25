package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class Knight extends Piece
{       
    public Knight()
    {
    
    }
    
    public Knight(AssetManager assetManager, Boolean isWhite, int row, int col)
    {  
       this.assetManager = assetManager;
       this.isWhite = isWhite;
       this.row = row;
       this.col = col;       
        
       this.piece = this.assetManager.loadModel("Models/Knight.j3o");  
       this.piece.setName("Knight," + this.isWhite + "," + this.row + "," + this.col);
       this.pieceType = "Knight";
    }   
    
    public ArrayList<String> getPotentialMoves(BoardType board)
    {
        ArrayList<String> potentialMoves = new ArrayList<String>(); 

        int row = this.row;
        int column = this.col;
        int knightRow, knightCol;
        
        if (this.isWhite)
        {
            // ********** Get Knight 1 where the knight is X **********
            //     X
            //    |
            //    |
            //    K
            knightRow = row - 2;
            knightCol = column + 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }



            // ********** Get Knight 2 where the knight is X **********
            //   
            //     - X
            //    |
            //    K
            knightRow = row - 1;
            knightCol = column + 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 3 where the knight is X **********
            //    K
            //    |
            //     - X
            knightRow = row + 1;
            knightCol = column + 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 4 where the knight is X **********
            //    K
            //    |
            //    |
            //     X
            knightRow = row + 2;
            knightCol = column + 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 5 where the knight is X **********
            //    K
            //    |
            //    |
            //   X
            knightRow = row + 2;
            knightCol = column - 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 6 where the knight is X **********
            //    K
            //    |
            // X -
            knightRow = row + 1;
            knightCol = column - 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }


            // ********** Get Knight 7 where the knight is X **********
            //   
            // X -
            //    |
            //    K
            knightRow = row - 1;
            knightCol = column - 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 8 where the knight is X **********
            //   X
            //    |
            //    |
            //    K
            knightRow = row - 2;
            knightCol = column - 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a black piece in the way
                else if (!board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            } 
            
            // Ask the king for permission to move there
            return board.getKing(true).removeIllegalMoves(row, column, potentialMoves, board);  
        }
        
        else if (!this.isWhite)
        {
            // ********** Get Knight 1 where the knight is X **********
            //     X
            //    |
            //    |
            //    K
            knightRow = row - 2;
            knightCol = column + 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }



            // ********** Get Knight 2 where the knight is X **********
            //   
            //     - X
            //    |
            //    K
            knightRow = row - 1;
            knightCol = column + 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 3 where the knight is X **********
            //    K
            //    |
            //     - X
            knightRow = row + 1;
            knightCol = column + 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 4 where the knight is X **********
            //    K
            //    |
            //    |
            //     X
            knightRow = row + 2;
            knightCol = column + 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 5 where the knight is X **********
            //    K
            //    |
            //    |
            //   X
            knightRow = row + 2;
            knightCol = column - 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 6 where the knight is X **********
            //    K
            //    |
            // X -
            knightRow = row + 1;
            knightCol = column - 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }


            // ********** Get Knight 7 where the knight is X **********
            //   
            // X -
            //    |
            //    K
            knightRow = row - 1;
            knightCol = column - 2;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            }

            // ********** Get Knight 8 where the knight is X **********
            //   X
            //    |
            //    |
            //    K
            knightRow = row - 2;
            knightCol = column - 1;
            if (knightRow >= 0 && knightRow <= 7 && knightCol >= 0 && knightCol <=7)
            {
                // If there is no piece in the way
                if (board.getBoard().get(knightRow).get(knightCol).getPieceType().contains("Empty"))
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }

                // If there is a white piece in the way
                else if (board.getBoard().get(knightRow).get(knightCol).isWhite())
                {
                    potentialMoves.add(knightRow + "," + knightCol);
                }
            } 
            
            // Ask the king for permission to move there
            return board.getKing(false).removeIllegalMoves(row, column, potentialMoves, board); 
        }
        
        // Something bad happened
        return null;        
    }
}
