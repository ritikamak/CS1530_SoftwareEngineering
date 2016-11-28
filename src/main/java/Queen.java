import java.util.*;
public class Queen extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */

	/*CONSTRUCTORS*/

	public Queen (Player owner, boolean gameColor, Square position){
		super(owner, "Queen", gameColor, position);
	}
	
	public Queen (boolean gameColor, Square position){
		super("Queen", gameColor, position);
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
		//check if diagonal move
		else{
			//slope calculation must be in here otherwise horizontal move would result in division by zero
			int slope = (d_rank-p_rank)/(d_file-p_file);
			if(slope == 1 || slope == -1){
				position.evictSquare();
				destination.occupySquare(this);
				position = destination;
				return true;
			}
		}
		return false;
	}
	
	public boolean move(Board board,Square dest) throws MoveException
	{
		Square src;
		int sf, sr, df, dr, rise, run;
		MoveTemplate mt;
		MoveTemplate.MovePattern pattern;
		
		src = this.getPosition(); //source square
		sf = src.getFile(); //source file
		sr = src.getRank(); //source rank
		df = dest.getFile(); //destination file
		dr = dest.getRank(); //destination rank
		
		//get rise-over-run to determine move type
		rise = dr-sr;
		run = df-sf;
		
		//if move is vertical
		if(rise != 0 && run == 0){
			pattern = MoveTemplate.MovePattern.ORTHOGONAL;
		}
		//if move is horizontal
		else if(run != 0 && rise == 0){
			pattern = MoveTemplate.MovePattern.ORTHOGONAL;
		}
		//if move is diagonal
		else if(run != 0 && run != 0){
			pattern = MoveTemplate.MovePattern.DIAGONAL;
		}
		//move is not something a queen can do
		else{
			throw new MoveException("Illegal queen move.");
		}
		//lets try to make the move template
		try{
			mt = new MoveTemplate(pattern, this.getPosition(), dest);
			//lets now check for obstructions
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
