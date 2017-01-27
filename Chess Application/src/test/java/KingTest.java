import org.junit.Test;
import static org.junit.Assert.*;

public class KingTest{
	
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

	King king;
	Piece obstruction;
	Piece captureTarget;
	Board board = new Board();
	Square src;
	Square dest;
	
	//test if moving a white king forward and backward causes no exceptions to be thrown
	@Test
	public void verticalMoveTest1(){
		boolean forward = true;
		board = new Board();
		//we will place this king at C4 with the intention to move it forward to C5
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(C,FIVE);
		//set king at src
		king = new King(WHITE, src);
		src.occupySquare(king);
		//now we will try to move it forward...
		try{
			king.move(board, dest);
			//if we got this far we succeeded in moving it forward
			king.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(king);
			forward = false;
			//now lets move it backward!
			king.move(board, src);
			//if we succeeded on this move we were totally successful!
			king.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(king);
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
	
	//test if moving a white king left and right causes no exceptions to be thrown
	@Test
	public void horizontallMoveTest2(){
		boolean left = true;
		board = new Board();
		//we will place this king at C4 with the intention to move it left to B4
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(B,FOUR);
		//set king at src
		king = new King(WHITE, src);
		src.occupySquare(king);
		//now we will try to move it left...
		try{
			king.move(board, dest);
			//if we got this far we succeeded in moving it left
			king.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(king);
			left = false;
			//now lets move it right!
			king.move(board, src);
			//if we succeeded on this move we were totally successful!
			king.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(king);
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
	
	//test if moving a white king diagonally up/down positive slope causes no exceptions to be thrown
	@Test
	public void diagonalMoveTest1(){
		board = new Board();
		//we will place this king at C4 with the intention to move it diagonally to d5
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(D,FIVE);
		//set king at src
		king = new King(WHITE, src);
		src.occupySquare(king);
		//now we will try to move it...
		try{
			king.move(board, dest);
			//if we got this far we succeeded in moving it up
			king.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(king);
			//now lets move it down!
			king.move(board, src);
			//if we succeeded on this move we were totally successful!
			king.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(king);
		}
		catch(MoveException e){
			System.out.println(e);
			fail();
		}
	}
	
	//test if moving a white king diagonally up/down negative slope causes no exceptions to be thrown
	@Test
	public void diagonalMoveTest2(){
		board = new Board();
		//we will place this king at C4 with the intention to move it diagonally to b5
		src = board.getSquareAt(C,FOUR);
		dest = board.getSquareAt(B,FIVE);
		//set king at src
		king = new King(WHITE, src);
		src.occupySquare(king);
		//now we will try to move it...
		try{
			king.move(board, dest);
			//if we got this far we succeeded in moving it up
			king.setPosition(dest);
			src.evictSquare();
			dest.occupySquare(king);
			//now lets move it down!
			king.move(board, src);
			//if we succeeded on this move we were totally successful!
			king.setPosition(dest);
			dest.evictSquare();
			src.occupySquare(king);
		}
		catch(MoveException e){
			System.out.println(e);
			fail();
		}
	}
}
