package russell.john;

import com.jme3.asset.AssetManager;
import java.util.ArrayList;

public class King extends Piece
{       
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
    
    /*
    public static boolean InCheck(PieceLocation piece, BoardType board)
    {
        return false;
    }

    public static ArrayList<String> InCheck(PieceLocation currentPieceLocation, PieceLocation currentKingLocation, ArrayList<String> potentialMoves, BoardType board)
    {
        int currentPieceRow = currentPieceLocation.getRow();
        int currentPieceColumn = currentPieceLocation.getColumn();
        
        
        
        
        for (int i = 0; i < potentialMoves.size(); i++)
        {
            BoardType newBoard;
            int newPieceRow = Integer.parseInt(potentialMoves.get(i).split(",")[0]);
            int newPieceColumn = Integer.parseInt(potentialMoves.get(i).split(",")[1]);            
            newBoard = move(board, currentPieceRow, currentPieceColumn, newPieceRow, newPieceColumn);
            
            
        }
        
        
        
        return null;
    }
    
    private static BoardType move(BoardType board, int currentRow, int currentColumn, int newRow, int newColumn)
    {
        // we get the piece in the original location
        Piece piece = board.getBoard().get(currentRow).get(currentColumn);
        
        // we then replace it with an empty square
        // TODO - FUCK, what about castling??  A separate function will have to be made to handle that
        // TODO - damnit i knew passing in assetManager to each piece was going to bite me in the ass.  
        board.getBoard().get(currentRow).set(currentColumn, new Empty(Board.assetManager, true, (currentRow * 8 + currentColumn)));
        
        // we now replace the new position with the current piece
        board.getBoard().get(newRow).set(newColumn, piece);      
        
        return board;        
    }
     * */
     
}
