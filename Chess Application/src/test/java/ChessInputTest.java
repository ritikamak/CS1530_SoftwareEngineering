import org.junit.Test;
import static org.junit.Assert.*;

public class ChessInputTest{

     //test if .getType() method in ChessInput.java returns boolean with value false
     //when assigned false (computer). 
     //assertTrue() with negation of .getType() return value of false; test should therefore pass.
     @Test
     public void FalseTest(){
          ChessInput input = new ChessInput();
          //input from stockfish engine (aka comp)
          input.type = false;
          //input.getType() should be false
          assertTrue(!input.getType());
     }

     //test if .getType() method in ChessInput.java returns boolean with value true
     //when assigned true (user). 
     //assertTrue() with .getType() return value of true; test should therefore pass.
     @Test
     public void TrueTest(){
          ChessInput input = new ChessInput();
          //input from user
          input.type = true;
          //input.getType() should be true
          assertTrue(input.getType());
     }
}
