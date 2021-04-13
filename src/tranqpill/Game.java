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
		
		if(input.equals("off")) {
			board.setCMarks(false);
			return null;
		}
		if(input.equals("on")) {
			board.setCMarks(true);
			return null;
		}
		
		//sc.close();
		if (correctForm(input)) {
			//System.out.println("good form!");
			Square[] positions = new Square[(input.length()+1)/3];
			for (int i = 0; i < positions.length; i++) {
				//System.out.println("yeem "+input);
				positions[i] = board.getBoard()[((int)(input.charAt(i*3+1))-49)][(int)(input.charAt(i*3))-97];
			}
			Move m = new Move(positions);
			if (isLegal(m)) {
				System.out.println("here");
				return m;
			}
		}
		else {
			System.out.println("bad form!");
			return null;
		}
		return null;
		
		
		// Move.isValid is not yet implemented
		//Move move = Move.isValid(s, board, blackToPlay); // s is in proper format and legal
		//return move;
	}
	
	private boolean isLegal(Move m) {
		if (m.getLocations().length<2) {
			//System.out.println("f1");
			return false;
		}
		// If either no piece is at the inital location or no player's piece
		if(m.getLocations()[0].getPiece()==null||(m.getLocations()[0].getPiece().isBlack()!=this.blackToPlay)) {
			//System.out.println("f2 "+(m.getLocations()[0].getLocation()));
			return false;
		}
		Point prevPoint = m.getLocations()[0].getLocation();
		Board updatedBoard = board.clone();
		int nJumps=0;
		for (int i =1; i < m.getLocations().length;i++) {
			Point currPoint = m.getLocations()[i].getLocation();
			if(Math.abs(prevPoint.x-currPoint.x)==1 && Math.abs(prevPoint.y-currPoint.y)==1) {
				if((this.blackToPlay?1:-1)*currPoint.x>(this.blackToPlay?1:-1)*prevPoint.x&& !m.getLocations()[0].getPiece().isDouble()) {
					//System.out.println("f3");
					return false; 
				}
				if(! (updatedBoard.getBoard()[currPoint.x][currPoint.y].getPiece()==null)) {
					//System.out.println("f4");
					return false;
				}
				
				updatedBoard.getBoard()[currPoint.x][currPoint.y].setPiece(updatedBoard.getBoard()[prevPoint.x][prevPoint.y].getPiece());
				updatedBoard.getBoard()[prevPoint.x][prevPoint.y].setPiece(null);
				
			}
			
			
			else if(Math.abs(prevPoint.x-currPoint.x)==2 && Math.abs(prevPoint.y-currPoint.y)==2) {
				if((this.blackToPlay?1:-1)*currPoint.x>(this.blackToPlay?1:-1)*prevPoint.x&& !m.getLocations()[0].getPiece().isDouble()) {
					return false; 
				}
				if(! (updatedBoard.getBoard()[currPoint.x][currPoint.y].getPiece()==null)) {
					return false;
				}
				if(updatedBoard.getBoard()[(prevPoint.x+currPoint.x)/2][(prevPoint.y+currPoint.y)/2].getPiece()==null) {
					return false;
				}
				if(updatedBoard.getBoard()[(prevPoint.x+currPoint.x)/2][(prevPoint.y+currPoint.y)/2].getPiece().isBlack()==this.blackToPlay) {
					return false;
				}
				updatedBoard.getBoard()[(prevPoint.x+currPoint.x)/2][(prevPoint.y+currPoint.y)/2].setPiece(null);
				updatedBoard.getBoard()[currPoint.x][currPoint.y].setPiece(updatedBoard.getBoard()[prevPoint.x][prevPoint.y].getPiece());
				updatedBoard.getBoard()[prevPoint.x][prevPoint.y].setPiece(null);
				nJumps++;
			}
			else {
				return false;
			}
			prevPoint=currPoint;
		}
		if(m.getLocations().length>2&&nJumps<m.getLocations().length-1)
			return false;
		System.out.println("doing it");
		updatedBoard.setCMarks(board.getCMarks());
		board = updatedBoard;
		return true;
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
			while (move == null) {
				board.print();
				move = prompt();
				if(move==null) {
					System.out.println("Invalid move, please re-enter");
				}
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
			//int captures = move.getCaptures();
			
			// if no capture, increment movesWithoutCapture
			//this.movesWithoutCapture = captures > 0 ? 0 : this.movesWithoutCapture+1;

			// move a piece from start square to end square
			//board.movePiece(move.getStartPoint(), move.getEndPoint());	
		}
	}
	
}
