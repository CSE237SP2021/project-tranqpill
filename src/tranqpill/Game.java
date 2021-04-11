// game generator class

package tranqpill;

import java.util.*;

public class Game {
	
	// constants
	protected final int DRAW_MOVE_RULE = 40;
	protected final int STARTING_NUMBER_OF_PIECES = 12;
	
	// instance variables
	protected int blackPieces;
	protected int whitePieces;
	private boolean blackToPlay;
	protected int movesWithoutCapture;
	private Board board;
    private Map<Board, Integer> pastPositions;
	
	// constructor
	public Game() {
		this.blackToPlay = true;
		this.movesWithoutCapture = 0;
		this.board = new Board();
		this.board.readyPlayers();
		this.blackPieces = STARTING_NUMBER_OF_PIECES;
		this.whitePieces = STARTING_NUMBER_OF_PIECES;
        this.pastPositions = new HashMap<Board, Integer>();
	}
	
	/** 
	 * Checks to see if the current board position has occurred twice before
	 * @return true if the board position has occurred twice before, false otherwise
	 */
	// TODO
	public boolean threefoldRepetition() {

        for (int b : pastPositions.values()) {
        	if (b >= 3) {
        		System.out.println("Claim a draw?");
        		Scanner sc = new Scanner(System.in);
        		String s = sc.nextLine();
        		sc.close();
        		if (s.equals("yes")) {
        			return true;
        		} 
        	}
        }
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
			System.out.println("40 moves have occurred without any captures, so the game is a draw.");
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
		
		String s="";
		if (sc.hasNextLine()) {
			s = sc.nextLine();	
		}
		
		if(s.equals("off")) {
			board.setCMarks(false);
		}
		if(s.equals("on")) {
			board.setCMarks(true);
		}
		
		return null;
		
		// Move.isValid is not yet implemented
		//Move move = Move.isValid(s, board, blackToPlay); // s is in proper format and legal
		//return move;
	}

	/**
	 * Starts a new checkers game
	 */
	public void play() {
		
		boolean gameOver = false;
		while (!gameOver) {
			
			board.print();
			Move move = null;
			
			// prompt player for a valid move
			if (move == null) {
				move = prompt();
			}
			
			// update board with move
			updateBoard(move);
			
			// if capture occurred, reset pastPositions
			if (move.getCaptures() > 0) {
				this.pastPositions.clear();
			} 
			this.pastPositions.put(this.board, pastPositions.getOrDefault(this.board, 0) + 1);			
			
			// check if the game is a draw or is over
			if (isDraw()) {
				gameOver = true;
			} else if (this.blackToPlay) {
				if (this.whitePieces <= 0) {
					System.out.println("White has no pieces remaining. Black wins!");
					gameOver = true;
				}
			} else if (this.blackPieces <= 0) {
				System.out.println("Black has no pieces remaining. White wins!");
				gameOver = true;				
			}
				
			// game is not over, so it's the next player's turn
			blackToPlay = !blackToPlay;
		}
	}
	
	/**
	 * This method updates the current game position based off of the move provided 
	 * by the player
	 * @param move Move to be made by player
	 */
	public void updateBoard(Move move) {
		if (move != null) {
			int captures = move.getCaptures();
			
			// if no capture, increment movesWithoutCapture
			this.movesWithoutCapture = captures > 0 ? 0 : this.movesWithoutCapture+1;

			// move a piece from start square to end square
			board.movePiece(move.getStartPoint(), move.getEndPoint());	
		}
	}
	
}
