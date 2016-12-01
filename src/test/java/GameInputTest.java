import org.junit.Test;
import static org.junit.Assert.*;

public class GameInputTest{
     @Test
     public void SquareTest1(){
          Square one = new Square(4,5);
          Square two = new Square (4,5);
          GameInput gi = new GameInput(true);
          gi.mapSquare("key", one);
          assertTrue(two.equals(gi.getSquare("key")));
     }
     @Test
     public void SquareTest2(){
          Square one = new Square(6,1);
          Square two = new Square (6,1);
          GameInput gi = new GameInput(false);
          gi.mapSquare("key", one);
          assertTrue(two.equals(gi.getSquare("key")));
     }
     @Test
     public void KeyTest1(){
          GameInput gi = new GameInput(true);
          gi.mapBoolean("boo", true);
          assertTrue(gi.getBoolean("boo"));

     }
     @Test
     public void KeyTest2(){
          GameInput gi = new GameInput(true);
          gi.mapBoolean("boo", false);
          assertFalse(gi.getBoolean("boo"));

     }
}
