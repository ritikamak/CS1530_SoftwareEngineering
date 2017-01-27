/**
 * This is a superclass to more specified subsystems of this 
 * chess application. 
 * Handles input coming in from both "user" and "computer".
 *
 */
public class ChessInput
{
	/* CONSTANTS */
	public final boolean USER = true;
	public final boolean COMP = false;
	
	/* VARIABLES */
	boolean type; 
	//input from user or stockfish engine (aka comp)
		
	/* METHODS */
	public boolean getType()
	{
		return this.type;
	}

}
