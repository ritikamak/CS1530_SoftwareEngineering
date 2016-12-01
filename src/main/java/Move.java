/**
 * Piece movement implementation - an interface for Move.
 *
 */
public interface Move
{
	//movement interface
	public boolean move(Board board, Square dest) throws MoveException;
}
