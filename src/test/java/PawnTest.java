import org.junit.Test;
import static org.junit.Assert.*;
/**
Start testing pawn
need to test color
need to test single movement
need to test double movement
need to test capture
need to test for black and white
*/
public class PawnTest{
	//reminder for square(file, rank)
	//reminder file is x axis, rank is y axis
	/**
	Test for color color
	*/
	@Test
	public void testPawnColor_1(){
		Piece test = new Pawn (true, new Square(1,1));
		assertEquals(test.getColor(), true);
	}
	/**
	Test for correct color
	*/
	@Test
	public void testPawnColor_2(){
		Piece test = new Pawn (false, new Square(2,2));
		assertEquals(test.getColor(), false);
	}
	/**
	Test going out of bounds
	*/
	@Test
	public void testPawnMove_1(){
		Piece test = new Pawn (false, new Square(2,2));
		assertEquals(test.movePiece(new Square(8,8)), false);
	}
	/**
	Test going out of bounds
	*/
	@Test
	public void testPawnMove_2(){
		Piece test = new Pawn (false, new Square(2,2));
		assertEquals(test.movePiece(new Square(-1,-3)), false);
	}
	/**
	Test white pawn movment one square
	*/
	@Test
	public void testPawnMove_3(){
		Piece test = new Pawn (false, new Square(6,4));
		Square test_square = new Square(6,5);
		assertTrue(test.movePiece(test_square));
	}
	/**
	Test white pawn movment two squares
	*/
	@Test
	public void testPawnMove_4(){
		Piece test = new Pawn (false, new Square(3,2));
		Square test_square = new Square(3,4);
		assertTrue(test.movePiece(test_square));
	}
	/**
	Test white pawn capture
	*/
	@Test
	public void testPawnMove_5(){
		Piece test = new Pawn (false, new Square(3,2));
		Square test_square = new Square(2,3);
		test_square.placePiece(new Pawn(true, new Square(2,3)));
		assertTrue(test.movePiece(test_square));
	}
	/**
	Test white pawn capture own piece
	*/
	@Test
	public void testPawnIllegalCapture_1(){
		Piece test = new Pawn (false, new Square(3,2));
		Square test_square = new Square(2,3);
		test_square.placePiece(new Pawn(false, new Square(2,3)));
		assertFalse(test.movePiece(test_square));
	}
	/**
	Test white pawn making illegal move
	Move too far vertically
	*/
	@Test
	public void testPawnBadMove_1(){
		Piece test = new Pawn (false, new Square(3,2));
		Square test_square = new Square(3,5);
		assertFalse(test.movePiece(test_square));
	}
	/**
	Test white pawn making illegal move
	Move horizontally
	*/
	@Test
	public void testPawnBadMove_2(){
		Piece test = new Pawn (false, new Square(3,2));
		Square test_square = new Square(5,2);
		assertFalse(test.movePiece(test_square));
	}
	/**
	Test white pawn making illegal move
	Move backwards
	*/
	@Test
	public void testPawnBadMove_3(){
		Piece test = new Pawn (false, new Square(3,2));
		Square test_square = new Square(3,1);
		assertFalse(test.movePiece(test_square));
	}
	/**
	Test black pawn movment one square
	*/
	@Test
	public void testPawnMove_6(){
		Piece test = new Pawn (true, new Square(6,4));
		Square test_square = new Square(6,3);
		assertTrue(test.movePiece(test_square));
	}
	/**
	Test black pawn movment two squares
	*/
	@Test
	public void testPawnMove_7(){
		Piece test = new Pawn (true, new Square(3,7));
		Square test_square = new Square(3,5);
		assertTrue(test.movePiece(test_square));
	}
	/**
	Test black pawn capture
	*/
	@Test
	public void testPawnMove_8(){
		Piece test = new Pawn (true, new Square(6,5));
		Square test_square = new Square(5,4);
		test_square.placePiece(new Pawn(false, new Square(5,4)));
		assertTrue(test.movePiece(test_square));
	}
	/**
	Test black pawn capture own piece
	*/
	@Test
	public void testPawnIllegalCapture_2(){
		Piece test = new Pawn (true, new Square(6,5));
		Square test_square = new Square(5,4);
		test_square.placePiece(new Pawn(true, new Square(5,4)));
		assertFalse(test.movePiece(test_square));
	}
	/**
	Test black pawn making illegal move
	Move too far vertically
	*/
	@Test
	public void testPawnBadMove_4(){
		Piece test = new Pawn (true, new Square(4,5));
		Square test_square = new Square(4,2);
		assertFalse(test.movePiece(test_square));
	}
	/**
	Test black pawn making illegal move
	Move horizontally
	*/
	@Test
	public void testPawnBadMove_5(){
		Piece test = new Pawn (true, new Square(4,5));
		Square test_square = new Square(3,5);
		assertFalse(test.movePiece(test_square));
	}
	/**
	Test black pawn making illegal move
	Move backwards
	*/
	@Test
	public void testPawnBadMove_6(){
		Piece test = new Pawn (true, new Square(4,5));
		Square test_square = new Square(4,6);
		assertFalse(test.movePiece(test_square));
	}


}
