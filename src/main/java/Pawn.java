import java.util.*;
public class Pawn extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;
	
	/* VARIABLES */
	boolean hasMoved; //a flag to determine if pawn can move two squares forward
	
	/*CONSTRUCTORS*/

	public Pawn (Player owner, boolean gameColor, Square position)
	{
		super(owner, "Pawn", gameColor, position);
		hasMoved = false;
	}
	
	public Pawn (boolean gameColor, Square position)
	{
		super("Pawn", gameColor, position);
		hasMoved = false;
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
		Piece captured;
		//USE ONLY FOR TESTING
		//check if in bounds
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		else if(d_file < 0 || d_rank < 0){
			return false;
		}
		//check three cases for white: single move, double move, capture
		else if(gameColor == WHITE){
			//movment for first row
			if(d_file == p_file && d_rank <= p_rank+2 && d_rank > p_rank &&!this.hasMoved){
				position.evictSquare();
				destination.occupySquare(this);
				position = destination;
				hasMoved = true;
				return true;
			}
			//any row
			else if(d_file == p_file && d_rank == p_rank+1){
				position.evictSquare();
				destination.occupySquare(this);
				position = destination;
				return true;
			}
			//capture
			//TODO: CONSIDER MAKING CAPTURE A SEPARATE METHOD
			else if(destination.isOccupied()){
				Piece p = destination.getPiece();
				if(p.getColor() != gameColor){
					if(d_file == p_file+1 && d_rank == p_rank+1){
						position.evictSquare();
						destination.occupySquare(this);
						position = destination;
						return true;
					}
					else if(d_file == p_file-1 && d_rank == p_rank+1){
						position.evictSquare();
						destination.occupySquare(this);
						position = destination;
						return true;
					}
				}
				//return false if capture piece is of the same color
				else{
					return false;
				}
			}
			//TODO: EN PASSENT
		}
		//check three cases for black: single move, double move, capture
		else if (gameColor == BLACK){
			//movment for first row
			if(d_file == p_file && (p_rank-d_rank) <= 2 && (p_rank-d_rank) >= 1 && !this.hasMoved){
				position.evictSquare();
				destination.occupySquare(this);
				position = destination;
				hasMoved = true;
				return true;
			}
			//any row
			else if(d_file == p_file && d_rank == p_rank-1){
				position.evictSquare();
				destination.occupySquare(this);
				position = destination;
				return true;
			}
			//capture
			//TODO: CONSIDER MAKING CAPTURE A SEPARATE METHOD
			else if(destination.isOccupied()){
				Piece p = destination.getPiece();
				if(p.getColor() != gameColor){
					if(d_file == p_file+1 && d_rank == p_rank-1){
						position.evictSquare();
						destination.occupySquare(this);
						position = destination;
						return true;
					}
					else if(d_file == p_file-1 && d_rank == p_rank-1){
						position.evictSquare();
						destination.occupySquare(this);
						position = destination;
						return true;
					}
				}
				//return false if capture piece is of the same color
				else{
					return false;
				}
			}
			//TODO: EN PASSENT
		}
		//TODO PROMOTION
		return false;
	}
}
