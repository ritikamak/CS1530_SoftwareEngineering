import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Chess
{
	public static Game game;
	public static ChessGUI gui;

	public static void newGame()
	{
		game = new Game();
		game.setup();
		gui.display(false);
		gui.dispose();
		gui = new ChessGUI(game);
		gui.display(true);
		InputHandler.initInputHandler(game, gui);
	}

	public static void main(String[] args)
	{
		Kibitzer kib = new Kibitzer();
		kib.start();
		game = new Game();
		game.setup();
		gui = new ChessGUI(game);
		InputHandler.initInputHandler(game, gui);
		gui.display(true);
	}
}
