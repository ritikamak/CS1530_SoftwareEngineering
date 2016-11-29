import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest{
	
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	//Files
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	public static final int H = 7;
	//Ranks
	public static final int ONE = 0;
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;

	Knight knight;
	Piece obstruction;
	Piece obstruction2;
	Piece captureTarget;
	Board board = new Board();
	Square src;
	Square dest;
	
	//test if knight remains unobstructed (as it jumps pieces)
	@Test
	public void obstructionTest1(){
		board = new Board();
		//we will place this Knight at C4 with the intention to move it to D6
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(D,SIX);
		//set Knight at src
		knight = new Knight(WHITE, src);
		src.occupySquare(knight);
		//set up two obstructions (for normal pieces) in squares D4 and D5
		obstruction = new Pawn(WHITE, board.getSquareAt(D,FOUR));
		board.getSquareAt(D,FOUR).occupySquare(obstruction);
		obstruction = new Pawn(BLACK, board.getSquareAt(D,FIVE));
		board.getSquareAt(D,FIVE).occupySquare(obstruction2);
		//now we will try to move it forward...
		try{
			boolean capture = knight.move(board, dest);
			//knight is landing on empty square, so move should return false
			if(capture != false){
				fail();
			}
			//if we got this far test was a success!!
		}
		catch(MoveException e){
			//no exception should be thrown with this theoretical setup
			System.out.println(e);
			fail();
		}
	}
	
	//test if knight is obstructed at its destination square (when applicable)
	@Test
	public void obstructionTest2(){
		board = new Board();
		//we will place this Knight at C4 with the intention to move it to D6
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(D,SIX);
		//set Knight at src
		knight = new Knight(WHITE, src);
		src.occupySquare(knight);
		//set up an obstruction (same color piece) in the dest square D6
		obstruction = new Pawn(WHITE, dest);
		dest.occupySquare(obstruction);
		//now we will try to move the knight
		try{
			knight.move(board, dest);
			//if we got this far test failed
			fail();
		}
		catch(MoveException e){
			//great! test is a success
		}
	}
	
	//test if capture works
	@Test
	public void captureTest1(){
		board = new Board();
		//we will place this Knight at C4 with the intention to move it to D6
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(D,SIX);
		//set Knight at src
		knight = new Knight(WHITE, src);
		src.occupySquare(knight);
		//set up a capture target (enemy color piece) in the dest square D6
		obstruction = new Pawn(BLACK, dest);
		dest.occupySquare(obstruction);
		//now we will try to move the knight
		try{
			boolean capture = knight.move(board, dest);
			//if move does not return true (signalling capture) we have failed test
			if(capture != true){
				fail();
			}
			//otherwise, we pass!
		}
		catch(MoveException e){
			//no exception should be thrown with this theoretical setup
			System.out.println(e);
			fail();
		}
	}
}