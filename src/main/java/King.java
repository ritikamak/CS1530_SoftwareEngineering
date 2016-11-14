import java.util.*;
public class King extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */
	
	/*CONSTRUCTORS*/

	public King (Player owner, boolean gameColor, Square position){
		super(owner, "King", gameColor, position);
	}
	
	public King (boolean gameColor, Square position){
		super("King", gameColor, position);
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
		Piece capture;
		
		//USE FOR TESTING ONLY
		//check bounds
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		else if(d_file < 0 || d_rank < 0){
			return false;
		}
		//check all eight valid moves
		else if(d_file == (p_file+1) && d_rank == (p_rank+1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank-1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file) && d_rank == (p_rank-1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank-1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank+1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file) && d_rank == (p_rank+1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		//castling
		if(gameColor == WHITE && p_file == 4 && p_rank == 0){
			if(d_file == 2 && d_rank == p_rank){
				return false; //need to work on castling, returns false in the meantime
			}
			else if(d_file == 6 && d_rank == p_rank){
				return false; //need to work on castling, returns false in the meantime
			}
		}
		else if(gameColor == BLACK && p_file == 4 && p_rank == 7){
			if(d_file == 2 && d_rank == p_rank){
			    return false; //need to work on castling, returns false in the meantime
			}
			else if(d_file == 6 && d_rank == p_rank){
				return false; //need to work on castling, returns false in the meantime
			}
		}
		return false;
	}
}
