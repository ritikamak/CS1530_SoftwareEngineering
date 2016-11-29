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
		type = PieceType.KNIGHT;
	}
	
	public Knight (boolean gameColor, Square position){
		super("Knight", gameColor, position);
		type = PieceType.KNIGHT;
	}

	/* METHODS */
	
	public boolean move(Board board, Square dest) throws MoveException
	{
		MoveTemplate mt;
		
		try{
			//knight is pretty easy in terms of error checking, MoveTemplate with knight pattern will do all the work for us. 
			mt = new MoveTemplate(MoveTemplate.MovePattern.KNIGHT, this.getPosition(), dest);
			if(pathObstructed(board, mt)){
				throw new MoveException("Path obstructed");
			}
			//if there are no obstructions the move is legal, so we finally return if this move results in a capture or not
			return dest.isOccupied();
		}
		catch(MoveTemplateException e){
			throw new MoveException();
		}
	}
}
