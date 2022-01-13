
package Objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Osvárt Bence FDYUGK
 *
 */
public class PacmanTest {

	@Test
	 public void testConst() {
	 Pacman p = new Pacman(100, 100, 0);
	 int x = 100, y = 100, d = 0;
	 Assert.assertEquals(p.xPos, x, 0);
	 Assert.assertEquals(p.yPos, y, 0);
	 Assert.assertEquals(p.direction, d, 0);	 
	 }
}
