package Run;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Display.Display;
import Display.ScoreSer;
import Display.ScoreComp;
import Input.ArrowListener;
import Input.setName;
import Graphics.Assets;
import Objects.Pacman;
import Objects.Map;
import Objects.Ghost;

/**
 * A játék magja. Itt található a fõ ciklus, ami a játék futásáért felelõs. 
 * A játék egy külön szálon fut.
 * 
 * @author Osvárt Bence FDYUGK
 *
 */
public class Game implements Runnable{

	/**
	 * A játék megjelenítésére alkalmas objektum.
	 */
	private Display display;		
	
	/**
	 * Az ablak szélessége.
	 */
	public int width;						

	/**
	 * Az ablak magassága.
	 */
	public int height;					
	
	/**
	 * Az ablak neve.
	 */
	public String title;	
	
	/**
	 * Ha true az értéke, akkor fut a játék.
	 */
	public boolean running = false;		
	
	/**
	 * A szál, amin a játék fut.
	 */
	public Thread thread;		
	
	/**
	 * A grafikai gyorsítótár.
	 */
	private BufferStrategy bs;			
	
	/**
	 * A grafika tárolására alkalmas .
	 */
	private Graphics g;					
	
	/**
	 * Az inputért felelõs.
	 */
	public ArrowListener arrowListener;		
	
	/**
	 * Egy max 5 elemû lista, ami a top 5 játékost tárolja.
	 */
	static ArrayList<ScoreSer> ranks = new ArrayList<ScoreSer>();
	
	/**
	 * A játék állotait tárolja szavakkal.
	 */
	String State = "menu";
	
	/**
	 * Blinky szellem.
	 */
	private Ghost blinky;				
	
	/**
	 * Pinky szellem.
	 */
	private Ghost pinky;											
	
	/**
	 * Inky szellem.
	 */			
	private Ghost inky;													
	
	/**
	 * Clyde szellem.
	 */
	private Ghost clyde;		
	
	/**
	 * Pac-Man amit a játékos irányít.
	 * (note: Pac middle at start : x = 280 y = 509)
	 */
	private Pacman p;	
	
	/**
	 * Az első gomb lenyomásától ez állítja Pacman irányát a gomblenyomások függvényében.
	 */
	private int dir = -1;							// could be nex_dir if change Pacman's const.'s direction to -1
	
//	private int next_dir = -1;				// The next direction saved if can't go that was at the time, and will go that way if possible (not working yet)
	
	/**
	 * A teljes pálya.
	 */
	private Map m;			
	
	/**
	 * A pályán maradt ehető ponotk száma.
	 */
	int PelletsLeft = 244;
	
	/**
	 * A megmaradt életek száma.
	 * (kezdetben 3)
	 */
	private int lives = 3;			
	
	/**
	 * A játékos pontjai amit szerez a játék közben.
	 */
	private int points = 0;
	
	/**
	 * Az időt számolja - 10et számol mp-enként!
	 */
	private int time = 0;
	
	/**
	 * Azon időpontok amikor Pacman megeszik egy Power pontot
	 * (Majd ha a time - powered >= 100, akkor a szellemek nem ehetőek megint)
	 */
	int powered = -1000;
	
	

	/**
	 * A játék konstruktora, beállít minden kezdő értéket.
	 * Létrehozza a pályát, szellemeket és Pac-Man-t.
	 * @param title Az ablak címe
	 * @param width Az ablak szélessége
	 * @param height Az ablak magassága
	 */
	public Game(String title, int width, int height) {		
		this.title = title;						
		this.width = width;						
		this.height = height;					
		arrowListener = new ArrowListener();	
		this.blinky = new Ghost(220,300);		
		this.pinky = new Ghost(220,330);		
		this.inky = new Ghost(310,300);			
		this.clyde = new Ghost(310,330);		
		this.p = new Pacman(265, 494, 0);				
		this.m = new Map();							
	}

	/**
	 * Inicializálás.
	 * Létrehozza az ablakot, Hozzáadja az arrowLisstenert, betölti a képeket.
	 */
	public void init() {				
		display = new Display(title, width, height);		
		display.getFrame().addKeyListener(arrowListener);	
		Assets.init();	
	}
	
	/**
	 * Ez a függvény az időt, a pontokat és az életeket frissíti.
	 */
	private void ScoresAndStats() {	
		
		if(lives == 3) {
			g.drawImage(Assets.p_right, 10, 690, null);
			g.drawImage(Assets.p_right, 50, 690, null);
			g.drawImage(Assets.p_right, 90, 690, null);
		}
		if(lives == 2) {
			g.drawImage(Assets.p_right, 10, 690, null);
			g.drawImage(Assets.p_right, 50, 690, null);
		}
		if(lives == 1) {
			g.drawImage(Assets.p_right, 10, 690, null);
		}
		if(lives == 0) {	}
		
		g.setColor(new Color(255,255,255));
		g.drawString("Score:", 250, 10);
		g.drawString(Integer.toString(points), 250, 22);
		
		g.drawString("Time:", 120, 10);
		g.drawString(Integer.toString(time/10), 120, 22);
		
	}
	
	/**
	 * Elmenti a legjobb 5 játékos nevét, pontját és idejét egy Fileba.
	 */
	private void savetoFile() {					
		//Get name
				//readfromFile();
				
				if(ranks.get(4)!=null) {	// If there is 5 elements in the array
					if (ranks.get(4).Point >= points) return;	//If doesn't reach the point to ranking
				}
				
				setName NS = new setName();
				String name = NS.getName();
				
				//Add new rank
				ranks.add(new ScoreSer(name, points, time/10));
				//Sort
				Collections.sort(ranks, new ScoreComp());
				
				//Max 5 in the ranking
				//Delete 6th if more than 5 
				if (ranks.size() > 5) {
					ranks.remove(5);
				}
				
				//Serialization
				
				try {
					FileOutputStream fos = new FileOutputStream("Scoreboard");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					oos.writeObject(ranks);
					oos.close();
					fos.close();
					
				}catch(IOException e) {
					e.printStackTrace();
				}
	}
	
	/**
	 * Beolvassa a legjobb 5 játékos nevét, pontját és idejét egy Fileból.
	 */
	private void readfromFile() {			
		try {
			FileInputStream fis = new FileInputStream("Scoreboard");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ranks = (ArrayList<ScoreSer>) ois.readObject();
	        ois.close();
	        fis.close();
	    }catch(IOException ioe){
	         ioe.printStackTrace();
		}catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
        }
		for(int i = 0; i < ranks.size(); i++) {
			System.out.println(ranks.get(i).Name + ':' + ranks.get(i).Point + ':' + ranks.get(i).Time);
		}
	}
	
	/**
	 * Ez a szellemek egy koordinátához eljutását fedi le. (nem tökéletes)
	 */
	private void Ghost_pathfind(Ghost one, int xt, int yt) {
		
		//if (time % 50 == 0) {					5mp-enként frissítené a cél adatokat.
			int xDiff = one.xPos - xt;
			int yDiff = one.yPos - yt;
		//}
    	
		//minden meghíváskor csak x vagy y irányba tudjon mozogni.
    	boolean change = false;
    	
    	if (xDiff != 0) {
   			//balra mozgás
	    	if (xDiff > 0 && m.tiles[(one.xPos+4)/20][(one.yPos-34)/20].isWall == false && one.yPos % 20 == 14) {
	    			change = true;
	    			if(one.xPos == 0) { one.xPos = display.getX() - 15;}	
	    			else one.xPos--;
   	    	}
	    	//jobbra mozgás
	    	if (xDiff < 0 && m.tiles[(one.xPos+25)/20][(one.yPos-34)/20].isWall == false && one.yPos % 20 == 14) {
	    			change = true;
	    			if(one.xPos == display.getX() - 30) { one.xPos = 0;}
	    			else one.xPos++;
	    	}
   		}
   		
    	if (yDiff != 0 && change == false) {
   			//felfele mozgás
	    	if (yDiff > 0 && m.tiles[(one.xPos+11)/20][(one.yPos-35)/20].isWall == false && one.xPos % 20 == 15) {
	    			one.yPos--;
   	    	}
	    	//lefele mozgás
	    	if (yDiff < 0 && m.tiles[(one.xPos+9)/20][(one.yPos-14)/20].isWall == false && m.tiles[(one.xPos+9)/20][(one.yPos-14)/20].isDoor == false && one.xPos % 20 == 15) {
	    			one.yPos++;
	    	}
   		}	
    	
    //Ha nem tu arra menni amerre akar a szellem (itt a kiegészítendő rész)
    	
    	//for(int i = 0; i < 33; i++) {
    		
    	//}
    	//else {
    	//	int rand = (int) (Math.random()*3);
    	//	if (rand == 0) blinky.xPos--;
    	//	if (rand == 1) blinky.yPos--;
    	//	if (rand == 2) blinky.xPos++;
    	//	if (rand == 3) blinky.yPos++;
    	//}
	}
	 
	/**
	 * Ez Blinky követését írja le.
	 * Végig Pacman-t követi.
	 */
    private void blinky() {				
    	
    	if (blinky.born == true) {
    		if (blinky.xPos != 265) blinky.xPos++;
    		else if (blinky.yPos != 254) blinky.yPos--;
    		else blinky.born = false;
    	}
    	
    	if (blinky.born == false) {
   			Ghost_pathfind(blinky, p.xPos, p.yPos);
   		}
    	
    }

    /**
	 * Ez Pinky követését írja le.
	 * Pinky azt a pontot követi ahova Pac-man néz (Pac man előtti utolsó nem fal mező.) (még nem hibátlan)
	 */
    private void pinky() {    		
    	int xEnd = p.xPos;
    	int yEnd = p.yPos;
    	boolean first = true;
    	
    	if (pinky.born == true) {
    		if (pinky.xPos != 265) pinky.xPos++;
    		else if (pinky.yPos != 254) pinky.yPos--;
    		else pinky.born = false;
    	}	
    	
    	if (pinky.born == false) {
    		
    		if(p.direction == 0) {
        		while(m.tiles[(p.xPos + 4) / 20][(p.yPos - 34) / 20].isWall == false && first == true)  {xEnd--; first = false;}
        		xEnd++;
        	}
        	if(p.direction == 1) {
        		while(m.tiles[(p.xPos + 11) / 20][(p.yPos- 35) / 20].isWall == false && first == true) {xEnd--; first = false;}
        		xEnd++;
        	}
        	if(p.direction == 2) {
        		while(m.tiles[(p.xPos +25) / 20][(p.yPos - 34) / 20].isWall == false && first == true) {xEnd++; first = false;}
        		xEnd--;
        	}
        	if(p.direction == 3) {
        		while(m.tiles[(p.xPos + 9) / 20][(p.yPos - 14) / 20].isWall == false && first == true) {xEnd++; first = false;}
        		xEnd--;
        	}
    		Ghost_pathfind(pinky, xEnd, yEnd);
    	}
    }
    
    /**
	 * Ez Inky követését írja le.
	 * Inky azt a pontot követi ahol Pac-Man Inky és Blinky között van félúton.
	 */
    private void inky() {		
    	int xforward = p.xPos - blinky.xPos + p.xPos;
    	int yforward = p.yPos - blinky.yPos + p.yPos;
    	
    	if (inky.born == true) {
    		if (inky.xPos != 265) inky.xPos--;
    		else if (inky.yPos != 254) inky.yPos--;
    		else inky.born = false;
    	}
    	
    	if (inky.born == false) {
    		Ghost_pathfind(inky, xforward, yforward);
    	}
    }
   
    /**
	 * Ez Clyde követését írja le.
	 * Clyde Pac-Mant követi, de ha 10 mező közeli körében van Pac-Man-nek, akkor a bal alsó sarok fele tart.
	 */
    private void clyde() {		
    	
    	if (clyde.born == true) {
    		if (clyde.xPos != 265) clyde.xPos--;
    		else if (clyde.yPos != 254) clyde.yPos--;
    		else clyde.born = false;
    	}
    	// A kör egyenlete
    	if (clyde.born == false && ((clyde.xPos-p.xPos)*(clyde.xPos-p.xPos)-(clyde.yPos-p.yPos)*(clyde.yPos-p.yPos) > 10000) ) {
    			Ghost_pathfind(clyde, p.xPos, p.yPos);
    	}
    	else if (clyde.born == false) Ghost_pathfind(clyde, 15, 614);
    }
   
    /**
	 * Pacman mozgása a billenytűk lenyomására
	 * Csak akkor fordul el, ha tud és a falak nem akadályozzák.
	 * Lentebb a Switch az itteni dir változóra, ami állítja Pacman irányát is. 
	 */
    private void pacmove() {		
    	int i0 = (p.xPos + 4) / 20 ;
		int j0 = ((p.yPos - 34) / 20);
		if((this.getArrowListener().left || this.getArrowListener().a) && m.tiles[i0][j0].isWall == false && p.yPos % 20 == 14){	
			dir = 0;
		}
		//if((this.getArrowListener().left || this.getArrowListener().a)) next_dir = 0;
		
		int i1 = ((p.xPos + 11) / 20);
		int j1 = ((p.yPos + 15 - 20 - 30) / 20);
		if((this.getArrowListener().up || this.getArrowListener().w) && m.tiles[i1][j1].isWall == false && p.xPos % 20 == 15){	
			dir = 1;
		}
		//if((this.getArrowListener().up || this.getArrowListener().w)) next_dir = 1;
		
		int i2 = ((p.xPos + 25) / 20);
		int j2 = ((p.yPos - 34) / 20);
		if((this.getArrowListener().right || this.getArrowListener().d) && m.tiles[i2][j2].isWall == false && p.yPos % 20 == 14){	
			dir = 2;
		}
		//if((this.getArrowListener().right || this.getArrowListener().d)) next_dir = 2;
		
		int i3 = ((p.xPos + 9) / 20);
		int j3 = ((p.yPos + 15 + 1 - 30) / 20);
		if((this.getArrowListener().down || this.getArrowListener().s) && m.tiles[i3][j3].isWall == false && m.tiles[i3][j3].isDoor == false && p.xPos % 20 == 15){	
			dir = 3;
		}
		//if((this.getArrowListener().down || this.getArrowListener().s)) next_dir = 3;
    
		//PacMan mozgása részletesen
		switch (dir) {	
			case 0: 										// balra
				if(m.tiles[i0][j0].isPellet == true) {
					
					points += 10;
					m.tiles[i0][j0].isPellet = false;
					PelletsLeft --;
				}
				
				if(m.tiles[i0][j0].isPowered == true) {
					
					points += 50;
					m.tiles[i0][j0].isPowered = false;
					PelletsLeft --;
					powered = time;
				}
				
				// a mozgást és a mezőket ellenőrző része
				if (m.tiles[i0][j0].isWall == false && p.yPos % 20 == 14) {
					
					p.direction = dir;
					if(p.xPos == 0) { p.xPos = display.getX() - 15;}
					else p.xPos --;}
			break;

			case 1: 										// felfele
				if(m.tiles[i1][j1].isPellet == true) {
					
					points += 10;
					m.tiles[i1][j1].isPellet = false;
					PelletsLeft --;
				}
				
				if(m.tiles[i1][j1].isPowered == true) {
					
					points += 50;
					m.tiles[i1][j1].isPowered = false;
					PelletsLeft --;
					powered = time;
				}
				
				// a mozgást és a mezőket ellenőrző része
				if (m.tiles[i1][j1].isWall == false && p.xPos % 20 == 15) {
					
					p.direction = dir; 
					p.yPos --;}
			break;

			case 2: 										// jobbra
				if(m.tiles[i2][j2].isPellet == true) {
					
					points += 10;
					m.tiles[i2][j2].isPellet = false;
					PelletsLeft --;
				}
				
				if(m.tiles[i2][j2].isPowered == true) {
					
					points += 50;
					m.tiles[i2][j2].isPowered = false;
					PelletsLeft --;
					powered = time;
				}
				
				// a mozgást és a mezőket ellenőrző része
				if (m.tiles[i2][j2].isWall == false && p.yPos % 20 == 14) {
					
					p.direction = dir; 
					if(p.xPos == display.getX() - 30) { p.xPos = 0;}
					else p.xPos ++;}
			break;
			
			case 3: 										// lefele
				
				if(m.tiles[i3][j3].isPellet == true) {
					
					points += 10;
					m.tiles[i3][j3].isPellet = false;
					PelletsLeft --;
				}
				
				if(m.tiles[i3][j3].isPowered == true) {
					
					points += 50;
					m.tiles[i3][j3].isPowered = false;
					PelletsLeft --;
					powered = time;
				}
				
				// a mozgást és a mezőket ellenőrző része
				if (m.tiles[i3][j3].isWall == false && m.tiles[i3][j3].isDoor == false && p.xPos % 20 == 15) {
					
					p.direction = dir; 
					p.yPos ++;}
			break;	
						
			default:
		}
		
		//Esetleg ha a követező irányt elmentené lehet kellene
		
/*		switch (next_dir) {
		case 0: 										// balra
			// a mozgást és a mezőket ellenőrző része
			if (m.tiles[i0][j0].isWall == false && p.yPos % 20 == 14) {
				
				p.direction = next_dir;
				if(p.xPos == 0) { p.xPos = display.getX() - 15;}
				else p.xPos --;}
		break;

		case 1: 										// felfele
			// a mozgást és a mezőket ellenőrző része
			if (m.tiles[i1][j1].isWall == false && p.xPos % 20 == 15) {
				
				p.direction = next_dir; 
				p.yPos --;}
		break;

		case 2: 										// jobbra
			// a mozgást és a mezőket ellenőrző része
			if (m.tiles[i2][j2].isWall == false && p.yPos % 20 == 14) {
				
				p.direction = next_dir; 
				if(p.xPos == display.getX() - 30) { p.xPos = 0;}
				else p.xPos ++;}
		break;
		
		case 3: 										// lefele
			// a mozgást és a mezőket ellenőrző része
			if (m.tiles[i3][j3].isWall == false && m.tiles[i3][j3].isDoor == false && p.xPos % 20 == 15) {
				
				p.direction = next_dir; 
				p.yPos ++;}
		break;	
					
		default:
		}
		*/
    }
	
    /**
	 * Update: framenként (meghívásonként) frissíti a szellemek célpontjait és a változó értékeket.
	 * a szellemek indításait és helyzeteit kezeli.
	 * Pacman helyzetét kezeli.
	 */
	private void update() {			
		arrowListener.update();
										//Szellemek helyzetének frissítése
		if (time > 10) blinky();	
		if (time > 50) pinky();
		if (time > 100) inky();
		if (time > 150) clyde();
		
										//Pacman helyzetének frissítése
		if (State == "game") pacmove();
	}
	
	/**
	 * Minden objektum kirajzolásáért felelős, a megfelelő helyre a megfelelő időben rajzol ki mindent.
	 * Kirajzolja a pályát, a szellemeket és Pacmant ahol éppen vannak.
	 */
	private void render() {		
		
		bs = display.getCanvas().getBufferStrategy();			//Get pics in buffer
		if(bs == null) {										// If no buffer, create one
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		// Clear Screen	
			g.clearRect(0, 0, width, height);
			
		
			m.render(g);										//A pálya elemeit frissíti
			
			ScoresAndStats();									// A fontos infókat rajzolja ki: eltelt idő, saját pont, megmaradt életek...
			
		// Szellemek
			if (time - powered >= 100) {
					blinky.renderB(g);
					pinky.renderP(g);
					inky.renderI(g);
					clyde.renderC(g);}
			
			else {	blinky.ScaredGhost(g);
					pinky.ScaredGhost(g);
					inky.ScaredGhost(g);
					clyde.ScaredGhost(g);}
			
		
			p.render(g, time); 									// Pacman 4 irányba néző arca és a szájmozgása kirajzolva
			
			if (State == "menu") menu();
			if (State == "Score") Scoreboard();
			if (lives == 0) {
				g.setColor(java.awt.Color.yellow);
				g.drawString("Game over!", 250, 274);
				g.drawString("Press Enter or Space to Menu", 200, 394);
			}
			if (PelletsLeft == 0) {
				g.setColor(java.awt.Color.yellow);
				g.drawString("You won!", 255, 274);
				g.drawString("Press Enter or Space to Menu", 200, 394);
			}
		//Draw end
			
			bs.show();	// Buffer Megjelenítése
			g.dispose();
	}
	
	/**
	 * Ez látszódik amíg nem nyom a játékos egy Space gombot.
	 */
	public void menu() {										// Ez látszódik amíg nem nyom a játékos egy Space gombot
		g.setColor(java.awt.Color.yellow);
		g.drawString("Press Space to Start", 230, 274);
		g.drawString("Press Enter to Scoreboard", 210, 394);
	}
	
	/**
	 * Kirajzolja a legjobb 5 játékos nevét, pontját és idejét.
	 */
	private void Scoreboard() {
		g.drawImage(Assets.Score_bg, 0, 0, null);
		
		g.drawString("Place:", 70, 40);
		g.drawString("Player Name:", 150, 40);
		g.drawString("Score:", 300, 40);
		g.drawString("Time:", 450, 40);	
		g.drawString("Press A or Left Arrow for Menu", 230, 274);
		
		for(int i = 0; i < ranks.size() ; i++) {
			g.drawString(Integer.toString(i+1), 70, 70 + 30*i);					// A mentett nevet nem olvassa be 
			g.drawString(ranks.get(i).Name, 150, 70 + 30*i);
			g.drawString(Integer.toString(ranks.get(i).Point), 300, 70 + 30*i);
			g.drawString(Integer.toString(ranks.get(i).Time), 450, 70 + 30*i);			
		}
	}
	
	/**
	 * Amikor Pac-mant elkapja valamelyik szellem.
	 * @return String (annak a szellemnek a nevével, aki közel van Pacmanhez, else "none")
	 */
	private String die()  {										// Amikor Pac-mant elkapja valamelyik szellem
		if (p.xPos - blinky.xPos < 3 && p.xPos - blinky.xPos > -3 && p.yPos - blinky.yPos < 3 && p.yPos - blinky.yPos > -3) {
			return "blinky";
		}
		if (p.xPos - pinky.xPos < 3 && p.xPos - pinky.xPos > -3 && p.yPos - pinky.yPos < 3 && p.yPos - pinky.yPos > -3) {
			return "pinky";
		}
		if (p.xPos - inky.xPos < 3 && p.xPos - inky.xPos > -3 && p.yPos - inky.yPos < 3 && p.yPos - inky.yPos > -3) {
			return "inky";
		}
		if (p.xPos - clyde.xPos < 3 && p.xPos - clyde.xPos > -3 && p.yPos - clyde.yPos < 3 && p.yPos - clyde.yPos > -3) {
			return "clyde";
		}
			
		else return "none";
	}
	
	/**
	 * A játék alapja - egy while ciklus.
	 */
	public void run() {											// A játék alapja - egy while ciklus.
		
		init();													// Inicializál.
		readfromFile();
		
		/**
		 * Timer     Ügyel rá, hogy a játék másodpercenként 60-szor frissítsen (optimalizálás céljából).
		 * 
		 */
		int fps = 60;
		double timePerTick = 1000000000 / fps;	// 1 sec / fps
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		boolean nameable = true;
		
		while (running) {
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer +=now - lastTime;			
			if(timer % 100000000 == 0) {time++;}	
			lastTime = now;		
			
			if(delta >= 1) {									// Frissít/Rajzol.
				
				if (State == "menu")  {
					time = 0;
					m = new Map();
					render();
					nameable = true;
					delta--;
					if (getArrowListener().spc) State = "game";
					if (getArrowListener().enter) State = "Score";
					else if (!getArrowListener().enter && !getArrowListener().spc) { 	}
					update();
				}
					
				if (State == "game") {
					update();
					render();
					delta--;
				}
						
				if (State == "Score") {
					time = 0;
					render();
					delta--;
					if (getArrowListener().left || getArrowListener().a) State = "menu";
					else if (!getArrowListener().enter) { 	}
					update();
				}
			}
			
			if (timer>= 1000000000) {
				timer = 0;
			}
	
			if (die() != "none") {
					if (die() == "blinky" && time - powered < 100) {
						points += 100;
						blinky = new Ghost(220,300);
					}
					else if (die() == "pinky" && time - powered < 100) {
						points += 100;
						pinky = new Ghost(220,330);	
					}
					else if (die() == "inky" && time - powered < 100) {
						points += 100;
						inky = new Ghost(310,300);	
					}
					else if (die() == "clyde" && time - powered < 100) {
						points += 100;
						clyde = new Ghost(310,330);	
					}

					else if (die() == "blinky" && time - powered >= 100) {
						lives--;
						this.blinky = new Ghost(220,300);
						this.pinky = new Ghost(220,330);
						this.inky = new Ghost(310,300);	
						this.clyde = new Ghost(310,330);
						this.p = new Pacman(265, 494, 0);
					}
					else if (die() != "pinky" && time - powered >= 100) {
						lives--;
						this.blinky = new Ghost(220,300);
						this.pinky = new Ghost(220,330);
						this.inky = new Ghost(310,300);	
						this.clyde = new Ghost(310,330);
						this.p = new Pacman(265, 494, 0);
					}
					else if (die() != "inky" && time - powered >= 100) {
						lives--;
						this.blinky = new Ghost(220,300);
						this.pinky = new Ghost(220,330);
						this.inky = new Ghost(310,300);	
						this.clyde = new Ghost(310,330);
						this.p = new Pacman(265, 494, 0);
					}
					else if (die() != "clyde" && time - powered >= 100) {
						lives--;
						this.blinky = new Ghost(220,300);
						this.pinky = new Ghost(220,330);
						this.inky = new Ghost(310,300);	
						this.clyde = new Ghost(310,330);
						this.p = new Pacman(265, 494, 0);
					}
			}
			
			if (lives <= 0) {
				render();
				if (nameable)  {savetoFile(); nameable = false;}
				time = 0;
				points = 0;
				lives = 3;
				State = "menu";
				update();
				
			}
			if (PelletsLeft == 0) {
				render();
				if (nameable)  {savetoFile(); nameable = false;}
				time = 0;
				points = 0;
				lives = 3;
				update();
			}
		}

		stop();													// Ha a ciklus végetér leállítja a szálat.
	}
	
	/**
	 * Visszaadja a ArrowListener-t, hogy más állapotokból is elérhetõ legyen.
	 * @return arrowListener
	 */
	public ArrowListener getArrowListener() {	
		return arrowListener;
	}

	/**
	 * Elindítja a szálat.
	 */
	public synchronized void start() {	
		if (running) return;	//Return if already runs
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Leállítja a szálat.
	 */
	public synchronized void stop() {	
		if(!running) return;	//Return if already stopped
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
