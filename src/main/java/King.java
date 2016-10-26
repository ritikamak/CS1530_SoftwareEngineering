import java.util.*;
public class King extends Piece
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;

	/* VARIABLES */
	public boolean color; // a piece has a color
	public Square position; // a piece has a position on the board represented by a square
	public String name; // a piece has a name, e.g. pawn, rook, bishop, knight, queen, king

	/*CONSTRUCTORS*/

	public King (boolean color, Square position){
		this.color = color;
		this.position = position;
		name = "King";
	}
	public King (boolean color){
		this.color = color;
		name = "King";
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
		//USE FOR TESTING ONLY
		//check bounds
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		else if(d_file < 0 || d_rank < 0){
			return false;
		}
		//check all eight valid moves
		else if(d_file == (p_file+1) && d_rank == (p_rank+1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file+1) && d_rank == (p_rank-1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file) && d_rank == (p_rank-1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank-1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file-1) && d_rank == (p_rank+1)){
			position = destination;
			return true;
		}
		else if(d_file == (p_file) && d_rank == (p_rank+1)){
			position = destination;
			return true;
		}
		return false;
	}

	/* GETTERS */
	public boolean getColor()
	{
		return color;
	}

	public Square getPosition(){
		return position;
	}

	public String getName(){
		return name;
	}

	/* SETTERS */
	public void setPosition(Square position ){
		this.position = position;
	}
	public void setColor(boolean color){
		this.color = color;
	}

	public void setName(String name){
		this.name = name;
	}

	/* toString */
	public String toString()
	{
		String c;
		String str;

		if(color == WHITE){
			c = "white";
		}

		else{
			c = "black";
		}

		str = c + " " + name;
		return str;
	}
	/**
	This main method should never be called under normal cases
	Main exists soley for testing purposes
	*/
	public static void main(String[] args){
		//TESTING BLACK
		System.out.println("TESTING BLACK KING MOVEMENT");
		Piece k = new King(BLACK, new Square(3,2));
		Square d = new Square(3,3);
		if(k.movePiece(d)){
			System.out.println("true - correct");
		}
		else{
			System.out.println("false");
		}
		//TESTING WHITE
		System.out.println("TESTING WHITE KING MOVEMENT");
		k = new King(WHITE, new Square(3,2));
		d = new Square(3,3);
		if(k.movePiece(d)){
			System.out.println("true - correct");
		}
		else{
			System.out.println("false");
		}
	}
}
