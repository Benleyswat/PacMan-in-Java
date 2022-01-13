package Objects;

import java.awt.Graphics;

import Graphics.Assets;
/**
 * A pálya változóit tárolja egy 2 dimenziós tömbben.
 * @author Osvárt Bence FDYUFK
 *
 */
public class Map {

	/**
	 * A 2 dimenziós tömb.
	 */
	public Tile[][] tiles = new Tile[28][31];

	/**
	 * A pálya (map) konstruktora.
	 */
	public Map() {
		generateMap();
	}
	
	/**
	 * A pálya kirajzolásáért felelős.
	 * Minden meghíváskor ellenőrzi hol van még gyűjthető pont és azokat kirajzolja.
	 * @param g A grafikus osztály
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.map, 0, 0, null);					// A térkép folyamatosan kirazolva

		for(int i = 0; i < 28; i++) {							//Kirajzolja a begyűjtendő pontokat, de csak azokat ami még nincs felszedve
			for(int j = 0; j < 31; j++) {
				
				if(tiles[i][j].isPellet == true) { g.drawImage(Assets.pellet, 20*i, 20*(j+2), null);}
				if(tiles[i][j].isPowered == true) { g.drawImage(Assets.powered,  20*i, 20*(j+2), null);}
			}
		}
	 }
	 
	/**
	 * A pálya generálása, ami a konstruktorban hívódik meg minden játék elején.
	 * Megmondja hol van fal, pont vagy ajtó.
	 */
	 public void generateMap() {
		 
		for(int i = 0; i < 28; i++) {
			for(int j = 0; j < 31; j++) {
			
				tiles[i][j] = new Tile();
				
				// Alapból minden mezőre beállítja, hogy pellet, és az egyes részeknél majd "kikapcsolom"
				tiles[i][j].isPellet = true;
				
													//g.drawImage(level[i][j], 20*i, 20*(j+2), this);
//Walls
				// Az összes fal főleg fentől lefele haladva
    			if(j == 0 || j == 30 || 																						// top and bottom walls
    			   i == 0 && j > 0 && j < 9 || i == 0 && j > 20 ||																// left walls
    			   i == 27 && j > 0 && j < 9 || i == 27 && j > 20 ||															// right walls
    			   i == 13 && j <= 4 || i == 14	&& j <= 4  ||																	// top middle
    			   (j == 2 || j == 4) && (i >= 2 && i <= 5 || i >= 7 && i <= 11 || i >= 16 && i <= 20 || i >= 22 && i <= 25) || // top boxes
    			   j == 3 && (i == 2 || i == 5 || i == 7 || i == 11 || i == 16 || i == 20 || i == 22 || i == 25) ||
    			   (j == 6 || j == 7) && (i >= 2 && i <= 5 || i == 7 || i == 8 || i >= 10 && i <= 17 || i == 19 || i == 20 || i >= 22 && i <= 25) ||
    			   j == 8 && (i == 7 || i == 8 || i == 13 || i == 14 || i == 19 || i == 20) ||
    			   j == 9 && (i <= 5 || i >= 7 && i <= 11 || i == 13 || i == 14 || i >= 16 && i <= 20 || i >= 22) ||			//middle borders and boxes
    			   j == 10 && (i == 5 || i >= 7 && i <= 11 || i == 13 || i == 14 || i >= 16 && i <= 20 || i == 22) ||
    			   j == 11 && (i == 5 || i >= 7 && i <= 8 || i >= 19 && i <= 20 || i == 22) ||
    			   j == 12 & (i == 5 || i >= 7 && i <= 8 || i >= 10 && i <= 12 || i >= 15 && i <= 17 || i >= 19 && i <= 20 || i == 22) ||
    			   j == 13 && (i <= 5 || i >= 7 && i <= 8 || i == 10 || i == 17 || i >= 19 && i <= 20 || i >= 22) || 
    			   j == 14 && (i == 10 || i == 17) ||
    			   j == 15 && (i <= 5 || i >= 7 && i <= 8 || i == 10 || i == 17 || i >= 19 && i <= 20 || i >= 22) ||			// lower middle boxes
    			   j == 16 && (i == 5 || i >= 7 && i <= 8 || i >= 10 && i <= 17 || i >= 19 && i <= 20 || i == 22) ||
    			   j == 17 && (i == 5 || i >= 7 && i <= 8 || i >= 19 && i <= 20 || i == 22) ||
    			   j == 18 && (i == 5 || i >= 7 && i <= 8 || i >= 10 && i <= 17 || i >= 19 && i <= 20 || i == 22) ||
    			   j == 19 && (i <= 5 || i >= 7 && i <= 8 || i >= 10 && i <= 17 || i >= 19 && i <= 20 || i >= 22) ||
    			   j == 20 && (i == 0 || i == 13 || i == 14 || i == 27) ||
    			   (j == 21 || j == 22) && (i >= 2 && i <= 5 || i >= 7 && i <= 11 || i == 13 || i == 14 || i >= 16 && i <= 20 || i >= 22 && i <= 25) ||
    			   j == 23 && (i == 4 || i == 5 || i == 22 || i == 23) ||														//Pac man starts this line
    			   (j == 24 || j == 25) && (i <= 2 || i == 4 || i == 5 || i == 7 || i == 8 || i >= 10 && i <= 17 || i == 19 || i == 20 || i == 22 || i == 23 || i >= 25) ||
    			   j == 26 && (i == 7 || i == 8 || i == 13 || i == 14 || i == 19 || i == 20) ||
    			   (j == 27 || j == 28) && (i >= 2 && i <= 11 || i == 13 || i == 14 || i >= 16 && i <= 25)	) {				
    					
    					tiles[i][j].isWall = true;
    					tiles[i][j].isPellet = false;
    			}
//Door
    			// Az ajtó a szellemeknek
    			if(j == 12 && (i == 13 || i == 14)) {
    	    	    
    					tiles[i][j].isDoor = true;
    					tiles[i][j].isPellet = false;
    			}	  
// Powered ones
    			// A 4 Power pellet
    			if((j == 3 || j == 23) && (i == 1 || i == 26)) {
    				
    					tiles[i][j].isPowered = true;
    					tiles[i][j].isPellet = false;
    			}
				
// not Pellets
    			// Ezek az üres mezők
    			if(j == 3 && (i == 3 || i == 4 || i >= 8 && i <= 10 || i >= 17 && i <= 19 || i == 23 || i == 24) ||		// Between the wall "gaps"
    			  (j >= 10 && j <= 12 || j >= 16 && j <= 18) && (i < 5 || i > 22) ||
    			  (j >= 13 && j <= 16) && (i >= 11 && i <= 16) ||														// the gost cave
    			  (j == 9 || j == 10) && (i == 12 || i == 15) || 														// in the middle corridors
    			  (j == 11 || j == 17) && (i >= 9 && i <= 18) ||
    			  (j == 12  || j == 13 || j == 15 || j == 16 || j == 18 || j == 19) && (i == 9 || i == 18) ||
    			  j == 14 && (i <= 5 || i >= 7 && i <= 9 || i >= 18 && i <= 20 || i >= 22) ||
    			  j == 23 && (i == 13 || i == 14)	) {
    					
    					tiles[i][j].isPellet = false;
    			}

    			//else TO DO
			}
		}	
	}
}
