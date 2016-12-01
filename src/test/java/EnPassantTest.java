import org.junit.Test;
import static org.junit.Assert.*;

public class EnPassantTest{
	
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	//Files
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	public static final int H = 7;
	//Ranks
	public static final int ONE = 0;
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;
	
	Piece pawn;
	Piece obstruction;
	Piece captureTarget;
	Game game;
	GameScribe gs;
	
	@Test
	public void enPassantTest1(){
		String myFEN;
		boolean legal;
		game = new Game();
		game.setup();
		gs = new GameScribe(game);
		
		//move white pawn from e2 to e4
		pawn = game.getSquareAt(E,TWO).getPiece();
		game.movePiece(pawn, game.getSquareAt(E,TWO), game.getSquareAt(E,FOUR));
		//move black pawn from e7 to e6
		pawn = game.getSquareAt(E,SEVEN).getPiece();
		game.movePiece(pawn, game.getSquareAt(E,SEVEN), game.getSquareAt(E,SIX));
		//move white pawn from e4 to e5
		pawn = game.getSquareAt(E,FOUR).getPiece();
		game.movePiece(pawn, game.getSquareAt(E,FOUR), game.getSquareAt(E,FIVE));
		//move black pawn from d7 to d5, giving an enPassant opportunity for white
		captureTarget = game.getSquareAt(D,SEVEN).getPiece();
		game.movePiece(captureTarget, game.getSquareAt(D,SEVEN), game.getSquareAt(D,FIVE));
		//cross fingers and make enPassant capture...
		legal = game.movePiece(pawn, pawn.getPosition(), game.getSquareAt(D,SIX));
		assertTrue(legal);
	}
}