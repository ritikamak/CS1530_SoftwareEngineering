import java.util.*;
public class Queen extends Piece
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

	/*CONSTRUCTORS*/

	public Queen (boolean color, Square position){
		this.color = color;
		this.position = position;
		name = "Queen";
	}
	public Queen (boolean color){
		this.color = color;
		name = "Queen";
	}

	/* METHODS */
	/**
	@param Square destiation is the location the piece will move to
	@return returns true is the move is valid, false if otherwise
	*/
	public boolean movePiece(Square destination){
		int p_file = position.getFile();
		int p_rank = position.getRank();
		int d_file = destination.getFile();
		int d_rank = destination.getRank();
		//check bounds
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		else if(d_file < 0 || d_rank < 0){
			return false;
		}
		//check if horizontal move
		else if(d_file == p_file){
			position = destination;
			return true;
		}
		//check if vertical move
		else if(d_rank == p_rank){
			position = destination;
			return true;
		}
		//check if diagonal move
		else{
			//slope calculation must be in here otherwise horizontal move would result in division by zero
			int slope = (d_rank-p_rank)/(d_file-p_file);
			if(slope == 1 || slope == -1){
				position = destination;
				return true;
			}
		}
		return false;
	}

	/* GETTERS */
	public boolean getColor()
	{
		return color;
	}

	public Square getPosition(){
		return position;
	}

	public String getName(){
		return name;
	}

	/* SETTERS */
	public void setPosition(Square position ){
		this.position = position;
	}
	public void setColor(boolean color){
		this.color = color;
	}

	public void setName(String name){
		this.name = name;
	}

	/* toString */
	public String toString(){
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
