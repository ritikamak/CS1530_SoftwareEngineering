import org.junit.Test;
import static org.junit.Assert.*;

public class QueenTest{
	
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

	Queen queen;
	Piece obstruction;
	Piece captureTarget;
	Board board = new Board();
	Square src;
	Square dest;
	
	//test moving a queen up and down a positive slope (and check capture)
	@Test
	public void diagonalMoveTest1(){
		boolean capture;
		boolean up = true;
		board = new Board();
		//starting at c4, we want to move queen to g8 (this move is theoretically legal)
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(G, EIGHT);
		queen = new Queen(BLACK, src);
		src.occupySquare(queen);
		//setup a capture target at destination
		captureTarget = new Pawn(WHITE, dest);
		dest.occupySquare(captureTarget);
		try{
			capture = queen.move(board, dest);
			//if we are here, great! we moved up a positive slope
			if(capture != true){
				//even though we got here, we want to make sure a capture was indicated also
				fail();
			}
			dest.evictSquare(); //normally we would bother recording captured piece... unnecessary here
			queen.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(queen);
			//now lets try moving down a positve slope (reverse of the previous move)
			up = false;
			capture = queen.move(board, src);
			if(capture != false){
				//even though we got here, we want to make sure no capture was falsely indicated
				fail();
			}
			//if we got here, thats a test success!
		}
		catch(MoveException e){
			//our move was theoretically legal, so the test fails
			if(up == true){
				System.out.println("Exception caught moving up: " + e.toString());
			}
			else{
				System.out.println("Exception caught moving up: " + e.toString());
			}
			fail();
		}
	}
	
	//test moving a queen up and down a negative slope 
	@Test
	public void diagonalMoveTest2(){
		boolean capture;
		boolean up = true;
		board = new Board();
		//starting at c4, we want to move queen to f1 (this move is theoretically legal)
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(F, ONE);
		queen = new Queen(BLACK, src);
		src.occupySquare(queen);
		//setup a capture target at destination
		captureTarget = new Pawn(WHITE, dest);
		dest.occupySquare(captureTarget);
		try{
			capture = queen.move(board, dest);
			//if we are here, great! we moved up a negative slope
			if(capture != true){
				//even though we got here, we want to make sure a capture was indicated also
				fail();
			}
			dest.evictSquare(); //normally we would bother recording captured piece... unnecessary here
			queen.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(queen);
			//now lets try moving down a negative slope (reverse of the previous move)
			up = false;
			capture = queen.move(board, src);
			if(capture != false){
				//even though we got here, we want to make sure no capture was falsely indicated
				fail();
			}
			//if we got here, thats a test success!
		}
		catch(MoveException e){
			//our move was theoretically legal, so the test fails
			if(up == true){
				System.out.println("Exception caught moving up: " + e.toString());
			}
			else{
				System.out.println("Exception caught moving up: " + e.toString());
			}
			fail();
		}
	}
	
	//test if moving a white queen forward and backward causes no exceptions to be thrown
	@Test
	public void verticalMoveTest1(){
		boolean forward = true;
		board = new Board();
		//we will place this queen at C4 with the intention to move it forward to C8
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(C,EIGHT);
		//set queen at src
		queen = new Queen(WHITE, src);
		src.occupySquare(queen);
		//now we will try to move it forward...
		try{
			queen.move(board, dest);
			//if we got this far we succeeded in moving it forward
			queen.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(queen);
			forward = false;
			//now lets move it backward!
			queen.move(board, src);
			//if we succeeded on this move we were totally successful!
			queen.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(queen);
		}
		catch(MoveException e){
			if(forward == true){
				System.out.println("Caught this error moving forward: " + e.toString());
			}
			else{
				System.out.println("Caught this error moving backward: " + e.toString());
			}
			fail();
		}
	}
	
	//test if moving a black queen forward and backward causes no exceptions to be thrown
	@Test
	public void verticalMoveTest2(){
		boolean forward = true;
		board = new Board();
		//we will place this queen at C4 with the intention to move it forward to C1
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(C,ONE);
		//set queen at src
		queen = new Queen(BLACK, src);
		src.occupySquare(queen);
		//now we will try to move it forward...
		try{
			queen.move(board, dest);
			//if we got this far we succeeded in moving it forward
			queen.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(queen);
			forward = false;
			//now lets move it backward!
			queen.move(board, src);
			//if we succeeded on this move we were totally successful!
			queen.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(queen);
		}
		catch(MoveException e){
			if(forward == true){
				System.out.println("Caught this error moving forward: " + e.toString());
			}
			else{
				System.out.println("Caught this error moving backward: " + e.toString());
			}
			fail();
		}
	}
	
	//test if moving a white queen left and right causes no exceptions to be thrown
	@Test
	public void horizontalMoveTest1(){
		boolean left = true;
		board = new Board();
		//we will place this queen at C4 with the intention to move it left to A4
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(A,FOUR);
		//set queen at src
		queen = new Queen(WHITE, src);
		src.occupySquare(queen);
		//now we will try to move it left...
		try{
			queen.move(board, dest);
			//if we got this far we succeeded in moving it left
			queen.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(queen);
			left = false;
			//now lets move it right!
			queen.move(board, src);
			//if we succeeded on this move we were totally successful!
			queen.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(queen);
		}
		catch(MoveException e){
			if(left == true){
				System.out.println("Caught this error moving left: " + e.toString());
			}
			else{
				System.out.println("Caught this error moving right: " + e.toString());
			}
			fail();
		}
	}
	
	//test if moving a black queen left and right causes no exceptions to be thrown
	@Test
	public void horizontalMoveTest2(){
		boolean left = true;
		board = new Board();
		//we will place this queen at C4 with the intention to move it left to H4
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(H,FOUR);
		//set queen at src
		queen = new Queen(BLACK, src);
		src.occupySquare(queen);
		//now we will try to move it left...
		try{
			queen.move(board, dest);
			//if we got this far we succeeded in moving it left
			queen.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(queen);
			left = false;
			//now lets move it right!
			queen.move(board, src);
			//if we succeeded on this move we were totally successful!
			queen.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(queen);
		}
		catch(MoveException e){
			if(left == true){
				System.out.println("Caught this error moving left: " + e.toString());
			}
			else{
				System.out.println("Caught this error moving right: " + e.toString());
			}
			fail();
		}
	}
	
	//test moving a queen in not-quite-a-diagonal-line
	@Test
	public void incorrectMoveTest1(){
		board = new Board();
		//starting at c4, we want to move queen to H8 (this move is most certainly illegal)
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(H, EIGHT);
		queen = new Queen(BLACK, src);
		src.occupySquare(queen);
		try{
			queen.move(board, dest);
			//if we are here, it means this move was allowed (which it shouldn't be)
			fail();
			
		}
		catch(MoveException e){
			//our move was illegal, so if we caught an exception the test is passed!
		}
	}
	
	//test queen properly handles obstructions diagonally
	@Test
	public void obstructionTest1(){
		board = new Board();
		//starting at c4, we want to move queen to g8
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(H, EIGHT);
		queen = new Queen(BLACK, src);
		src.occupySquare(queen);
		//we will place an obstruction in its path at f7
		Square block = board.getSquareAt(F,SEVEN);
		obstruction = new Pawn(WHITE, block);
		block.occupySquare(obstruction);
		try{
			queen.move(board, dest);
			//if we are here, it means this move was allowed (which it shouldn't be)
			fail();
			
		}
		catch(MoveException e){
			//our move was illegal, so if we caught an exception the test is passed!
		}
	}
	
	//test queen properly handles obstructions diagonally
	@Test
	public void obstructionTest2(){
		board = new Board();
		//starting at c4, we want to move queen to g8
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(H, EIGHT);
		queen = new Queen(BLACK, src);
		src.occupySquare(queen);
		//we will place an obstruction in its destination
		obstruction = new Pawn(BLACK, dest);
		dest.occupySquare(obstruction);
		try{
			queen.move(board, dest);
			//if we are here, it means this move was allowed (which it shouldn't be)
			fail();
			
		}
		catch(MoveException e){
			//our move was illegal, so if we caught an exception the test is passed!
		}
	}
}
