import java.util.*;
public class Bishop extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */

	/*CONSTRUCTORS*/

	public Bishop (boolean color, Square position){
		this.gameColor = color;
		this.position = position;
		name = "Bishop";
		this.displayColor = displayColor.STANDARD;
	}
	public Bishop (boolean color){
		this.gameColor = color;
		name = "Bishop";
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
		int slope = (d_rank-p_rank)/(d_file-p_file);
		//check if in bounds
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		else if(d_file < 0 || d_rank < 0){
			return false;
		}
		//check if horizontal move is correct, slope must equal one
		else if(slope == 1 || slope == -1){
			position = destination;
			return true;
		}
		return false;
	}
}