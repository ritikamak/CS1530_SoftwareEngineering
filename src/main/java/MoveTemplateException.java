/**
 * Piece movement implementation - an exception for movementTemplate.
 *
 */
class MoveTemplateException extends Exception
{
    public MoveTemplateException(){}
     //custom exception for the movementTemplate
    public MoveTemplateException(String message)
    {
		super(message);
    }
}
