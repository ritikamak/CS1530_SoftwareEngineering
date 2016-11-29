import org.junit.Test;
import static org.junit.Assert.*;

public class RookTest{
	
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

	Rook rook;
	Piece obstruction;
	Piece captureTarget;
	Board board = new Board();
	Square src;
	Square dest;
	
	//test if moving a white rook forward and backward causes no exceptions to be thrown
	@Test
	public void verticalMoveTest1(){
		boolean forward = true;
		board = new Board();
		//we will place this rook at C4 with the intention to move it forward to C8
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(C,EIGHT);
		//set rook at src
		rook = new Rook(WHITE, src);
		src.occupySquare(rook);
		//now we will try to move it forward...
		try{
			rook.move(board, dest);
			//if we got this far we succeeded in moving it forward
			rook.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(rook);
			forward = false;
			//now lets move it backward!
			rook.move(board, src);
			//if we succeeded on this move we were totally successful!
			rook.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(rook);
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
	
	//test if moving a black rook forward and backward causes no exceptions to be thrown
	@Test
	public void verticalMoveTest2(){
		boolean forward = true;
		board = new Board();
		//we will place this rook at C4 with the intention to move it forward to C1
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(C,ONE);
		//set rook at src
		rook = new Rook(BLACK, src);
		src.occupySquare(rook);
		//now we will try to move it forward...
		try{
			rook.move(board, dest);
			//if we got this far we succeeded in moving it forward
			rook.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(rook);
			forward = false;
			//now lets move it backward!
			rook.move(board, src);
			//if we succeeded on this move we were totally successful!
			rook.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(rook);
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
	
	//test if moving a white rook left and right causes no exceptions to be thrown
	@Test
	public void horizontalMoveTest1(){
		boolean left = true;
		board = new Board();
		//we will place this rook at C4 with the intention to move it left to A4
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(A,FOUR);
		//set rook at src
		rook = new Rook(WHITE, src);
		src.occupySquare(rook);
		//now we will try to move it left...
		try{
			rook.move(board, dest);
			//if we got this far we succeeded in moving it left
			rook.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(rook);
			left = false;
			//now lets move it right!
			rook.move(board, src);
			//if we succeeded on this move we were totally successful!
			rook.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(rook);
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
	
	//test if moving a black rook left and right causes no exceptions to be thrown
	@Test
	public void horizontalMoveTest2(){
		boolean left = true;
		board = new Board();
		//we will place this rook at C4 with the intention to move it left to H4
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(H,FOUR);
		//set rook at src
		rook = new Rook(BLACK, src);
		src.occupySquare(rook);
		//now we will try to move it left...
		try{
			rook.move(board, dest);
			//if we got this far we succeeded in moving it left
			rook.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(rook);
			left = false;
			//now lets move it right!
			rook.move(board, src);
			//if we succeeded on this move we were totally successful!
			rook.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(rook);
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
	
	//test if moving a rook diagonally results in exception
	@Test
	public void diagonalFailTest1(){
		board = new Board();
		//we will place this rook at C4 with the intention to move it diagonally to D5
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(D,FIVE);
		//set rook at src
		rook = new Rook(BLACK, src);
		src.occupySquare(rook);
		//now we will try to move it diagonally...
		try{
			rook.move(board, dest);
			//if move succeeded, test failed
			fail();
		}
		catch(MoveException e){
			//if exception was caught test is successful
		}
	}
	
	//test if moving a white rook through/into obstruction results in exception
	@Test
	public void obstructionTest1(){
		board = new Board();
		//we will place this rook at C4 with the intention to move it right to H4
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(H,FOUR);
		//block will occur in the middle of the move
		Square block = board.getSquareAt(G,FOUR);
		//set rook at src
		rook = new Rook(BLACK, src);
		src.occupySquare(rook);
		//create an obstruction and place it on block square
		obstruction = new Pawn(WHITE, block);
		block.occupySquare(obstruction);
		//now we will try to move rook through obstruction
		try{
			rook.move(board, dest);
			//if move succeeded, test failed
			fail();
		}
		catch(MoveException e){
			//if exception was caught initial part of test is successful
			//now lets make sure rook cannot end its move in a square with a friendly piece
			//evict the old block and make the new block the same as the destination
			block.evictSquare();
			block = dest;
			//create a friendly piece and place it on our block square
			obstruction = new Pawn(BLACK, block);
			block.occupySquare(obstruction);
			try{
				rook.move(board, dest);
				//if move succeeded, test failed
				fail();
			}
			catch(MoveException e1){
				//if exception is caught, test is completely successful!
			}
		}
	}
}