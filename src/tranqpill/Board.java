package tranqpill;


public class Board {
	
	protected static final int ROWS = 8;
	protected static final int COLUMNS = 8;
	
	private Square[][] board;
	
	public Board() {
		board = new Square[ROWS][COLUMNS];
		for(int i = 0; i < 64; ++i) {
			board[i/8][i%8]= new Square((i+i/8)%2==0);
		}
	}
	
	public void readyPlayers() {
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < board[i].length; ++j) {
				if(!board[i][j].getIsWhite()) {
					board[i][j].addPiece(new Piece('w'));
				}
			}
		}
		
		for(int i = 6; i < 8; ++i) {
			for(int j = 0; j < board[i].length; ++j) {
				if(!board[i][j].getIsWhite()) {
					board[i][j].addPiece(new Piece('b'));
				}
			}
		}
	}
	
	public Square getSquare(int x, int y) {
		return this.board[x][y];
	}
	
	public void print() {
		System.out.println(this);
	}
	
	/**
	 * moves a piece from square at point a to square at point b
	 * @param a starting square
	 * @param b ending square
	 */
	public void movePiece(Point a, Point b) {
		Piece p = new Piece(board[a.x][a.y].getPiece()); 
		board[b.x][b.y].setPiece(p);
		board[a.x][a.y].setPiece(null);
	}
	
	/**
	 * converts the coordinates of a square to fit bottom-left perspective
	 * @param x x-coordinate from bottom-left perspective
	 */
	public static int convertCoordinate(Integer x) {
		return ROWS-x-1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Square[]row: board) {
			for (Square sq : row) {
				sb.append(sq.toString());
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
