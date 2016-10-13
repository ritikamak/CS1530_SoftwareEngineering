import java.util.*;
import java.io.*;

/* Currently this class is just a test driver for our game. It will probably change drastically once we get GUI set up*/
public class Chess
{
	public static Game game;
	public static ChessGUI gui;
	
	public static void main(String[] args)
	{
		game = new Game();
		game.setup("test");
		gui = new ChessGUI();
		gui.display(true);
	}
}