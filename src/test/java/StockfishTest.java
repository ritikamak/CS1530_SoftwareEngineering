import org.junit.Test;
import static org.junit.Assert.*;

public class StockfishTest{

	//test that creating a new Stockfish object works properly;
	//assertTrue if method .startEngine() on the object starts the
	//engine and initializes it- thus returning true on success.
	//If returns false then was not successful. 
	@Test
	public void contstructorTest(){
		Stockfish engine = new Stockfish();
		//call method startEngine- returns true on success; false otherwise
		assertTrue(engine.startEngine());
		engine.stopEngine();
	}
}
