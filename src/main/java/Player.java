import java.util.*;
/* A player is an object created within a Game object. Each player object holds an ArrayList of Piece objects representing their pieces*/
public class Player
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
	boolean type; // a player is either a user or a computer
	boolean color; // a player controls either black or white
	ArrayList<Piece> pieces; // a player starts with 16 pieces
	
	/* CONSTRUCTORS */
	public Player(boolean t, boolean c)
	{
		type = t;
		color = c;
		pieces = new ArrayList<Piece>();
		for(int i = 0; i < 16; i++){
			pieces.add(new Piece(color, i));
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