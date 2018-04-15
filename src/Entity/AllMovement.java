// commented*

package Entity;

// This class was very similar to animation

import java.awt.image.BufferedImage;

public class AllMovement 
{
	
	// Declare variables
	
	private BufferedImage[] frames;
	private int cFrame;
	private long sTime;
	private long del;
	private boolean pOnce;
	
	// pOnce must start false
	
	public AllMovement() 
	{
		pOnce = false;
	}
	
	// frame is set to first frame and time 
	
	public void setFrames(BufferedImage[] frames) 
	{
		this.frames = frames;
		cFrame = 0;
		pOnce = false;
		sTime = System.nanoTime();
	}
	
	// delay set
	
	public void setDelay(long d) 
	{ 
		del = d; 
	}
	
	// sets frame to current frame
	
	public void setFrame(int i) 
	{ 
		cFrame = i; 
	}
	
	public void update() 
	{
		
		if(del == -1) return;
		
		// sets timer
		
		long tPassed = (System.nanoTime() - sTime) / 1000000;
		
		// sets time between frame
		
		if(tPassed > del) 
		{
			cFrame++;
			sTime = System.nanoTime();
		}
		
		// loops frames
		
		if(cFrame == frames.length) 
		{
			cFrame = 0;
			pOnce = true;
		}
		
	}
	
	// get current frame
	
	public int getF() 
	{ 
		return cFrame; 
	}
	
	// get all frames
	
	public BufferedImage getI() 
	{ 
		return frames[cFrame]; 
	}
	
	// has movement played through once
	
	public boolean hasPOnce() 
	{ 
		return pOnce; 
	}
	
}

