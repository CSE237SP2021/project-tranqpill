package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BlackSingleTest {
	
	@Test
	void testBlackSingle() {			
		Piece bs = new BlackSingle();
		assertEquals(true, bs.isBlack());
		assertEquals(false, bs.isDouble());
		assertEquals("b", bs.toString());
		
	}
}