import java.util.*;

/**
 * This is a subclass of Piece.
 * Piece type: King.
 *
 */
public class King extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */
	boolean hasKingSideCastle;
	boolean	hasQueenSideCastle;
	boolean inCheck;
	
	/* CONSTRUCTORS */
	public King (Player owner, boolean gameColor, Square position){
		super(owner, "King", gameColor, position);
		type = PieceType.KING;
		hasKingSideCastle = true;
		hasQueenSideCastle = true;
		inCheck = false;
	}
	
	public King (boolean gameColor, Square position){
		super("King", gameColor, position);
		type = PieceType.KING;
		hasKingSideCastle = true;
		hasQueenSideCastle = true;
		inCheck = false;
	}

	/* METHODS */
	public boolean move(Board board, Square dest) throws MoveException
	{
		boolean isCastle;
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
		//isCastle starts initially as false
		isCastle = false;
		
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
		//there are a lot of conditions to meet for castling! the simplest to check is hasMoved (hence why its the first condition in this huge if statement)
		else if(hasMoved == false && //check if king has moved
				dest.isOccupied() && //check if dest is occupied
				dest.getPiece().getPieceType() == Piece.PieceType.ROOK &&  //if dest is occupied by a rook
				dest.getPiece().getColor() == gameColor &&//and that rook is the same color as the king
				inCheck == false){ //a king cannot castle out of check
			
			//next check if that rook has not moved either (and is not vertical from us (although this condition could not occur is that rook has moved.))
			if(!dest.getPiece().hasMoved() && rise == 0){
				//if neither piece has moved, we can castle and are going to make an orthoganal move template
				moveType = MoveTemplate.MovePattern.ORTHOGONAL;
				isCastle = true;
			}
			else{
			// if either piece has moved, then no castle :(
				throw new MoveException("Illegal king move.");
			}
		}
		//else, we just throw a move exception 
		else{
			throw new MoveException("Illegal king move.");
		}
		//lets try to make the move template
		try{
			mt = new MoveTemplate(moveType, this.getPosition(), dest);
			if(this.pathObstructed(board, mt, isCastle)){
				throw new MoveException("Path obstructed");
			}
			//if there are no obstructions the move is legal, so we finally return if this move results in a capture or castle
			return dest.isOccupied(); 
		}
		catch(MoveTemplateException e){
			throw new MoveException("Illegal king move.");
		}
	}
	
	//this is the kings pathObstructed function which supports checking path obstruction during castling 
	public boolean pathObstructed(Board board, MoveTemplate mt, boolean castle)
	{
		Square path[];
		boolean friendlyColor;
		Piece captureTarget;
		Square dest;
		int i;
		
		friendlyColor = this.getColor();
		path = mt.getPath(board, this.getPosition());
		dest = path[path.length - 1];
		if(path.length > 1){
		//scan through path to see if all squares before the destination square are clear of obstructions
			i = 0;
			while(i < (path.length - 1)){
				if(path[i].isOccupied()){
					return true;
				}
				i++;
			}
		}
		//special condition for castling
		if(castle == true){
			return false;
		}
		//check destination to see if it has a friendly piece or a capture target
		if(dest.isOccupied()){
		//if a piece is in the destination square, we need to check if it is enemy or friendly
			captureTarget = dest.getPiece();
			//if it is not the same color, path is considered unobstructed (it results in a capture)
			if(captureTarget.getColor() != friendlyColor){
				return false;
			}
			//if we found the same color, thats a friendly and we can't capture it! 
			else{
			//so the path is considered obstructed
				return true;
			}
		}
		//if nothing is in the destination square, then it is considered obstructed
		else{
			return false;
		}
	}
	
	public void setCheck()
	{
		inCheck = true;
	}
	
	public void unsetCheck()
	{
		inCheck = false;
	}
	
	public boolean isInCheck()
	{
		return inCheck;
	}
	
	//kings and rooks also have their own special version of the Pieces function moved()
	public void moved()
	{
		hasMoved = true;
		//after a king moves, it is disqualified from castling on either side
		hasKingSideCastle = false;
		hasQueenSideCastle = false;
	}

}
