import java.util.*;
import java.io.*;

public class InputHandler
{
	/* Constants */
	public static final boolean USER = true;
    public static final boolean COMP = true;
    public static final boolean BLACK = true;
    public static final boolean WHITE = false;
	
	/* variables */
	static ChessGUI gui;
	static Game game;
	static Player theHuman; //user
	static Player theRobot; //comp
	
	/* Public Methods */
	
	/* initInputHandler() */
	// this gets the input handler initialized
	public static void initInputHandler(Game g, ChessGUI gu)
	{
		game = g;
		gui = gu;
		theHuman = game.player_user;
		theRobot = game.player_comp;
	}
	
	/* handleApplicationInput() */
	// this routes an application input to the proper handler function
	public static void handleApplicationInput(ApplicationInput ai)
	{
		switch(ai.getCmd()){
			case CHANGE_PIECE_DISPLAY_COLOR:
				changePieceDisplayColor(ai);
				break;
			case FLIP_BOARD:
				flipTheBoard(ai);
				break;
			case NEW_GAME:
				Chess.newGame();
				break;
			default:
		}
	}
	
	/* handleGameInput() */
	// this routes a game input to the proper handler function
	public static void handleGameInput(GameInput gi)
	{
		boolean type;
		Square src;
		Square dest;
		
		type = gi.getType();
		dest = gi.getSquare("dest");
		//user game input
		if(type == USER){
			//if the user has a square selected
			if(theHuman.hasSelectedSquare()){
				src = theHuman.getSelected();
				//if the user clicks on the same square they have previously selected they have then deselected that square
				if(dest == src){
					deselectSquare(gi);
				}
				//if the square they previously selected contained one of their own pieces we will attempt to move it
				else if(src.isOccupied() && src.getPiece().belongsTo() == theHuman){
					attemptMove(gi, src.getPiece(), src, dest);
				}
				//if no piece they controlled was in the src square, then just highlight the new dest square
				else{
					highlightNewSquare(gi);
				}
			}
			//if the user does not have a square selected
			else{
				highlightNewSquare(gi);
			}
		}
		//comp game input
		else{
		}
	}
	
	/* Private Methods */
	
	/* highlightNewSquare() */
	// highlights the most recently selected square
	private static void highlightNewSquare(GameInput gi)
	{
		Square dest;
		Square src;
		
		dest = gi.getSquare("dest");
		theHuman.setSelected(dest);
		gui.refreshBoard();
	}
	
	/* deselectSquare() */
	// deselects currently selected square
	private static void deselectSquare(GameInput gi)
	{
		Square dest;
		Square src;
		
		dest = gi.getSquare("dest");
		theHuman.unsetSelected();
		gui.refreshBoard();
	}
	
	/* attemptMove() */
	// attempts to move piece
	private static void attemptMove(GameInput gi, Piece p, Square src, Square dest)
	{
		boolean capture;
		boolean check;
		Player inputPlayer; //player at the source of this input
		Player opposingPlayer; //that player's opponent
		Piece capturedPiece;
	
		try{
			//we begin by letting our piece move function determine the legality of the move (an exception will be thrown if there is a problem)
			//if move is successful, it will return true or false depending on whether the move captures a piece or not
			capture = p.move(game.getBoard(), dest);
			game.movePiece(p, src, dest, capture);
			//next we need to test if move resulted in check
			inputPlayer = game.getPlayer(gi.getType());
			opposingPlayer = game.getPlayer(!gi.getType());
			//if input player's own move put them in check, thats illegal and we need to rollback the move
			if(inputPlayer.isInCheck()){
				game.undoMovePiece();
				ErrorMsg.infoBox("Detected an illegal move: cannot put/leave own king in check.", "Illegal Move");
				System.out.println("Detected an illegal move: cannot put/leave own king in check.");
			}
			//if input player has put opponent in check as a result of their move, notify game that there is a player in check
			else if(opposingPlayer.isInCheck()){
				game.playerInCheck(opposingPlayer);
				System.out.println(opposingPlayer.getKing().toString() + " is in check!");
			}
			//else, no one is in check and game needs to know that
			else{
				game.playerOutOfCheck();
			}
		}
		catch(MoveException e){
			ErrorMsg.infoBox("Detected an illegal move: " + e.toString(), "Illegal Move");
			System.out.println("Detected an illegal move: " + e.toString());
		}
		theHuman.unsetSelected();
		gui.refreshBoard();
	}
	
	/* changePieceDisplayColor() */
	//this function handles piece color change
	private static void changePieceDisplayColor(ApplicationInput ai)
	{
		//gets a list of pieces from either user or computer
		ArrayList<Piece> pieces;
		if(ai.getType() == USER){
			pieces = game.player_user.getPieces();
		}
		else{
			pieces = game.player_comp.getPieces();
		}
		for(int i = 0; i < pieces.size(); i++){
			Piece p = pieces.get(i);
			//sets them to the displayColor key in the map
			p.setDisplayColor(ai.getDisplayColor("displayColor"));
		}
		//refresh gui
		gui.refreshPieces();
	}
	
	/* flipTheBoard() */
	// this function flips the board, it also sounds like "flipthebird" 
	private static void flipTheBoard(ApplicationInput ai)
	{
		//I'm the mother-flippin' Rhyme-nocerous! I'm the hiphop-oppotamous my lyrics are bottomless...
		gui.flipBoard();
	}
	
}