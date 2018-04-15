// commented

package Entity;

// imports

import TileMap.*;
import Audio.GameMusic;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player1 extends AnyEntity {
	
	// player 
	public int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	public boolean dead;
	private boolean hit;
	private long hitTimer;
	private GameMusic bgMusic;
	private long leveltime;
	
	// fireball 
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBallAttack> fireBalls;
	
	// flames
	private boolean flame;
	private int flameDamage;
	private int flameRange;
	
	// gliding 
	private boolean gliding;
	
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 8, 1, 2, 4, 2, 5, 0
	};
	
	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int FLAMES = 6;	
	
	public Player1(SquareMap tm) {
		
		// attributes
		
		super(tm);
		
		width = 30;
		height = 30;
		colwidth = 20;
		colheight = 20;
		
		movementSpeed = 0.3;
		maximumSpeed = 1.6;
		sSpeed = 0.4;
		fSpeed = 0.15;
		maxFSpeed = 4.0;
		jStart = -4.8;
		sJSpeed = 0.3;
		
		facingR = true;
		
		health = maxHealth = 5;
		fire = maxFire = 1000;
		
		fireCost = 100;
		// for testing
		// fireBallDamage = 100;
		 fireBallDamage = 5;
		
		fireBalls = new ArrayList<FireBallAttack>();
		
		flameDamage = 10;
		flameRange = 40;
		
	
		// load sprite images
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/Sprites.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != FLAMES) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width * 2,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		anim = new AllAnimation();
		cAction = IDLE;
		anim.setFrames(sprites.get(IDLE));
		anim.setD(400);
		
		
		
	}
	
	
	// set the time
	
		public String getTimeToString() {
			int mins = (int) (leveltime / 3600);
			int secs = (int) ((leveltime % 3600) / 60);
			return secs < 10 ? mins + ":0" + secs : mins + ":" + secs;
		}
		// getters
	public long getTime() { return leveltime; }
	public void setTime(long t) { leveltime = t; }
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getFire() { return fire; }
	public int getMaxFire() { return maxFire; }
	
	public void setFiring() { 
		firing = true;
	}
	public void setFlames() {
		flame = true;
	}
	public void setGliding(boolean b) { 
		gliding = b;
	}
	
	
	public void checkAttack(ArrayList<AnyEnemy> enemies) {
		
		// loop through enemies
		for(int i = 0; i < enemies.size(); i++) {
			
			AnyEnemy e = enemies.get(i);
			
			// flame attack
			if(flame) {
				if(facingR) {
					if(
						e.getx() > x &&
						e.getx() < x + flameRange && 
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.damage(flameDamage);
					}
				}
				else {
					if(
						e.getx() < x &&
						e.getx() > x - flameRange &&
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.damage(flameDamage);
					}
				}
			}
			
			
			// fireball att
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e)) {
					e.damage(fireBallDamage);
					fireBalls.get(j).setHit();
					break;
				}
			}
			
			// check enemy collision ( intersecrt
			if(intersects(e)) {
				hit(e.getEnemyDamage());
			}
			
		}
		
	}
	
// check hit
	public void hit(int damage) {
		if(hit) return;
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
		hit = true;
		hitTimer = System.nanoTime();
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= movementSpeed;
			if(dx < -maximumSpeed) {
				dx = -maximumSpeed;
			}
		}
		else if(right) {
			dx += movementSpeed;
			if(dx > maximumSpeed) {
				dx = maximumSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= sSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += sSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		
		
		// cannot move while attacking, except in air
		if(
		(cAction == FLAMES || cAction == FIREBALL) &&
		!(jumping || falling)) {
			dx = 0;
		}
		
		// jumping
		if(jumping && !falling) {
			dy = jStart;
			falling = true;
		}
		
		// falling
		if(falling) {
			
			if(dy > 0 && gliding) dy += fSpeed * 0.1;
			else dy += fSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += sJSpeed;
			
			if(dy > maxFSpeed) dy = maxFSpeed;
			
		}
		
	}
	
	public void update() {
		
		leveltime++;
		
		// update position
		getNextPosition();
		checkSquareMapCollision();
		setPosition(xtemp, ytemp);
		
		// check attack has stopped
		if(cAction == FLAMES) {
			if(anim.hasPOnce()) flame = false;
		}
		if(cAction == FIREBALL) {
			if(anim.hasPOnce()) firing = false;
		}
		
		// fireball attack
		fire += 1;
		if(fire > maxFire) fire = maxFire;
		if(firing && cAction != FIREBALL) {
			if(fire > fireCost) {
				fire -= fireCost;
				FireBallAttack fb = new FireBallAttack(squareMap, facingR);
				fb.setPosition(x, y);
				fireBalls.add(fb);
			}
		}
		
		// update fireballs
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).update();
			if(fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				i--;
			}
		}
		
		// check done flinching
		if(hit) {
			long elapsed =
				(System.nanoTime() - hitTimer) / 1000000;
			if(elapsed > 1000) {
				hit = false;
			}
		}
		
		// set animation
		if(flame) {
			if(cAction != FLAMES) {
				bgMusic = new GameMusic("/NewMusic/scratch.wav");
				bgMusic.start();
				cAction = FLAMES;
				anim.setFrames(sprites.get(FLAMES));
				anim.setD(50);
				width = 60;
			}
		}
		else if(firing) {
			if(cAction != FIREBALL) {
				bgMusic = new GameMusic("/NewMusic/fireball.wav");
				bgMusic.start();
				cAction = FIREBALL;
				anim.setFrames(sprites.get(FIREBALL));
				anim.setD(100);
				width = 30;
			}
		}
		else if(dy > 0) {
			if(gliding) {
				if(cAction != GLIDING) {
					cAction = GLIDING;
					anim.setFrames(sprites.get(GLIDING));
					anim.setD(200);
					width = 30;
				}
			}
			else if(cAction != FALLING) {
				cAction = FALLING;
				anim.setFrames(sprites.get(FALLING));
				anim.setD(1000);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(cAction != JUMPING) {
				cAction = JUMPING;
				anim.setFrames(sprites.get(JUMPING));
				anim.setD(-1);
				width = 30;
				bgMusic = new GameMusic("/NewMusic/jump.wav");
				bgMusic.start();
			}
		}
		else if(left || right) {
			if(cAction != WALKING) {
				cAction = WALKING;
				anim.setFrames(sprites.get(WALKING));
				anim.setD(80);
				width = 30;
			}
		}
		else {
			if(cAction != IDLE) {
				cAction = IDLE;
				anim.setFrames(sprites.get(IDLE));
				anim.setD(20000);
				width = 30;
			}
		}
		
		anim.upd();
		
		// set direction
		if(cAction != FLAMES && cAction != FIREBALL) {
			if(right) facingR = true;
			if(left) facingR = false;
		}
		
	}
	
	public void draw(Graphics2D dr) {
		
		setMapPosition();
		
		// draw fireballs
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(dr);
		}
		
		// draw player
		if(hit) {
			long elapsed =
				(System.nanoTime() - hitTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		
		
		
			 
		super.draw(dr);
		
	}
	public void reset() {
		
	}
	public void setDead() {
		health = 0;
		stop();
	}
	
	public void stop() {
		left = right = up = down = hit = 
			  jumping  = false;
	}
	
}

















