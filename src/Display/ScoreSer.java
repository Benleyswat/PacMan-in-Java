package Display;

import java.io.Serializable; 
/**
 * Szerializálható osztály a játékosok nevének és pontjának tárolásához.
 * @author Osvárt Bence FDYUGK
 *
 */
public class ScoreSer implements Serializable {
	
	/**
	 * A játékos neve
	 */
	public String Name;
	
	/**
	 * A játékos pontja
	 */
	public int Point;
	
	/**
	 * A játékos ideje
	 */
	public int Time;
	/**
	 * A konstruktorban meg kell adni a nevet és a pontszámot.
	 * @param n Név
	 * @param p Pontszám
	 * @param t Idő
	 */
	public ScoreSer(String n, int p, int t){
		this.Name = n;
		this.Point = p;
		this.Time = t;
	}
}