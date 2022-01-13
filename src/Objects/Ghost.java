package Objects;

import java.awt.Graphics;

import Graphics.Assets;
/**
 * A szellemek helyzetéért felelős.
 * @author Osvárt Bence FDYUFK
 *
 */
public class Ghost {

	/**
	 * A szellemek x és y koordinátája és a most született változó, ha meghal egy szellem.
	 */
	public int xPos;
	public int yPos;
	public boolean born = true;
	
	/**
	 * Egy szellem konstruktora.
	 * @param x A szellem kezdeti x koordinátája
	 * @param y A szellem kezdeti y koordinátája
	 */
    public Ghost(int x, int y) {
    	
    	this.xPos = x;
    	this.yPos = y;
    	this.born = true;
    }
    
    /**
	 * Blinkyt rajzolja ki az épp adott helyén, ahol van.
	 * @param g A grafikus osztály
	 */
    public void renderB(Graphics g) {
    	g.drawImage(Assets.blinky, xPos, yPos, null);
    }
    
    /**
	 * Pinkyt rajzolja ki az épp adott helyén, ahol van.
	 * @param g A grafikus osztály
	 */
    public void renderP(Graphics g) {
    	g.drawImage(Assets.pinky, xPos, yPos, null);
    }
    
    /**
	 * Inkyt rajzolja ki az épp adott helyén, ahol van.
	 * @param g A grafikus osztály
	 */
    public void renderI(Graphics g) {
    	g.drawImage(Assets.inky, xPos, yPos, null);
    }
    
    /**
	 * Clydeot rajzolja ki az épp adott helyén, ahol van.
	 * @param g A grafikus osztály
	 */
    public void renderC(Graphics g) {
    	g.drawImage(Assets.clyde, xPos, yPos, null);
    }	
    
    /**
	 * A kék megijedt szellemet rajzolja ki az épp adott helyén, ahol van.
	 * @param g A grafikus osztály
	 */
    public void ScaredGhost(Graphics g) {
    	g.drawImage(Assets.scared, xPos, yPos, null);
    }
}
