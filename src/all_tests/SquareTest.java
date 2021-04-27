package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SquareTest {
	
	@Test
	void testBlackSingle() {
		Game g = new Game();
		g.getBoard().readyPlayers();
		
		
		Square sq = g.getBoard().getSquare(1, 0);
		
		assertEquals(1, sq.getLocation().x);
		assertEquals(0, sq.getLocation().y);
		assertEquals("w", sq.getPiece().toString());
		assertEquals(false, sq.getIsWhite());
		
		sq.setPiece(new WhiteDouble());
		assertEquals("W", sq.getPiece().toString());
		
		Square clone = sq.clone();
		assertEquals(clone.getPiece().toString(), sq.getPiece().toString());
		
		assertEquals(sq.toString(), "W");
	}
}