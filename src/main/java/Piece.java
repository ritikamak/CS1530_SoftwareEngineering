import java.util.*;
/* This is a generic Piece object. As we develop further, it will be a superclass to more specific piece types: kings, queens, knights, bishops, rooks, pawns. */
public abstract class Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */
	public boolean color; // a piece has a color
	public Square position; // a piece has a position on the board represented by a square
	public String name; // a piece has a name, e.g. pawn, rook, bishop, knight, queen, king

	/* METHODS */
	public abstract boolean movePiece(Square destination);

	/* GETTERS */
	public boolean getColor()
	{
		return color;
	}

	public Square getPosition()
	{
		return position;
	}

	public String getName()
	{
		return name;
	}

	/* SETTERS */
	public void setPosition(Square position)
	{
		this.position = position;
	}
	
	public void setColor(boolean color)
	{
		this.color = color;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/* toString */
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

		str = c + " " + name;
		return str;
	}

}
