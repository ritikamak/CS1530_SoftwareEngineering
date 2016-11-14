import java.util.*;
public class ApplicationInput extends ChessInput
{
	/* Constants */
	/* Application Options */
	public enum AppOp {
		CHANGE_PIECE_DISPLAY_COLOR,
		FLIP_BOARD,
		NEW_GAME,
		SAVE_GAME,
		LOAD_GAME
	};
	
	/* Variables */
	AppOp cmd; // the cmd variable represents what application operations this instance of application input should handle
	TreeMap map; //this map will store any additional instructions the input might communicate
	
	/* Constructor */
	
	public ApplicationInput(AppOp cmd)
	{
		this.type = type;
		this.cmd = cmd;
		map = new TreeMap();
	}
	
	public ApplicationInput(boolean type, AppOp cmd)
	{
		this.type = type;
		this.cmd = cmd;
		map = new TreeMap();
	}
	
	/* Methods */
	
	//adds a key and a DisplayColor value to this ApplicationInput's map variable.
	public void mapDisplayColor(String key, Piece.DisplayColor value)
	{
		this.map.put(key, value);
	}
	
	//adds a key and a DisplayColor value to this ApplicationInput's map variable.
	public Piece.DisplayColor getDisplayColor(String key)
	{
		return (Piece.DisplayColor)this.map.get(key);
	}
	
	public ApplicationInput.AppOp getCmd()
	{
		return this.cmd;
	}
	
}