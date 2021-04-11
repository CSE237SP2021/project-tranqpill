package tranqpill;


public class UltimateCheckers {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Checkers!");
		System.out.println("Coordinate markers are off, type 'on' anytime to turn them on.\n");
		Game game = new Game();
		game.play();
	}
}
