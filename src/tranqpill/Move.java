// move parser class

package tranqpill;

import java.util.*;

public class Move {

//	private Point startPoint;
//	private Point endPoint;
//	private ArrayList<Point> captures;
//	private int numCaptures;
	private Square[]locations;
	
	public Move(Square[] locations) {
		this.locations=locations;
		//this.captures = new ArrayList<>();
	}
	
	public Square[] getLocations() {
		return locations;
	}
	
	/**
	 * Determines the squares of captured pieces
	 * @return The number of captures associated with a move
	 */
	// TODO
//	private int getCaptures(String s) {
//		return 0;
//	}
//	

	
	/**
	 * getter
	 * @return number of captures associated with this move
	 */
//	public int getCaptures() {
//		return this.numCaptures;
//	}
//	
//	public Point getStartPoint() {
//		return this.startPoint;
//	}
//	
//	public Point getEndPoint() {
//		return this.endPoint;
//	}
	
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
