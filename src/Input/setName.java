package Input;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import java.applet.Applet;
/**
 * A név beállítását végzi
 * @author Osvárt Bence FDYUGK
 */
public class setName extends JPanel{
	/**
	 * Egy JOptionPanel ugrik elõ, ahova a játékos beírhatja a nevet egy rublikába
	 * @return string A beírt nevet adja vissza
	 */
	public String getName() {
		String string;
		string = JOptionPane.showInputDialog("You Won! Please enter your name: ");
		return string;
	}

}
