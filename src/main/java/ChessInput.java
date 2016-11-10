public class ChessInput
{
	/* Constants */
	public final boolean USER = true;
	public final boolean COMP = false;
	
	/* Variables */
	boolean type; //input from user or stockfish engine (aka comp)
	
	/* Constructor */
	
	/* Methods */
	public boolean getType()
	{
		return this.type;
	}
}