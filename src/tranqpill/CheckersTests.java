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
	
	@Test
	// good form, should return true
	void goodForm() {
		String s = "a4-b3-c2";
		Game g = new Game();
		assertEquals(true, g.correctForm(s));
	}
	
	@Test
	// invalid form, r is not valid, should return false
	void badForm1() {
		String s = "r4-b3-c2";
		Game g = new Game();
		assertEquals(false, g.correctForm(s));
	}
	
	@Test
	// invalid form, 9 is not valid, should return false
	void badForm2() {
		String s = "a9-b3-c2";
		Game g = new Game();
		assertEquals(false, g.correctForm(s));
	}
	
	// invalid form, must end in a square, should return false
	@Test
	void badForm3() {
		String s = "a4-b3-";
		Game g = new Game();
		assertEquals(false, g.correctForm(s));
	}
	
	@Test 
	void move_legality() {
		Game g = new Game();
		Board b = g.getBoard();
		b.readyPlayers();
		
		// Black moves first, try to make a legal move
		String input = "b7-a6";
		Square[] coordinates = new Square[(input.length()+1)/3];
		
		// get the correct coordinates 
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = b.getBoard()[((int)(input.charAt(i*3+1))-49)][(int)(input.charAt(i*3))-97];
		}
				
		Move m = new Move(coordinates);
		assertEquals(true, g.isLegal(m)); // should return true, as this is a legal move and it's blacks turn
		
		// make an illegal move for white
		String input2 = "a2-a3";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = b.getBoard()[((int)(input2.charAt(i*3+1))-49)][(int)(input2.charAt(i*3))-97];
		}
		
		Move m2 = new Move(coordinates);
		assertEquals(false, g.isLegal(m2)); // should return false, this pieces must move diagonally
		
		// try to make another move for black, when it is still white's turn
		String input3 = "a6-b5";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = b.getBoard()[((int)(input3.charAt(i*3+1))-49)][(int)(input3.charAt(i*3))-97];
		}
		
		Move m3 = new Move(coordinates);
		assertEquals(false, g.isLegal(m3)); // should return false, it is still white's turn
		
		// Lastly, make moves until we can test out a capture (jump)
		
		String input4 = "a2-b3";
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = b.getBoard()[((int)(input4.charAt(i*3+1))-49)][(int)(input4.charAt(i*3))-97];
		}
		
		Move m4 = new Move(coordinates);
		assertEquals(true, g.isLegal(m4)); // should return true, isLegal also makes the move
		
//		String input5 = "a6-b5";
//		for (int i = 0; i < coordinates.length; i++) {
//			coordinates[i] = b.getBoard()[((int)(input5.charAt(i*3+1))-49)][(int)(input5.charAt(i*3))-97];
//		}
//		
//		Move m5 = new Move(coordinates);
//		assertEquals(false, g.isLegal(m5)); // should return true, isLegal also makes the move
//		
//		String input6 = "b3-c4";
//		for (int i = 0; i < coordinates.length; i++) {
//			coordinates[i] = b.getBoard()[((int)(input6.charAt(i*3+1))-49)][(int)(input6.charAt(i*3))-97];
//		}
//		
//		Move m6 = new Move(coordinates);
//		assertEquals(false, g.isLegal(m6)); // should return true, isLegal also makes the move
//		
//		String input7 = "b5-d3";
//		for (int i = 0; i < coordinates.length; i++) {
//			coordinates[i] = b.getBoard()[((int)(input7.charAt(i*3+1))-49)][(int)(input7.charAt(i*3))-97];
//		}
//		
//		Move m7 = new Move(coordinates);
//		assertEquals(false, g.isLegal(m7)); // should return true, isLegal also makes the move
//		
		
	}

}