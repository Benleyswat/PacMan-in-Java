package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Az ImageLoader seg�ts�g�vel egyszer�bb a k�pek bet�lt�se.
 * 
 * @author Osv�rt Bence FDYUGK
 *
 */
public class Loader {
	/**
	 * A k�pek el�r�si �tj�nak megad�s�val a f�ggv�ny bet�lti a k�pet.
	 * 
	 * @param path A k�pek el�r�si �tja
	 * @return A k�ppel t�r vissza, ha az el�r�si �t j� volt, null �rt�kkel, ha nem.
	 */
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Loader.class.getResource(path));	//Load image
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);		//If image can't be loaded, then exit the program
		}
		return null;
	}
	
}
