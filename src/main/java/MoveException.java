/**
 * Piece movement implementation - an exception for illegal moves.
 *
 */
class MoveException extends Exception
{
     //custom exception for illegal moves
    public MoveException(){}

    public MoveException(String message)
    {
		super(message);
    }
}
