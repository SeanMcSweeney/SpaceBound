// all enemies are similar so I will comment loosely on the other enemies and indepth in this class

// commented *

package Entity.Enemies;

// IMPORTS

import Entity.*;
import TileMap.SquareMap;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

// extends AnyEnemy to be able to call attributes

public class Boss extends AnyEnemy 
{
	
	private BufferedImage[] sprites;
	public static boolean bossDead;
	
	public Boss(SquareMap sm) 
	{
		
		super(sm);
		
		movementSpeed = 0.5;
		maximumSpeed = 0.5;
		fSpeed = 0.2;
		maxFSpeed = 10.0;
		
		width = 45;
		height = 70;
		colwidth = 30;
		colheight = 40;
		
		setEnemyHealth(enemyMaxHealth = 50);
		enemyDamage = 2;
		
		 // load sprite images
		
		try 
		{
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/Boss1New.gif"
				)
			);
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++) 
			{
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
		}
		catch(Exception eX) 
		{
			eX.printStackTrace();
		}
		
		// sets animation ( delay determines speed of animation)
		
		anim = new AllAnimation();
		anim.setFrames(sprites);
		anim.setD(300);
		
		right = true;
		facingR = true;
		
	}
	
	// gets position
	
	private void getNextPosition() 
	{
		
		// if x is minus move left if plus move right
		
		if(left) 
		{
			dx -= movementSpeed;
			if(dx < -maximumSpeed) 
			{
				dx = -maximumSpeed;
			}
		}
		else if(right) 
		{
			dx += movementSpeed;
			if(dx > maximumSpeed) 
			{
				dx = maximumSpeed;
			}
		}
		
		// if falling add to y
		
		if(falling) {
			dy += fSpeed;
		}
		
	}
	
	public void update() 
	{
		
		// update position
		
		getNextPosition();
		checkSquareMapCollision();
		setPosition(xtemp, ytemp);
		
		// hurt
		
		if(enemyHurt) 
		{
			long elapsed =
				(System.nanoTime() - enemyHurtTimer) / 1000000;
			if(elapsed > 400) 
			{
				enemyHurt = false;
			}
		}
		
		
		// if it hits a wall, go other direction ( x is 0)
		
		if(right && dx == 0) 
		{
			right = false;
			left = true;
			facingR = false;
		}
		else if(left && dx == 0) 
		{
			right = true;
			left = false;
			facingR = true;
		}
		
		// update animation
		
		anim.upd();
		
	}
	
	public void draw(Graphics2D dr) 
	{
		
		setMapPosition();
		
		super.draw(dr);
		
	}
	
	// special boss only method which returns bossDead if boss is dead
	
	public void damage(int d) 
	{
		if(bossDead || enemyHurt) return;
		enemyHealth -= d;
		if(enemyHealth < 0) enemyHealth = 0;
		if(enemyHealth == 0) bossDead = true;
		enemyHurt = true;
		enemyHurtTimer = System.nanoTime();
	}
	

	
}











