package tranqpill;

public class Square {
	
	private boolean isWhite;
	private Piece piece;
	
	public Square(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void addPiece(Piece piece) {
		this.piece = piece;
	}
	
	public boolean getIsWhite() {
		return this.isWhite;
	}
	
	@Override
	public String toString() {
		if(isWhite) {
			return " ";
		}
		else {
			if(this.piece == null) {
				return "*";
			}
			else {
				return this.piece.toString();
			}
			
		}
	}
}
