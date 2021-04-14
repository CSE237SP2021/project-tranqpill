package tranqpill;

public class Point {
	
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Point p) {
		if (p.x == this.x && p.y == this.y) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}
}
