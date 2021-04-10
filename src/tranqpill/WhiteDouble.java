package tranqpill;

public class WhiteDouble implements Piece{
	public boolean isBlack() {
		return false;
	}
	
	public boolean isDouble() {
		return true;
	}
	
	@Override
	public String toString() {
		return "W";
	}

}
