// move parser class

package tranqpill;

import java.util.*;

public class Move {

	private Square[]locations;
	
	public Move(Square[] locations) {
		this.locations=locations;
	}
	
	public Square[] getLocations() {
		return locations;
	}
	
	/**
	 * Verifies that the provided coordinates are within the bounds of the board
	 * @param x x-coordinate of the move
	 * @param y y-coordinate of them ove
	 * @return true if the point is within the bounds of the board, false otherwise
	 */
	private static boolean withinBounds(int x, int y) {
		return (x >= 0 && x < Board.ROWS && y >= 0 && y < Board.COLUMNS);
	}
	
	/**
	 * Checks whether an input string is valid
	 * If it is, then a new Move object will be created with the start and end points
	 * associated with the the move.
	 * A valid input string is of the form [startX],[startY]-[endX],[endY]
	 * @param s
	 * @return
	 */
	// TODO
	public static Move isValid(String s, Board board, boolean blackToPlay) {
		return null;
	}
}
