package russell.john;

import com.jme3.asset.AssetManager;

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
}
