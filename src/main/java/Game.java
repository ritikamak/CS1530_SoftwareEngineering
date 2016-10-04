import java.util.*;
/* Each chess game is an object. As you can see below, the chess game object holds two player objects and a board object. */ 
public class Game 
{
	/* CONSTANTS */
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	public static final int H = 7;
	public static final int ONE = 0;
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;
	
	/* VARIABLES */
	Player player1; // a game of chess has two players
	Player player2;
	Board board; // a game of chess has a board
	String name; // a game has a name, this variable will probably be used once we get a save/load game system in place
	
	/* CONSTRUCTORS */
	public Game()
	{
		player1 = new Player(USER, WHITE);
		player2 = new Player(COMP, BLACK);
		board = new Board();
	}
	
	public Game(boolean color)
	{
		player1 = new Player(USER, color);
		player2 = new Player(COMP, color);
		board = new Board();
	}
	
	/* METHODS */
	public void setup(String mode) //right now, this function only checks for mode "test". In the future, I could see various modes implemented, including "newGame" and "loadGame"
	{
		ArrayList<Piece> wpcs;
		ArrayList<Piece> bpcs;
		Piece p;
		int i;
		
		//Get each players list of pieces
		if(player1.getColor() == WHITE){
			wpcs = player1.getPieces();
			bpcs = player2.getPieces();
		}
		else{
			wpcs = player2.getPieces();
			bpcs = player1.getPieces();
		}
		
		//in test setup, we are just setting 16 generic pieces at the two ends of the board
		if(mode.equals("test")){
			//placing white pieces
			for(i = 0; i < 8; i++){
				p = wpcs.get(i);
				p.placePiece(board.getSquareAt(i, ONE));
			}
			for(i = 0; i < 8; i++){
				p = wpcs.get(8 + i);
				p.placePiece(board.getSquareAt(i, TWO));
			}
			//placing black pieces
			for(i = 0; i < 8; i++){
				p = bpcs.get(i);
				p.placePiece(board.getSquareAt(i, EIGHT));
			}
			for(i = 0; i < 8; i++){
				p = bpcs.get(8 + i);
				p.placePiece(board.getSquareAt(i, SEVEN));
			}
		}
	}
	
	public Square getSquareAt(int file, int rank)
	{
		return board.getSquareAt(file, rank);
	}
	
	public Piece getPieceAt(int file, int rank)
	{
		Square source = getSquareAt(file, rank);
		return source.getPiece();
	}
	
	public Piece getPieceAt(Square source) //overloaded method
	{
		return source.getPiece();
	}
	
	public void movePieceAt(int srcFile, int srcRank, int destFile, int destRank)
	{
		Piece p = getPieceAt(srcFile, srcRank);
		Square dest = board.getSquareAt(destFile, destRank);
		p.movePiece(dest);
	}
	
	public void movePieceAt(Square source, Square dest)  //overloaded method
	{
		Piece p = getPieceAt(source);
		p.movePiece(dest);
	}
}