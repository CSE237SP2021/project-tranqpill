package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardTest {
	
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
	void testBoard() {	
		
		Game g = new Game();
		g.getBoard().readyPlayers();
		
		// test that readyPlayers works
		assertEquals(g.getBoard().getSquare(1, 0).getPiece().toString(), "w");
		assertEquals(g.getBoard().getSquare(7, 0).getPiece().toString(), "b");
		assertEquals(g.getBoard().getSquare(7, 1).getPiece(), null);
		
		// test moving a piece
		Point a = new Point(6, 1);
		Point b = new Point(5, 0);
		g.getBoard().movePiece(a, b);
		assertEquals(g.getBoard().getSquare(6, 1).getPiece(), null);
		assertEquals(g.getBoard().getSquare(5, 0).getPiece().toString(), "b");
		
		// test clone function (check a particular square)
		Board clone = g.getBoard().clone();
		assertEquals(clone.getSquare(1, 0).getPiece().toString(), g.getBoard().getSquare(1, 0).getPiece().toString());
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
		assertEquals(b.toString(), 
				" w w w w\n" + 
				"w w w w \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" * * * *\n" + 
				"* * * * \n" + 
				" b b b b\n" + 
				"b b b b \n");
	}
	
}


