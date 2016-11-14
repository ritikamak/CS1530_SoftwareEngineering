import java.util.*;
public class Knight extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */
	
	/*CONSTRUCTORS*/
	public Knight (Player owner, boolean gameColor, Square position){
		super(owner, "Knight", gameColor, position);
	}
	
	public Knight (boolean gameColor, Square position){
		super("Knight", gameColor, position);
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
		//check if in bounds
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		if(d_file < 0 || d_rank < 0){
			return false;
		}
		//check the eight possible destinations
		if(d_file == (p_file-1) && d_rank == (p_rank+2)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank+2)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file+2) && d_rank == (p_rank+1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file+2) && d_rank == (p_rank-1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank-2)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank-2)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file-2) && d_rank == (p_rank-1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		else if(d_file == (p_file-2) && d_rank == (p_rank+1)){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		return false;
	}
}
