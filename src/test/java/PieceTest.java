import org.junit.Test;
import static org.junit.Assert.*;

public class PieceTest{
	@Test
	public void testPiece1(){
		Piece test = new Piece (true, 3);
		assertEquals(test.getColor(), true);
	}
	@Test
	public void testPiece2(){
		Piece test = new Piece (false, 2);
		assertEquals(test.getColor(), false);
	}
	@Test
	public void testPiece3(){
		Piece test = new Piece (true, 3);
		assertEquals(test.getId(), 3);
	}
	@Test
	public void testPiece4(){
		Piece test = new Piece (false, 2);
		assertEquals(test.getId(), 2);
	}
}