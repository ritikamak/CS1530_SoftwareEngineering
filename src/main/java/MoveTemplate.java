/**
 * Piece movement implementation - template for board's setup; 
 * checks legality of different movements and special cases.
 *
 */
public class MoveTemplate
{
	/* CONSTANTS */
	public static final int X = 0;
	public static final int Y = 1;
	
	public enum MovePattern{
		ORTHOGONAL,
		DIAGONAL,
		KNIGHT,
		SPECIAL_CASE
	};

	/* VARIABLES */
	MovePattern type; //is a movement orthoganal? diagonal? Knight? a King's castle?
	int moveLen; //number of total squares moved
	int moveVector[]; //a 2-dimensional vector
	int slope; //this helps us construct paths later
	
	/* CONSTRUCTORS */
	/* NOTE: this constructor throws exceptions in the event of a MoveTemplate object failing to satisfy the conditions of its MovementPattern.
	It only concerns itself with the basic move pattern. It will NOT check for a move that goes off the board, a move that is obstructed, a move that leaves a King in check, etc...
	
	parameter 'type' indicates to MoveTemplate what type of movepattern is being used, so that it can make calculations and verifications specific to that kind of move
	parameter src is the Square representation  of a move's starting position, 
	parameter dest is the Square representation  of a move's ending position */
	public MoveTemplate(MovePattern type, Square src, Square dest) throws MoveTemplateException
	{
		this.type = type;
		moveVector = new int[2]; //(vector[X] = squares moved horizontally, vector[Y] = square moved vertically)
		//horizontal movement is determined by a change in file
		moveVector[X] = dest.getFile() - src.getFile();
		//vertical movement is determined by a change in rank
		moveVector[Y] = dest.getRank() - src.getRank();
		moveLen = Math.abs(moveVector[X]) + Math.abs(moveVector[Y]);
		
	/* Here is where we want to check for move pattern problems and throw exceptions accordingly */
		switch(this.type){
			case ORTHOGONAL:
				/*in ORTHOGONAL movement, either vector[X] or vector[Y] must be equal to 0. 
				Example: a rook can't move up 4 squares, and then over 2 squares in the same move. */
				
				//if x vector has a value...
				if(Math.abs(moveVector[X]) != 0){
					//then y vector must be equal to 0
					if(Math.abs(moveVector[Y]) != 0){
						throw new MoveTemplateException("this MoveTemplate violates the rules of an ORTHOGONAL movement pattern.");
					}
					/*since Y is 0, and 0 divided by anything is 0, so we are going to set slope to 0*/
					slope = 0;
				}
				//if y vector has a value 
				else if(Math.abs(moveVector[Y]) != 0){
					//then x vector must be equal to 0
					if(Math.abs(moveVector[X]) != 0){
						throw new MoveTemplateException("this MoveTemplate violates the rules of an ORTHOGONAL movement pattern.");
					}
					/* since X is 0, and anything divided by 0 is...madness, we are just going to set slope to an appropriately sinister flag number */
					slope = 666;
				}
				//if both x and y vectors are non-zero, then this is NOT a ORTHOGONAL move
				else{
					throw new MoveTemplateException("this MoveTemplate violates the rules of an ORTHOGONAL movement pattern.");
				}
				break;
				
			case DIAGONAL:
				/* in diagonal movement, absolute value of the slope must be exactly 1.
				Example: Follow the movement path a bishop takes; at each square the rise-over-run is always 1 or -1. */
				
				if(Math.abs(moveVector[X]) != Math.abs(moveVector[Y])){
					throw new MoveTemplateException("this MoveTemplate violates the rules of a DIAGONAL movement pattern.");
				}
				//if diagonal move is legal, we also need to set the slope and adjust the moveLen for diagonal movement by dividing it in half
				else{
					//in diagonal's case, slope is just good ol' fashion rise/run (which should come out to either 1 or -1)
					slope = moveVector[Y] / moveVector[X];
					moveLen = moveLen / 2;
				}
				break;
				
			case KNIGHT:
				/* in a knight's movement, the piece always moves a total of 3 squares. A knight's move has two phases performed in any order:
				1) an vertical or horizontal move of 2 spaces; 
				2) a move of one space in the orthogonal direction opposite from that of the move's previous phase */
				
				//as such, we will verify movetemplate in two phases:
				//if 2 spaces are moved horizontally
				if(Math.abs(moveVector[X]) == 2){
					//then y vector must have a magnitude equal to 1
					if(Math.abs(moveVector[Y]) != 1){
						throw new MoveTemplateException("this MoveTemplate violates the rules of a KNIGHT movement pattern.");
					}
					//since a Knight jumps other pieces, its path is just 1 move (a jump to the destination square) so we reset moveLen to 1. It also, obviously, has no slope
					moveLen = 1;
				}
				//if 2 spaces are moved vertically
				else if(Math.abs(moveVector[Y]) == 2){
					//then x vector must have a magnitude equal to 1
					if(Math.abs(moveVector[X]) != 1){
						throw new MoveTemplateException("this MoveTemplate violates the rules of a KNIGHT movement pattern.");
					}
					//but, since a Knight jumps other pieces, its path is really just 1 move (a jump to the destination square) so we reset moveLen to 1. It also, obviously, has no slope
					moveLen = 1;
				}
				//if this movetemplate does not satisfy the above conditions, it cannot be of type KNIGHT
				else{
					throw new MoveTemplateException("this MoveTemplate violates the rules of a KNIGHT movement pattern.");
				}
				break;
	
			case SPECIAL_CASE:
				/* you might have noticed we have not there's not much in this special_case section
				For now, no exceptions are thrown when a special_case movement type is provided */
				moveLen = 1; //in special cases, there is but one move...to the destination square. THERE CAN ONLY BE ONE!
				break;
				
			default:
				throw new MoveTemplateException("this MoveTemplate has an unknown movement pattern.");
		}
	}
	
	/* METHODS */
	/* returns the pattern used by this template */
	public MovePattern getPattern()
	{
		return type;
	}
	
	/* returns the total number of squares moved */
	public int getMoveLen()
	{
		return moveLen;
	}
	
	/* returns the move's X vector */
	public int getX()
	{
		return moveVector[X];
	}
	
	/* returns the move's Y vector */
	public int getY()
	{
		return moveVector[Y];
	}
	
	/* returns the move's path. which is an array of squares representing the move's path and indexed in sequence from beginning to end (exclusive of starting square)*/
	public Square[] getPath(Board board, Square src)
	{
		Square path[];
		int i,j;
		int x, y;
		
		//get the x,y coordinates of our starting square
		x = src.getFile();
		y = src.getRank();
		//initialize our path array with length = to moveLen
		path = new Square[moveLen];
		i = 0;
		//generating path is then determined by movement pattern and slope
		switch(type){
				
			case ORTHOGONAL:
				//horizontal move
				if(slope == 0){
					while(i < moveLen){
						//check if we are going right or left
						//right
						if(moveVector[X] > 0){
							x++;
						}
						//left
						else{
							x--;
						}
						//next square in path
						path[i] = board.getSquareAt(x,y);
						i++;
					}
				}
				//vertical move
				else{
					while(i < moveLen){
						//check if we are going up or down
						//up
						if(moveVector[Y] > 0){
							y++; 
						}
						//down
						else{
							y--;
						}
						//next square in path
						path[i] = board.getSquareAt(x,y);
						i++;
					}
				}
				break;
				
			case DIAGONAL:
				while(i < moveLen){
					//check if we are sloping positively or sloping negatively
					//positive :)
					if(slope > 0){
						//then check if we are traveling down that slope in the reverse direction!
						if(moveVector[X] < 0){
							x--;
							y--;
						}
						else{
							x++;
							y++;
						}
					}
					//negative :(
					else{
						//then check if we are traveling up that slope in the reverse direction!
						if(moveVector[X] < 0){
							x--;
							y++;
						}
						else{
							x++;
							y--;
						}
					}
					//next square in path
					path[i] = board.getSquareAt(x,y);
					i++;
				}
				break;
				
			case KNIGHT:
				//just add vectors to source
				x = x + moveVector[X];
				y = y + moveVector[Y];
				path[0] = board.getSquareAt(x,y);
				break;
				
			case SPECIAL_CASE:
				//just add vectors to source
				x = x + moveVector[X];
				y = y + moveVector[Y];
				path[0] = board.getSquareAt(x,y);
				break;
		}
		
		return path;
	}

}
