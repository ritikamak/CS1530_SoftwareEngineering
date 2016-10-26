import org.junit.Test;
import static org.junit.Assert.*;
/**
Start testing Rook
need to test color
need to test vertical movment
need to test horizontal movement
need to test for black and white
*/
public class RookTest{
     /**
     Test for correct color
     */
     @Test
     public void testRookColor_1(){
          Piece test = new Rook (true, new Square(1,1));
          assertEquals(test.getColor(), true);
     }
     /**
     Test for correct color
     */
     @Test
     public void testRookColor_2(){
          Piece test = new Rook (false, new Square(2,2));
          assertEquals(test.getColor(), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testRookMove_1(){
          Piece test = new Rook (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(8,8)), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testRookMove_2(){
          Piece test = new Rook (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(-1,-3)), false);
     }
     /**
     test movment vertical +3
     */
     @Test
     public void testRookMove_3(){
          Piece test = new Rook (false, new Square(3,3));
          Square test_square = new Square(3,6);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment vertical -3
     */
     @Test
     public void testRookMove_4(){
          Piece test = new Rook (false, new Square(3,3));
          Square test_square = new Square(3,0);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment horizontal +3
     */
     @Test
     public void testRookMove_5(){
          Piece test = new Rook (false, new Square(3,3));
          Square test_square = new Square(6,3);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment horizontal -3
     */
     @Test
     public void testRookMove_6(){
          Piece test = new Rook (false, new Square(3,3));
          Square test_square = new Square(0,3);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test bad move
     */
     @Test
     public void testRookBadMove_1(){
          Piece test = new Rook (false, new Square(3,3));
          Square test_square = new Square(4,4);
          assertFalse(test.movePiece(test_square));
     }

}
