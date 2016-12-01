import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Chess
{
	public static Game game;
	public static ChessGUI gui;
	public static Stockfish stockfish; //stockfish enginee
	public static ArrayList<String> fenList; //list of player moves;
	public static GameScribe gamescribe;
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
		fenList = new ArrayList<String>();
		stockfish = new Stockfish();
		stockfish.startEngine();
		Kibitzer kib = new Kibitzer();
		kib.start();
		gamescribe = new GameScribe(game);
		game = new Game();
		game.setup();
		gui = new ChessGUI(game);
		InputHandler.initInputHandler(game, gui);
		gui.display(true);
	}
}
