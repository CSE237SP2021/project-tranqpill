// game generator class

package tranqpill;

import java.util.*;

public class Game {
	
	// constants
	protected final int DRAW_MOVE_RULE = 50;
	protected final int STARTING_NUMBER_OF_PIECES = 12;
	
	// instance variables
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
	 * Retrieves the player's input string and checks if it is a valid move and good form
	 * @return the move if valid and good form, otherwise null
	 */
	public Move prompt() {
		
		if (this.blackToPlay) {
			System.out.println("Black to play. Coordinates for move: ");
		} else {
			System.out.println("White to play. Coordinates for move: ");
		}
		
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();
		if (correctForm(input)) {
			System.out.println("good form!");
		}
		else {
			System.out.println("bad form!");
		}
		
		sc.close();
		
		
		if(input.equals("off")) {
			board.setCMarks(false);
		}
		if(input.equals("on")) {
			board.setCMarks(true);
		}
		
		return null;
		
		// Move.isValid is not yet implemented
		//Move move = Move.isValid(s, board, blackToPlay); // s is in proper format and legal
		//return move;
	}

	public boolean correctForm(String user_input) {
		
		Set<Character> good_letters = new HashSet<Character>();
		good_letters.add('a');
		good_letters.add('b');
		good_letters.add('c');
		good_letters.add('d');
		good_letters.add('e');
		good_letters.add('f');
		good_letters.add('g');
		good_letters.add('h');

		for (int i = 0; i < user_input.length(); i = i+3) {
			char c = user_input.charAt(i);
			if (!good_letters.contains(c)) {
				return false;	
			}	
		}
		
		for (int i = 1; i < user_input.length(); i = i+3) {
			char c = user_input.charAt(i);
			if (!Character.isDigit(c)) {
				return false;	
			}	
		}
		
		Character dash = new Character('-');
		for (int i = 2; i < user_input.length(); i = i+3) {
			Character c = user_input.charAt(i);
			if (!c.equals(dash)) {
				return false;	
			}	
		}
		
		Character last = user_input.charAt(user_input.length()-1);
		if (!Character.isDigit(last)) {
			return false;
		}
		
		return true;	
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
