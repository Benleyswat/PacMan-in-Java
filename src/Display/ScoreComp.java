package Display;

import java.util.Comparator;

/**
 * A szerializ�lhat� RankSer oszt�ly pont szerinti �sszehasonl�t�s��rt felel�s
 * 
 * @author Osv�rt Bence FDYUGK
 *
 */
public class ScoreComp  implements Comparator<ScoreSer> {
    /**
     * Pont szerint �sszehasonl�tja a RankSer t�pus� objektumokat
     * @return 0 Ha Azonos a pontsz�m. 1, ha az els� nagyobb. 2, ha a m�sodik nagyobb.
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
