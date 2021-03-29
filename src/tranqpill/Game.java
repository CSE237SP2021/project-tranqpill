// game generator class

package tranqpill;

import java.util.*;

public class Game {
	
	// constants
	protected final int DRAW_MOVE_RULE = 50;
	protected final int STARTING_NUMBER_OF_PIECES = 12;
	
	// instance variables
	//private Color colorToPlay;
	private int blackPieces;
	private int whitePieces;
	private boolean blackToPlay;
	private int movesWithoutCapture;
	private Board board;
	
	// constructor
	public Game() {
		this.blackToPlay = true;
		this.movesWithoutCapture = 0;
		this.board = new Board();
		this.board.readyPlayers();
		this.blackPieces = STARTING_NUMBER_OF_PIECES;
		this.whitePieces = STARTING_NUMBER_OF_PIECES;
	}
	
	/** 
	 * Checks to see if the current board position has occurred twice before
	 * @return true if the board position has occurred twice before, false otherwise
	 */
	// TODO
	public boolean threefoldRepetition() {
		return false;
	}

	/**
	 * Checks to see if the current board position is a tie
	 * @return true if the game is a tie, false otherwise
	 */
	public boolean isDraw() {
		
		if (threefoldRepetition()) {
			System.out.println("The same position has occurred 3 times, so the game is a tie.");
			return true;
		}
		
		if (this.movesWithoutCapture >= DRAW_MOVE_RULE) {
			System.out.println("50 moves have occurred without any captures, so the game is a draw.");
			return true;
		}
		
		return false;
	}
	
	/**
	 * Retrieves the player's input string and checks if it is a valid move
	 * @return the move associated with the input string if valid, otherwise null
	 */
	public Move prompt() {
		
		if (this.blackToPlay) {
			System.out.println("Black to play. Coordinates for move: ");
		} else {
			System.out.println("White to play. Coordinates for move: ");
		}
		Scanner sc = new Scanner(System.in);
		String s = "hi";
		if (sc.hasNextLine()) {
			s = sc.nextLine();
			System.out.println("hello");
		}
		sc.close();
		
		
		
		Move move = Move.isValid(s, board, blackToPlay); // s is in proper format and legal
		return move;
	}

	/**
	 * Starts a new checkers game
	 */
	public void play() {
		
		board.print();
		
		boolean gameOver = false;
		while (!gameOver) {
			Move move = null;
			// continue to prompt player for a valid move
			if (move == null) {
				move = prompt();
			}
			
			// update board with move
			updateBoard(move);
			
			// check if the game is a draw or is over
			if (isDraw()) {
				gameOver = true;
			} else if (this.blackToPlay) {
				if (this.whitePieces <= 0) {
					System.out.println("White has no pieces remaining. Black wins!");
					gameOver = true;
				}
			} else {
				if (this.blackPieces <= 0) {
					System.out.println("Black has no pieces remaining. White wins!");
					gameOver = true;				
				}
			}
				
			// game is not over, so it's the next player's turn
			blackToPlay = !blackToPlay;
		}
	}
	
	public void updateBoard(Move move) {
		
		int captures = move.getCaptures();
		
		// if no capture, increment movesWithoutCapture
		this.movesWithoutCapture = captures > 0 ? 0 : this.movesWithoutCapture+1;

		// move a piece from start square to end square
		board.movePiece(move.getStartPoint(), move.getEndPoint());
	}
	
}
