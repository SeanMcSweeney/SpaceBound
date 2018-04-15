// commented*

package Entity;

// imports

import java.awt.image.BufferedImage;

public class AllAnimation 
{
	
	// Declare variables
	// Buffered image is used instead of image as image is an abstract java class and cannot be instantiated
	// private longs as both stop time and delay are unknowns and may need more space than an int has to offer
	
	private BufferedImage[] frames;
	private int cFrame;
	private long sTime;
	private long del;
	private boolean pOnce;
	
	// set pOnce to false as at the start of the animation played once must be false to continue the animation
	
	public AllAnimation() 
	{
		pOnce = false;
	}
	
	// this sets the frames of what is to be animated so it can be animated 
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		cFrame = 0;
		pOnce = false;
		
		// set time
		
		sTime = System.nanoTime();
	}
	
	// sets the delay ( how fast the animation moves )
	
	public void setD(long d) 
	{ 
		del = d; 
	}
	
	// set the current frame 
	
	public void setF(int i) 
	{ 
		cFrame = i; 
	}
	
	public void upd() 
	{
		
		// will not work if the delay is a minus
		
		if(del == -1) return;
		
		// calculates the time that has passed ( makes animation move at delay speed provided) 
		
		long tPassed = (System.nanoTime() - sTime) / 1000000;
		
		// if the time has passed move to next frame
		// start timer
		
		if(tPassed > del) 
		{
			cFrame++;
			sTime = System.nanoTime();
		}
		
		// loops the frames 
		
		if(cFrame == frames.length)
		{
			cFrame = 0;
			pOnce = true;
		}
		
	}
	
	// get the current frame
	
	public int getF() 
	{ 
		return cFrame; 
	}
	
	// get the current image ( all frames)
	
	public BufferedImage getI() 
	{ 
		return frames[cFrame]; 
	}
	
	// return if the animation has been played through once
	
	public boolean hasPOnce() 
	{ 
		return pOnce; 
	}
	
}
