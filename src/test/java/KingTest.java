import org.junit.Test;
import static org.junit.Assert.*;
/**
Start testing King
need to test color
need to test all direction movement
need to test for black and white
*/
public class KingTest{
     /**
     Test for correct color
     */
     @Test
     public void testKingColor_1(){
          Piece test = new King (true, new Square(1,1));
          assertEquals(test.getColor(), true);
     }
     /**
     Test for correct color
     */
     @Test
     public void testKingColor_2(){
          Piece test = new King (false, new Square(2,2));
          assertEquals(test.getColor(), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testKingMove_1(){
          Piece test = new King (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(8,8)), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testKingMove_2(){
          Piece test = new King (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(-1,-3)), false);
     }
     /**
     test movment +0 +1
     */
     @Test
     public void testKingMove_3(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(6,5);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +1 +1
     */
     @Test
     public void testKingMove_4(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(7,5);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +1 +0
     */
     @Test
     public void testKingMove_5(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(7,4);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +1 -1
     */
     @Test
     public void testKingMove_6(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(7,3);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment +0 -1
     */
     @Test
     public void testKingMove_7(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(6,3);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment -1 -1
     */
     @Test
     public void testKingMove_8(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(5,3);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment -1 +0
     */
     @Test
     public void testKingMove_9(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(5,4);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment -1 +1
     */
     @Test
     public void testKingMove_10(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(5,5);
          assertTrue(test.movePiece(test_square));
     }
     /**
     Test bad move
     */
     @Test
     public void testKingBedMove_1(){
          Piece test = new King (false, new Square(6,4));
          Square test_square = new Square(7,2);
          assertFalse(test.movePiece(test_square));
     }

}
