import org.junit.Test;
import static org.junit.Assert.*;

public class GameScribeTest{
	
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
	final String startingPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	GameScribe gs;
	Game game;
	
	@Test
	public void generateFENtest(){
		String myFEN;
		String strTurn1 = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
		String strTurn2 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2";
		String strTurn3 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
		
		game = new Game();
		game.setup();
		gs = new GameScribe(game);
		myFEN = gs.generateFEN();
		//test starting FEN is correct
		assertEquals(startingPosition, myFEN);
		//move white pawn from e2 to e4, test new FEN is correct
		Piece p = game.getSquareAt(E,TWO).getPiece();
		game.movePiece(p, game.getSquareAt(E,TWO), game.getSquareAt(E,FOUR));
		myFEN = gs.generateFEN();
		assertEquals(strTurn1, myFEN);
		//move black pawn from c7 to c5, test new FEN is correct
		p = game.getSquareAt(C,SEVEN).getPiece();
		game.movePiece(p, game.getSquareAt(C,SEVEN), game.getSquareAt(C,FIVE));
		myFEN = gs.generateFEN();
		assertEquals(strTurn2, myFEN);
		//move white knight from g1 to f3, test new FEN is correct
		p = game.getSquareAt(G,ONE).getPiece();
		game.movePiece(p, game.getSquareAt(G,ONE), game.getSquareAt(F,THREE));
		myFEN = gs.generateFEN();
		assertEquals(strTurn3, myFEN);
	}
}