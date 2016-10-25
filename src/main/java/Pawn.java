import java.util.*;
public class Pawn extends Piece
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

	public Pawn (boolean color, Square position){
		this.color = color;
		this.position = position;
		name = "Pawn";
	}
	public Pawn (boolean color){
		this.color = color;
		name = "Pawn";
	}

	/* METHODS */
	public boolean movePiece(Square destination){
		int p_file = position.getFile();
		int p_rank = position.getRank();
		int d_file = destination.getFile();
		int d_rank = destination.getRank();
		//movement
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		if(d_file < 0 || d_rank < 0){
			return false;
		}
		if(color == WHITE){
			//movment for first row
			if(d_rank == 4 && p_rank == 2){
				return true;
			}
			//any row
			if(d_file == p_file && d_rank == p_rank++){
				return true;
			}
			//capture
			if(destination.isOccupied()){
				if(d_file == p_file+1 && d_rank == p_rank++){
					return true;
				}
				if(d_file == p_file-1 && d_rank == p_rank++){
					return true;
				}
			}
			//TODO: EN PASSENT
		}
		else if (color == BLACK){
			//movment for first row
			if(d_rank == 5 && p_rank == 7){
				return true;
			}
			//any row
			if(d_file == p_file && d_rank == p_rank--){
				return true;
			}
			if(destination.isOccupied()){
				if(d_file == p_file+1 && d_rank == p_rank--){
					return true;
				}
				if(d_file == p_file-1 && d_rank == p_rank--){
					return true;
				}
			}
			//TODO: EN PASSENT
		}
		//TODO PROMOTION
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
