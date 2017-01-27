import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTest{
	
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
	
	Pawn pawn;
	Pawn obstruction;
	Pawn captureTarget;
	Board board;
	
	//test if moving a white pawn backward (to a rank less than the one currently occupied) correctly throws an MoveException
	@Test
	public void backwardMoveTest1(){
		board = new Board();
		//we will place this pawn at a2
		pawn = new Pawn(WHITE, board.getSquareAt(A,TWO));
		board.getSquareAt(A,TWO).occupySquare(pawn);
		//now we will try to move it backward...
		try{
			pawn.move(board, board.getSquareAt(A,ONE));
			//if no move exception was thrown, we have failed this test
			fail();
		}
		catch(MoveException e){
			//otherwise, we succeeded!
		}
	}
	
	//test if moving a black pawn backward (to a rank greater than the one currently occupied) correctly throws an MoveException
	@Test
	public void backwardMoveTest2(){
		board = new Board();
		//we will place this pawn at a7
		pawn = new Pawn(BLACK, board.getSquareAt(A,SEVEN));
		board.getSquareAt(A,SEVEN).occupySquare(pawn);
		//now we will try to move it backward...
		try{
			pawn.move(board, board.getSquareAt(A,EIGHT));
			//if no move exception was thrown, we have failed this test
			fail();
		}
		catch(MoveException e){
			//otherwise, we succeeded!
		}
	}
	
	//test if moving a white pawn forward more than 2 spaces throws exception
	@Test
	public void forwardMoveTest1(){
		board = new Board();
		//we will place this pawn at a2
		pawn = new Pawn(WHITE, board.getSquareAt(A, TWO));
		board.getSquareAt(A,TWO).occupySquare(pawn);
		//now we will try to move it forward more than 2 squares
		try{
			pawn.move(board, board.getSquareAt(A, FIVE));
			//if no move exception was thrown, we have failed this test
			fail();
		}
		catch(MoveException e){
			//otherwise, we succeeded!
		}
	}
	
	//test if moving a white pawn forward more than 1 space after its first move throws an exception
	@Test
	public void forwardMoveTest2(){
		board = new Board();
		//we will place this pawn at a2
		pawn = new Pawn(WHITE, board.getSquareAt(A,TWO));
		board.getSquareAt(A,TWO).occupySquare(pawn);
		//now we will try to move it forward once, then make an illegal move forward..
		try{
			//first move should be legal
			pawn.move(board, board.getSquareAt(A,FOUR));
			pawn.setPosition(board.getSquareAt(A,FOUR));
			pawn.moved();
			board.getSquareAt(A,FOUR).evictSquare();
			board.getSquareAt(A,FOUR).occupySquare(pawn);
			try{
				//trying to move 2 squares forward again...should be illegal
				pawn.move(board, board.getSquareAt(A,SIX));
				//if no move exception was thrown, we have failed this test
				fail();
			}
			catch(MoveException e){
				//otherwise, we succeeded!
			}
		}
		catch(MoveException e){
			//first move should not throw exception
			fail();
		}
	}
	
	//test if moving a white pawn forward into obstruction throws exception
	@Test
	public void forwardMoveTest3(){
		board = new Board();
		//we will place this pawn at a2
		pawn = new Pawn(WHITE, board.getSquareAt(A,TWO));
		board.getSquareAt(A,TWO).occupySquare(pawn);
		//we will place a black piece in front of it (a3)
		obstruction = new Pawn(BLACK, board.getSquareAt(A,THREE));
		board.getSquareAt(A,THREE).occupySquare(obstruction);
		try{
			pawn.move(board, board.getSquareAt(A,THREE));
			//this white pawn should not be allowed to move forward with obstruction
			fail();
		}
		catch(MoveException e){
			//we want a move exception to be thrown here
		}
	}
	
	//test if moving a black pawn forward more than 2 spaces throws exception
	@Test
	public void forwardMoveTest4(){
		board = new Board();
		//we will place this pawn at a7
		pawn = new Pawn(BLACK, board.getSquareAt(A,SEVEN));
		board.getSquareAt(A,SEVEN).occupySquare(pawn);
		//now we will try to move it forward more than 2 spaces
		try{
			pawn.move(board, board.getSquareAt(A, FOUR));
			//if no move exception was thrown, we have failed this test
			fail();
		}
		catch(MoveException e){
			//otherwise, we succeeded!
		}
	}
	
	//test if moving a black pawn forward more than 1 space after its first move throws an exception
	@Test
	public void forwardMoveTest5(){
		board = new Board();
		//we will place this pawn at a7
		pawn = new Pawn(BLACK, board.getSquareAt(A,SEVEN));
		board.getSquareAt(A,SEVEN).occupySquare(pawn);
		//now we will try to move it forward once, then make an illegal move forward..
		try{
			//first move should be legal
			pawn.move(board, board.getSquareAt(A,FIVE));
			pawn.setPosition(board.getSquareAt(A,FIVE));
			pawn.moved();
			board.getSquareAt(A,SEVEN).evictSquare();
			board.getSquareAt(A,FIVE).occupySquare(pawn);
			try{
				//trying to move 2 squares forward again...should be illegal
				pawn.move(board, board.getSquareAt(A,THREE));
				//if no move exception was thrown, we have failed this test
				fail();
			}
			catch(MoveException e){
				//otherwise, we succeeded!
			}
		}
		catch(MoveException e){
			//first move should not throw exception
			fail();
		}
	}
	
	//test if moving a black pawn forward into obstruction throws exception
	@Test
	public void forwardMoveTest6(){
		board = new Board();
		//we will place this pawn at a7
		pawn = new Pawn(BLACK, board.getSquareAt(A,SEVEN));
		board.getSquareAt(A,SEVEN).occupySquare(pawn);
		//we will place a white piece in front of it (a6)
		obstruction = new Pawn(WHITE, board.getSquareAt(A,SIX));
		board.getSquareAt(A,SIX).occupySquare(obstruction);
		try{
			//moving two spaces forward
			pawn.move(board, board.getSquareAt(A,FIVE));
			//this should not have happened
			fail();
		}
		catch(MoveException e){
			try{
				//moving one space forward
				pawn.move(board, board.getSquareAt(A,SIX));
				//this should not have happened
				fail();
			}
			catch(MoveException e1){
				//great, pawn was obstructed for both moves!
			}
		}
	}
	
	//test if moving a white pawn diagonally without an enemy target throws exception
	@Test
	public void diagonalTest1(){
		board = new Board();
		//we will place this pawn at a2
		pawn = new Pawn(WHITE, board.getSquareAt(A,TWO));
		board.getSquareAt(A,TWO).occupySquare(pawn);
		//now we will try to move this pawn diagonally to B3 with no target (it should throw exception)
		try{
			pawn.move(board, board.getSquareAt(B,THREE));
			//if this move was allowed, test has failed
			fail();
		}
		catch(MoveException e){
			//otherwise, lets put a friendly pawn down and make sure move still throws exception
			obstruction = new Pawn(WHITE, board.getSquareAt(B,THREE));
			board.getSquareAt(B,THREE).occupySquare(obstruction);
			try{
				pawn.move(board, board.getSquareAt(B,THREE));
				//if this move was allowed we failed...
				fail();
			}
			catch(MoveException e1){
				//yay!!! we succeeded. bad moves threw exceptions!
			}
		}
	}
	
	//test if moving a black pawn diagonally without an enemy target throws exception
	@Test
	public void diagonalTest2(){
		board = new Board();
		//we will place this pawn at a7
		pawn = new Pawn(BLACK, board.getSquareAt(A,SEVEN));
		board.getSquareAt(A,SEVEN).occupySquare(pawn);
		//now we will try to move this pawn diagonally to B6 with no target (it should throw exception)
		try{
			pawn.move(board, board.getSquareAt(B,SIX));
			//if this move was allowed, test has failed
			fail();
		}
		catch(MoveException e){
			//otherwise, lets put a friendly pawn down and make sure move still throws exception
			obstruction = new Pawn(BLACK, board.getSquareAt(B,SIX));
			board.getSquareAt(B,SIX).occupySquare(obstruction);
			try{
				pawn.move(board, board.getSquareAt(B,SIX));
				//if this move was allowed we failed...
				fail();
			}
			catch(MoveException e1){
				//yay!!! we succeeded. bad moves threw exceptions!
			}
		}
	}
	
	//test if white pawn diagonal capture works in right direction
	@Test
	public void captureTest1(){
		board = new Board();
		//we will place this pawn at a2
		pawn = new Pawn(WHITE, board.getSquareAt(A,TWO));
		board.getSquareAt(A,TWO).occupySquare(pawn);
		//we need to place a capture target diagonally at B3
		captureTarget = new Pawn(BLACK, board.getSquareAt(B,THREE));
		board.getSquareAt(B,THREE).occupySquare(captureTarget);
		try{
			boolean capture = pawn.move(board, board.getSquareAt(B,THREE));
			//move() should be both legal and return true if there is a piece to be captured at the end of the move
			if(capture != true){
				fail();
			}
		}
		catch(MoveException e){
			System.out.println(e);
			//uh-oh...
			fail();
		}
	}
	
	//test if white pawn diagonal capture works in left direction
	@Test
	public void captureTest2(){
		board = new Board();
		//we will place this pawn at b2
		pawn = new Pawn(WHITE, board.getSquareAt(B,TWO));
		board.getSquareAt(B,TWO).occupySquare(pawn);
		//we need to place a capture target diagonally at a3
		captureTarget = new Pawn(BLACK, board.getSquareAt(A,THREE));
		board.getSquareAt(A,THREE).occupySquare(captureTarget);
		try{
			boolean capture = pawn.move(board, board.getSquareAt(A,THREE));
			//move() should be both legal and return true if there is a piece to be captured at the end of the move
			if(capture != true){
				fail();
			}
		}
		catch(MoveException e){
			System.out.println(e);
			//uh-oh...
			fail();
		}
	}
	
	//test if black pawn diagonal capture works in right direction
	@Test
	public void captureTest3(){
		board = new Board();
		//we will place this pawn at c7
		pawn = new Pawn(BLACK, board.getSquareAt(C,SEVEN));
		board.getSquareAt(C,SEVEN).occupySquare(pawn);
		//we need to place a capture target diagonally at d6
		captureTarget = new Pawn(WHITE, board.getSquareAt(D,SIX));
		board.getSquareAt(D,SIX).occupySquare(captureTarget);
		try{
			boolean capture = pawn.move(board, board.getSquareAt(D,SIX));
			//move() should be both legal and return true if there is a piece to be captured at the end of the move
			if(capture != true){
				fail();
			}
		}
		catch(MoveException e){
			System.out.println(e);
			//uh-oh...
			fail();
		}
	}
	
	//test if black pawn diagonal capture works in left direction
	@Test
	public void captureTest4(){
		board = new Board();
		//we will place this pawn at c7
		pawn = new Pawn(BLACK, board.getSquareAt(C,SEVEN));
		board.getSquareAt(C,SEVEN).occupySquare(pawn);
		//we need to place a capture target diagonally at b6
		captureTarget = new Pawn(WHITE, board.getSquareAt(B,SIX));
		board.getSquareAt(B,SIX).occupySquare(captureTarget);
		try{
			boolean capture = pawn.move(board, board.getSquareAt(B,SIX));
			//move() should be both legal and return true if there is a piece to be captured at the end of the move
			if(capture != true){
				fail();
			}
		}
		catch(MoveException e){
			System.out.println(e);
			//uh-oh...
			fail();
		}
	}
}
