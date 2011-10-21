/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package russell.john;

import com.jme3.asset.AssetManager;

/**
 *
 * @author John
 */
public class Empty extends Piece
{
    public Empty()
    {
    }
    
    public Empty(AssetManager assetManager, Boolean isWhite, int row, int col)
    {
       this.assetManager = assetManager;
       this.isWhite = isWhite;
       this.row = row;
       this.col = col; 
       
       this.piece = this.assetManager.loadModel("Models/Empty.j3o");  
       this.piece.setName("Empty," + this.isWhite + "," + this.row + "," + this.col);
       this.pieceType = "Empty";
        
    }
    
}
