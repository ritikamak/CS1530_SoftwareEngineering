import java.util.*;
/* The board is an object created within a Game object. The board class is essentially just a 8x8 2-dimensional array of Square objects responsible for tracking board state*/
public class Board
{

	/* VARIABLES */
/* A board is a 8x8 grid of squares. The grid is traditionally labeled by columns called file (a-h)
and rows called rank (1-8). Square 'a1' is the bottom left corner of the board, and is board[A][ONE]
in our array representation of it. */
	Square[][] board;

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
