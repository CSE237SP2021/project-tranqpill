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
	protected boolean blackToPlay;
	protected int movesWithoutCapture;
	private Board board;
    private Map<Board, Integer> pastPositions;
	
	// constructor
	public Game() {
		this.blackToPlay = true;
		this.movesWithoutCapture = 0;
		this.board = new Board();
		this.board.readyPlayers();
//		Piece white = new WhiteDouble();
//		Piece black = new BlackDouble();
//		this.board.getBoard()[1][4].setPiece(black);
//		this.board.getBoard()[3][4].setPiece(white);
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
		
		if (pastPositions.size()==0) {
			System.out.println("no positions recorded");
		}

        for (Board b : pastPositions.keySet()) {
        	System.out.println("freq "+b+", "+pastPositions.get(b));
        	if (pastPositions.get(b) >= 3) {
        		return true;
        	//	System.out.println("Claim a draw?");
        		/*Scanner sc = new Scanner(System.in);
        		String s = sc.nextLine();
        		sc.close();
        		if (s.equals("yes")) {
        			if (this.blackToPlay) {
        				System.out.println("The black player claimed a draw. The game is a tie!");
        			} else {
        				System.out.println("The white player claimed a draw. The game is a tie!");
        			}
        			return true;
        		} */
        	}
        }
		return false;
	}
	
	/**
	 * Checks to see if either player has won the game and prints a message accordingly
	 * @return true if a player has won, false otherwise
	 */
	public boolean win() {
		int numWhitePieces = 0;
		int numBlackPieces = 0;
		for (Square[] row : this.getBoard().getBoard())
			for(Square sq :row) {
				if (sq.getPiece()!=null)
				{
					
					if(sq.getPiece().isBlack())
						numBlackPieces++;
					else
						numWhitePieces++;
				}
			}
		if(numWhitePieces==0) {
			System.out.println("White wins!");
			return true;
		}
		if(numBlackPieces==0) {
			System.out.println("Black wins!");
			return true;
		}
		System.out.println("num white pieces "+numWhitePieces);
		System.out.println("num black pieces "+numBlackPieces);
		return false;
		
		/*
		System.out.println("white pieces: " + this.whitePieces);
		System.out.println("black pieces: " + this.blackPieces);
		if (this.whitePieces <= 0) {
				System.out.println("White has no pieces remaining. Black wins!");
				return true;
			}
		if (this.blackPieces <= 0) {
			System.out.println("Black has no pieces remaining. White wins!");
			return true;				
		}
		return false;
		*/
	}

	/**
	 * Checks to see if the current board position is a tie
	 * @return true if the game is a tie, false otherwise
	 */
	public boolean isDraw() {
		
		System.out.println(this.movesWithoutCapture);
		
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
	 * Retrieves the player's input string and checks if it is a valid move and of good form
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
		
		//sc.close();
		
		if(input.equals("off")) {
			board.setCMarks(false);
			return null;
		}
		
		if(input.equals("on")) {
			board.setCMarks(true);
			return null;
		}
		
		if (correctForm(input)) {
			// Fill positions array with the coordinates of each part of a move
			Square[] positions = new Square[(input.length()+1)/3];
			for (int i = 0; i < positions.length; i++) {
				positions[i] = board.getBoard()[((int)(input.charAt(i*3+1))-49)][(int)(input.charAt(i*3))-97];
			}
			
			Move m = new Move(positions);
			if (isLegal(m)) {
				return m;
			}
		}
		else {
			System.out.println("Invalid move form!");
			return null;
		}
		return null;
		
	}
	
	/**
	 * Checks to see if a move is legal given the current board position
	 * If the move is legal, the board position is updated 
	 * @param m the move that is being checked
	 * @return true if the move is legal, false if not
	 */
	
	public boolean isLegal(Move m) {
		
		if (m.getLocations().length<2) {
			return false;
		}
		// If either no piece is at the initial location or the piece doesn't belong to the player whose turn it is
		if(m.getLocations()[0].getPiece()==null||(m.getLocations()[0].getPiece().isBlack()!=this.blackToPlay)) {
			return false;
		}
		
		Point prevPoint = m.getLocations()[0].getLocation();
		Board updatedBoard = board.clone();
		int nJumps=0;
	
		for (int i =1; i < m.getLocations().length;i++) {
			Point currPoint = m.getLocations()[i].getLocation();
			// checking for legality of moving a piece one step diagonally 
			if(Math.abs(prevPoint.x-currPoint.x)==1 && Math.abs(prevPoint.y-currPoint.y)==1) {
				if((this.blackToPlay?1:-1)*currPoint.x>(this.blackToPlay?1:-1)*prevPoint.x&& !m.getLocations()[0].getPiece().isDouble()) {
					return false; 
				}
				// if trying to land on a square where there is already another piece
				if(! (updatedBoard.getBoard()[currPoint.x][currPoint.y].getPiece()==null)) {
					return false;
				}
				
				//move was made, update board position by removing/setting piece 
				updatedBoard.getBoard()[currPoint.x][currPoint.y].setPiece(updatedBoard.getBoard()[prevPoint.x][prevPoint.y].getPiece());
				updatedBoard.getBoard()[prevPoint.x][prevPoint.y].setPiece(null);
			}
			
			// checking for legality of a jump (e.g. tries to make a capture)
			else if(Math.abs(prevPoint.x-currPoint.x)==2 && Math.abs(prevPoint.y-currPoint.y)==2) {
				
				if ((this.blackToPlay?1:-1)*currPoint.x>(this.blackToPlay?1:-1)*prevPoint.x&& !m.getLocations()[0].getPiece().isDouble()) {
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
		
		// if capture occurred, reset pastPositions
		if (nJumps > 0) {
			this.pastPositions.clear();
			this.movesWithoutCapture = 0;
		}  else {
			++movesWithoutCapture;
		}
		System.out.println("incrementing position");
		this.pastPositions.put(this.board, pastPositions.getOrDefault(this.board, 0) + 1);	
		//System.out.println("Past positions size "+pastPositions.size());
		
		updatedBoard.setCMarks(board.getCMarks());
		for(int i=0; i< updatedBoard.getBoard()[0].length;i++) {
			Square s = updatedBoard.getBoard()[0][i];
			if(s.getPiece()!=null && s.getPiece().isBlack()) {
				s.setPiece(new BlackDouble());
			}
		}
		for(int i=0; i< updatedBoard.getBoard()[7].length;i++) {
			Square s = updatedBoard.getBoard()[7][i];
			if(s.getPiece()!=null && !s.getPiece().isBlack()) {
				s.setPiece(new WhiteDouble());
			}
		}
		board = updatedBoard;
		return true;
	}

	/**
	 * checks if the form of the move is correct (i.e. a valid coordinate (e.g. a4) followed by a dash (-) then followed by another coordinate)
	 * @param user_input the list of coordinates (dash separated) supplied by the user to represent their move
	 * @return true if the input is of correct form, false if not
	 */
	
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

		// check every third character for a lowercase letter 
		for (int i = 0; i < user_input.length(); i = i+3) {
			char c = user_input.charAt(i);
			if (!good_letters.contains(c)) {
				return false;	
			}	
		}
		
		// check every third character for a digit (not 0 or 9)
		for (int i = 1; i < user_input.length(); i = i+3) {
			char c = user_input.charAt(i);
			if (!Character.isDigit(c) || (int) c == 57 || (int) c == 48) {
				return false;	
			}	
		}
		
		// check every third character for a dash
		Character dash = new Character('-');
		for (int i = 2; i < user_input.length(); i = i+3) {
			Character c = user_input.charAt(i);
			if (!c.equals(dash)) {
				return false;	
			}	
		}
		
		// make sure the move sequence ends with a square (and thus a digit)
		Character last = user_input.charAt(user_input.length()-1);
		if (!Character.isDigit(last) || (int) last == 57 || (int) last == 48) {
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
					System.out.println("Enter a valid move");
				}
			}		
			
			// check if the game is a draw or is over
			if (isDraw() || win()) {
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
				
			// game is not over, so it's the other player's turn
			blackToPlay = !blackToPlay;
		}
	}
	
	/**
	 * getter method
	 * @return board for current game
	 */
	public Board getBoard() {
		return this.board;
	}
	
	public void setBlackToPlay(boolean b) {
		this.blackToPlay=b;
	}

	public void setBoard(Board b) {
		this.board=b;
	}
}
