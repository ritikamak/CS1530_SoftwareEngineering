import org.junit.Test;
import static org.junit.Assert.*;
/**
Start testing Bishop
need to test color
need to test positive slope
need to test negative slope
need to test for black and white
*/
public class BishopTest{
     /**
     Test for correct color
     */
     @Test
     public void testBishopColor_1(){
          Piece test = new Bishop (true, new Square(1,1));
          assertEquals(test.getColor(), true);
     }
     /**
     Test for correct color
     */
     @Test
     public void testBishopColor_2(){
          Piece test = new Bishop (false, new Square(2,2));
          assertEquals(test.getColor(), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testBishopMove_1(){
          Piece test = new Bishop (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(8,8)), false);
     }
     /**
     Test going out of bounds
     */
     @Test
     public void testBishopMove_2(){
          Piece test = new Bishop (false, new Square(2,2));
          assertEquals(test.movePiece(new Square(-1,-3)), false);
     }
     /**
     test movment slope -1
     */
     @Test
     public void testBishopMove_3(){
          Piece test = new Bishop (false, new Square(2,5));
          Square test_square = new Square(3,6);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment slope -1
     */
     @Test
     public void testBishopMove_4(){
          Piece test = new Bishop (false, new Square(2,5));
          Square test_square = new Square(1,6);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment slope +1
     */
     @Test
     public void testBishopMove_5(){
          Piece test = new Bishop (false, new Square(2,5));
          Square test_square = new Square(1,4);
          assertTrue(test.movePiece(test_square));
     }
     /**
     test movment slope +1
     */
     @Test
     public void testBishopMove_6(){
          Piece test = new Bishop (false, new Square(2,5));
          Square test_square = new Square(4,7);
          assertTrue(test.movePiece(test_square));
     }


}
