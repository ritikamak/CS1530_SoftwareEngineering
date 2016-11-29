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
		type = PieceType.KING;
	}
	
	public King (boolean gameColor, Square position){
		super("King", gameColor, position);
		type = PieceType.KING;
	}

	/* METHODS */
	
	public boolean move(Board board, Square dest) throws MoveException
	{
		Square src;
		int sf, sr, df, dr, rise, run;
		MoveTemplate mt;
		MoveTemplate.MovePattern moveType;
		
		src = this.getPosition(); //source square
		sf = src.getFile(); //source file
		sr = src.getRank(); //source rank
		df = dest.getFile(); //destination file
		dr = dest.getRank(); //destination rank
		
		//get rise-over-run to determine move type
		rise = dr-sr;
		run = df-sf;
		
		//if move is vertical
		if(Math.abs(rise) == 1 && run == 0){
			moveType = MoveTemplate.MovePattern.ORTHOGONAL;
		}
		//if move is horizontal
		else if(Math.abs(run) == 1 && rise == 0){
			moveType = MoveTemplate.MovePattern.ORTHOGONAL;
		}
		//if move is diagonal
		else if(Math.abs(run) == 1 && Math.abs(rise) == 1){
			moveType = MoveTemplate.MovePattern.DIAGONAL;
		}
		//move is not something a king can do (we will need to add some castling code later)
		else{
			throw new MoveException("Illegal king move.");
		}
		//lets try to make the move template
		try{
			mt = new MoveTemplate(MoveTemplate.MovePattern.SPECIAL_CASE, this.getPosition(), dest);
			//king will never move more than one space (even in castling, as the SPECIAL_CASE pattern always returns aa moveLen of 1)
			if(mt.getMoveLen() > 1){
				throw new MoveException("Illegal king move.");
			}
			if(pathObstructed(board, mt)){
				throw new MoveException("Path obstructed");
			}
			//if there are no obstructions the move is legal, so we finally return if this move results in a capture or not
			return dest.isOccupied();
		}
		catch(MoveTemplateException e){
			throw new MoveException("Illegal king move.");
		}
	}
}
