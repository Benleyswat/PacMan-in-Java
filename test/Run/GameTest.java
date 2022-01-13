package Run;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

	@Test
	 public void testConst() {
	 Game game = new Game("Pacman",100, 100);
	 int x = 100, y = 100;
	 Assert.assertEquals(game.width, x, 0);
	 Assert.assertEquals(game.height, y, 0);	 
	 }

}
