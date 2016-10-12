import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest{
	@Test
	public void testSquare1(){
		Square test = new Square (5,8);
		assertEquals(test.getFile(), 5);
	}
	@Test
	public void testSquare2(){
		Square test = new Square (5,8);
		assertEquals(test.getRank(), 8);
	}
}