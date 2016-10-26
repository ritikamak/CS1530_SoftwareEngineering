import java.util.*;
public class Pawn extends Piece
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

	public Pawn (boolean color, Square position){
		this.color = color;
		this.position = position;
		name = "Pawn";
	}
	public Pawn (boolean color){
		this.color = color;
		name = "Pawn";
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
		//USE ONLY FOR TESTING
		//check if in bounds
		if(d_file > 7 || d_rank > 7){
			return false;
		}
		else if(d_file < 0 || d_rank < 0){
			return false;
		}
		//check three cases for white: single move, double move, capture
		else if(color == WHITE){
			//movment for first row
			if(d_rank == 4 && p_rank == 2){
				position = destination;
				return true;
			}
			//any row
			else if(d_file == p_file && d_rank == p_rank+1){
				position = destination;
				return true;
			}
			//capture
			//TODO: CONSIDER MAKING CAPTURE A SEPARATE METHOD
			else if(destination.isOccupied()){
				Piece p = destination.getPiece();
				if(p.getColor() != color){
					if(d_file == p_file+1 && d_rank == p_rank+1){
						position = destination;
						return true;
					}
					else if(d_file == p_file-1 && d_rank == p_rank+1){
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
		//check three cases for white: single move, double move, capture
		else if (color == BLACK){
			//movment for first row
			if(d_rank == 5 && p_rank == 7){
				position = destination;
				return true;
			}
			//any row
			else if(d_file == p_file && d_rank == p_rank-1){
				position = destination;
				return true;
			}
			//capture
			//TODO: CONSIDER MAKING CAPTURE A SEPARATE METHOD
			else if(destination.isOccupied()){
				Piece p = destination.getPiece();
				if(p.getColor() != color){
					if(d_file == p_file+1 && d_rank == p_rank-1){
						position = destination;
						return true;
					}
					else if(d_file == p_file-1 && d_rank == p_rank-1){
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
		//check out of bounds
		Piece k = new Pawn(true, new Square(3,2));
		Square d = new Square(10,2);
		if(k.movePiece(d)){
			System.out.println("true");
		}
		else{
			System.out.println("false - correct");
		}
		//check out of bounds
		k = new Pawn(true, new Square(3,2));
		d = new Square(-9,10);
		if(k.movePiece(d)){
			System.out.println("true");
		}
		else{
			System.out.println("false - correct");
		}
		//BEGIN TEST WHITE PIECES
		System.out.println("TESTING WHITE PIECES");
		//check first row movement
		System.out.println("TESTING FIRST ROW MOVEMENT");
		k = new Pawn(WHITE, new Square(3,2));
		d = new Square(3,4);
		if(k.movePiece(d)){
			System.out.println("true - correct");
		}
		else{
			System.out.println("false");
		}
		//check any row movement
		System.out.println("TESTING ANY ROW MOVEMENT");
		k = new Pawn(WHITE, new Square(5,4));
		d = new Square(5,5);
		if(k.movePiece(d)){
			System.out.println("true - correct");
		}
		else{
			System.out.println("false");
		}
		//BEGIN TEST BLACK PIECES
		System.out.println("TESTING BLACK PIECES");
		//check first row movement
		System.out.println("TESTING FIRST ROW MOVEMENT");
		k = new Pawn(BLACK, new Square(3,7));
		d = new Square(3,5);
		if(k.movePiece(d)){
			System.out.println("true - correct");
		}
		else{
			System.out.println("false");
		}
		//check any row movement
		System.out.println("TESTING FIRST ANY ROW MOVEMENT");
		k = new Pawn(BLACK, new Square(5,4));
		d = new Square(5,3);
		if(k.movePiece(d)){
			System.out.println("true - correct");
		}
		else{
			System.out.println("false");
		}
	}

}
