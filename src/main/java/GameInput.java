import java.util.*;
public class GameInput extends ChessInput
{
	/* Variables */
	TreeMap map;
	
	/* Constructor */
	public GameInput(boolean type)
	{
		this.type = type;
		map = new TreeMap();
	}
	
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