import java.util.*;
/* This is a generic Piece object. As we develop further, it will be a superclass to more specific piece types: kings, queens, knights, bishops, rooks, pawns. */
public abstract class Piece
{
	/* CONSTANTS */
	public final boolean BLACK = true;
	public final boolean WHITE = false;
	public final boolean USER = true;
	public final boolean COMP = false;
	
	/* VARIABLES */
	public boolean gameColor; // a piece has a game color (the traditional black or white)
	public DisplayColor displayColor; // a piece has a display color (independent of their game color)
	public Square position; // a piece has a position on the board represented by a square
	public String name; // a piece has a name, e.g. pawn, rook, bishop, knight, queen, king

	/* METHODS */
	public abstract boolean movePiece(Square destination);

	/* GETTERS */
	public boolean getColor()
	{
		return gameColor;
	}
	
	public DisplayColor getDisplayColor()
	{
		return displayColor;
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
	
	public void setColor(boolean gameColor)
	{
		this.gameColor = gameColor;
	}

	public void setDisplayColor(DisplayColor displayColor)
	{
		this.displayColor = displayColor;
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

		if(gameColor == WHITE){
			c = "white";
		}

		else{
			c = "black";
		}
		
		str = c + " " + name;
		return str;
	}

}
