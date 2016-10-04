import java.util.*;
import java.io.*;

/* Currently this class is just a test driver for our game. It will probably change drastically once we get GUI set up*/
public class Chess
{
	public static Game game;
	public static Scanner kb;
	public static void main(String[] args)
	{
		System.out.println("Would you like to play a game?"); //definitely not the puppet from the Saw movies or the military supercomputer from WarGames!
		System.out.println("1. New Game\n2. Exit");
		kb = new Scanner(System.in);
		try{
			String input = kb.nextLine();
			if(Integer.parseInt(input) == 1){
				mainMenu();
			}
			else if(Integer.parseInt(input) == 2){
				chessExit(0);
			}
			else{
				throw new IOException();
			}
		}
		catch(IOException e){
			System.out.println("Please use an integer between 1-2, friendo!");
			chessExit(1);
		}
	}
	
	public static void mainMenu()
	{	
		System.out.println("Starting a new game!");
		game = new Game();
		game.setup("test");
		while(true){
			gameMenu();
			try{
				String input = kb.nextLine();
				if(Integer.parseInt(input) == 1){
					moveAPiece();
				}
				else if(Integer.parseInt(input) == 2){
					checkASquare();
				}
				else if(Integer.parseInt(input) == 3){
					chessExit(0);
				}
				else{
					throw new IOException();
				}
			}
			catch(IOException e){
				System.out.println("Please use an integer between 1-3, friendo!");
				chessExit(1);
			}
		}
		
	}
	
	public static void gameMenu()
	{
		System.out.println("1. Move a piece");
		System.out.println("2. Check a square");
		System.out.println("3. Exit");
	}
	
	public static void moveAPiece()
	{
		int srcFile;
		int srcRank;
		int destFile;
		int destRank;
		Square source;
		Square dest;
		Piece p;
		String input;
		
		System.out.println("Enter the board location of the piece you'd like to move (eg. 'a2'):");
		input = kb.nextLine();
		srcFile = (int) input.charAt(0) - 97; //97 is ascii value of 'a'
		srcRank = Integer.parseInt("" + input.charAt(1)) - 1;
		p = game.getPieceAt(srcFile, srcRank);	
		if(p != null){
			source = game.getSquareAt(srcFile, srcRank);
			System.out.println("You have selected the " + p.toString() + "on square " + source.toString());
			System.out.println("Enter the board location of the square you would like to move to (eg. 'a3'):");
			input = kb.nextLine();
			destFile = (int)(input.charAt(0) - 97); //97 is ascii value of 'a'
			destRank = Integer.parseInt("" + input.charAt(1)) - 1;
			dest = game.getSquareAt(destFile, destRank);
			if(dest != null){
				game.movePieceAt(source, dest);
				System.out.println("You have moved the " + p.toString() + " from " + source.toString() + " to " + dest.toString());
				return;
			}
			else{
				System.out.println("Piece cannot be placed on square " + input);
				return;
			}
		}
		else{
			System.out.println("There is no piece at " + input);
			return;
		}
	}
	
	public static void checkASquare()
	{
		int srcFile;
		int srcRank;
		Square source;
		Piece p;
		String input;
		
		System.out.println("Enter the board location of the square you'd like to check (eg. 'a2'):");
		input = kb.nextLine();
		srcFile = (int) input.charAt(0) - 97; //97 is ascii value of 'a'
		srcRank = Integer.parseInt("" + input.charAt(1)) - 1;
		source = game.getSquareAt(srcFile, srcRank);
		if(source != null){
			if(source.isOccupied()){
				p = game.getPieceAt(source);
				System.out.println("There is a " + p.toString() + " at square " + source.toString());
				return;
			}
			else{
				System.out.println("There is no piece currently at square " + source.toString());
			}
		}
		else{
			System.out.println("No such square exists at " + input);
			return;
		}
	}
	
	public static void chessExit(int error)
	{
		if(error != 0){
			kb.close();
			System.exit(error);
		}
		else{
			System.out.println("You play a good game, boy!"); //I couldn't help myself...
			kb.close();
			System.exit(error);
		}
	}

	
}