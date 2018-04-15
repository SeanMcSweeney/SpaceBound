package Entity;

// imports

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Main.InWindow;

public class AllTitles {
	
	// Declare the variables
	
	public BufferedImage i;
	public int counter;
	
	// variable is true when process is finished
		
	private boolean finished;
	private boolean rem;	
	private double x;
	private double y;
	private double dx;	
	private int w;
	
	public AllTitles(String s) 
	{
		
		// try prevents crashing
		
		try 
		{
			// reads in the image
			
			i = ImageIO.read(getClass().getResourceAsStream(s));
			w = i.getWidth();
			x = -w;
			finished = false;
		}
		catch(Exception eX) 
		{
			eX.printStackTrace();
		}
		
	}
	
	
	// gets image info from the Buffered image that is set in the level 1 class
	
	public AllTitles(BufferedImage i) 
	{
		this.i = i;
		w = i.getWidth();
		x = -w;
		finished = false;
	}
	
	// sets the Y position
	
	public void setY(double y) 
	{ 
		this.y = y; 
	}
	
	// begins the title animation
	
	public void begin() 
	{
		// determines speed of movement before and after still
		// moving 7 pixels at a time 
		
		dx = 7;
	}
	
	// removes the title
	
	public boolean shouldRem() 
	{ 
		return rem; 
	}
	
	public void upd() 
	{
		
		// checks that the transition is not completed
		
		if(!finished) 
		{
			
			// allows the transition to happen ( if the x position is greater or equal to half of the window(screen)  it stops moving)
			// when x gets to half of the screen it starts counter
			// when counter = the time wanted I chose 200 it makes finished = true
			
			
			if(x >= (InWindow.WIDTH - w) / 2) 
			{
				x = (InWindow.WIDTH - w) / 2;
				counter++;
				
				// speed of the title when still before exiting
				// finished after the transition
				
				if(counter >= 200) finished = true;
			}
			
			// after counter is complete the if statement is complete and it just runs the else
			// this adds dx to the x position which moves the image to the right at the desired speed
			
			else 
			{
				x += dx;
			}
		}
		else 
		{
			
			// if finished is true ( title moved to middle and counter complete ) it moves the x off the screen
			
			x += dx;
			
			// remove when outside the game panel
			
			if(x > InWindow.WIDTH) rem = true;
		}
	}
	
	// draws image
	
	public void draw(Graphics2D dr) 
	{
		dr.drawImage(i, (int)x, (int)y, null);
	}
	
}
