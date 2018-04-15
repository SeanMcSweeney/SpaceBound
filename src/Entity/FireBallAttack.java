// commented*

package Entity;

// imports

import TileMap.SquareMap;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FireBallAttack extends AnyEntity 
{
	
	// variables
	
	private boolean onHit;
	private boolean rem;
	private BufferedImage[] Char;
	private BufferedImage[] onHitChar;
	
	public FireBallAttack(SquareMap sm, boolean right) 
	{
		
		// get attributes from the square map
		
		super(sm);
		
		// attributes and square size
		
		facingR = right;
		
		movementSpeed = 3.8;
		if(right) dx = movementSpeed;
		else dx = -movementSpeed;
		
		width = 30;
		height = 30;
		colwidth = 14;
		colheight = 14;
		
		// Get sprites while preventing a crash
		
		try 
		{
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/fireball.gif"
				)
			);
			
			// first line of sprites
			
			Char = new BufferedImage[4];
			for(int i = 0; i < Char.length; i++) 
			{
				Char[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
			// second line of sprites
			
			onHitChar = new BufferedImage[3];
			for(int i = 0; i < onHitChar.length; i++) 
			{
				onHitChar[i] = spritesheet.getSubimage(
					i * width,
					height,
					width,
					height
				);
			}
			
			// the animation set the delay on each frame
			
			anim = new AllAnimation();
			anim.setFrames(Char);
			anim.setD(70);
			
		}
		catch(Exception eX) 
		{
			eX.printStackTrace();
		}
		
	}
	// starts the on hit sprite
	public void setHit() 
	{
		if(onHit) return;
		onHit = true;
		anim.setFrames(onHitChar);
		anim.setD(70);
		dx = 0;
	}
	
	// remove
	
	public boolean shouldRemove() 
	{ 
		return rem; 
	}
	
	public void update() 
	{
		
		//if fireball hits it sets the sprite number 2 and then removes it after it has played once
		
		
		checkSquareMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !onHit) 
		{
			setHit();
		}
		
		anim.upd();
		if(onHit && anim.hasPOnce()) 
		{
			rem = true;
		}
		
	}
	
	public void draw(Graphics2D dr)
	{
		
		// draw with the position
		
		setMapPosition();
		
		super.draw(dr);
		
	}
	
}


















