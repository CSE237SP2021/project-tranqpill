package tranqpill;

public class Piece {
	
	private char type;
	private boolean isBlack;
	
	public Piece(char s) {
		type = s;
		if (s == 'w' || s == 'W') {
			this.isBlack = false;
		} else {
			this.isBlack = true;
		}
	}
	
	public Piece(Piece p) {
		this.type = p.getType();
		this.isBlack = p.isBlack();
	}
	
	public char getType() {
		return this.type;
	}
	
	public boolean isBlack() {
		return this.isBlack;
	}
	
	@Override
	public String toString() {
		return "" + this.type;
	}
}
