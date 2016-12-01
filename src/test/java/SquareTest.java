import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest{
	Square test;
	Piece a;
	Piece b;
	
	//tests that getter for int file value works; assigned file=5
	@Test
	public void testSquareFile(){
		//initialize new Square object; file=5 rank=7
		test = new Square (5,7);
		//test.getFile() should return 5
		assertEquals(test.getFile(), 5);
	}

	//tests that getter for int rank value works; assigned rank=7
	@Test
	public void testSquareRank(){
		//initialize new Square object; file=5 rank=7
		test = new Square (5,7);
		//test.getRank() should return 7
		assertEquals(test.getRank(), 7);
	}
	
	//tests that when a square is assigned a piece to occupy it,
	//isOccupied() returns true, indicating that it is indeed occupied.
	@Test
	public void testSquareOccupy()
	{
		a = new Pawn(true, test);
		//initialize new Square object; file=0 rank=0
		test = new Square (0,0);
		// testing square occupy
		test.occupySquare(a);
		//test.isOccupied() should return boolean true
		assertEquals(test.isOccupied(), true);
	}
}
