package Run;

/**
 * A f�program. L�trehozzuk a j�t�kot.
 * @author Osv�rt Bence FDYUGK
 *
 */
public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Pac-Man", 562, 743);		//Set up window
		game.start();									//Start thread
		
	}
}
