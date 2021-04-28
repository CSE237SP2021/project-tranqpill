package all_tests;
import tranqpill.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MoveTest {
	
	@Test
	void testMove() {	
		Game g = new Game();
		String input = "b7-a6";
		Square[] coordinates = new Square[(input.length()+1)/3];
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = g.getBoard().getBoard()[((int)(input.charAt(i*3+1))-49)][(int)(input.charAt(i*3))-97];
		}
		
		Move m6 = new Move(coordinates);
		assertEquals(coordinates, m6.getLocations()); 		
	}
}