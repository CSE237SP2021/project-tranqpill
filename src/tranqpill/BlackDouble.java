package tranqpill;

public class BlackDouble implements Piece{
	public boolean isBlack() {
		return true;
	}
	
	public boolean isDouble() {
		return true;
	}
	
	@Override
	public String toString() {
		return "B";
	}

}