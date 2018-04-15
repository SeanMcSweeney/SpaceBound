// commented*

package Audio;

// imports

import javax.sound.sampled.*;

public class GameMusic 
{
	
	// only 1 variable to declare
	// Clip is used here as it is in my resources already 
	
	private Clip c;
	
	public GameMusic(String g) 
	{
		
		// try statement here to prevent a crash
		
		try 
		{
			
			// method gets gets the audio format ( mp3 ect (I only used wav files)) then plays the music after decoding it
			
			AudioInputStream STREAM =
					AudioSystem.getAudioInputStream(getClass().getResourceAsStream(g));
			AudioFormat bF = STREAM.getFormat();
			
			// decoder 
			
			AudioFormat dF = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, bF.getSampleRate(), 16, bF.getChannels(), bF.getChannels() * 2, bF.getSampleRate(), false );
			
			// make decoded
			
			AudioInputStream Dec = AudioSystem.getAudioInputStream(dF, STREAM); 
			
			c = AudioSystem.getClip();
			c.open(Dec);
		}
		
		// exception prevents the crash
		
		catch(Exception ce) 
		{
			ce.printStackTrace();
		}
		
	}
	
	// starts the music by getting the clip setting position to the beginning and playing the music
	
	public void start() 
	{
		if(c == null)return;
		end();
		c.setFramePosition(0);
		c.start();
	}
	
	// does not reset the song
	public void continueClip() 
	{
		c.start();
		
	}
	
	
	// checks if the clip is running if it is it stops the clip
	
	public void end() 
	{
		if(c.isRunning()) c.stop();
	}
	
	// runs the end method the closes the clip 
	
	public void sDown() 
	{
		end();
		c.close();
	}
	
}














