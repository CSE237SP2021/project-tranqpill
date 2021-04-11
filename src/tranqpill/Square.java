package tranqpill;

public class Square {
	
	private boolean isWhite;
	private Piece piece;
	private Point location;
	
	public Square(boolean isWhite, Point location) {
		this.isWhite = isWhite;
		this.location=location;
	}
	public Point getLocation() {
		return location;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece p) {
		this.piece = p;
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
