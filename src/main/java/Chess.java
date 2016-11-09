import java.util.*;
import java.io.*;

public class Chess
{
	/* Variables */
	public static Game game;
	public static ChessGUI gui;
	static boolean srcSelected; //this is a boolean to indicate if the user has a square currently selected
	static Square srcSquare; //this is the square that the user currently has selected
	static boolean srcPieceSelected; //this is a boolean to indicate if a piece resides in the currently selected square
	static Piece srcPiece;
	static Square destSquare;
	
	/* Function for handling piece color change */
	public static void changePieceDisplayColor(ApplicationInput ai)
	{
		//gets a list of pieces from either user or computer
		ArrayList<Piece> pieces;
		if(game.player1.getType() == ai.getType()){
			pieces = game.player1.getPieces();
		}
		else{
			pieces = game.player2.getPieces();
		}
		for(int i = 0; i < pieces.size(); i++){
			Piece p = pieces.get(i);
			//sets them to the displayColor key in the map
			p.setDisplayColor(ai.getDisplayColor("displayColor"));
		}
		//refresh gui
		gui.boardRefresh();
	}
	/* Function which routes application input to appropriate functions */
	public static void handleApplicationInput(ApplicationInput ai)
	{
		switch(ai.getCmd()){
			case CHANGE_PIECE_DISPLAY_COLOR:
				changePieceDisplayColor(ai);
				break;
			default:
		}
	}
	/* right now, handling game input is really just handling squares selected */
	public static void handleGameInput(GameInput gi)
	{
		//if a source is already selected, and there is a piece there, we are moving that piece
		if(srcSelected && srcPieceSelected){
			destSquare = gi.getSquare("selectedSquare");
			System.out.println("When you clicked " + destSquare.toString() + " I was supposed to" +
							   " move the " + srcPiece.toString() + " on " + srcSquare.toString() +
							   " to there. I will by next sprint! I promise!");
			srcSelected = false;
		}
		//otherwise, select a source
		else{
			srcSquare = gi.getSquare("selectedSquare");
			srcSelected = true;
			System.out.println("Hey look, you selected square " + srcSquare.toString());
			if(srcSquare.isOccupied()){
				srcPiece = srcSquare.getPiece();
				srcPieceSelected = true;
			}
		}
	}
	
	public static void main(String[] args)
	{
		game = new Game();
		game.setup();
		//initially set src square selected to false
		srcSelected = false;
		gui = new ChessGUI(game);
		gui.display(true);
	}
}
