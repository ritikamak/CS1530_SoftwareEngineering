import org.junit.Test;
import static org.junit.Assert.*;

public class GameInputTest{

     //Adds (String "key", Square one) to the map; then checks that .getSquare 
     //method passed in parameter (String "key") will in fact equal another Square
     //with same values. 
     //assertTrue that 2 squares with values (4,5) are equal.
     @Test
     public void SquareTest1(){
          //initialize 2 squares with set values that are equal
          Square one = new Square(4,5);
          Square two = new Square (4,5);
          GameInput gi = new GameInput(true);
          //add key and square value to map
          gi.mapSquare("key", one);
          //test that Square value returned from map is equal to that of Square two
          assertTrue(two.equals(gi.getSquare("key")));
     }

     //Adds (String "key", Square one) to the map; then checks that .getSquare 
     //method passed in parameter (String "key") will in fact equal another Square
     //with same values. 
     //assertTrue that 2 squares with values (6,1) are equal.
     @Test
     public void SquareTest2(){
          //initialize 2 squares with set values that are equal
          Square one = new Square(6,1);
          Square two = new Square (6,1);
          GameInput gi = new GameInput(false);
          //add key and square value to map
          gi.mapSquare("key", one);
          //test that Square value returned from map is equal to that of Square two
          assertTrue(two.equals(gi.getSquare("key")));
     }

     //Tests that getter and setter for boolean value to/from the map work appropriately.
     //For key "boo", boolean value=true passed into mapBoolean should mean getBoolean
     //return boolean value is also true.
     //assertTrue(true)
     @Test
     public void KeyTest1(){
          GameInput gi = new GameInput(true);
          //adds boolean value=true to the map
          gi.mapBoolean("boo", true);
          //gets boolean value from the map; passed in (String key)
          assertTrue(gi.getBoolean("boo"));

     }

     //Tests that getter and setter for boolean value to/from the map work appropriately.
     //For key "boo", boolean value=false passed into mapBoolean should mean getBoolean
     //return boolean value is also false.
     //assertFalse(false)
     @Test
     public void KeyTest2(){
          GameInput gi = new GameInput(true);
          //adds boolean value=false to the map
          gi.mapBoolean("boo", false);
          //gets boolean value from the map; passed in (String key)
          assertFalse(gi.getBoolean("boo"));

     }
}
