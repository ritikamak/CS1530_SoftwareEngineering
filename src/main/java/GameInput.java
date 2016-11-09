public class GameInput extends ChessInput
{
	/* Variables */
	Game game; //the game input needs a game to communicate with
	boolean srcSelected; //this is a boolean to indicated if the user has a square currently selected
	Square srcSquare; //this is the square that the user currently has selected
	boolean srcPieceSelected;
	Piece srcPiece;
	Square destSquare;
	
	/* Constructor */
	public GameInput(boolean type, Game game)
	{
		this.type = type;
		this.game = game;
	}
	
	/* handles input with square selection */
	public boolean handleInput(int file, int rank)
	{
		//if a source is already selected, and there is a piece there, we are moving that piece
		if(srcSelected && srcPieceSelected){
			destSquare = game.getSquareAt(file, rank);
			System.out.println("When you clicked " + destSquare.toString() + " I was supposed to" +
							   " move the " + srcPiece.toString() + " on " + srcSquare.toString() +
							   " to there. I will by next sprint! I promise!");
			srcSelected = false;
		}
		//otherwise, select a source
		else{
			srcSquare = game.getSquareAt(file, rank);
			srcSelected = true;
			System.out.println("Hey look, you selected square " + srcSquare.toString());
			if(srcSquare.isOccupied()){
				srcPiece = srcSquare.getPiece();
				srcPieceSelected = true;
			}
		}
		
		return srcSelected;
	}
	
	// returns this UI's associated game object
	public Game getGame()
	{
		return game;
	}
	
	// returns if a source square is selected or not
	public boolean isSrcSelected()
	{
		return srcSelected;
	}
	
	// returns the source square currently selected
	public Square getSrcSelected()
	{
		return srcSquare;
	}
	
}