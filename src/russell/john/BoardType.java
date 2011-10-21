package russell.john;

import java.util.ArrayList;

/**
 * The datastructure containing the pieces
 * @author John
 */
public class BoardType 
{
    public ArrayList<ArrayList<Piece>> board;
    
    BoardType()
    {
       board = new ArrayList<ArrayList<Piece>>();        
    }
    
    public ArrayList<ArrayList<Piece>> getBoard()
    {
        return board;
    }    
    
    public Piece getPiece(String name)
    {
        for (int i = 0; i < board.size(); i ++)
            for (int j = 0; j < board.get(i).size(); j++)
                if (board.get(i).get(j).getSpatial().getName().contains(name))
                    return board.get(i).get(j);
        return null;                    
    }
}
