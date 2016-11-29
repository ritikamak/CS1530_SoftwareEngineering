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
	public Bishop (Player owner, boolean gameColor, Square position)
	{
		super(owner, "Bishop", gameColor, position);
		type = PieceType.BISHOP;
	}
	
	public Bishop (boolean gameColor, Square position)
	{
		super("Bishop", gameColor, position);
		type = PieceType.BISHOP;
	}
	
	/* METHODS */

	public boolean move(Board board, Square dest) throws MoveException
	{
		MoveTemplate mt;
		
		try{
			//bishop is pretty easy in terms of error checking, MoveTemplate with bishop pattern will do all the work for us. 
			mt = new MoveTemplate(MoveTemplate.MovePattern.DIAGONAL, this.getPosition(), dest);
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