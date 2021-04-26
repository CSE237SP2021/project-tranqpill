package tranqpill;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckersTests {
	
	@Test
	void testCoordinateMarkers() {
		Board b = new Board();
		assertEquals(b.toString()," * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n");
		b.setCMarks(true);
		assertEquals(b.toString(),"1  * * * *\n" + 
				"2 * * * * \n" + 
				"3  * * * *\n" + 
				"4 * * * * \n" + 
				"5  * * * *\n" + 
				"6 * * * * \n" + 
				"7  * * * *\n" + 
				"8 * * * * \n" + 
				"  abcdefgh\n");
	}
	
	@Test
	void testPieceProperties() {
		WhiteSingle ws = new WhiteSingle();
		assertEquals(ws.isBlack(),false);
		assertEquals(ws.isDouble(),false);
		
		WhiteDouble wd = new WhiteDouble();
		assertEquals(wd.isBlack(),false);
		assertEquals(wd.isDouble(),true);
		
		BlackSingle bs = new BlackSingle();
		assertEquals(bs.isBlack(),true);
		assertEquals(bs.isDouble(),false);
		
		BlackDouble bd = new BlackDouble();
		assertEquals(bd.isBlack(),true);
		assertEquals(bd.isDouble(),true);
	}

	@Test
	void testEmptyBoard() {
		Board b = new Board();
		assertEquals(b.toString(), " * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n");
	}
	@Test
	void testReadyPlayers() {
		Board b = new Board();
		b.readyPlayers();
		assertEquals(b.toString(), " w w w w\n" + 
				"w w w w \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" b b b b\n" + 
				"b b b b \n");
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
		
		//assertFalse(g.threefoldRepetition());
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
		
		//assertFalse(g.threefoldRepetition());
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
		
		Point black1 = new Point(2,3);
		Point black2 = new Point(4,5);
		
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
		Square[] moves2 = new Square[2];
		
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