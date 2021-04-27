package tranqpill;


public class Board {
	
	protected static final int ROWS = 8;
	protected static final int COLUMNS = 8;
	private boolean coordinateMarkers;
	
	private Square[][] board;
	
	// initializes an 8-by-8 array of squares as the game board
	public Board() {
		coordinateMarkers = false;
		board = new Square[ROWS][COLUMNS];
		for(int i = 0; i < 64; ++i) {
			board[i/8][i%8]= new Square((i+i/8)%2==0, new Point(i/8,i%8));
		}
	}
	public Board(Square[][]b) {
		coordinateMarkers = false;
		board=b;
	}
	public boolean getCMarks() {
		return this.coordinateMarkers;
	}
	
	// sets up the initial game position
	public void readyPlayers() {
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < board[i].length; ++j) {
				if(!board[i][j].getIsWhite()) {
					board[i][j].addPiece(new WhiteSingle());
				}
			}
		}
		
		for(int i = 6; i < 8; ++i) {
			for(int j = 0; j < board[i].length; ++j) {
				if(!board[i][j].getIsWhite()) {
					board[i][j].addPiece(new BlackSingle());
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
		Piece p = board[a.x][a.y].getPiece(); 
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
	
	public void setCMarks(boolean cMarks) {
		this.coordinateMarkers=cMarks;
	}
	
	public Square[][] getBoard() {
		return board;
	}
	
	public Board clone() {
		Square[][] result = new Square[board.length][board[0].length];
		for(int i = 0; i< board.length; i++)
			for(int j =0; j<board[i].length;j++)
				result[i][j]=board[i][j].clone();
		return new Board(result);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int markerNum=1;
		for (Square[]row: board) {
			if (coordinateMarkers)
				sb.append(markerNum++ + " ");
			for (Square sq : row) {
				sb.append(sq.toString());
			}
			sb.append("\n");
		}
		if (coordinateMarkers)
			sb.append("  abcdefgh\n");
		return sb.toString();
	}
}
