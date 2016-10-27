import org.junit.Test;
import static org.junit.Assert.*;

public class UserInputTest{
	@Test
	public void contstructorTest(){ 
		Game g = new Game();
		UserInput ui = new UserInput(g);
		assertEquals(g, ui.getGame());
		assertEquals(ui.isSrcSelected(), false);
	}
}