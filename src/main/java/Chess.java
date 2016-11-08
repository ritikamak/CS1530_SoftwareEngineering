import java.util.*;
import java.io.*;

public class Chess
{
	public static Game game;
	public static UserInput ui;
	public static ChessGUI gui;
	public static void main(String[] args)
	{
		game = new Game();
		game.setup();
		ui = new UserInput(game);
		gui = new ChessGUI(game, ui);
		gui.display(true);
	}
}
