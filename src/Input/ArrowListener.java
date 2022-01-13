package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * A le�t�tt billenty�k figyel�s��rt felel�s
 * @author Osv�rt Bence FDYUFK
 *
 */
public class ArrowListener  implements KeyListener {
	
	/**
	 * Egy boolean t�mb. Mindig az az index� elem lesz igaz, amelyik gomb le van nyomva (ASCII k�d alapj�n). Pl.: Ha az "a" bet� ker�l lenyom�sra, akkor a 97. elem lesz igaz, mivel az a ASCII k�dja a 97.
	 */
	private boolean[] keys;
	/**
	 * Gombok, amiket figyel�nk
	 */
	public boolean up, down, left, right, w, a, s, d, enter, spc;
	/**
	 * Lefoglalunk 256 boolean elemnek helyet (ASCII k�dok sz�ma) a keys t�mbbe
	 */
	public ArrowListener() {
		keys = new boolean[256];
	}
	
	/**
	 * Minden friss�t�sn�l ellen�rizz�k, hogy melyik gomb van lenyomva
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
	 * A lenyomott gomb index�nek elem�t igazra �ll�tja
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	/**
	 * A felengedett gomb index�nek elem�t igazra �ll�tja
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
