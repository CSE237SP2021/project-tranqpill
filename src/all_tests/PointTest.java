package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PointTest {
	
	@Test
	void testPoint() {			
		Point p = new Point (1, 0);
		assertEquals(p.toString(), "(1, 0)");
	}
}