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
		user = game.getPlayer(USER);
		comp = game.getPlayer(COMP);
		boolean check;
		
		//manually set white king in middle at C4
		king = user.getKing();
		king.setPosition(game.getSquareAt(C,FOUR));
		game.getSquareAt(C,FOUR).occupySquare(king);
		//move black pawn forward from d7 to d5 putting king in check
		check = game.movePiece(game.getSquareAt(D,SEVEN).getPiece(),game.getSquareAt(D,SEVEN), game.getSquareAt(D,FIVE));
		//move should be legal and piece moved forward
		assertTrue(check);
		//and user should be in check
		assertTrue(game.isThereCheck());
		assertTrue(game.whoIsInCheck() == user);
	}
	
	//king in check after friendly move (illegal)
	@Test
	public void isInCheckTest3(){
		game = new Game();
		game.setup();
		user = game.getOpponent(COMP);
		comp = game.getOpponent(USER);
		boolean check;
		
		//manually set white king in middle at C4
		king = user.getKing();
		king.setPosition(game.getSquareAt(C,FOUR));
		game.getSquareAt(C,FOUR).occupySquare(king);
		//manually set white pawn in middle at d5
		obstruction = game.getSquareAt(D, TWO).getPiece();
		obstruction.getPosition().evictSquare();
		obstruction.setPosition(game.getSquareAt(D, FIVE));
		game.getSquareAt(D,FIVE).occupySquare(obstruction);
		//manually set black bishop at e6
		attacker = game.getSquareAt(C, EIGHT).getPiece();
		attacker.getPosition().evictSquare();
		attacker.setPosition(game.getSquareAt(E,SIX));
		game.getSquareAt(E,SIX).occupySquare(attacker);
		//attempt to move white pawn at d5 to d6
		check = game.movePiece(obstruction,obstruction.getPosition(), game.getSquareAt(D,SIX));
		//move should be illegal and piece did NOT move forward
		assertFalse(check);
	}
	
}