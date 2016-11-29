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
	Player player_user; // a game of chess has two players
	Player player_comp;
	boolean check;// a boolean letting the game know there is a player in check
	Player inCheck; //game takes note if a player is in check
	Board board; // a game of chess has a board
	String name; // a game has a name, this variable will probably be used once we get a save/load game system in place
	Square enPassant; //square behind the pawn that just double moved
	boolean isEnPassant; //boolean that indicates there if an enPassant is set or not
	Piece lastMoved;
	Piece lastCaptured;
	boolean lastWasACapture;
	Square lastSrc;
	Square lastDest;
	boolean activeColor; //which color moves NEXT? (effectively a turn tracker)
	int fullmoveClock; //a number which increments after every black move (two turns)
	int halfmoveClock; //the number of halfmoves (one turn) since the last capture or pawn advance -- starts at 0
	
	/* CONSTRUCTORS */
	/*Default constructor gives player the first move*/
	public Game()
	{
		board = new Board();
		player_user = new Player(USER, WHITE, this);
		player_comp = new Player(COMP, BLACK, this);
		activeColor = WHITE;
		fullmoveClock = 1;
		halfmoveClock = 0;

	}
	/*Providing a color to constructor will set the player's color to the parameter provided*/
	public Game(boolean color)
	{
		player_user = new Player(USER, color, this);
		player_comp = new Player(COMP, !color, this);
		activeColor = WHITE;
		fullmoveClock = 1;
		halfmoveClock = 0;
		isEnPassant = false;
	}

	/* METHODS */
	public void setup()
	{
		ArrayList<Piece> wpcs;
		ArrayList<Piece> bpcs;
		Piece p;
		int i;

		//Get each players list of pieces
		if(player_user.getColor() == WHITE){
			wpcs = player_user.getPieces();
			bpcs = player_comp.getPieces();
		}
		else{
			wpcs = player_user.getPieces();
			bpcs = player_comp.getPieces();
		}
		/**
		two for loops
		Using the circular relationship between to place and Square
		set each square on the board the piece's square
		*/
		for(Piece s: wpcs){
			Square position = s.getPosition();
			position.occupySquare(s);
		}
		for(Piece s: bpcs){
			Square position = s.getPosition();
			position.occupySquare(s);
		}
	}
	
	/*tells the game someone is in check and provides the player*/
	public void playerInCheck(Player checkedPlayer)
	{
		inCheck = checkedPlayer;
		check = true;
	}
	
	/*tells the game no one is in check anymore*/
	public void playerOutOfCheck()
	{
		check = false;
	}
	
	/*updates the board and pieces according to parameters (does not check legality of move!)*/
	public void movePiece(Piece p, Square src, Square dest, boolean capture)
	{
		Piece capturedPiece;
		//update our lastMoved variables (for undoMovePiece() below)
		lastMoved = p;
		lastSrc = src;
		lastDest = dest;
		//update the piece
		p.setPosition(dest);
		p.moved(); //this is for pieces that need to know if this is their first move of the game
		//update the src square
		src.evictSquare();
		//update the dest square
		if(capture){
			//if we are capturing a piece...
			capturedPiece = dest.evictSquare();
			lastCaptured = capturedPiece;
			lastWasACapture = true;
			capturedPiece.capture();
		}
		else{
			lastWasACapture = false;
		}
		dest.occupySquare(p);
	}
	
	/* undo's the most recent call of movePiece() - use this if move was found to be illegal after it was made (most common case is check) */
	public void undoMovePiece()
	{
		//set the moved piece to original src
		lastMoved.setPosition(lastSrc);
		lastSrc.occupySquare(lastMoved);
		//evict the dest
		lastDest.evictSquare();
		//then restore captured piece (if applicable)
		if(lastWasACapture){
			//return piece from captured_pieces list to pieces list
			lastCaptured.getOwner().returnPiece(lastCaptured);
			//set piece where it originally resided on dest
			lastCaptured.setPosition(lastDest);
			lastDest.occupySquare(lastCaptured);
		}
	}
	
	/*if a pawn moves double forward , it must set the enPassant square*/
	public void setEnPassant(Square ep)
	{
		enPassant = ep;
		isEnPassant = true;
	}
	
	/*if any other kind of move occurs we unset the enPassant square*/
	public void unsetEnPassant()
	{
		isEnPassant = false;
	}
	
	public boolean isEnPassant()
	{
		return isEnPassant;
	}
	
	public Square getEnPassant()
	{
		return enPassant;
	}
	
	
	public String canCastle(boolean player_type)
	{
		boolean gameColor;
		Player player;
		
		if(player_type == USER){
			player = player_user;	
		}
		else{
			player = player_comp;
		}
		
		gameColor = player.getColor();
		return "KQkq";
	}
	
	public void nextTurn(boolean didCaptureOrPawnMove)
	{
		if(activeColor == BLACK){
			fullmoveClock++;
		}
		halfmoveClock++;
		if(didCaptureOrPawnMove){
			halfmoveClock = 0;
		}
		activeColor = !activeColor;
	}
	
	/* some getters */
	public Square getSquareAt(int file, int rank)
	{
		return board.getSquareAt(file, rank);
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public boolean getActiveColor()
	{
		return activeColor;
	}
	
	public int getFullmoveClock()
	{
		return fullmoveClock;
	}
	
	public int getHalfmoveClock()
	{
		return halfmoveClock;
	}
	
	public Player getOpponent(boolean player_type)
	{
		if(player_type == USER){
			return player_comp;
		}
		else{
			return player_user;
		}
	}
	
	public Player getPlayer(boolean player_type)
	{
		if(player_type == USER){
			return player_user;
		}
		else{
			return player_comp;
		}
	}
}
