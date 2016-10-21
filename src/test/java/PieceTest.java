import org.junit.Test;
import static org.junit.Assert.*;

public class PieceTest{
	@Test
	public void testPiece1(){
		Piece test = new Pawn (true, new Square(1,1));
		assertEquals(test.getColor(), true);
	}
	@Test
	public void testPiece2(){
		Piece test = new Pawn (false, new Square(2,2));
		assertEquals(test.getColor(), false);
	}
}
