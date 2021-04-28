package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class WhiteSingleTest {
	
	@Test
	void testWhiteSingle() {			
		Piece ws = new WhiteSingle();
		assertEquals(false, ws.isBlack());
		assertEquals(false, ws.isDouble());
		assertEquals("w", ws.toString());
		
	}
}