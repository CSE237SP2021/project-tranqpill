package tranqpill;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckersTests {
	
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

}