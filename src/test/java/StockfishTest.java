import org.junit.Test;
import static org.junit.Assert.*;

public class StockfishTest{
	@Test
	public void contstructorTest(){
		Stockfish engine = new Stockfish();
          assertTrue(engine.startEngine());
	}
}
