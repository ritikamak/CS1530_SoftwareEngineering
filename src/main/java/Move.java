public interface Move 
{
	public boolean move(Board board, Square dest) throws MoveException;
	
	
	//public boolean hasKingInPath(MoveTemplate move);
	//public boolean hasKingInCheck(MoveTemplate move);
}