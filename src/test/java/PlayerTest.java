import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest{
	
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	public static final boolean USER = true;
	public static final boolean COMP = false;
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
	
	Game game;
	Player user;
	Player comp;
	King king;
	Piece obstruction;
	Piece attacker;
	
	//start of the game, obviously should not be in check
	@Test
	public void isInCheckTest1(){
		game = new Game();
		user = game.getOpponent(COMP);
		comp = game.getOpponent(USER);
		
		assertFalse(user.isInCheck());
	}
	
	//king in check after enemy move
	@Test
	public void isInCheckTest2(){
		game = new Game();
		game.setup();
		user = game.getOpponent(COMP);
		comp = game.getOpponent(USER);
		
		//move white king into middle at C4
		king = user.getKing();
		game.movePiece(king, king.getPosition(), game.getSquareAt(C,FOUR), false);
		//move black pawn from d7 to d5 (putting our king in check)
		attacker = game.getSquareAt(D,SEVEN).getPiece();
		game.movePiece(attacker, attacker.getPosition(), game.getSquareAt(D,FIVE), false);
		assertTrue(user.isInCheck());
	}
	
	//king in check after friendly move (illegal)
	@Test
	public void isInCheckTest3(){
		game = new Game();
		game.setup();
		user = game.getOpponent(COMP);
		comp = game.getOpponent(USER);
		
		//move white king into middle at C4
		king = user.getKing();
		game.movePiece(king, king.getPosition(), game.getSquareAt(C,FOUR), false);
		//move black bishop from c8 to e6 (putting our king in check)
		attacker = game.getSquareAt(C,EIGHT).getPiece();
		game.movePiece(attacker, attacker.getPosition(), game.getSquareAt(E,SIX), false);
		assertTrue(user.isInCheck());
		//move white pawn from d2 to d5 to block
		obstruction = game.getSquareAt(D,TWO).getPiece();
		game.movePiece(obstruction, obstruction.getPosition(), game.getSquareAt(D,FIVE), false);
		assertFalse(user.isInCheck());
		//move white pawn from d5, opening white king up to check again (illegal)
		game.movePiece(obstruction, obstruction.getPosition(), game.getSquareAt(D,SIX), false);
		assertTrue(user.isInCheck());
	}
	
}