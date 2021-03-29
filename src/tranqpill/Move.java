// move parser class

package tranqpill;

import java.util.*;

public class Move {

	//private String move;
	private Point startPoint;
	private Point endPoint;
	private ArrayList<Point> captures;
	private int numCaptures;
	
	public Move(Point a, Point b) {
		//this.move = move;
		this.startPoint = a;
		this.endPoint = b;
		this.captures = new ArrayList<>();
		//this.numCaptures = getCaptures(s);
	}
	
	/**
	 * Determines the squares of captured pieces
	 * @return The number of captures associated with a move
	 */
	// TODO
	private int getCaptures(String s) {
		return 0;
	}
	
	/**
	 * getter
	 * @return number of captures associated with this move
	 */
	public int getCaptures() {
		return this.numCaptures;
	}
	
	public Point getStartPoint() {
		return this.startPoint;
	}
	
	public Point getEndPoint() {
		return this.endPoint;
	}

	/**
	 * Computes the starting square of a move
	 * @param s Input string
	 * @return the square on the board
	 */
	/*private Square initializeStartSquare(String s, Board board) {
		int dash = s.indexOf('-');
		String fromSquare = s.substring(0,dash);
		String[] startPoint = fromSquare.split(",");
		Integer startX = Integer.parseInt(startPoint[0]);
		Integer startY = Integer.parseInt(startPoint[1]);
		Board.convertCoordinate(startX);
		System.out.println("StartX: " + startX + ", startY: " + startY);
		return board.getSquare(startX, startY);
	}*/
	
	/**
	 * Computes the ending square of a move
	 * @param s
	 * @return the square on the board
	 */
	/*private Square initializeFinalSquare(String s, Board board) {
		int dash = s.indexOf('-');
		String endSquare = s.substring(dash+1,s.length());
		String[] endPoint = endSquare.split(",");
		Integer endX = Integer.parseInt(endPoint[0]);
		Integer endY = Integer.parseInt(endPoint[1]);
		Board.convertCoordinate(endX);	
		System.out.println("endX: " + endX + ", endY: " + endY);
		return board.getSquare(endX, endY);
	}*/
	
	private static boolean withinBounds(int x, int y) {
		return (x >= 0 && x < Board.ROWS && y >= 0 && y < Board.COLUMNS);
	}
	
	/**
	 * Checks whether an input string is valid
	 * A valid input string is of the form [startX],[startY]-[endX],[endY]
	 * @param s
	 * @return
	 */
	public static Move isValid(String s, Board board, boolean blackToPlay) {
		
		int dash = s.indexOf('-');
		if (dash != -1) {
			
			String fromSquare = s.substring(0,dash);
			String[] startPoint = fromSquare.split(",");
			System.out.println(startPoint.length);
			if (startPoint.length != 2) return null;
			int startX = Integer.parseInt(startPoint[0]);
			int startY = Integer.parseInt(startPoint[1]);
			if (!withinBounds(startX, startY)) return null;
			startX = Board.convertCoordinate(startX);		
			Square startSquare = board.getSquare(startX, startY);
			System.out.println("is white " + startSquare.getIsWhite());
			System.out.println(startSquare.getPiece().isBlack() == blackToPlay);
			if (startSquare.getIsWhite() || startSquare.getPiece().isBlack() != blackToPlay) {
				return null;
			}
			
			System.out.println("startX: " + startX + ", startY: " + startY);
			
			String endSquare = s.substring(dash+1, s.length());
			String[] endPoint = endSquare.split(",");
			System.out.println(endPoint.length);
			if (endPoint.length != 2) return null;
			int endX = Integer.parseInt(endPoint[0]);
			int endY = Integer.parseInt(endPoint[1]);
			if (!withinBounds(endX, endY)) return null;
			endX = Board.convertCoordinate(endX);		
			Square finalSquare = board.getSquare(endX, endY);
			if (finalSquare.getIsWhite() || !finalSquare.getPiece().toString().isEmpty()) {
				return null;
			}
			
			System.out.println("startX: " + endX + ", startY: " + endY);
			return new Move(new Point(startX, startY), new Point(endX, endY));
		}
		return null;
	}
}
