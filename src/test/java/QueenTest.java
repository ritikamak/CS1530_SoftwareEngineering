import org.junit.Test;
import static org.junit.Assert.*;
/**
Start testing Queen
need to test color
need to test vertical movment
need to test horizontal movement
need to test positive slope
need to test negative slope
need to test for black and white
*/
public class QueenTest{
     /**
     Test for correct color
     */
     @Test
     public void testQueenColor_1(){
          Piece test = new Queen (true, new Square(1,1));
          assertEquals(test.getColor(), true);
     }
     /**
     Test for correct color
     */
     @Test
     public void testQueenColor_2(){
          Piece test = new Queen (false, new Square(2,2));
          assertEquals(test.getColor(), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testQueenMove_1(){
          Piece test = new Queen (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(8,8)), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testQueenMove_2(){
          Piece test = new Queen (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(-1,-3)), false);
     }
     /**
     test movment slope -1
     */
     @Test
     public void testQueenMove_3(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(3,6);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment slope -1
     */
     @Test
     public void testQueenMove_4(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(1,6);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment slope +1
     */
     @Test
     public void testQueenMove_5(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(1,4);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment slope +1
     */
     @Test
     public void testQueenMove_6(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(4,7);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment vetical +2
     */
     @Test
     public void testQueenMove_7(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(2,7);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment vertical -2
     */
     @Test
     public void testQueenMove_8(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(2,3);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment horizontal +2
     */
     @Test
     public void testQueenMove_9(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(4,5);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment horizontal -2
     */
     @Test
     public void testQueenMove_10(){
          Piece test = new Queen (false, new Square(2,5));
          Square test_square = new Square(0,5);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test bad move
     */
     @Test
     public void testQueenBadtMove_8(){
          Piece test = new Queen (false, new Square(4,3));
          Square test_square = new Square(3,1);
          assertFalse(test.movePiece(test_square));
     }

}
