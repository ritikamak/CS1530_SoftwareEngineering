import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest{
	Square test;
	Piece a;
	Piece b;
	
	@Test
	public void testSquareFile(){
		test = new Square (5,7);
		assertEquals(test.getFile(), 5);
	}
	@Test
	public void testSquareRank(){
		test = new Square (5,7);
		assertEquals(test.getRank(), 7);
	}
	
	@Test
	public void testSquareOccupy()
	{
		a = new Pawn(true, test);
		test = new Square (0,0);
		// testing square occupy
		test.occupySquare(a);
		assertEquals(test.isOccupied(), true);
	}
}