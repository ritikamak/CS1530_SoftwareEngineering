import java.util.*;
public class ApplicationInput extends ChessInput
{
	/* Constants */
	
	/* Variables */
	AppOp cmd; // the cmd variable represents what application operations this instance of application input should handle
	TreeMap map; //this map will store any additional instructions the input might communicate
	
	/* Constructor */
	public ApplicationInput(boolean type, AppOp cmd)
	{
		this.type = type;
		this.cmd = cmd;
		map = new TreeMap();
	}
	
	/* Methods */
	
	//adds a key and a DisplayColor value to this ApplicationInput's map variable.
	public void mapDisplayColor(String key, DisplayColor value)
	{
		this.map.put(key, value);
	}
	
	//adds a key and a DisplayColor value to this ApplicationInput's map variable.
	public DisplayColor getDisplayColor(String key)
	{
		return (DisplayColor)this.map.get(key);
	}
	
	public AppOp getCmd()
	{
		return this.cmd;
	}
	
}