import java.util.*;
/* This is a generic Piece object. As we develop further, it will be a superclass to more specific piece types: kings, queens, knights, bishops, rooks, pawns. */
public abstract class Piece implements Move
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
	
	/*a public enum for piece type (valuable for gamescribe)*/
	public enum PieceType {
		PAWN,
		ROOK,
		KNIGHT,
		BISHOP,
		QUEEN,
		KING,
		GENERIC
	};
	
	/* VARIABLES */
	public boolean gameColor; // a piece has a game color (the traditional black or white)
	public DisplayColor displayColor; // a piece has a display color (independent of their game color)
	public Square position; // a piece has a position on the board represented by a square
	public String name; // a piece has a name, e.g. pawn, rook, bishop, knight, queen, king
	public PieceType type; //a piece has a piece type (used to quickly determine type of piece)
	public Player owner; //what player owns this piece?
	public boolean selected; //piece owned by human player is considered selected if its position is the same square that is currently selected
	public boolean hasMoved;// this variable tracks whether or not a piece has moved this game. primarily used by pawns and castling
	
	/* Constructors */
	public Piece(Player o, String n, boolean gc, Square pos)
	{
		owner = o;
		name = n;
		gameColor = gc;
		position = pos;
		displayColor = DisplayColor.STANDARD;
		type = PieceType.GENERIC;
		selected = false;
		hasMoved = false;
	}
	
	public Piece(String n, boolean gc, Square pos)
	{
		name = n;
		gameColor = gc;
		position = pos;
		displayColor = DisplayColor.STANDARD;
		type = PieceType.GENERIC;
		selected = false;
		hasMoved = false;
	}
	
	/* METHODS */

	public abstract boolean move(Board board, Square dest) throws MoveException;
	
	//this "captures" the piece, erasing it from the board and from its owner's array list
	public void capture()
	{
		owner.takePiece(this.getPosition().evictSquare());
	}
	
	public void toggleSelected()
	{
		selected = !selected;
	}
	
	public void moved()
	{
		hasMoved = true;
	}
	
	/* GETTERS */
	
	//this is a default function for a pathObstructed function. 
	public boolean pathObstructed(Board board, MoveTemplate mt)
	{
		Square path[];
		boolean friendlyColor;
		Piece captureTarget;
		Square dest;
		int i;
		
		friendlyColor = this.getColor();
		path = mt.getPath(board, this.getPosition());
		dest = path[path.length - 1];
		if(path.length > 1){
		//scan through path to see if all squares before the destination square are clear of obstructions
			i = 0;
			while(i < (path.length - 1)){
				if(path[i].isOccupied()){
					return true;
				}
				i++;
			}
		}
		//check destination to see if it has a friendly piece or a capture target
		if(dest.isOccupied()){
		//if a piece is in the destination square, we need to check if it is enemy or friendly
			captureTarget = dest.getPiece();
			//if it is not the same color, path is considered unobstructed (it results in a capture)
			if(captureTarget.getColor() != friendlyColor){
				return false;
			}
			//if we found the same color, thats a friendly and we can't capture it! 
			else{
			//so the path is considered obstructed
				return true;
			}
		}
		//if nothing is in the destination square, then it is considered obstructed
		else{
			return false;
		}
	}
	
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
	
	public Player getOwner()
	{
		return owner;
	}
	
	public PieceType getPieceType()
	{
		return type;
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
