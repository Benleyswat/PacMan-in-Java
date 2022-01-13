package Objects;

import java.awt.Graphics;

import Graphics.Assets;
/**
 * Pacman helyzetéért és mozgásáért felelős.
 * @author Osvárt Bence FDYUFK
 *
 */
public class Pacman {

	/**
	 * Pac-Man x és y koordinátája és a "milyen irányba néz" változó.
	 */
	public int xPos;
	public int yPos;
	public int direction = 0;	 // (0) left, (1) up, (2) right, (3) down
	    
	/**
	 * Pacman konstruktora.
	 * @param x Pacman kezdeti helye az x tengelyen
	 * @param y Pacman kezdeti helye az y tengelyen
	 * @param d Pacman kezdeti iránya amerre halad
	 */
	public Pacman(int x, int y, int d) {
	    	
	    this.xPos = x;
	    this.yPos = y;
	    this.direction = d;
	}
	
	/**
	 * Pac-Man kirajzolásáért felel az épp adott helyen az adott irányba rajzolja ki és közben a száját is nyitogatja.
	 * @param g A grafikus osztály
	 * @param time Az éppen adott idő, tizedmásodpercekben
	 */
	public void render(Graphics g, int time) {
	    if (time % 10 == 0 || time % 10 == 1 || time % 10 == 4 || time % 10 == 5 || time % 10 == 8 || time % 10 == 9 ) {
			if(direction == 0) {g.drawImage(Assets.p_left, xPos, yPos, null);}
			if(direction == 1) {g.drawImage(Assets.p_up, xPos, yPos, null);}
			if(direction == 2) {g.drawImage(Assets.p_right, xPos, yPos, null);}
			if(direction == 3) {g.drawImage(Assets.p_down, xPos, yPos, null);}
		}
		else {g.drawImage(Assets.p_closed, xPos, yPos, null);}
	}
}
