package tranqpill;

public class WhiteSingle implements Piece{

	public boolean isBlack() {
		return false;
	}
	
	public boolean isDouble() {
		return false;
	}
	
	@Override
	public String toString() {
		return "w";
	}
}