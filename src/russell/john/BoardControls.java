package russell.john;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.ArrayList;

public class BoardControls 
{
    
    AssetManager assetManager;
    Node boardNode;
    Material whiteMaterial, blackMaterial, boardMaterial, emptyMaterial, selectedPieceMaterial, suggestedMovementMaterial;
    BoardType board;
    
    ArrayList<String> selectedPotentialMoves;
    Piece selectedPiece;
    Boolean selected = false;
    
    
    
    
    /**
     * Initializes the board by setting all pieces to blank
     */
    public BoardControls(AssetManager assetManager, Node boardNode, Material emptyMaterial, Material whiteMaterial, Material blackMaterial, Material selectedPieceMaterial, Material suggestedMovementMaterial)
    {
        this.assetManager = assetManager;
        this.boardNode = boardNode;
        this.emptyMaterial = emptyMaterial;
        this.whiteMaterial = whiteMaterial;
        this.blackMaterial = blackMaterial;
        this.selectedPieceMaterial = selectedPieceMaterial;
        this.suggestedMovementMaterial = suggestedMovementMaterial;
        board = new BoardType();
    }
    
    public void select(String name)
    {
       try
       {
            // Is there a piece already selected
            if (selected)
            {       
                // If so, deselct the last piece (and the potential move sets)
                deselectPotentialMoves();
                selected = false;
                
                                               
                // Check to see if the newly selected piece is in the potential move set.
                // If it is, we will move the piece
                if(isInPotentialMoveSet(name))
                {
                    Piece destinationPiece = board.getPiece(name);
                    // if it is, move it!
                    
                    // Check to see if its a king move
                    if (selectedPiece.getPieceType().contains("King"))
                    {
                        // If the move piece is 2 spaces to its right, its a castle kingside
                        if (destinationPiece.getColumn() == selectedPiece.getColumn() + 2)                        
                            castleMove(selectedPiece, destinationPiece);
                        
                        // If the move piece is 2 spaces to its left, its a castle queenside
                        else if (destinationPiece.getColumn() == selectedPiece.getColumn() - 2)                        
                            castleMove(selectedPiece, destinationPiece);    
                        
                        // It's a normal king move
                        else 
                            move(selectedPiece, destinationPiece);                        
                    }
                    
                    // If its not a king move
                    else
                        move(selectedPiece, destinationPiece);                    
                    
                    // No longer selected
                    selected = false;
                }
                
                // If it is not, we will assume the user is selecting a different piece other than what is in the move set!
                else
                {
                    selectedPiece = board.getPiece(name); 
                    // Select the new piece if it is not an empty                
                    if (!selectedPiece.getPieceType().contains("Empty"))
                    {
                        selectedPiece.getSpatial().setMaterial(selectedPieceMaterial);
                        
                        // sets selectedPotentialMoves
                        getLegalMoves(selectedPiece);
                        
                        // Set this piece to selected
                        selected = true;  
                    }                    
                }
            }
            
            // If not, select the new piece
            else
            {
                // Select the new piece if it is not an empty
                selectedPiece = board.getPiece(name);
                System.out.println(selectedPiece.getPieceType() + " " + selectedPiece.getRow() + "," + selectedPiece.getColumn());
                if (!selectedPiece.getPieceType().contains("Empty"))
                {
                    selectedPiece.getSpatial().setMaterial(selectedPieceMaterial);  
                    
                    // sets selectedPotentialMoves
                    getLegalMoves(selectedPiece);
                    
                    selected = true;
                }
            }    
       }
        
       catch (Exception e)
       {
            System.out.println(e.toString());
       }   
    }
    
    private boolean isInPotentialMoveSet(String name)
    {
        Piece validMovePiece = board.getPiece(name); 
        // Check to see if the next piece they selected is in the selected potential moves set
        for (int i = 0; i < selectedPotentialMoves.size(); i++)
        {
            // Check for castling
            if (selectedPotentialMoves.get(i).contains(King.CastleKing) || selectedPotentialMoves.get(i).contains(King.CastleQueen))
            {
                // Make sure the selected piece is a king.  This should always return true
                if (selectedPiece.getPieceType().contains("King"))
                {
                    // If the king is white
                    if(selectedPiece.isWhite())
                    {
                        // If the king is trying to castle on its king side
                        if (selectedPotentialMoves.get(i).contains(King.CastleKing))
                        {
                            // Check to see if the selected empty square is two spaces to the right of the king
                            if (validMovePiece.getRow() == 7 && validMovePiece.getColumn() == 6)
                                return true;
                        }
                        
                        // If the king is trying to castle on its queen side
                        else if (selectedPotentialMoves.get(i).contains(King.CastleQueen))
                        {
                            // Check to see if the selected mepty square is two spaces to the left of the king
                            if (validMovePiece.getRow() == 7 && validMovePiece.getColumn() == 2)
                                return true;
                        }                
                    }
                    // If the king is black
                    if (!selectedPiece.isWhite())
                    {
                        // If the king is trying to castle on its king side
                        if (selectedPotentialMoves.get(i).contains(King.CastleKing))
                        {
                            // Check to see if the selected empty square is two spaces to the right of the king
                            if (validMovePiece.getRow() == 0 && validMovePiece.getColumn() == 6)
                                return true;
                        }
                        
                        // If the king is trying to castle on its queen side
                        else if (selectedPotentialMoves.get(i).contains(King.CastleQueen))
                        {
                            // Check to see if the selected mepty square is two spaces to the left of the king
                            if (validMovePiece.getRow() == 0 && validMovePiece.getColumn() == 2)
                                return true;
                        }                         
                    }              
                }         
                    
            }
            
            // If there is no castling, just check the selected square to see if its in the move range
            else if (selectedPotentialMoves.get(i).split(",")[0].contains(Integer.toString(validMovePiece.getRow())) && selectedPotentialMoves.get(i).split(",")[1].contains(Integer.toString(validMovePiece.getColumn())))
            {                
                return true;
            }
        }

        return false;
    }
    
    private void getLegalMoves(Piece piece)
    {        
        selectedPotentialMoves = new ArrayList<String>();
        int row, col;        
        
        if (piece.getPieceType().contains("Pawn"))
        {                     
            Pawn pawn = (Pawn) piece;
            selectedPotentialMoves = pawn.getPotentialMoves(board);
            for (int i = 0; i < selectedPotentialMoves.size(); i++)
            {
                row = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }
                          
        }
        
        else if (piece.getPieceType().contains("Rook"))
        {
            Rook rook = (Rook) piece;
            selectedPotentialMoves = rook.getPotentialMoves(board);
            for (int i = 0; i < selectedPotentialMoves.size(); i++)
            {
                row = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }
            
        }
        
        else if (piece.getPieceType().contains("Knight"))
        {
            Knight knight = (Knight) piece;
            selectedPotentialMoves = knight.getPotentialMoves(board);
            for (int i = 0; i < selectedPotentialMoves.size(); i++)
            {
                row = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            } 
            
        }
        
        else if (piece.getPieceType().contains("Bishop"))
        {
            Bishop bishop = (Bishop) piece;
            selectedPotentialMoves = bishop.getPotentialMoves(board);
            for (int i = 0; i < selectedPotentialMoves.size(); i++)
            {
                row = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }           
        }
        
        else if (piece.getPieceType().contains("Queen"))
        {
            Queen queen = (Queen) piece;
            selectedPotentialMoves = queen.getPotentialMoves(board);
            for (int i = 0; i < selectedPotentialMoves.size(); i++)
            {
                row = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[1]);
                board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
            }
            
        }
        
        else if (piece.getPieceType().contains("King"))
        {
            King king = (King) piece;
            selectedPotentialMoves = king.getPotentialMoves(board);
            for (int i = 0; i < selectedPotentialMoves.size(); i++)
            {
                // check for castling
                if (selectedPotentialMoves.contains(King.CastleKing) || selectedPotentialMoves.contains(King.CastleQueen))
                {                                            
                    if (king.isWhite())  
                    {
                        if (selectedPotentialMoves.contains(King.CastleKing))                   
                            board.getBoard().get(7).get(6).getSpatial().setMaterial(selectedPieceMaterial);  
                        else if (selectedPotentialMoves.contains(King.CastleQueen))
                            board.getBoard().get(7).get(2).getSpatial().setMaterial(selectedPieceMaterial);
                    }  

                    else if (!king.isWhite())
                    {
                        if (selectedPotentialMoves.contains(King.CastleKing))                   
                            board.getBoard().get(0).get(6).getSpatial().setMaterial(selectedPieceMaterial);  
                        else if (selectedPotentialMoves.contains(King.CastleQueen))
                            board.getBoard().get(0).get(2).getSpatial().setMaterial(selectedPieceMaterial);
                    }                        
                }
                
                // No castle move, proceed as normal
                else
                {
                    row = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[0]);
                    col = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[1]);
                    board.getBoard().get(row).get(col).getSpatial().setMaterial(selectedPieceMaterial);
                }
            }
        }
        
        else
        {
            // probably clikced on an empty square.  this function should never have to deal with the "legal" "moves" of empty squares.  ERROR
        }
    }
    
    private void deselectPotentialMoves()
    {     
        if (selectedPiece.isWhite() && !selectedPiece.getPieceType().contains("Empty"))
            selectedPiece.getSpatial().setMaterial(whiteMaterial);
        else if (!selectedPiece.isWhite() && !selectedPiece.getPieceType().contains("Empty"))
            selectedPiece.getSpatial().setMaterial(blackMaterial);
        else if (selectedPiece.getPieceType().contains("Empty"))
            selectedPiece.getSpatial().setMaterial(emptyMaterial);
        
        int row, col;
        for (int i = 0; i < selectedPotentialMoves.size(); i++)
        {
            // Check for a castle move
            if (selectedPotentialMoves.get(i).contains(King.CastleKing) && selectedPotentialMoves.get(i).contains(King.CastleQueen))
            {   
                // Make sure the selected piece is a king.  This hsould alwyas return true
                if (selectedPiece.getPieceType().contains("King"))
                {
                    // Is the king white?
                    if (((King) selectedPiece).isWhite())
                    {
                        // Is the king castling on the right side?
                        if (selectedPotentialMoves.get(i).contains(King.CastleKing))
                        {
                            // Put the king back to white and the square 2 spaces to its right back to empty
                            selectedPiece.getSpatial().setMaterial(whiteMaterial);
                            board.getBoard().get(selectedPiece.getRow()).get(selectedPiece.getColumn() + 2).getSpatial().setMaterial(emptyMaterial);                            
                        }

                        else if (selectedPotentialMoves.get(i).contains(King.CastleQueen))
                        {
                            // Put the king back to white and the square 2 spaces to its left back to empty
                            selectedPiece.getSpatial().setMaterial(whiteMaterial);
                            board.getBoard().get(selectedPiece.getRow()).get(selectedPiece.getColumn() - 2).getSpatial().setMaterial(emptyMaterial);
                        }

                    }
                    
                    // If the king is black
                    else if (!((King) selectedPiece).isWhite())
                    {

                        if (selectedPotentialMoves.get(i).contains(King.CastleKing))
                        {
                            // Put the king back to white and the square 2 spaces to its right back to empty
                            selectedPiece.getSpatial().setMaterial(blackMaterial);
                            board.getBoard().get(selectedPiece.getRow()).get(selectedPiece.getColumn() + 2).getSpatial().setMaterial(emptyMaterial);                            
                        }

                        else if (selectedPotentialMoves.get(i).contains(King.CastleQueen))
                        {
                            // Put the king back to white and the square 2 spaces to its left back to empty
                            selectedPiece.getSpatial().setMaterial(blackMaterial);
                            board.getBoard().get(selectedPiece.getRow()).get(selectedPiece.getColumn() - 2).getSpatial().setMaterial(emptyMaterial);
                        }
                    }

                }
            }
            
            // There is no castling going on, just put it back to normal
            else
            {                       
                row = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[0]);
                col = Integer.parseInt(selectedPotentialMoves.get(i).split(",")[1]);
                if (board.getBoard().get(row).get(col).isWhite && !board.getBoard().get(row).get(col).getPieceType().contains("Empty"))
                    board.getBoard().get(row).get(col).getSpatial().setMaterial(whiteMaterial);
                else if (!board.getBoard().get(row).get(col).isWhite && !board.getBoard().get(row).get(col).getPieceType().contains("Empty"))
                    board.getBoard().get(row).get(col).getSpatial().setMaterial(blackMaterial);
                else if (board.getBoard().get(row).get(col).getPieceType().contains("Empty"))
                    board.getBoard().get(row).get(col).getSpatial().setMaterial(emptyMaterial);       
            }
        }        
        
    }
    
    // This fucntion will not be used for castling.
    private void move(Piece startPiece, Piece endPiece)
    { 
        int startRow = startPiece.getRow();
        int startCol = startPiece.getColumn();
        int endRow = endPiece.getRow();
        int endCol = endPiece.getColumn();
        
        
        // Update the location of the startPiece with the location of the endPiece
        startPiece.setRow(endRow);
        startPiece.setColumn(endCol);        

        // Copy the start piece to the end piece
        board.getBoard().get(endRow).set(endCol, startPiece);

        // Remove the start piece by replacing with an empty
        board.getBoard().get(startRow).set(startCol, new Empty(assetManager, true, startRow, startCol));

        // Detach the startPiece from the boardNode
        boardNode.detachChild(startPiece.getSpatial());

        // Detach the endPiece from the boardNode
        boardNode.detachChild(endPiece.getSpatial());       

        // Attach the empty piece to the boardnode
        boardNode.attachChild(board.getBoard().get(startRow).get(startCol).getSpatial());
        board.getBoard().get(startRow).get(startCol).getSpatial().setLocalTranslation(BoardConstants.vectors.get(startRow).get(startCol));
        board.getBoard().get(startRow).get(startCol).getSpatial().setMaterial(emptyMaterial);

        // Attach the the startPiece with the location of the endPiece to the boardNode
        boardNode.attachChild(board.getBoard().get(endRow).get(endCol).getSpatial());
        startPiece.getSpatial().setLocalTranslation(BoardConstants.vectors.get(endRow).get(endCol));           
    }
    
    private void castleMove(Piece startPiece, Piece endPiece)
    {
        // We will first proceed as normal by moving the king to the empty space and create a new empty space where the king was
        move(startPiece, endPiece);
        
        // Now we have to move the rook      
        if (startPiece.isWhite())
        {
            // Is it a castle kingside
            if (endPiece.getColumn() == 6)
            {
                Piece whiteKingsideRook = board.getBoard().get(7).get(7);                
                Piece destinationKingRookLocation = board.getBoard().get(7).get(5); 
                move (whiteKingsideRook, destinationKingRookLocation);
            }
            
            // Is it a castle queenside
            else if (endPiece.getColumn() == 2)
            {
                Piece whiteQueensideRook = board.getBoard().get(7).get(0);
                Piece destinationQueenRookLocation = board.getBoard().get(7).get(3);
                move (whiteQueensideRook, destinationQueenRookLocation);
            }
        }
        
        else if (!startPiece.isWhite())
        {
            // Is it a castle kingside
            if (endPiece.getColumn() == 6)
            {
                Piece blackKingsideRook = board.getBoard().get(0).get(7);                
                Piece destinationKingRookLocation = board.getBoard().get(0).get(5); 
                move (blackKingsideRook, destinationKingRookLocation);
            }
            
            // Is it a castle queenside
            else if (endPiece.getColumn() == 2)
            {
                Piece blackQueensideRook = board.getBoard().get(0).get(0);                
                Piece destinationKingRookLocation = board.getBoard().get(0).get(3); 
                move (blackQueensideRook, destinationKingRookLocation);
            }
        }    
    }
    
    public void setBoard()
    {
        BoardConstants.CreateBoard();
        ArrayList<Piece> temp;        
        // Set the entire board with empty pieces
        for (int i = 0; i < 8; i++)
        {
            temp = new ArrayList<Piece>();
            for (int j = 0; j < 8; j++)
            {                
                temp.add(new Empty(assetManager, true, i, j));
                temp.get(j).getSpatial().setMaterial(emptyMaterial);
                temp.get(j).getSpatial().setLocalTranslation(BoardConstants.vectors.get(i).get(j));
                boardNode.attachChild(temp.get(j).getSpatial());
            }
            board.getBoard().add(temp);
        }
        
         // set white pawns
        for (int i = 0; i < 8; i++)
        {
            board.getBoard().get(6).set(i, new Pawn(assetManager, true, 6, i));
            if (board.getBoard().get(6).get(i).getSpatial() != null)
            {               
                board.getBoard().get(6).get(i).getSpatial().setLocalTranslation(BoardConstants.vectors.get(6).get(i));
                board.getBoard().get(6).get(i).getSpatial().setMaterial(whiteMaterial);
                boardNode.detachChildNamed("Empty,true," + "6," + i);
                boardNode.attachChild(board.getBoard().get(6).get(i).getSpatial());
            }
        }
        
        // set black pawns
        for (int i = 0; i < 8; i++)
        {
            board.getBoard().get(1).set(i, new Pawn(assetManager, false, 1, i));
            if (board.getBoard().get(1).get(i).getSpatial() != null)
            {
               board.getBoard().get(1).get(i).getSpatial().setLocalTranslation(BoardConstants.vectors.get(1).get(i));
               board.getBoard().get(1).get(i).getSpatial().setMaterial(blackMaterial);
               boardNode.detachChildNamed("Empty,true," + "1," + i);
               boardNode.attachChild(board.getBoard().get(1).get(i).getSpatial());
            }
        }
        
        
        // set white roocks    
        board.getBoard().get(7).set(0, new Rook(assetManager, true, 7, 0));
        board.getBoard().get(7).get(0).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(0));
        board.getBoard().get(7).get(0).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,0");
        boardNode.attachChild(board.getBoard().get(7).get(0).getSpatial());
        
        board.getBoard().get(7).set(7, new Rook(assetManager, true, 7, 7));
        board.getBoard().get(7).get(7).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(7));
        board.getBoard().get(7).get(7).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,7");
        boardNode.attachChild(board.getBoard().get(7).get(7).getSpatial());
        
        // set black roocks
        board.getBoard().get(0).set(0, new Rook(assetManager, false, 0, 0));
        board.getBoard().get(0).get(0).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(0));
        board.getBoard().get(0).get(0).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,0");
        boardNode.attachChild(board.getBoard().get(0).get(0).getSpatial());
        
        board.getBoard().get(0).set(7, new Rook(assetManager, false, 0, 7));
        board.getBoard().get(0).get(7).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(7));
        board.getBoard().get(0).get(7).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,7");
        boardNode.attachChild(board.getBoard().get(0).get(7).getSpatial());
        
        
        // set white knights        
        board.getBoard().get(7).set(1, new Knight(assetManager, true, 7, 1));
        board.getBoard().get(7).get(1).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(1));
        board.getBoard().get(7).get(1).getSpatial().setMaterial(whiteMaterial);        
        boardNode.detachChildNamed("Empty,true,7,1");
        boardNode.attachChild(board.getBoard().get(7).get(1).getSpatial());
        
        board.getBoard().get(7).set(6, new Knight(assetManager, true, 7, 6));
        board.getBoard().get(7).get(6).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(6));
        board.getBoard().get(7).get(6).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,6");
        boardNode.attachChild(board.getBoard().get(7).get(6).getSpatial());
        
        // set black knights
        Quaternion YAW180 = new Quaternion().fromAngleAxis(FastMath.PI, new Vector3f(0,1,0));
        board.getBoard().get(0).set(1, new Knight(assetManager, false, 0, 1));
        board.getBoard().get(0).get(1).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(1));
        board.getBoard().get(0).get(1).getSpatial().setLocalRotation(YAW180);
        board.getBoard().get(0).get(1).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,1");
        boardNode.attachChild(board.getBoard().get(0).get(1).getSpatial());
        
        board.getBoard().get(0).set(6, new Knight(assetManager, false, 0, 6));
        board.getBoard().get(0).get(6).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(6));
        board.getBoard().get(0).get(6).getSpatial().setLocalRotation(YAW180);
        board.getBoard().get(0).get(6).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,6");
        boardNode.attachChild(board.getBoard().get(0).get(6).getSpatial());       
        
        // set white bishops
        board.getBoard().get(7).set(2, new Bishop(assetManager, true, 7, 2));
        board.getBoard().get(7).get(2).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(2));
        board.getBoard().get(7).get(2).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,2");
        boardNode.attachChild(board.getBoard().get(7).get(2).getSpatial());
        
        board.getBoard().get(7).set(5, new Bishop(assetManager, true, 7, 5));
        board.getBoard().get(7).get(5).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(5));
        board.getBoard().get(7).get(5).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,5");
        boardNode.attachChild(board.getBoard().get(7).get(5).getSpatial());
        
        // set black bishops
        board.getBoard().get(0).set(2, new Bishop(assetManager, false, 0, 2));
        board.getBoard().get(0).get(2).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(2));
        board.getBoard().get(0).get(2).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,2");
        boardNode.attachChild(board.getBoard().get(0).get(2).getSpatial());
        
        board.getBoard().get(0).set(5, new Bishop(assetManager, false, 0, 5));
        board.getBoard().get(0).get(5).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(5));
        board.getBoard().get(0).get(5).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,5");
        boardNode.attachChild(board.getBoard().get(0).get(5).getSpatial());         
        
        
        // set white queen
        board.getBoard().get(7).set(3, new Queen(assetManager, true, 7, 3));
        board.getBoard().get(7).get(3).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(3));
        board.getBoard().get(7).get(3).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,3");
        boardNode.attachChild(board.getBoard().get(7).get(3).getSpatial());
        
        // set black queen
        board.getBoard().get(0).set(3, new Queen(assetManager, false, 0, 3));
        board.getBoard().get(0).get(3).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(3));
        board.getBoard().get(0).get(3).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,3");
        boardNode.attachChild(board.getBoard().get(0).get(3).getSpatial());
        
        // set white king
        board.getBoard().get(7).set(4, new King(assetManager, true, 7, 4));
        board.getBoard().get(7).get(4).getSpatial().setLocalTranslation(BoardConstants.vectors.get(7).get(4));
        board.getBoard().get(7).get(4).getSpatial().setMaterial(whiteMaterial);
        boardNode.detachChildNamed("Empty,true,7,4");
        boardNode.attachChild(board.getBoard().get(7).get(4).getSpatial());
        
        // set black kings
        board.getBoard().get(0).set(4, new King(assetManager, false, 0, 4));
        board.getBoard().get(0).get(4).getSpatial().setLocalTranslation(BoardConstants.vectors.get(0).get(4));
        board.getBoard().get(0).get(4).getSpatial().setMaterial(blackMaterial);
        boardNode.detachChildNamed("Empty,true,0,4");
        boardNode.attachChild(board.getBoard().get(0).get(4).getSpatial());    
        
        // rotate the board node so white is on bottom
        boardNode.setLocalRotation(YAW180);
        
    }
    
}
