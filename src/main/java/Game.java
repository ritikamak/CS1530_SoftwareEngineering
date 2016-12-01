import java.util.*;

/**
 * Each chess game is an object. As you can see below in this class, the
 * chess game object holds two player objects and a board object.
 *
 */
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
	
	//enpassant variables
	Square enPassantSquare;
	Piece enPassantPiece;
	boolean enPassantAvailible;
	
	//game turn variables
	//which color moves NEXT? (effectively a turn tracker)
	boolean activeColor; 
	//a number which increments after every black move (two turns)
	int fullmoveClock; 
	//the number of halfmoves (one turn) since the last capture or pawn advance -- starts at 0
	int halfmoveClock; 

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
		enPassantAvailible = false;
	}
	/*Providing a color to constructor will set the player's color to the parameter provided*/
	public Game(boolean color)
	{
		player_user = new Player(USER, color, this);
		player_comp = new Player(COMP, !color, this);
		activeColor = WHITE;
		fullmoveClock = 1;
		halfmoveClock = 0;
		enPassantAvailible = false;
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
		/*
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
	
	//this is a helper function for movePiece it updates a piece and the relevant squares in the event of a move or capture
	private void updatePieceAndSquare(Piece p, Square dest, boolean captured){
		//if a piece is getting captured
		if(captured){
			p.capture();
		}
		//if piece is moving
		else{
			//piece will no longer be on its original square
			p.getPosition().evictSquare();
			//it now occupies the dest square
			dest.occupySquare(p);
			//its position is set to reflect that
			p.setPosition(dest);
		}
	}

	//this function helps movePiece() 
	//the move function in King.java actually checks if a castle is valid, performCastle just enacts it
	private void performCastle(King k, Rook r)
	{
		Square src;
		Square newKingLocation;
		Square newRookLocation;
		int kf;
		int kr;
		
		src = k.getPosition();
		kf = src.getFile();
		kr = src.getRank();
		
		//determine new positions
		//kingside castle
		if(r.isKingSide()){
			newKingLocation = getSquareAt(kf , kr + 2); //king always moves two squares over
			newRookLocation = getSquareAt(kf, kr + 1); //rook moves to the outside square flanking king
		}
		//queenside castle
		else{
			newKingLocation = getSquareAt(kf , kr - 2); //king always moves two squares over
			newRookLocation = getSquareAt(kf, kr - 1); //rook moves to the outside square flanking king
		}
		
		//actually update the board and pieces
		updatePieceAndSquare(r, newRookLocation, false);
		updatePieceAndSquare(k, newKingLocation, false);
	}

	//this function helps movePiece() 
	//unperformCastle is meant to rewind a castle on the rare occasions it placed the king in check
	private void unperformCastle(King k, Rook r, Square src, Square dest)
	{
		//using the src and dest squares provided to the move, we can reset the pieces to those positions
		updatePieceAndSquare(r, dest, false);
		updatePieceAndSquare(k, src, false);
	}
	
	/*this is a big function that coordinates several of the moving parts which both check for the legality of and resolove a move*/
	public boolean movePiece(Piece p, Square src, Square dest)
	{
		boolean capture;
		boolean pawnMoved; //for halfturns
		boolean castled;
		boolean enPassanted; //if we need to set enPassant
		Piece destPiece; //piece, if any, on the dest square
		Player activePlayer; //player who is moving
		Player passivePlayer; //player who is not moving
		
		pawnMoved = false;
		castled = false;
		enPassanted = false;
		destPiece = null;
		//make initial move
		switch(p.getPieceType()){
			//when moving a king, there's a couple factors to consider (mainly w/r/t castling)
			case KING:
				try{
					capture = p.move(board, dest);
					if(capture){
						destPiece = dest.getPiece();
						//this is a castle (and King.move() considered it valid)
						if(destPiece.getPieceType() == Piece.PieceType.ROOK && destPiece.getColor() == p.getColor()){
							performCastle((King)p, (Rook)destPiece);
							castled = true;
						}
						else{
							//capture piece
							updatePieceAndSquare(destPiece, dest, true);
							//move king
							updatePieceAndSquare(p, dest, false);
						}
					}
					else{
						//justmove king
						updatePieceAndSquare(p, dest, false);
					}
				}
				catch(MoveException e){
					//total move failure, return false
					return false;
				}
				break;
				
			case PAWN:
				try{
					//inform pawn of its option to capture enPassant
					if(enPassantAvailible){
						((Pawn)p).setEnPassantTarget(enPassantSquare, enPassantPiece);
					}
					capture = p.move(board, dest);
					pawnMoved = true;
					//capture and move pawn
					if(capture){
						if(dest.isOccupied() == false){
							destPiece = enPassantPiece;
						}
						else{
							destPiece = dest.getPiece();
						}
						//capture piece
						updatePieceAndSquare(destPiece, destPiece.getPosition(), true);
						//move pawn
						updatePieceAndSquare(p, dest, false);
					}
					// or just move pawn
					else{
						updatePieceAndSquare(p, dest, false);
						//determine if we need to set enPassant
						if(p.hasMoved() == false && Math.abs(dest.getRank() - src.getRank()) == 2){
							enPassanted = true;
						}
					}
				}
				catch(MoveException e){
					if(enPassantAvailible){
						((Pawn)p).unsetEnPassantTarget();
					}
					return false;
				}
				break;
				
			default:
				try{
					capture = p.move(board, dest);
					if(capture){
						destPiece = dest.getPiece();
						//capture piece
						updatePieceAndSquare(destPiece, dest, true);
						//move piece
						updatePieceAndSquare(p, dest, false);
					}
					else{
						//just move piece
						updatePieceAndSquare(p, dest, false);
					}
				}
				catch(MoveException e){
					//total move failure, return false
					return false;
				}
		}
		
		//after making initial move, verify active player is not in check
		activePlayer = p.getOwner();
		if(activePlayer.isInCheck()){
			//active player cannot put themselves in check, so we need to undo move and return false
			//rewind move
			updatePieceAndSquare(p, src, false);
			//undo capture (if able)
			if(capture){
				//if move was a castle (not quite a capture)
				if(castled){
					unperformCastle((King)p, (Rook)destPiece, src, dest);
				}
				//otherwise, just call uncapture to reset the piece to its original position
				else{
					destPiece.uncapture(); 
				}
			}
			//since we had to rewind move, move is a failure, return false
			return false;
		}
		
		//check if we need to unset check (active player moved OUT of check)
		else{
			if(check == true && inCheck == activePlayer){
				check = false;
				activePlayer.toggleCheck();
			}
		}
		
		//if active player is not in check, we then need to check if passive player IS 
		passivePlayer = getOpponent(activePlayer.getType());
		if(passivePlayer.isInCheck()){
			//we need to update the game state to let it know
			check = true;
			inCheck = passivePlayer;
			//we need to let the player and their king know also
			passivePlayer.toggleCheck();
			//we will also announce check
			ErrorMsg.infoBox(passivePlayer.getKing().toString() + " is in check!", "King In Check Position");
		}
		
		//then finally, update some other game stuff
		if(castled){
			p.moved();
			destPiece.moved();
			advanceTurn(false, p, dest, false);
		}
		else{
			if(pawnMoved){
				((Pawn)p).unsetEnPassantTarget();
			}
			p.moved();
			advanceTurn(capture || pawnMoved, p, dest, enPassanted);
		}
		
		//return true, indicating move success
		return true;
	}

	public boolean isEnPassant()
	{
		return enPassantAvailible;
	}

	public Square getEnPassant()
	{
		return enPassantSquare;
	}

	//returns a fen castle string
	public String fenCastleString()
	{
		Player pl;
		Piece r;
		Piece k;
		Square rookStart;
		String str;
		
		str = "";
		pl = getPlayer(USER);
		if(pl.getColor() != WHITE){
			pl = getPlayer(COMP);
		}
		//check white first
		k = pl.getKing();
		//if king has moved, this color has no castle opportunity
		if(!k.hasMoved()){
			//for white, we will check Kingside first (H1)
			rookStart = board.getSquareAt(H, ONE);
			if(rookStart.isOccupied()){
				r = rookStart.getPiece();
				if(!r.hasMoved() && r.getPieceType() == Piece.PieceType.ROOK && r.getColor() == WHITE){
					//we can castle kingside
					str = str + "K";
				}
			}
			//for white, we will check  queenside second (A1)
			rookStart = board.getSquareAt(A, ONE);
			if(rookStart.isOccupied()){
				r = rookStart.getPiece();
				if(!r.hasMoved() && r.getPieceType() == Piece.PieceType.ROOK && r.getColor() == WHITE){
					//we can castle queenside
					str = str + "Q";
				}
			}
		}
		
		pl = getOpponent(pl.getType());
		k = pl.getKing();
		//check black next
		//if king has moved, this color has no castle opportunity
		if(!k.hasMoved()){
			//for black, we will check Kingside first (H8)
			rookStart = board.getSquareAt(H, EIGHT);
			if(rookStart.isOccupied()){
				r = rookStart.getPiece();
				if(!r.hasMoved() && r.getPieceType() == Piece.PieceType.ROOK && r.getColor() == BLACK){
					//we can castle kingside
					str = str + "k";
				}
			}
			//for white, we will check  queenside second (A8)
			rookStart = board.getSquareAt(A, EIGHT);
			if(rookStart.isOccupied()){
				r = rookStart.getPiece();
				if(!r.hasMoved() && r.getPieceType() == Piece.PieceType.ROOK && r.getColor() == BLACK){
					//we can castle queenside
					str = str + "q";
				}
			}
		}
		return str;
	}
	
	//sets enPassant if we need to
	private void setEnPassant(Pawn p, Square dest)
	{
		int epRank;
		
		if(p.getColor() == WHITE){
			epRank = dest.getRank() - 1; //behind white pawn
		}
		else{
			epRank = dest.getRank() + 1; //behind black pawn
		}

		//set enPassant square
		enPassantSquare = board.getSquareAt(dest.getFile(), epRank);
		//set enPassant piece
		enPassantPiece = p;
		//set enPassantAvailible to true
		enPassantAvailible = true;
	}
	
	//advances the turn for successful movePiece()
	private void advanceTurn(boolean didCaptureOrPawnMove, Piece p, Square dest, boolean enPassanted)
	{
		if(activeColor == BLACK){
			fullmoveClock++;
		}
		halfmoveClock++;
		if(didCaptureOrPawnMove && enPassanted){
			halfmoveClock = 0;
			//set enPassant square
			setEnPassant((Pawn)p, dest);
		}
		else if(didCaptureOrPawnMove){
			halfmoveClock = 0;
			enPassantAvailible = false;
		}
		else{
			enPassantAvailible = false;
		}
		activeColor = !activeColor;
	}

	/* GETTERS */
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
	
	public boolean isThereCheck()
	{
		return check;
	}
	
	public Player whoIsInCheck()
	{
		return inCheck;
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
