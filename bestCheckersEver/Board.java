public class Board {
	
	private Square[][] board;
	private Piece current;
	
	public Board() {
		board = new Square[8][8];
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
	@Override
	public String toString() {
		String s ="";
		for (Square[]row: board) {
			for (Square sq : row) {
				s+=sq;
			}
			s+="\n";
		}
		return s;
	}
}
