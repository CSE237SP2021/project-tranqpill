package tranqpill;

public class BlackSingle implements Piece{
	


	public boolean isBlack() {
		return true;
	}
	
	public boolean isDouble() {
		return false;
	}
	
	@Override
	public String toString() {
		return "b";
	}

}
