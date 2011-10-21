package russell.john;

import com.jme3.asset.AssetManager;

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
}
