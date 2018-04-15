// commented *

package Entity;

// imports

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class EnemyDeadEffect 
{
	
	// variables
	
	private int x;
	private int xmap;
	private int y;
	private int ymap;	
	
	// width and height
	
	private int w;
	private int h;	
	
	// remove
	
	private boolean r;	
	private AllMovement animation;
	private BufferedImage[] sprites;
	
	
	public EnemyDeadEffect(int x, int y) 
	{
		
		// this gets x and y from current class
		
		this.x = x;
		this.y = y;
		
		
		//width and height 
		w = 30;
		h = 30;
		
		// try prevents a crash
		// read in the sprites 1 by 1 
		try 
		{
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/Ede.gif"
				)
			);
			
			sprites = new BufferedImage[6];
			for(int i = 0; i < sprites.length; i++) 
			{
				sprites[i] = spritesheet.getSubimage(
					i * w,
					0,
					w,
					h
				);
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		// run the movement class ( animate the character)
		animation = new AllMovement();
		animation.setFrames(sprites);
		animation.setDelay(70);
		
	}
	
	public void update() 
	{
		animation.update();
		
		// makes sure the sprites arent added more than once
		
		if(animation.hasPOnce()) 
		{
			r = true;
		}
	}
	
	// removes the sprite
	
	public boolean shouldRemove() 
	{
		return r; 
	}
	
	// sets the postion on the map
	
	public void setMapPosition(int x, int y) 
	{
		xmap = x;
		ymap = y;
	}
	
	// draws the image
	
	public void draw(Graphics2D dr) 
	{
		dr.drawImage(
			animation.getI(),
			x + xmap - w / 2,
			y + ymap - h / 2,
			null
		);
	}
	
}

















