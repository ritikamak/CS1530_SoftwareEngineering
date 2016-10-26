import org.junit.Test;
import static org.junit.Assert.*;
public class BoardTest{
     @Test
     public void boardTest_1(){
          Board b = new Board();
          Square s = b.getSquareAt(9,9);
          assertNull(s);
     }
}
