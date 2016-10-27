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
	ArrayList<Piece> pieces; // a player starts with 16 pieces

	/* CONSTRUCTORS */
	public Player(boolean t, boolean c)
	{
		type = t;
		color = c;
	}
	
	public Player(boolean t, boolean c, Board b)
	{
		type = t;
		color = c;
		//Piece constructor goes
		pieces = new ArrayList<Piece>();
		if(c == WHITE){
			//8 pawns
			pieces.add(new Pawn(color, b.getSquareAt(A, TWO)));
			pieces.add(new Pawn(color, b.getSquareAt(B, TWO)));
			pieces.add(new Pawn(color, b.getSquareAt(C, TWO)));
			pieces.add(new Pawn(color, b.getSquareAt(D, TWO)));
			pieces.add(new Pawn(color, b.getSquareAt(E, TWO)));
			pieces.add(new Pawn(color, b.getSquareAt(F, TWO)));
			pieces.add(new Pawn(color, b.getSquareAt(G, TWO)));
			pieces.add(new Pawn(color, b.getSquareAt(H, TWO)));
			//2 rooks
			pieces.add(new Rook(color, b.getSquareAt(A, ONE)));
			pieces.add(new Rook(color, b.getSquareAt(H, ONE)));
			//two knights
			pieces.add(new Knight(color, b.getSquareAt(B, ONE)));
			pieces.add(new Knight(color,b.getSquareAt(G, ONE)));
			//2 bishops
			pieces.add(new Bishop(color, b.getSquareAt(C, ONE)));
			pieces.add(new Bishop(color, b.getSquareAt(F, ONE)));
			//king and queen
			pieces.add(new Queen(color, b.getSquareAt(D, ONE)));
			pieces.add(new King(color, b.getSquareAt(E, ONE)));
		}
		else if (c == BLACK){
			pieces.add(new Pawn(color, b.getSquareAt(A, SEVEN)));
			pieces.add(new Pawn(color, b.getSquareAt(B, SEVEN)));
			pieces.add(new Pawn(color, b.getSquareAt(C, SEVEN)));
			pieces.add(new Pawn(color, b.getSquareAt(D, SEVEN)));
			pieces.add(new Pawn(color, b.getSquareAt(E, SEVEN)));
			pieces.add(new Pawn(color, b.getSquareAt(F, SEVEN)));
			pieces.add(new Pawn(color, b.getSquareAt(G, SEVEN)));
			pieces.add(new Pawn(color, b.getSquareAt(H, SEVEN)));
			//2 rooks
			pieces.add(new Rook(color, b.getSquareAt(A, EIGHT)));
			pieces.add(new Rook(color, b.getSquareAt(H, EIGHT)));
			//two knights
			pieces.add(new Knight(color, b.getSquareAt(B, EIGHT)));
			pieces.add(new Knight(color, b.getSquareAt(G, EIGHT)));
			//2 bishops
			pieces.add(new Bishop(color, b.getSquareAt(C, EIGHT)));
			pieces.add(new Bishop(color, b.getSquareAt(F, EIGHT)));
			//king and queen
			pieces.add(new Queen(color, b.getSquareAt(D, EIGHT)));
			pieces.add(new King(color, b.getSquareAt(E, EIGHT)));
		}
	}

	/* METHODS */

	// Getters
	public boolean getType()
	{
		return type;
	}

	public boolean getColor()
	{
		return color;
	}

	public ArrayList<Piece> getPieces()
	{
		return pieces;
	}

	public int piecesLeft()
	{
		return pieces.size();
	}
}
