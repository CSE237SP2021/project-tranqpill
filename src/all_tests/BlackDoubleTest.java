package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BlackDoubleTest {
	
	@Test
	void testBlackDouble() {			
		Piece bd = new BlackDouble();
		assertEquals(true, bd.isBlack());
		assertEquals(true, bd.isDouble());
		assertEquals("B", bd.toString());
		
	}
}