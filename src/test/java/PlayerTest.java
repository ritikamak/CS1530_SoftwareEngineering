import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest{
	@Test
	public void testPlayer1(){
		Player test = new Player (true, false);
		assertEquals(test.getType(), true);
	}
	@Test
	public void testPlayer2(){
		Player test = new Player (false, true);
		assertEquals(test.getColor(), true);
	}
}