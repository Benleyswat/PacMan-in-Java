package Input;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import java.applet.Applet;
/**
 * A n�v be�ll�t�s�t v�gzi
 * @author Osv�rt Bence FDYUGK
 */
public class setName extends JPanel{
	/**
	 * Egy JOptionPanel ugrik el�, ahova a j�t�kos be�rhatja a nevet egy rublik�ba
	 * @return string A be�rt nevet adja vissza
	 */
	public String getName() {
		String string;
		string = JOptionPane.showInputDialog("You Won! Please enter your name: ");
		return string;
	}

}
