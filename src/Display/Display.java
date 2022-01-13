package Display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Display class.
 * Ez az osztály felelős az ablak létrehozásáért.
 * Ezen lesznek a grafikus elemek kirajzolva.
 * 
 * @author Osvárt Bence	FDYUGK
 */
public class Display {
	/**
	 * Az ablak alapja
	 */
	private JFrame frame;
	/**
	 * A vászon, amire a grafika lesz rajzolva
	 */
	private Canvas canvas;
	/**
	 * Az ablak címe
	 */
	private String title;
	/**
	 * Az ablak szélessége
	 */
	private int width; 
	/**
	 * Az ablak magassága
	 */
	private int height;
	
	/**
	 * A konstruktorban megadjuk az ablak tulajdonságait	
	 * @param title Az ablak címe
	 * @param width Az ablak szélessége (pixelben)
	 * @param height Az ablak magassága (pixelben)
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	/**
	 * A "createDisplay() létrehozza az ablakot, amin a játék fut majd. 
	 * Ehhez szükség van egy JFrame-re, valamint egy Canvas-re.
	 * A grafika a Canvas-re lesz kirajzolva.
	 */
	private void createDisplay() {
		//Set frame values
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
		// Set canvas values
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	/**
	 * Visszaadja a Canvast
	 * @return canvas
	 */
	public Canvas getCanvas(){
		return canvas;
	}
	
	/**
	 * Visszaadja a JFrame-t
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Visszaadja az ablak szélességét
	 * @return width
	 */
	public int getX() {
		return width;
	}
	
	/**
	 * Visszaadja az ablak magasságát
	 * @return height
	 */
	public int getY() {
		return height;
	}

}
