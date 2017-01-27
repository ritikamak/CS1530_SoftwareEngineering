import org.junit.Test;
import static org.junit.Assert.*;

public class BishopTest{
	
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

	Bishop bishop;
	Piece obstruction;
	Piece captureTarget;
	Board board = new Board();
	Square src;
	Square dest;
	
	//test moving a bishop up and down a positive slope (and check capture)
	@Test
	public void diagonalMoveTest1(){
		boolean capture;
		boolean up = true;
		board = new Board();
		//starting at c4, we want to move bishop to g8 (this move is theoretically legal)
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(G, EIGHT);
		bishop = new Bishop(BLACK, src);
		src.occupySquare(bishop);
		//setup a capture target at destination
		captureTarget = new Pawn(WHITE, dest);
		dest.occupySquare(captureTarget);
		try{
			capture = bishop.move(board, dest);
			//if we are here, great! we moved up a positive slope
			if(capture != true){
				//even though we got here, we want to make sure a capture was indicated also
				fail();
			}
			dest.evictSquare(); //normally we would bother recording captured piece... unnecessary here
			bishop.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(bishop);
			//now lets try moving down a positve slope (reverse of the previous move)
			up = false;
			capture = bishop.move(board, src);
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
	
	//test moving a bishop up and down a negative slope 
	@Test
	public void diagonalMoveTest2(){
		boolean capture;
		boolean up = true;
		board = new Board();
		//starting at c4, we want to move bishop to f1 (this move is theoretically legal)
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(F, ONE);
		bishop = new Bishop(BLACK, src);
		src.occupySquare(bishop);
		//setup a capture target at destination
		captureTarget = new Pawn(WHITE, dest);
		dest.occupySquare(captureTarget);
		try{
			capture = bishop.move(board, dest);
			//if we are here, great! we moved up a negative slope
			if(capture != true){
				//even though we got here, we want to make sure a capture was indicated also
				fail();
			}
			dest.evictSquare(); //normally we would bother recording captured piece... unnecessary here
			bishop.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(bishop);
			//now lets try moving down a negative slope (reverse of the previous move)
			up = false;
			capture = bishop.move(board, src);
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
	
	//test moving a bishop in not-quite-a-diagonal-line
	@Test
	public void incorrectMoveTest1(){
		board = new Board();
		//starting at c4, we want to move bishop to H8 (this move is most certainly illegal)
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(H, EIGHT);
		bishop = new Bishop(BLACK, src);
		src.occupySquare(bishop);
		try{
			bishop.move(board, dest);
			//if we are here, it means this move was allowed (which it shouldn't be)
			fail();
			
		}
		catch(MoveException e){
			//our move was illegal, so if we caught an exception the test is passed!
		}
	}
	
	//test bishop properly handles obstructions
	@Test
	public void obstructionTest1(){
		board = new Board();
		//starting at c4, we want to move bishop to g8
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(H, EIGHT);
		bishop = new Bishop(BLACK, src);
		src.occupySquare(bishop);
		//we will place an obstruction in its path at f7
		Square block = board.getSquareAt(F,SEVEN);
		obstruction = new Pawn(WHITE, block);
		block.occupySquare(obstruction);
		try{
			bishop.move(board, dest);
			//if we are here, it means this move was allowed (which it shouldn't be)
			fail();
			
		}
		catch(MoveException e){
			//our move was illegal, so if we caught an exception the test is passed!
		}
	}
	
	//test bishop properly handles obstructions
	@Test
	public void obstructionTest2(){
		board = new Board();
		//starting at c4, we want to move bishop to g8
		src = board.getSquareAt(C, FOUR);
		dest = board.getSquareAt(H, EIGHT);
		bishop = new Bishop(BLACK, src);
		src.occupySquare(bishop);
		//we will place an obstruction in its destination
		obstruction = new Pawn(BLACK, dest);
		dest.occupySquare(obstruction);
		try{
			bishop.move(board, dest);
			//if we are here, it means this move was allowed (which it shouldn't be)
			fail();
			
		}
		catch(MoveException e){
			//our move was illegal, so if we caught an exception the test is passed!
		}
	}
}
