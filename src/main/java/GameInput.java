import java.util.*;

/**
 * This is a subclass of ChessInput.
 * It maps the game through String key and Square value.
 *
 */
public class GameInput extends ChessInput
{
	/* VARIABLES */
	TreeMap map;
	
	/* CONSTRUCTORS */
	public GameInput(boolean type)
	{
		this.type = type;
		map = new TreeMap();
	}
	
	/* GETTERS&SETTERS */
	//adds a key and a square value to the map 
	public void mapSquare(String key, Square value)
	{
		this.map.put(key, value);
	}
	//gets a square value from the map 
	public Square getSquare(String key)
	{
		return (Square)this.map.get(key);
	}
	
	// adds a boolean value to the map 
	public void mapBoolean(String key, boolean value)
	{
		this.map.put(key, value);
	}
	
	//gets a boolean value from the map
	public boolean getBoolean(String key)
	{
		return (boolean)this.map.get(key);
	}

}
