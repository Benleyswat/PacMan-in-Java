package Objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Osvárt Bence FDYUGK
 *
 */
public class GhostTest {

	@Test
	 public void testConst() {
	 Ghost g = new Ghost(100, 100);
	 int x = 100, y = 100;
	 Assert.assertEquals(g.xPos, x, 0);
	 Assert.assertEquals(g.yPos, y, 0);	 
	 }
}
