import java.util.*;
/* Each Square object represents a square on the chessboard grid. A Square may contain a Piece object */ 
public class Square
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
	int file;
	int rank;
	boolean occupied;
	Piece piece;
	
	/* CONSTRUCTORS */
	public Square(int f, int r)
	{
		file = f;
		rank = r;
		occupied = false;
	}
	
	/* METHODS */
	public void placePiece(Piece p)
	{
		piece = p;
		occupied = true;
	}
	
	public Piece removePiece() 
	{
		occupied = false;
		return piece;
	}
	
	//Getters
	public int getFile()
	{
		return file;
	}
	
	public int getRank()
	{
		return rank;
	}
	
	public boolean isOccupied()
	{
		return occupied;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	//Setters
	public void setOccupied(boolean o)
	{
		occupied = o;
	}
	
	public void setPiece(Piece p)
	{
		piece = p;
	}
	
	//toString
	public String toString()
	{
		String str = "" + ((char)(97 + file));
		str = str + (rank + 1);
		return str;
	}

}