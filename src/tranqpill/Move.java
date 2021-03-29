// move parser class

package tranqpill;

import java.util.*;

public class Move {

	private String move;
	private Square startSquare;
	private Square finalSquare;
	private ArrayList<Square> captures;
	private int numCaptures;
	
	public Move(String s, Board board) {
		this.move = move;
		this.startSquare = getStartSquare(s, board);
		this.finalSquare = getFinalSquare(s, board);
		this.captures = new ArrayList<>();
		this.numCaptures = getCaptures(s);
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
	
	/**
	 * Computes the starting square of a move
	 * @param s Input string
	 * @return the square on the board
	 */
	private Square getStartSquare(String s, Board board) {
		int dash = s.indexOf('-');
		String fromSquare = s.substring(0,dash);
		String[] startPoint = fromSquare.split(",");
		Integer startX = Integer.parseInt(startPoint[0]);
		Integer startY = Integer.parseInt(startPoint[1]);
		Board.convertCoordinate(startX);		
		return board.getSquare(startX, startY);
	}
	
	/**
	 * Computes the ending square of a move
	 * @param s
	 * @return the square on the board
	 */
	private Square getFinalSquare(String s, Board board) {
		int dash = s.indexOf('-');
		String endSquare = s.substring(dash,s.length());
		String[] endPoint = endSquare.split(",");
		Integer endX = Integer.parseInt(endPoint[0]);
		Integer endY = Integer.parseInt(endPoint[1]);
		Board.convertCoordinate(endX);		
		return board.getSquare(endX, endY);
	}
	
	private static boolean withinBounds(int x, int y) {
		return (x >= 0 && x < Board.ROWS && y >= 0 && y < Board.COLUMNS);
	}
	
	/**
	 * Checks whether an input string is valid
	 * A valid input string is of the form [startX],[startY]-[endX],[endY]
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s, Board board, boolean blackToPlay) {
		
		int dash = s.indexOf('-');
		if (dash != -1) {
			
			String fromSquare = s.substring(0,dash);
			String[] startPoint = fromSquare.split(",");
			if (startPoint.length != 2) return false;
			Integer startX = Integer.parseInt(startPoint[0]);
			Integer startY = Integer.parseInt(startPoint[1]);
			if (!withinBounds(startX, startY)) return false;
			Board.convertCoordinate(startX);		
			Square startSquare = board.getSquare(startX, startY);
			if (startSquare.getIsWhite() || startSquare.getPiece().isBlack() != blackToPlay) {
				return false;
			}
			
			String endSquare = s.substring(dash, s.length());
			String[] endPoint = endSquare.split(",");
			if (endPoint.length != 2) return false;
			Integer endX = Integer.parseInt(endPoint[0]);
			Integer endY = Integer.parseInt(endPoint[1]);
			if (!withinBounds(endX, endY)) return false;
			Board.convertCoordinate(endX);		
			Square finalSquare = board.getSquare(endX, endY);
			if (finalSquare.getIsWhite() || finalSquare.getPiece() != null) {
				return false;
			}
			
			return true;
		}
		return false;
	}
}
