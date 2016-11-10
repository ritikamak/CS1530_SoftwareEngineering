import java.util.*;
public class Rook extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */

	/*CONSTRUCTORS*/

	public Rook (boolean color, Square position){
		this.gameColor = color;
		this.position = position;
		name = "Rook";
		this.displayColor = displayColor.STANDARD;
	}
	
	public Rook (boolean color){
		this.gameColor = color;
		name = "Rook";
		this.displayColor = displayColor.STANDARD;
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
		return false;
	}
}
