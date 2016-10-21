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
		//Piece constructor goes
		pieces = new ArrayList<Piece>();
		if(c == WHITE){
			pieces.add(new Pawn(color, new Square(A, TWO)));
			pieces.add(new Pawn(color, new Square(B, TWO)));
			pieces.add(new Pawn(color, new Square(C, TWO)));
			pieces.add(new Pawn(color, new Square(D, TWO)));
			pieces.add(new Pawn(color, new Square(E, TWO)));
			pieces.add(new Pawn(color, new Square(F, TWO)));
			pieces.add(new Pawn(color, new Square(G, TWO)));
			pieces.add(new Pawn(color, new Square(H, TWO)));
			//2 rooks
			pieces.add(new Rook(color, new Square(A, ONE)));
			pieces.add(new Rook(color, new Square(H, ONE)));
			//two knights
			pieces.add(new Knight(color, new Square(B, ONE)));
			pieces.add(new Knight(color, new Square(G, ONE)));
			//2 bishops
			pieces.add(new Bishop(color, new Square(C, ONE)));
			pieces.add(new Bishop(color, new Square(F, ONE)));
			//king and queen
			pieces.add(new Queen(color, new Square(D, ONE)));
			pieces.add(new King(color, new Square(E, ONE)));
		}
		else if (c == BLACK){
			pieces.add(new Pawn(color, new Square(A, SEVEN)));
			pieces.add(new Pawn(color, new Square(B, SEVEN)));
			pieces.add(new Pawn(color, new Square(C, SEVEN)));
			pieces.add(new Pawn(color, new Square(D, SEVEN)));
			pieces.add(new Pawn(color, new Square(E, SEVEN)));
			pieces.add(new Pawn(color, new Square(F, SEVEN)));
			pieces.add(new Pawn(color, new Square(G, SEVEN)));
			pieces.add(new Pawn(color, new Square(H, SEVEN)));
			//2 rooks
			pieces.add(new Rook(color, new Square(A, EIGHT)));
			pieces.add(new Rook(color, new Square(H, EIGHT)));
			//two knights
			pieces.add(new Knight(color, new Square(B, EIGHT)));
			pieces.add(new Knight(color, new Square(G, EIGHT)));
			//2 bishops
			pieces.add(new Bishop(color, new Square(C, EIGHT)));
			pieces.add(new Bishop(color, new Square(F, EIGHT)));
			//king and queen
			pieces.add(new Queen(color, new Square(D, EIGHT)));
			pieces.add(new King(color, new Square(E, EIGHT)));
		}
		//8 pawns



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
