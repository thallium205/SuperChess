package russell.john;

import com.jme3.asset.AssetManager;

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
}
