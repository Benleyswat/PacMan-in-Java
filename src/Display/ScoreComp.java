package Display;

import java.util.Comparator;

/**
 * A szerializálható RankSer osztály pont szerinti összehasonlításáért felelõs
 * 
 * @author Osvárt Bence FDYUGK
 *
 */
public class ScoreComp  implements Comparator<ScoreSer> {
    /**
     * Pont szerint összehasonlítja a RankSer típusú objektumokat
     * @return 0 Ha Azonos a pontszám. 1, ha az elsõ nagyobb. 2, ha a második nagyobb.
     */
	@Override
    public int compare(ScoreSer o1, ScoreSer o2) {
        if (o1.Point == o2.Point) 
        	return 0;
        if(o1.Point > o2.Point) 
        	return -1;
        if(o1.Point < o2.Point) 
        	return 1;
        return (o1.Point < o2.Point) ? -1 : 1; 
    }
}
