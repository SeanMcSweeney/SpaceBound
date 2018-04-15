// commented

package GameState;

import Main.InWindow;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Audio.GameMusic;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Entity.AllTitles;


public class Level1 extends TheGameState {
	
	private SquareMap tileMap;
	private BackG galaxy;
	private BackG asteroid;
	private BackG spaceship;	
	private Player1 player1;
	private Font font;	
	private ArrayList<AnyEnemy> enemies;
	private ArrayList<EnemyDeadEffect> ede;	
	private PlayerHUD hud;	
	private GameMusic bgM;
	private BufferedImage l1StartText;
	private AllTitles l1Title;
	private AllTitles l1Subtitle;
	
	public Level1(AllGameStates ags) {
		this.ags = ags;
		
		font = new Font("Impact", Font.PLAIN, 14);
		initialize();
	}
	
	public void initialize() {
		
		//load map
		tileMap = new SquareMap(30);
		tileMap.loadTiles("/Tilesets/Level1BG.png");
		tileMap.loadMap("/Maps/level1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		//backgrounds
		galaxy = new BackG ("/NewBackgrounds/NewBG.gif", 1);
		asteroid = new BackG ("/NewBackgrounds/asteroid.gif",  1.7 );
		asteroid.setV(1 , 0.5);
		spaceship = new BackG("/NewBackgrounds/spaceship.gif", 2 );
		spaceship.setV(2 , 0.1);
		
		//player position
		player1 = new Player1(tileMap);
		player1.setPosition(100, 100);
		
		populateEnemies();
		
		ede = new ArrayList<EnemyDeadEffect>();
		
		hud = new PlayerHUD(player1);
		
		//add background music
		bgM = new GameMusic("/NewMusic/Level1.wav");
		bgM.start();
		
		// try prevents crash 
				try 
				{
					
					l1StartText = ImageIO.read(
						getClass().getResourceAsStream("/HUD/LevelStartText1.gif")
					);
					l1Title = new AllTitles(l1StartText.getSubimage(0, 0, 178, 20));
					l1Title.setY(60);
					l1Subtitle = new AllTitles(l1StartText.getSubimage(0, 20, 82, 13));
					l1Subtitle.setY(85);
					l1Title.begin();
					l1Subtitle.begin();
				}
				catch(Exception eX) {
					eX.printStackTrace();
				}
		
	}
	
	// add all enemies !!
	
	private void populateEnemies() {
		
		enemies = new ArrayList<AnyEnemy>();
		
		// add Classy Worm
		
		Worm w;
		Point[] pointWorm = new Point[] {
			new Point(204, 200),
			new Point(227, 200),
			new Point(249, 200),
			new Point(270, 200),
			new Point(867, 200),
			new Point(772, 200),
			new Point(674, 200),
			new Point(1519, 200),
			new Point(1543, 200),
			new Point(1667, 200),
			new Point(1800, 200),
			new Point(2134, 100),
			
		};
		for(int i = 0; i < pointWorm.length; i++) {
			w = new Worm(tileMap);
			w.setPosition(pointWorm[i].x, pointWorm[i].y);
			enemies.add(w);
		}
		
		// add Rock
		
				Rock r;
				Point[] pointRock = new Point[] {
					new Point(772, 200),
					new Point(594, 200),
					new Point(1320, 100),
					new Point(1900, 100),
					new Point(2134, 100),
					new Point(2294, 100),
					new Point(2400, 100),
					
				};
				for(int i = 0; i < pointRock.length; i++) {
					r = new Rock(tileMap);
					r.setPosition(pointRock[i].x, pointRock[i].y);
					enemies.add(r);
				}
		
		// add the boss
		
		Boss b;
		Point[] pointBoss = new Point[] {
				
				// for testing Boss
				// new Point(200, 100),
				new Point(2900, 100)
		};
		for(int i = 0; i < pointBoss.length; i++) {
			b = new Boss(tileMap);
			b.setPosition(pointBoss[i].x, pointBoss[i].y);
			enemies.add(b);
		}
		
		
		
		
		
	}
	
	public void upd() {
		
		// if boss is dead then ..
		
		if (Boss.bossDead) {
		ags.setState(AllGameStates.LEVEL2STATE);
		bgM.sDown();
		}
		
		// update player
		
		player1.update();
		tileMap.setPosition(
				InWindow.WIDTH / 2 - player1.getx(),
				InWindow.HEIGHT / 2 - player1.gety()
		);
		
		// player dead **
		
		if(player1.dead) {
			ags.setState(AllGameStates.MENUSTATE);
			bgM.sDown();
		}
						
		
		// set background
		galaxy.setP(tileMap.getx(), tileMap.gety());
	    asteroid.update();
		spaceship.update();
		
		// attack enemies
		player1.checkAttack(enemies);
		
		// update all enemies
		for(int i = 0; i < enemies.size(); i++) {
			AnyEnemy e = enemies.get(i);
			e.update();
			if(e.isEnemyDead()) {
				enemies.remove(i);
				i--;
				ede.add(
					new EnemyDeadEffect(e.getx(), e.gety()));
			}
		}
		
		// update explosions
		for(int i = 0; i < ede.size(); i++) {
			ede.get(i).update();
			if(ede.get(i).shouldRemove()) {
				ede.remove(i);
				i--;
			}
		}
		
		// move title and subtitle
				if(l1Title != null) {
					l1Title.upd();
					if(l1Title.shouldRem()) l1Title = null;
				}
				if(l1Subtitle != null) {
					l1Subtitle.upd();
					if(l1Subtitle.shouldRem()) l1Subtitle = null;
				}
		
	}
	

	public void draw(Graphics2D dr) {
		
		// draw the backgrounds
		galaxy.draw(dr);
		asteroid.draw(dr);
		spaceship.draw(dr);
		
		// draw the map
		tileMap.draw(dr);
		
		// draw the player
		player1.draw(dr);
		
		// draw the enemies
		
		for(int i = 0; i < enemies.size(); i++) 
		{
			enemies.get(i).draw(dr);
		}
		
		
		// draw explosion
		for(int i = 0; i < ede.size(); i++) 
		{
			ede.get(i).setMapPosition(
				(int)tileMap.getx(), (int)tileMap.gety());
			ede.get(i).draw(dr);
		}
		
		// draw title
		if(l1Title != null) l1Title.draw(dr);
		if(l1Subtitle != null) l1Subtitle.draw(dr);
				
		//level name
		
		dr.setColor(Color.BLACK);
		dr.setFont(font);
		dr.drawString("Lost In Space", 247, 15 );
		dr.drawString("Level 1", 282, 30 );
		
		dr.setColor(Color.WHITE);
		dr.setFont(font);
		dr.drawString("Lost In Space", 245, 15 );
		dr.drawString("Level 1", 280, 30 );
		
		
		// draw hud
		hud.draw(dr);
		
	}
		// controls
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player1.setLeft(true);
		if(k == KeyEvent.VK_A) player1.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player1.setRight(true);
		if(k == KeyEvent.VK_D) player1.setRight(true);
		if(k == KeyEvent.VK_UP) player1.setJumping(true);
		if(k == KeyEvent.VK_DOWN) player1.setDown(true);
		if(k == KeyEvent.VK_S) player1.setDown(true);
		if(k == KeyEvent.VK_W) player1.setJumping(true);
		if(k == KeyEvent.VK_E) player1.setGliding(true);
		if(k == KeyEvent.VK_R) player1.setFlames();
		if(k == KeyEvent.VK_F) player1.setFiring();
		if(k == KeyEvent.VK_C) ags.setIgc(true);
		if(k == KeyEvent.VK_0) ags.setState(AllGameStates.LEVEL1STATE);
		if(k == KeyEvent.VK_M) bgM.end();
		if(k == KeyEvent.VK_P) bgM.continueClip();
		if(k == KeyEvent.VK_9) bgM.start();
		
		
		if(k == KeyEvent.VK_ESCAPE) {
			bgM.sDown();
			ags.setState(AllGameStates.MENUSTATE);
		}
		}
		
	
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player1.setLeft(false);
		if(k == KeyEvent.VK_A) player1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player1.setRight(false);
		if(k == KeyEvent.VK_D) player1.setRight(false);
		if(k == KeyEvent.VK_UP) player1.setJumping(false);
		if(k == KeyEvent.VK_DOWN) player1.setDown(false);
		if(k == KeyEvent.VK_S) player1.setDown(false);
		if(k == KeyEvent.VK_W) player1.setJumping(false);
		if(k == KeyEvent.VK_E) player1.setGliding(false);
		if(k == KeyEvent.VK_C) ags.setIgc(false);
	}
	
}












