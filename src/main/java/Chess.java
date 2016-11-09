import java.util.*;
import java.io.*;

public class Chess
{
	public static Game game;
	public static ChessGUI gui;
	
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
	
	public static void handleApplicationInput(ApplicationInput ai)
	{
		switch(ai.getCmd()){
			case CHANGE_PIECE_DISPLAY_COLOR:
				changePieceDisplayColor(ai);
				break;
			default:
		}
	}
	
	public void handleGameInput(GameInput gi)
	{
		
	}
	
	public static void main(String[] args)
	{
		game = new Game();
		game.setup();
		gui = new ChessGUI(game);
		gui.display(true);
	}
}
