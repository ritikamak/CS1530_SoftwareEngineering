import java.util.*;
/* This is a generic Piece object. As we develop further, it will be a superclass to more specific piece types: kings, queens, knights, bishops, rooks, pawns. */ 
public class Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	public static final int H = 7;
	public static final int ONE = 0;
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;
	
	/* VARIABLES */
	boolean color; // a piece has a color
	Square position; // a piece has a position on the board represented by a square
	String name; // a piece has a name, e.g. pawn, rook, bishop, knight, queen, king
	int id; // i'm tentatively assining each piece an id unique within its color. This may be unnecessary.
	boolean taken; //this variable is also up-in-the-air, a taken piece could be simply removed from the Player object pieces arraylist... 
	
	/* CONSTRUCTORS */
	public Piece(boolean c, int i) //generic piece constructor
	{
		color = c;
		id = i;
		name = "generic piece";
	}
	
	/* METHODS */
	public void placePiece(Square destination)
	{
		destination.placePiece(this); //updates the square where the piece is being placed
		position = destination; //updates our piece where it is now located
	}
	
	public void movePiece(Square destination)  //this function is different from placePiece because it removes the piece from the position it originated from.
	{
		position.removePiece(); 
		this.placePiece(destination);
	}
	
	// Getters
	public boolean getColor()
	{
		return color;
	}
	
	public int getId()
	{
		return id;
	}
	
	// toString
	public String toString()
	{
		String c;
		String str;
		
		if(color == WHITE){
			c = "white";
		}
		
		else{
			c = "black";
		}
		
		str = c + " " + name + "(#" + id + ")";
		return str;
	}
	
}