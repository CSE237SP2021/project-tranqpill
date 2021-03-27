public class Piece {
	private char type;
	
	public Piece(char s) {
		type = s;
	}
	
	@Override
	public String toString() {
		return "" + this.type;
	}
}
