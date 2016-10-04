import java.util.*;
/* The board is an object created within a Game object. The board has a 8x8 2dimensional array of Square objects */
public class Board
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	public static final int H = 7;
	public static final int ONE = 0;
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;
	
	/* VARIABLES */
	Square[][] board; /* A board is a 8x8 grid of squares. The grid is traditionally labeled by columns called file (a-h) 
		and rows called rank (1-8). Square 'a1' is the bottom left corner of the board, and is board[A][ONE] 
		in our array representation of it. */
		
	/* CONSTRUCTORS */
	public Board()
	{
		board = new Square[8][8];
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new Square(i, j);
			}
		}
	}
	
	/* METHODS */
	public Square getSquareAt(int file, int rank)
	{
		if(file < 0 || file > 7 || rank < 0 || rank > 7){
			return null; //I don't love returning null values in Java, hopefully in the future we will have some custom exceptions in place
		}
		
		else{
			return board[file][rank];
		}
	}
	
}