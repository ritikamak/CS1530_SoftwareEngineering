import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest{

	//test that when a new Board object is created, it is done so properly.
	//Should be a 8x8 grid of squares. Call method .getSquareAt() on the
	//object; should return a Square[][] which represents the file (a-h) and 
	//rank(1-8). 
	//A board is 8x8, thus Square s should be null because no 9th file or 9th rank. 
	//Perform assertNull() on s.
     @Test
     public void boardTest_1(){
          Board b = new Board();
          Square s = b.getSquareAt(9,9);
          assertNull(s);
     }
}
