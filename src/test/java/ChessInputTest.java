import org.junit.Test;
import static org.junit.Assert.*;

public class ChessInputTest{
     @Test
     public void FalseTest(){
          ChessInput input = new ChessInput();
          input.type = false;
          assertTrue(!input.getType());
     }
     @Test
     public void TrueTest(){
          ChessInput input = new ChessInput();
          input.type = true;
          assertTrue(input.getType());
     }
}
