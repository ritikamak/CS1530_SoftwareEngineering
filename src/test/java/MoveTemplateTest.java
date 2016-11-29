import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTemplateTest
{
	//before starting tests, we make a fake board and create a source square at the center of it
	Board board = new Board();
	Square src = board.getSquareAt(3,3);
	
	//testing a legal, positive, horizontal orthogonal move 
	@Test
	public void orthoganalTest1(){
	/* Test setup */
		//initialize orthogonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.ORTHOGONAL; 
		Square dest = board.getSquareAt(7,3);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);

			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.ORTHOGONAL);
			//verify move was strictly horizontal
			assertEquals(template.getY(), 0);
			//verify x vector was set properly (should be 7 - 3 = 4)
			assertEquals(template.getX(), 4);
			//verify moveLen was calculated properly (should be 4)
			assertEquals(template.getMoveLen(), 4);
			//verify move path
			assertEquals(path[0], board.getSquareAt(4,3));
			assertEquals(path[1], board.getSquareAt(5,3));
			assertEquals(path[2], board.getSquareAt(6,3));
			assertEquals(path[3], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal, negative, horizontal orthogonal move 
	@Test
	public void orthoganalTest2(){
	/* Test setup */
		//initialize orthogonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.ORTHOGONAL; 
		Square dest = board.getSquareAt(0,3);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);

			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.ORTHOGONAL);
			//verify move was strictly horizontal
			assertEquals(template.getY(), 0);
			//verify x vector was set properly (should be 0-3 = -3)
			assertEquals(template.getX(), -3);
			//verify moveLen was calculated properly (should be 3)
			assertEquals(template.getMoveLen(), 3);
			//verify move path
			assertEquals(path[0], board.getSquareAt(2,3));
			assertEquals(path[1], board.getSquareAt(1,3));
			assertEquals(path[2], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal, positive, vertical orthogonal move 
	@Test
	public void orthoganalTest3(){
	/* Test setup */
		//initialize orthogonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.ORTHOGONAL; 
		Square dest = board.getSquareAt(3,7);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);

			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.ORTHOGONAL);
			//verify move was strictly vertical
			assertEquals(template.getX(), 0);
			//verify y vector was set properly (should be 7-3 = 4)
			assertEquals(template.getY(), 4);
			//verify moveLen was calculated properly (should be 4)
			assertEquals(template.getMoveLen(), 4);
			//verify move path
			assertEquals(path[0], board.getSquareAt(3,4));
			assertEquals(path[1], board.getSquareAt(3,5));
			assertEquals(path[2], board.getSquareAt(3,6));
			assertEquals(path[3], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal, negative, vertical orthogonal move 
	@Test
	public void orthoganalTest4(){
	/* Test setup */
		//initialize orthogonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.ORTHOGONAL; 
		Square dest = board.getSquareAt(3,0);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);

			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.ORTHOGONAL);
			//verify move was strictly vertical
			assertEquals(template.getX(), 0);
			//verify y vector was set properly (should be 0-3 = -3)
			assertEquals(template.getY(), -3);
			//verify moveLen was calculated properly (should be 3)
			assertEquals(template.getMoveLen(), 3);
			//verify move path
			assertEquals(path[0], board.getSquareAt(3,2));
			assertEquals(path[1], board.getSquareAt(3,1));
			assertEquals(path[2], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing an illegal, vertical orthogonal move  (constructor should throw exception)
	@Test
	public void orthoganalTest5(){
	/* Test setup */
		//initialize orthogonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.ORTHOGONAL; 
		Square dest = board.getSquareAt(4,4);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//if no exception was caught
			fail();
		}
		//if movetemplate threw an exception, this test should pass
		catch(MoveTemplateException e){
			//if test completes, it simply passes
		}
	}
	
	//testing a legal, positive-sloped diagonal move from lower to higher
	@Test
	public void diagonalTest1(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.DIAGONAL; 
		Square dest = board.getSquareAt(7,7);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			
			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.DIAGONAL);
			//verify move was legal
			assertEquals(Math.abs(template.getX()), Math.abs(template.getY()));
			//verify x vector was set properly (should be = 4)
			assertEquals(template.getX(), 4);
			//verify y vector was set properly (should be = 4)
			assertEquals(template.getY(), 4);
			//verify moveLen was calculated properly (should be 4)
			assertEquals(template.getMoveLen(), 4);
			//verify move path (should be moving on up!)
			assertEquals(path[0], board.getSquareAt(4,4));
			assertEquals(path[1], board.getSquareAt(5,5));
			assertEquals(path[2], board.getSquareAt(6,6));
			assertEquals(path[3], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal, positive-sloped diagonal move from higher to lower
	@Test
	public void diagonalTest2(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.DIAGONAL; 
		Square dest = board.getSquareAt(0,0);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			
			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.DIAGONAL);
			//verify move was legal
			assertEquals(Math.abs(template.getX()), Math.abs(template.getY()));
			//verify x vector was set properly (should be = -3)
			assertEquals(template.getX(), -3);
			//verify y vector was set properly (should be = -3)
			assertEquals(template.getY(), -3);
			//verify moveLen was calculated properly (should be 4)
			assertEquals(template.getMoveLen(), 3);
			//verify move path (the get down)
			assertEquals(path[0], board.getSquareAt(2,2));
			assertEquals(path[1], board.getSquareAt(1,1));
			assertEquals(path[2], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal, negative-sloped diagonal move from lower rank to higher
	@Test
	public void diagonalTest3(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.DIAGONAL; 
		Square dest = board.getSquareAt(0,6);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			
			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.DIAGONAL);
			//verify move was legal
			assertEquals(Math.abs(template.getX()), Math.abs(template.getY()));
			//verify x vector was set properly (should be = -3)
			assertEquals(template.getX(), -3);
			//verify y vector was set properly (should be = 3)
			assertEquals(template.getY(), 3);
			//verify moveLen was calculated properly (should be 3)
			assertEquals(template.getMoveLen(), 3);
			//verify move path (moving up)
			assertEquals(path[0], board.getSquareAt(2,4));
			assertEquals(path[1], board.getSquareAt(1,5));
			assertEquals(path[2], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal, negative-sloped diagonal move from higher rank to lower
	@Test
	public void diagonalTest4(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.DIAGONAL; 
		Square dest = board.getSquareAt(6,0);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			
			//verify pattern was set correctly
			assertEquals(template.getPattern(), MoveTemplate.MovePattern.DIAGONAL);
			//verify move was legal
			assertEquals(Math.abs(template.getX()), Math.abs(template.getY()));
			//verify x vector was set properly (should be = -3)
			assertEquals(template.getX(), 3);
			//verify y vector was set properly (should be = 3)
			assertEquals(template.getY(), -3);
			//verify moveLen was calculated properly (should be 3)
			assertEquals(template.getMoveLen(), 3);
			//verify move path (moving up)
			assertEquals(path[0], board.getSquareAt(4,2));
			assertEquals(path[1], board.getSquareAt(5,1));
			assertEquals(path[2], dest);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing an illegal diagonal move
	@Test
	public void diagonalTest5(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.DIAGONAL; 
		Square dest = board.getSquareAt(7,0);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//if we've gotten this far, movetemplate constructor failed to throw exception
			fail();
		}
		//if movetemplate threw an exception, this test should pass
		catch(MoveTemplateException e){
		}
	}
	
	//testing a legal knight move (up 1, left 2) then testing the move in reverse (down 1, right 2)
	@Test
	public void knightTest1(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.KNIGHT; 
		Square dest = board.getSquareAt(1,4);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			//path should just be our destination square
			assertEquals(path[0], dest);
			//now lets move in reverse!
			template = new MoveTemplate(pattern, dest, src); //notice src and dest are flipped!
			path = template.getPath(board, dest);
			//path should just be our original src square
			assertEquals(path[0], src);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal knight move (up 2, left 1) then testing the move in reverse (down 2, right 1)
	@Test
	public void knightTest2(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.KNIGHT; 
		Square dest = board.getSquareAt(2,5);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			//path should just be our destination square
			assertEquals(path[0], dest);
			//now lets move in reverse!
			template = new MoveTemplate(pattern, dest, src); //notice src and dest are flipped!
			path = template.getPath(board, dest);
			//path should just be our original src square
			assertEquals(path[0], src);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal knight move (down 1, right 2) then testing the move in reverse (up 1, left 2)
	@Test
	public void knightTest3(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.KNIGHT; 
		Square dest = board.getSquareAt(5,2);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			//path should just be our destination square
			assertEquals(path[0], dest);
			//now lets move in reverse!
			template = new MoveTemplate(pattern, dest, src); //notice src and dest are flipped!
			path = template.getPath(board, dest);
			//path should just be our original src square
			assertEquals(path[0], src);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
	
	//testing a legal knight move (down 2, right 1) then testing the move in reverse (up 2,left 1)
	@Test
	public void knightTest4(){
	/* Test setup */
		//initialize diagonal template settings
		MoveTemplate.MovePattern pattern = MoveTemplate.MovePattern.KNIGHT; 
		Square dest = board.getSquareAt(4,1);
	
	/* Begin test */
		try{
			//create our template (catch block will grab exception )
			MoveTemplate template = new MoveTemplate(pattern, src, dest);
			//get movepath
			Square path[] = template.getPath(board, src);
			//path should just be our destination square
			assertEquals(path[0], dest);
			//now lets move in reverse!
			template = new MoveTemplate(pattern, dest, src); //notice src and dest are flipped!
			path = template.getPath(board, dest);
			//path should just be our original src square
			assertEquals(path[0], src);
		}
		//if movetemplate threw an exception, this test should fail
		catch(MoveTemplateException e){
			fail();
		}
	}
}