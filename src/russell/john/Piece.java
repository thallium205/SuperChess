package russell.john;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import java.util.ArrayList;

public class Piece
{      
    protected AssetManager assetManager;       
    protected Spatial piece;
    protected boolean isWhite;
    protected int row, col;   
    protected String pieceType;
    
    public Piece()
    {
        this.pieceType = "";
    }
    public Piece(AssetManager assetManager, Boolean isWhite, int row, int col)
    {
        this.pieceType = "";
        this.assetManager = assetManager;
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
    }   
    
    public Spatial getSpatial()
    {        
        return piece;
    }
    
    public String getPieceType()
    {
        return pieceType;
    }
    
    public boolean isWhite()
    {
        return isWhite();
    }

    public int getRow()
    {
        return row;
    }
    
    public void setRow(int row)
    {
        this.row = row;
    }
    
    public int getColumn()
    {
        return col;
    }
    
    public void setColumn(int column)
    {
        this.col = column;
    }
    
    
    
}
