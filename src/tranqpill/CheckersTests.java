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
		
		Board b = new Board();
		
		Piece white = new WhiteDouble();
		Piece black = new BlackDouble();
		
		Game g = new Game();
		
		b.getBoard()[2][4].setPiece(black);
		b.getBoard()[4][4].setPiece(white);
		
		Move up1 = new Move(new Point(2, 4), new Point(1,5));
		Move down1 = new Move(new Point(1,5), new Point(2,4));
		
		Move up2 = new Move(new Point(4,4), new Point(3,3));
		Move down2 = new Move(new Point(3,3), new Point(4,4));
		
		// first occurrence
		g.updateBoard(up1);
		g.updateBoard(up2);
		g.updateBoard(down1);
		g.updateBoard(down2);
		
		// second occurrence
		g.updateBoard(up1);
		g.updateBoard(up2);
		g.updateBoard(down1);
		g.updateBoard(down2);
		
		// third occurrence
		g.updateBoard(up1);
		g.updateBoard(up2);
		g.updateBoard(down1);
		g.updateBoard(down2);
		
		assertTrue(g.threefoldRepetition());
	}
	
	@Test
	void test40MoveRule() {

		Board b = new Board();
		
		Piece white = new WhiteDouble();
		Piece black = new BlackDouble();
		
		Game g = new Game();
		g.movesWithoutCapture = 37;
		
		b.getBoard()[2][4].setPiece(black);
		b.getBoard()[4][4].setPiece(white);
		
		Move up1 = new Move(new Point(2, 4), new Point(1,5));
		Move down1 = new Move(new Point(1,5), new Point(2,4));
		
		Move up2 = new Move(new Point(4,4), new Point(3,3));
		
		g.updateBoard(up1);
		g.updateBoard(up2);
		g.updateBoard(down1);
		
		assertTrue(g.isDraw());	
	}
	
	@Test
	void testBlackWins() {
	
		Board b = new Board();
		
		Piece white = new WhiteDouble();
		Piece black = new BlackDouble();
		
		Game g = new Game();
		
		g.whitePieces = 0;
		g.blackPieces = 1;
		
		b.getBoard()[2][4].setPiece(black);
		b.getBoard()[2][4].setPiece(white);
		
		
	
	}
}