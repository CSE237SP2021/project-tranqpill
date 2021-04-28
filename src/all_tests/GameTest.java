package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GameTest {
			
	@Test
	// invalid form, r is not valid, should return false
	void badForm1() {
		String s = "r4-b3-c2";
		Game g = new Game();
		assertEquals(false, g.correctForm(s));
	}
	
	@Test
	// invalid form, 9 is not valid, should return false
	void badForm2() {
		String s = "a9-b3-c2";
		Game g = new Game();
		assertEquals(false, g.correctForm(s));
	}
	
	// invalid form, must end in a square, should return false
	@Test
	void badForm3() {
		String s = "a4-b3-";
		Game g = new Game();
		assertEquals(false, g.correctForm(s));
	}
	
	@Test
	// good form, should return true
	void goodForm() {
		String s = "a4-b3-c2";
		Game g = new Game();
		assertEquals(true, g.correctForm(s));
	}
	

	@Test 
	void move_legality() {
		Game g = new Game();
		//Board b = g.getBoard();
		g.getBoard().readyPlayers();
		
		// Black moves first, try to make a legal move
		String input = "b7-a6";
		Square[] coordinates = new Square[(input.length()+1)/3];
		
		// get the correct coordinates 
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input.charAt(i*3+1))-49)][(int)(input.charAt(i*3))-97];
		}
				
		Move m = new Move(coordinates);
		assertEquals(true, g.isLegal(m)); // should return true, as this is a legal move and it's blacks turn
		g.setBlackToPlay(false);

		// make an illegal move for white
		String input2 = "a2-a3";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input2.charAt(i*3+1))-49)][(int)(input2.charAt(i*3))-97];
		}
		
		Move m2 = new Move(coordinates);
		assertEquals(false, g.isLegal(m2)); // should return false, this pieces must move diagonally
		
		// try to make another move for black, when it is still white's turn
		String input3 = "a6-b5";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input3.charAt(i*3+1))-49)][(int)(input3.charAt(i*3))-97];
		}
		
		Move m3 = new Move(coordinates);
		assertEquals(false, g.isLegal(m3)); // should return false, it is still white's turn

		// Lastly, make moves until we can test out a capture (jump)
		
		String input4 = "a2-b3";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input4.charAt(i*3+1))-49)][(int)(input4.charAt(i*3))-97];
		}
		
		Move m4 = new Move(coordinates);
		assertEquals(true, g.isLegal(m4)); // should return true, isLegal also makes the move
		g.setBlackToPlay(true);
				
		String input5 = "a6-b5"; // it was a6-b5
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input5.charAt(i*3+1))-49)][(int)(input5.charAt(i*3))-97];
		}
		
		Move m5 = new Move(coordinates);
		assertEquals(true, g.isLegal(m5)); // should return true, isLegal also makes the move
		g.setBlackToPlay(false);
		
		String input6 = "b3-c4";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input6.charAt(i*3+1))-49)][(int)(input6.charAt(i*3))-97];
		}
		
		Move m6 = new Move(coordinates);
		assertEquals(true, g.isLegal(m6)); // should return true, isLegal also makes the move
		g.setBlackToPlay(true);
		
		String input7 = "b5-d3";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input7.charAt(i*3+1))-49)][(int)(input7.charAt(i*3))-97];
		}
		
		Move m7 = new Move(coordinates);
		assertEquals(true, g.isLegal(m7)); // should return true, isLegal also makes the move
		g.setBlackToPlay(false);
				
	}
	
	@Test
	void testThreefoldRepetition() {

		Piece white = new WhiteDouble();
		Piece black = new BlackDouble();
		
		Game g = new Game();
		
		g.getBoard().getBoard()[1][4].setPiece(black);
		g.getBoard().getBoard()[3][4].setPiece(white);
		
		Square[] moves1 = new Square[2];
		Square[] moves2 = new Square[2];
		
		Square[] moves3 = new Square[2];
		Square[] moves4 = new Square[2];
		
		Point black1 = new Point(1,4);
		Point black2 = new Point(2,5);
		Point white1 = new Point(3,4);
		Point white2 = new Point(4,3);
		
		Square[][] b = g.getBoard().getBoard();
		
		Square s1 = b[black1.x][black1.y];
		Square s2 = b[black2.x][black2.y];
		Square s3 = b[white1.x][white1.y];
		Square s4 = b[white2.x][white2.y];
		
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s2;
		moves2[1] = s1;
		
		moves3[0] = s3;
		moves3[1] = s4;
		moves4[0] = s4;
		moves4[1] = s3;
		
		Move move1 = new Move(moves1);
		Move move2 = new Move(moves2);
		Move move3 = new Move(moves3);
		Move move4 = new Move(moves4);
				
		assertTrue(g.isLegal(move1));

		g.blackToPlay = !g.blackToPlay;
		assertTrue(g.isLegal(move3));
		g.blackToPlay = !g.blackToPlay;
		b=g.getBoard().getBoard();
		s1 = b[black1.x][black1.y];
		s2 = b[black2.x][black2.y];
		s3 = b[white1.x][white1.y];
		s4 = b[white2.x][white2.y];
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s2;
		moves2[1] = s1;
		
		moves3[0] = s3;
		moves3[1] = s4;
		moves4[0] = s4;
		moves4[1] = s3;
		assertTrue(g.isLegal(move2));
		
		g.blackToPlay = !g.blackToPlay;
		assertTrue(g.isLegal(move4));

		
		g.blackToPlay = !g.blackToPlay;
		
		b=g.getBoard().getBoard();
		s1 = b[black1.x][black1.y];
		s2 = b[black2.x][black2.y];
		s3 = b[white1.x][white1.y];
		s4 = b[white2.x][white2.y];
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s2;
		moves2[1] = s1;
		
		moves3[0] = s3;
		moves3[1] = s4;
		moves4[0] = s4;
		moves4[1] = s3;
		
		assertTrue(g.isLegal(move1));
		g.blackToPlay = !g.blackToPlay;
		assertTrue(g.isLegal(move3));
		g.blackToPlay = !g.blackToPlay;
		b=g.getBoard().getBoard();
		s1 = b[black1.x][black1.y];
		s2 = b[black2.x][black2.y];
		s3 = b[white1.x][white1.y];
		s4 = b[white2.x][white2.y];
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s2;
		moves2[1] = s1;
		
		moves3[0] = s3;
		moves3[1] = s4;
		moves4[0] = s4;
		moves4[1] = s3;
		assertTrue(g.isLegal(move2));
		g.blackToPlay = !g.blackToPlay;
		assertTrue(g.isLegal(move4));
		g.blackToPlay = !g.blackToPlay;
		
		b=g.getBoard().getBoard();
		s1 = b[black1.x][black1.y];
		s2 = b[black2.x][black2.y];
		s3 = b[white1.x][white1.y];
		s4 = b[white2.x][white2.y];
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s2;
		moves2[1] = s1;
		
		moves3[0] = s3;
		moves3[1] = s4;
		moves4[0] = s4;
		moves4[1] = s3;
		assertTrue(g.isLegal(move1));
		g.blackToPlay = !g.blackToPlay;
		assertTrue(g.isLegal(move3));
		g.blackToPlay = !g.blackToPlay;
		b=g.getBoard().getBoard();
		s1 = b[black1.x][black1.y];
		s2 = b[black2.x][black2.y];
		s3 = b[white1.x][white1.y];
		s4 = b[white2.x][white2.y];
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s2;
		moves2[1] = s1;
		
		moves3[0] = s3;
		moves3[1] = s4;
		moves4[0] = s4;
		moves4[1] = s3;
		assertTrue(g.isLegal(move2));
		g.blackToPlay = !g.blackToPlay;
		assertTrue(g.isLegal(move4));
		g.blackToPlay = !g.blackToPlay;
		
		assertTrue(g.threefoldRepetition());
	}
	
	@Test
	void test40MoveRule() {
		
		Piece white = new WhiteDouble();
		Piece black = new BlackDouble();
		
		Game g = new Game();
		g.setBoard(new Board());
		
		g.movesWithoutCapture = 38;
		
		g.getBoard().getBoard()[1][4].setPiece(black);
		g.getBoard().getBoard()[3][4].setPiece(white);
		
		Square[] moves1 = new Square[2];
		Square[] moves2 = new Square[2];
		
		Point black1 = new Point(1,4);
		Point black2 = new Point(2,5);
		Point white1 = new Point(3,4);
		Point white2 = new Point(2,3);
		
		
		Square s1 = g.getBoard().getBoard()[black1.x][black1.y];
		Square s2 =  g.getBoard().getBoard()[black2.x][black2.y];
		Square s3 =  g.getBoard().getBoard()[white1.x][white1.y];
		Square s4 =  g.getBoard().getBoard()[white2.x][white2.y];
		
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s3;
		moves2[1] = s4;
		
		Move move1 = new Move(moves1);
		Move move2 = new Move(moves2);
		
		assertTrue(g.isLegal(move1));
		g.blackToPlay = !g.blackToPlay;
		assertTrue(g.isLegal(move2));
		
		assertTrue(g.isDraw());	

	}
		
	@Test
	void testBlackWins() {
		
		Piece white = new WhiteDouble();
		Piece black = new BlackDouble();
		
		Game g = new Game();
		
		g.setBoard(new Board());
		
		g.getBoard().getBoard()[2][3].setPiece(black);
		g.getBoard().getBoard()[3][4].setPiece(white);
		
		Square[] moves1 = new Square[2];
		
		
		Square s1 = g.getBoard().getBoard()[2][3];
		Square s2 = g.getBoard().getBoard()[4][5];
		
		moves1[0] = s1;
		moves1[1] = s2;
		
		Move move1 = new Move(moves1);
		
		assertTrue(g.isLegal(move1));
		
		assertTrue(g.win());
		
	}
	
	@Test
	void testWhiteWins() {
		
		Piece white = new WhiteDouble();
		Piece black = new BlackDouble();
		
		Game g = new Game();
		g.setBoard(new Board());
		g.getBoard().getBoard()[1][4].setPiece(black);
		g.getBoard().getBoard()[3][4].setPiece(white);
		
		Square[] moves1 = new Square[2];
		Square[] moves2 = new Square[2];
		
		Point black1 = new Point(1,4);
		Point black2 = new Point(2,3);
		Point white1 = new Point(3,4);
		Point white2 = new Point(1,2);
		
		Square s1 = g.getBoard().getBoard()[black1.x][black1.y];
		Square s2 =  g.getBoard().getBoard()[black2.x][black2.y];
		Square s3 =  g.getBoard().getBoard()[white1.x][white1.y];
		Square s4 =  g.getBoard().getBoard()[white2.x][white2.y];
		
		moves1[0] = s1;
		moves1[1] = s2;
		moves2[0] = s3;
		moves2[1] = s4;
		
		Move move1 = new Move(moves1);
		Move move2 = new Move(moves2);
		
		assertTrue(g.isLegal(move1));
		g.blackToPlay = !g.blackToPlay;
		
		assertTrue(g.isLegal(move2));
		
		assertTrue(g.win());
	
	}
	@Test
	void testDoubling() {
		
		Piece black = new BlackSingle();
		
		Game g = new Game();
		g.setBoard(new Board());
		g.getBoard().getBoard()[1][4].setPiece(black);
		
		Square[] moves1 = new Square[2];
		
		Point black1 = new Point(1,4);
		Point black2 = new Point(0,5);
		
		Square s1 = g.getBoard().getBoard()[black1.x][black1.y];
		Square s2 =  g.getBoard().getBoard()[black2.x][black2.y];
		
		moves1[0] = s1;
		moves1[1] = s2;

		Move move1 = new Move(moves1);

		assertTrue(g.isLegal(move1));
		assertTrue(g.getBoard().getBoard()[0][5].getPiece().isDouble());
	
	}
	
}

