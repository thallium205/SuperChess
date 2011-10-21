package russell.john;

import com.jme3.asset.AssetManager;

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
}
