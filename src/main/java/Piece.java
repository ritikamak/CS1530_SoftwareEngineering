import java.util.*;
/* This is a generic Piece object. As we develop further, it will be a superclass to more specific piece types: kings, queens, knights, bishops, rooks, pawns. */
public abstract class Piece
{
	/* CONSTANTS */
	public final boolean BLACK = true;
	public final boolean WHITE = false;
	public final boolean USER = true;
	public final boolean COMP = false;
	
	/*a public enum for display colors */
	public enum DisplayColor {
		STANDARD, 
		BLUE, 
		GREEN,
		ORANGE, 
		RED, 
		YELLOW 
	};
	
	/* VARIABLES */
	public boolean gameColor; // a piece has a game color (the traditional black or white)
	public DisplayColor displayColor; // a piece has a display color (independent of their game color)
	public Square position; // a piece has a position on the board represented by a square
	public String name; // a piece has a name, e.g. pawn, rook, bishop, knight, queen, king
	public Player owner; //what player owns this piece?
	public boolean selected; //piece owned by human player is considered selected if its position is the same square that is currently selected
	
	/* Constructors */
	public Piece(Player o, String n, boolean gc, Square pos)
	{
		owner = o;
		name = n;
		gameColor = gc;
		position = pos;
		displayColor = DisplayColor.STANDARD;
		selected = false;
	}
	
	public Piece(String n, boolean gc, Square pos)
	{
		name = n;
		gameColor = gc;
		position = pos;
		displayColor = DisplayColor.STANDARD;
		selected = false;
	}
	
	/* METHODS */
	public abstract boolean movePiece(Square destination);

	public void toggleSelected()
	{
		selected = !selected;
	}
	
	/* GETTERS */
	
	public Player belongsTo()
	{
		return owner;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
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
	
	
	public void setOwner(Player p)
	{
		owner = p;
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
