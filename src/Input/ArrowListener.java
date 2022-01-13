package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * A leütött billentyûk figyeléséért felelõs
 * @author Osvárt Bence FDYUFK
 *
 */
public class ArrowListener  implements KeyListener {
	
	/**
	 * Egy boolean tömb. Mindig az az indexû elem lesz igaz, amelyik gomb le van nyomva (ASCII kód alapján). Pl.: Ha az "a" betû kerül lenyomásra, akkor a 97. elem lesz igaz, mivel az a ASCII kódja a 97.
	 */
	private boolean[] keys;
	/**
	 * Gombok, amiket figyelünk
	 */
	public boolean up, down, left, right, w, a, s, d, enter, spc;
	/**
	 * Lefoglalunk 256 boolean elemnek helyet (ASCII kódok száma) a keys tömbbe
	 */
	public ArrowListener() {
		keys = new boolean[256];
	}
	
	/**
	 * Minden frissítésnél ellenõrizzük, hogy melyik gomb van lenyomva
	 */
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		w = keys[KeyEvent.VK_W];
		a = keys[KeyEvent.VK_A];
		s =  keys[KeyEvent.VK_S];
		d =  keys[KeyEvent.VK_D];
		enter = keys[KeyEvent.VK_ENTER];
		spc = keys[KeyEvent.VK_SPACE];
	}
	
	/**
	 * A lenyomott gomb indexének elemét igazra állítja
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	/**
	 * A felengedett gomb indexének elemét igazra állítja
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
