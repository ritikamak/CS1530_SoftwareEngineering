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

	public Rook (Player owner, boolean gameColor, Square position){
		super(owner, "Rook", gameColor, position);
	}
	
	public Rook (boolean gameColor, Square position){
		super("Rook", gameColor, position);
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
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		//check if vertical move
		else if(d_rank == p_rank){
			position.evictSquare();
			destination.occupySquare(this);
			position = destination;
			return true;
		}
		return false;
	}
	
	public boolean move(Board board, Square dest) throws MoveException
	{
		MoveTemplate mt;
		
		try{
			//rook is pretty easy in terms of error checking, MoveTemplate with orthoganal pattern will do all the work for us. 
			mt = new MoveTemplate(MoveTemplate.MovePattern.ORTHOGONAL, this.getPosition(), dest);
			if(pathObstructed(board, mt)){
				throw new MoveException("Path obstructed");
			}
			//if there are no obstructions the move is legal
			return dest.isOccupied();
			//will build support for castling later
		}
		catch(MoveTemplateException e){
			throw new MoveException(e.toString());
		}
	}
}
