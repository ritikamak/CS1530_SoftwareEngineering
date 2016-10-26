import org.junit.Test;
import static org.junit.Assert.*;
/**
Start testing knight
need to test color
need to test all movment
need to test for black and white
*/
public class KnightTest{
     /**
     Test for correct color
     */
     @Test
     public void testKnightColor_1(){
          Piece test = new Knight (true, new Square(1,1));
          assertEquals(test.getColor(), true);
     }
     /**
     Test for correct color
     */
     @Test
     public void testKnightColor_2(){
          Piece test = new Knight (false, new Square(2,2));
          assertEquals(test.getColor(), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testKnightMove_1(){
          Piece test = new Knight (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(8,8)), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testKnightMove_2(){
          Piece test = new Knight (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(-1,-3)), false);
     }
     /**
     test movment -1 +2
     */
     @Test
     public void testKnightMove_3(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(3,5);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +1 +2
     */
     @Test
     public void testKnightMove_4(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(5,5);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +2 -1
     */
     @Test
     public void testKnightMove_5(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(6,2);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +2 +1
     */
     @Test
     public void testKnightMove_6(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(6,4);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +1 -2
     */
     @Test
     public void testKnightMove_7(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(5,1);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment -1 -2
     */
     @Test
     public void testKnightMove_8(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(3,1);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment -2 -1
     */
     @Test
     public void testKnightMove_9(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(2,2);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment -2 +1
     */
     @Test
     public void testKnightMove_10(){
          Piece test = new Knight (false, new Square(4,3));
          Square test_square = new Square(2,4);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test bad move
     */
     @Test
     public void testKnightBadMove_1(){
          Piece test = new Knight (false, new Square(3,3));
          Square test_square = new Square(3,4);
          assertFalse(test.movePiece(test_square));
     }

}
