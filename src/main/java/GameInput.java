import java.util.*;
public class GameInput extends ChessInput
{
	/* Variables */
	Game game;
	TreeMap map;
	
	/* Constructor */
	public GameInput(boolean type, Game game)
	{
		this.type = type;
		this.game = game;
		map = new TreeMap();
	}
	
	//adds a key and a square value to this ApplicationInput's map variable.
	public void mapSquare(String key, Square value)
	{
		this.map.put(key, value);
	}
	
	public Square getSquare(String key)
	{
		return (Square)this.map.get(key);
	}
}