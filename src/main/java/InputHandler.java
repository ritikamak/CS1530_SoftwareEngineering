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
	static boolean robotColor;
	static boolean activeColor;
	static GameScribe gs;

	/* Public Methods */

	/* initInputHandler() */
	// this gets the input handler initialized
	public static void initInputHandler(Game g, ChessGUI gu)
	{
		game = g;
		gs = new GameScribe(game);
		gui = gu;
		theHuman = game.player_user;
		theRobot = game.player_comp;
		robotColor = theRobot.getColor();
		activeColor = game.getActiveColor();
	}
	
	public static void initInputHandler(Game g, ChessGUI gu, GameScribe gscribe)
	{
		game = g;
		gs = gscribe;
		gui = gu;
		theHuman = game.player_user;
		theRobot = game.player_comp;
		robotColor = theRobot.getColor();
		activeColor = game.getActiveColor();
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
			case SAVE_GAME:
				gs.saveGame();
				break;
			case LOAD_GAME:
				Chess.loadGame();
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
				else if(src.isOccupied() && src.getPiece().belongsTo() == theHuman && activeColor == theHuman.getColor()){
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
			src = gi.getSquare("src");
			attemptMove(gi, src.getPiece(), src, dest);
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
		boolean success;
		String toSave;

		success = game.movePiece(p, src, dest);
		theHuman.unsetSelected();
		gui.refreshBoard();
		if(!success){
			ErrorMsg.infoBox("That move was determined to be illegal. Try another move.", "Illegal Move");
		}
		else{
			toSave = src.toString();
			toSave = toSave+dest.toString();
			gs.saveMoveToCurrentGame(toSave);
			activeColor = game.getActiveColor();
			if(gi.getType() == theHuman.getType()){
				theRobot.getStockfishMove(gs.generateFEN());
			}
		}
		theHuman.unsetSelected();
		gui.refreshBoard();
		gui.updateSidePanel(theHuman, theRobot);
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
