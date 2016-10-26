import java.util.*;
public class Knight extends Piece
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

	public Knight (boolean color, Square position){
		this.color = color;
		this.position = position;
		name = "Knight";
	}
	public Knight (boolean color){
		this.color = color;
		name = "Knight";
	}

	/* METHODS */
	public boolean movePiece(Square destination){
		int p_file = position.getFile();
		int p_rank = position.getRank();
		int d_file = destination.getFile();
		int d_rank = destination.getRank();
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		if(d_file < 0 || d_rank < 0){
			return false;
		}
		if(d_file == (p_file-1) && d_rank == (p_rank+2)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank+2)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file+2) && d_rank == (p_rank+1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file+2) && d_rank == (p_rank-1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank-2)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank-2)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file-2) && d_rank == (p_rank-1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file-2) && d_rank == (p_rank+1)){
			position = destination;
			return true;
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
