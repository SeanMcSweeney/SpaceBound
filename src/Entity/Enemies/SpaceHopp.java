// commented*

package Entity.Enemies;

//imports

import Entity.*;
import TileMap.SquareMap;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public class SpaceHopp extends AnyEnemy {
	
	private BufferedImage[] sprites;
	
	public SpaceHopp(SquareMap sm) {
		
		super(sm);
		
		movementSpeed = 0.5;
		maximumSpeed = 0.5;
		fSpeed = 0.2;
		maxFSpeed = 10.0;
		jumping = true;
		jStart = -5;
		
		width = 30;
		height = 30;
		colwidth = 20;
		colheight = 20;
		
		setEnemyHealth(enemyMaxHealth = 1);
		enemyDamage = 1;
		
		// load sprite images
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/SpaceHopp.gif"
				)
			);
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		anim = new AllAnimation();
		anim.setFrames(sprites);
		anim.setD(50);
		
		right = true;
		facingR = true;
		
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
		
		// jumping
		if(jumping && !falling) {
			dy = jStart;
			falling = true;
		}
		
		// falling
		if(falling) {
			dy += fSpeed;
		}
		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkSquareMapCollision();
		setPosition(xtemp, ytemp);
		
		// check flinching
		if(enemyHurt) {
			long elapsed =
				(System.nanoTime() - enemyHurtTimer) / 1000000;
			if(elapsed > 400) {
				enemyHurt = false;
			}
		}
		
		// if it hits a wall, go other direction
		if(right && dx == 0) {
			right = false;
			left = true;
			facingR = false;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
			facingR = true;
		}
		
		// update animation
		anim.upd();
		
	}
	
	public void draw(Graphics2D dr) {
		
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(dr);
		
	}
	
}

