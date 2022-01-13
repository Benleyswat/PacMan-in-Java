package Objects;

/**
 * A pálya egy mezőjének a tulajdonságait tárolja.
 * @author Osvárt Bence FDYUFK
 *
 */
public class Tile {

	/**
	 * isDoor ha egy mező ajtó.
	 * isWall ha egy mező fal.
	 * isPellet ha egy mező ehető kis pont.
	 * isPowered ha egy mező ehető nagy pont.
	 */
	public boolean isDoor = false;
	public boolean isWall = false;
	public boolean isPellet = false;
	public boolean isPowered = false;
}
