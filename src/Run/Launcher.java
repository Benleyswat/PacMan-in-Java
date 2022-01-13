package Run;

/**
 * A fõprogram. Létrehozzuk a játékot.
 * @author Osvárt Bence FDYUGK
 *
 */
public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Pac-Man", 562, 743);		//Set up window
		game.start();									//Start thread
		
	}
}
