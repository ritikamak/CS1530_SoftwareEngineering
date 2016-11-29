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
		type = PieceType.ROOK;
	}
	
	public Rook (boolean gameColor, Square position){
		super("Rook", gameColor, position);
		type = PieceType.ROOK;
	}

	/* METHODS */
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
