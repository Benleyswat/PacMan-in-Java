package Graphics;

import java.awt.image.BufferedImage;
/**
 * A grafikai elemek bet�lt�s��rt felel�s
 * 
 * @author Osv�rt Bence FDYUGK
 *
 */
public class Assets {
	/**
	 * K�p a p�ly�hoz
	 */
	public static BufferedImage map, pellet, powered, Score_bg;
	
	/**
	 * Pac-Man k�pei
	 */
	public static BufferedImage p_left, p_right, p_up, p_down, p_closed;
	
	/**
	 * A szellemek k�pei
	 */
	public static BufferedImage blinky, pinky, inky, clyde, scared;
	
	/**
	 * Bet�lti a k�peket a mem�ri�ba
	 */
	public static void init() {
		map = Loader.loadImage("/Pictures/map.png");
		pellet = Loader.loadImage("/Pictures/Pellet.png");
		powered = Loader.loadImage("/Pictures/Powered.png");
		Score_bg = Loader.loadImage("/Pictures/Score_bg.png");
		p_left = Loader.loadImage("/Pictures/Pac_open_left.png");
		p_right = Loader.loadImage("/Pictures/Pac_open_right.png");
		p_up = Loader.loadImage("/Pictures/Pac_open_up.png");
		p_down = Loader.loadImage("/Pictures/Pac_open_down.png");
		p_closed = Loader.loadImage("/Pictures/Pac_closed.png");
		blinky = Loader.loadImage("/Pictures/Blinky.png");
		pinky = Loader.loadImage("/Pictures/Pinky.png");
		inky = Loader.loadImage("/Pictures/Inky.png");
		clyde = Loader.loadImage("/Pictures/Clide.png");
		scared = Loader.loadImage("/Pictures/Scared_ghost.png");
	}
}
