package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class WhiteDoubleTest {
	
	@Test
	void testWhiteDouble() {			
		Piece wd = new WhiteDouble();
		assertEquals(false, wd.isBlack());
		assertEquals(true, wd.isDouble());
		assertEquals("W", wd.toString());
		
	}
}
