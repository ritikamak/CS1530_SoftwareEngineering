import java.util.*;

/** 
* Each Square object represents a square on the chessboard grid. 
* This class forms the basis for communicating position in this application.
*
*/
public class Square
{
	/* VARIABLES */
	//column, horizontal (a-h)
	int file; 
	//row, vertical (1-8)
	int rank; 
	boolean occupied;
	Piece piece;
	boolean selected; //this is a variable for the GUI and input handler to determine a user's src square for movement and square highlighting

	/* CONSTRUCTORS */
	public Square(int f, int r) //file rank
	{
		file = f;
		rank = r;
		occupied = false;
		selected = false;
	}

	/* METHODS */
	public void occupySquare(Piece p)
	{
		piece = p;
		occupied = true;
	}

	public Piece evictSquare()
	{
		occupied = false;
		return piece;
	}

	public void toggleSelected()
	{
		selected = !selected;
	}
	
	/* GETTERS */
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
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public Piece getPiece()
	{
		return piece;
	}

	/* SETTERS */
	public void setOccupied(boolean o)
	{
		occupied = o;
	}

	public void setPiece(Piece p)
	{
		piece = p;
	}
	//equals
	public boolean equals (Square s){
		if (s.getFile() == file && s.getRank() == rank){
			return true;
		}
		else{
			return false;
		}
	}
	//toString
	public String toString()
	{
		String str = "" + ((char)(97 + file));
		str = str + (rank + 1);
		return str;
	}

}
