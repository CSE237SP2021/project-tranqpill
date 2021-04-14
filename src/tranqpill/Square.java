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
	
	public Square clone() {
		Square result = new Square(isWhite, location);
		result.setPiece(piece);
		return result;
	}
	
	public boolean equals (Square s) {
		if (s == null || this == null) return s == this;
		Piece p = s.getPiece();
		if (p == null || this.getPiece() == null) return p == this.getPiece() && s.getLocation() == this.getLocation();
		if (s.getLocation().equals(this.getLocation()) && p.isBlack() == this.getPiece().isBlack() && p.isDouble() == this.getPiece().isDouble()) {
			return true;
		}
		return false;
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
