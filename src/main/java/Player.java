import java.util.*;
/* A player is an object created within a Game object. Each player object holds an ArrayList of Piece objects representing their pieces*/
public class Player
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;
	//Files
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	public static final int H = 7;
	//Ranks
	public static final int ONE = 0;
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;

	/* VARIABLES */
	boolean type; // a player is either a user or a computer
	boolean color; // a player controls either black or white
	boolean hasSelectedSquare; //a human player can have a square selected (COMP this is always false)
	Square selectedSquare; //we store that square here
	boolean hasSelectedPiece; //if the human player owns a piece on the selected square, hasSelectedPiece == true
	Piece selectedPiece;
	Board board;
	ArrayList<Piece> pieces; // a player starts with 16 pieces
	ArrayList<Piece> captured_pieces; //a player's captured pieces
	boolean inCheck; //a player can be in check
	Game game; //a player is involved in a current game
	King myKing; // the player's king(for check functions)

	/* CONSTRUCTORS */
	public Player(boolean t, boolean c)
	{
		type = t;
		color = c;
		hasSelectedSquare = false;
		hasSelectedPiece = false;
		inCheck = false;
	}

	public Player(boolean t, boolean c, Board b)
	{
		type = t;
		color = c;
		board = b;
		hasSelectedSquare = false;
		hasSelectedPiece = false;
		//Piece constructor goes
		pieces = new ArrayList<Piece>();
		captured_pieces = new ArrayList<Piece>();
		if(c == WHITE){
			//8 pawns
			pieces.add(new Pawn(this, color, b.getSquareAt(A, TWO)));
			pieces.add(new Pawn(this,color, b.getSquareAt(B, TWO)));
			pieces.add(new Pawn(this,color, b.getSquareAt(C, TWO)));
			pieces.add(new Pawn(this,color, b.getSquareAt(D, TWO)));
			pieces.add(new Pawn(this,color, b.getSquareAt(E, TWO)));
			pieces.add(new Pawn(this,color, b.getSquareAt(F, TWO)));
			pieces.add(new Pawn(this,color, b.getSquareAt(G, TWO)));
			pieces.add(new Pawn(this,color, b.getSquareAt(H, TWO)));
			//2 rooks
			pieces.add(new Rook(this, color, b.getSquareAt(A, ONE)));
			pieces.add(new Rook(this, color, b.getSquareAt(H, ONE)));
			//two knights
			pieces.add(new Knight(this, color, b.getSquareAt(B, ONE)));
			pieces.add(new Knight(this, color, b.getSquareAt(G, ONE)));
			//2 bishops
			pieces.add(new Bishop(this, color, b.getSquareAt(C, ONE)));
			pieces.add(new Bishop(this, color, b.getSquareAt(F, ONE)));
			//king and queen
			pieces.add(new Queen(this, color, b.getSquareAt(D, ONE)));
			myKing = new King(this, color, b.getSquareAt(E, ONE));
			pieces.add(myKing);
		}
		else if (c == BLACK){
			pieces.add(new Pawn(this, color, b.getSquareAt(A, SEVEN)));
			pieces.add(new Pawn(this, color, b.getSquareAt(B, SEVEN)));
			pieces.add(new Pawn(this, color, b.getSquareAt(C, SEVEN)));
			pieces.add(new Pawn(this, color, b.getSquareAt(D, SEVEN)));
			pieces.add(new Pawn(this, color, b.getSquareAt(E, SEVEN)));
			pieces.add(new Pawn(this, color, b.getSquareAt(F, SEVEN)));
			pieces.add(new Pawn(this, color, b.getSquareAt(G, SEVEN)));
			pieces.add(new Pawn(this, color, b.getSquareAt(H, SEVEN)));
			//2 rooks
			pieces.add(new Rook(this, color, b.getSquareAt(A, EIGHT)));
			pieces.add(new Rook(this, color, b.getSquareAt(H, EIGHT)));
			//two knights
			pieces.add(new Knight(this, color, b.getSquareAt(B, EIGHT)));
			pieces.add(new Knight(this, color, b.getSquareAt(G, EIGHT)));
			//2 bishops
			pieces.add(new Bishop(this, color, b.getSquareAt(C, EIGHT)));
			pieces.add(new Bishop(this, color, b.getSquareAt(F, EIGHT)));
			//king and queen
			pieces.add(new Queen(this, color, b.getSquareAt(D, EIGHT)));
			myKing = new King(this, color, b.getSquareAt(E, EIGHT));
			pieces.add(myKing);
		}
		inCheck = false;
	}

	public Player(boolean t, boolean c, Game g)
	{
		type = t;
		color = c;
		game = g;
		board = g.getBoard();
		hasSelectedSquare = false;
		hasSelectedPiece = false;
		//Piece constructor goes
		pieces = new ArrayList<Piece>();
		captured_pieces = new ArrayList<Piece>();
		if(c == WHITE){
			//8 pawns
			pieces.add(new Pawn(this, color, board.getSquareAt(A, TWO)));
			pieces.add(new Pawn(this,color, board.getSquareAt(B, TWO)));
			pieces.add(new Pawn(this,color, board.getSquareAt(C, TWO)));
			pieces.add(new Pawn(this,color, board.getSquareAt(D, TWO)));
			pieces.add(new Pawn(this,color, board.getSquareAt(E, TWO)));
			pieces.add(new Pawn(this,color, board.getSquareAt(F, TWO)));
			pieces.add(new Pawn(this,color, board.getSquareAt(G, TWO)));
			pieces.add(new Pawn(this,color, board.getSquareAt(H, TWO)));
			//2 rooks
			pieces.add(new Rook(this, color, board.getSquareAt(A, ONE)));
			pieces.add(new Rook(this, color, board.getSquareAt(H, ONE)));
			//two knights
			pieces.add(new Knight(this, color, board.getSquareAt(B, ONE)));
			pieces.add(new Knight(this, color, board.getSquareAt(G, ONE)));
			//2 bishops
			pieces.add(new Bishop(this, color, board.getSquareAt(C, ONE)));
			pieces.add(new Bishop(this, color, board.getSquareAt(F, ONE)));
			//king and queen
			pieces.add(new Queen(this, color, board.getSquareAt(D, ONE)));
			//pieces.add(new King(this, color, board.getSquareAt(E, ONE)));
			myKing = new King(this, color, board.getSquareAt(E, ONE));
			pieces.add(myKing);
		}
		else if (c == BLACK){
			pieces.add(new Pawn(this, color, board.getSquareAt(A, SEVEN)));
			pieces.add(new Pawn(this, color, board.getSquareAt(B, SEVEN)));
			pieces.add(new Pawn(this, color, board.getSquareAt(C, SEVEN)));
			pieces.add(new Pawn(this, color, board.getSquareAt(D, SEVEN)));
			pieces.add(new Pawn(this, color, board.getSquareAt(E, SEVEN)));
			pieces.add(new Pawn(this, color, board.getSquareAt(F, SEVEN)));
			pieces.add(new Pawn(this, color, board.getSquareAt(G, SEVEN)));
			pieces.add(new Pawn(this, color, board.getSquareAt(H, SEVEN)));
			//2 rooks
			pieces.add(new Rook(this, color, board.getSquareAt(A, EIGHT)));
			pieces.add(new Rook(this, color, board.getSquareAt(H, EIGHT)));
			//two knights
			pieces.add(new Knight(this, color, board.getSquareAt(B, EIGHT)));
			pieces.add(new Knight(this, color, board.getSquareAt(G, EIGHT)));
			//2 bishops
			pieces.add(new Bishop(this, color, board.getSquareAt(C, EIGHT)));
			pieces.add(new Bishop(this, color, board.getSquareAt(F, EIGHT)));
			//king and queen
			pieces.add(new Queen(this, color, board.getSquareAt(D, EIGHT)));
			//pieces.add(new King(this, color, board.getSquareAt(E, EIGHT)));
			myKing = new King(this, color, board.getSquareAt(E, EIGHT));
			pieces.add(myKing);
		}
		inCheck = false;
	}

	/* METHODS */


	public boolean hasSelectedSquare()
	{
		return hasSelectedSquare;
	}

	public boolean hasSelectedPiece()
	{
		return hasSelectedPiece;
	}

	public Square getSelected()
	{
		return selectedSquare;
	}

	public ArrayList<Piece> getPieces()
	{
		return pieces;
	}

	public void takePiece(Piece captured)
	{
		pieces.remove(captured);
		captured_pieces.add(captured);
	}

	public void returnPiece(Piece returned)
	{
		captured_pieces.remove(returned);
		pieces.add(returned);
	}

	public ArrayList<Piece> getCapturedPieces()
	{
		return captured_pieces;
	}

	public int piecesLeft()
	{
		return pieces.size();
	}

	public boolean isInCheck()
	{
		Player opponent;
		ArrayList<Piece> enemyPieces;
		Piece enemyPiece;
		int i;
		boolean captured;
		Square myKingSquare;

		myKingSquare = myKing.getPosition();
		opponent = game.getOpponent(type);
		enemyPieces = opponent.getPieces();
		for(i = 0; i < enemyPieces.size(); i++){
			enemyPiece = enemyPieces.get(i);
			try{
				captured = enemyPiece.move(board, myKingSquare);
				if(captured){
			
					return true;
				}
				
			}
			catch(MoveException e){
				//if move exception is thrown, it means this enemy piece does not have the king in check. move along
				
			}
		}
		//if all enemy pieces can't capture king it is definitely not in check!
		return false;
	}
	
	public void toggleCheck()
	{
		inCheck = !inCheck;
		if(inCheck){
			myKing.setCheck();
		}
		else{
			myKing.unsetCheck();
		}
	}

	// Getters
	public boolean getType()
	{
		return type;
	}

	public boolean getColor()
	{
		return color;
	}

	public Board getBoard()
	{
		return board;
	}

	public King getKing()
	{
		return myKing;
	}

	/* setters */
	public void setSelected(Square s)
	{
		if(hasSelectedSquare){
			unsetSelected();
		}
		selectedSquare = s;
		selectedSquare.toggleSelected();
		hasSelectedSquare = true;
		if(selectedSquare.isOccupied() && selectedSquare.getPiece().belongsTo() == this){
			hasSelectedPiece = true;
			selectedPiece = selectedSquare.getPiece();
			selectedPiece.toggleSelected();
		}
		else{
			hasSelectedPiece = false;
		}
	}

	public void unsetSelected()
	{
		if(hasSelectedSquare){
			selectedSquare.toggleSelected();
			if(hasSelectedPiece){
				selectedPiece.toggleSelected();
			}
		}
		hasSelectedSquare = false;
		hasSelectedPiece = false;
	}
}
