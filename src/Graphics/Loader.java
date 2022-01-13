package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Az ImageLoader segítségével egyszerûbb a képek betöltése.
 * 
 * @author Osvárt Bence FDYUGK
 *
 */
public class Loader {
	/**
	 * A képek elérési útjának megadásával a függvény betölti a képet.
	 * 
	 * @param path A képek elérési útja
	 * @return A képpel tér vissza, ha az elérési út jó volt, null értékkel, ha nem.
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
