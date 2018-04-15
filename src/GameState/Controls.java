// commented*

package GameState;

// imports

import TileMap.BackG;
import java.awt.*;
import java.awt.event.KeyEvent;
import Audio.GameMusic;

public class Controls extends TheGameState {
	
	// declare the variables
	
	private BackG bg;
	private BackG bg2;
	private GameMusic bgM;
	private Color titleColor;
	private Font titleFont;	
	private Color title2Color;
	private Font title2Font;	
	private Font plain;
	
	public Controls(AllGameStates ags) {
		
		this.ags = ags;
		
		try {
			
			//instantiate the classes 
			//start the controls music 
			
			bgM = new GameMusic("/NewMusic/Controls.wav");
			bgM.start();
			
			//set the backgrounds and positions
			// the setV method allows me to move the gifs in the desired direction
			
			bg = new BackG("/NewBackgrounds/menuBG.gif", 1);
			bg.setV(-0.1, 0);
			bg2 = new BackG("/NewBackgrounds/asteroid.gif", 1);
			bg2.setV(0.5, 0.2);
			
			// set the fonts 
			
			titleColor = new Color(0,255,255);			
			titleFont = new Font("Impact", Font.PLAIN, 28);
			
			title2Color = new Color(255,43,226);			
			title2Font = new Font( "Impact", Font.PLAIN, 28);
			
			plain = new Font("Arial", Font.PLAIN, 12);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

		
	

	public void initialize() {}
	
	public void upd() {
		//update the backgrounds 
		bg.update();
		bg2.update();
	}
	
	public void draw(Graphics2D dr) {
		
		// draw backgrounds
		bg.draw(dr);
		bg2.draw(dr);
		
		// draw titles
		dr.setColor(titleColor);
		dr.setFont(titleFont);
		dr.drawString("Controls", 111, 70);
		
		dr.setColor(title2Color);
		dr.setFont(title2Font);
		dr.drawString("Controls", 110, 70);
		
		// draw  controls
		dr.setFont(plain);
		dr.setColor(Color.white);
		dr.drawString("Left / A = Move Left " , 40, 100 );
		dr.drawString("Right / D = Move Right " , 40, 120 );
		dr.drawString("Up / W = Jump " , 40, 140 );
		dr.drawString("E = Glide " , 40, 160 );
		dr.drawString("R = Flame Attack " , 40, 180 );
		dr.drawString("F = Fireball" , 180, 100 );
		dr.drawString("0 = Restart Level" , 180, 120 );
		dr.drawString("M = Mute Music" , 180, 140 );
		dr.drawString("P = Continue Music" , 180, 160 );
		dr.drawString("9 = Restart Music" , 180, 180 );
		dr.drawString("ESC = Return To Menu " , 20, 20 );
		}
		
	
	// if escape is pressed return to the menu
	
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_ESCAPE) {
			ags.setState(AllGameStates.MENUSTATE);
			bgM.sDown();
			}
		if(k == KeyEvent.VK_M) bgM.end();
		if(k == KeyEvent.VK_P) bgM.continueClip();
		if(k == KeyEvent.VK_9) bgM.start();
		}
		
	
	public void keyReleased(int k) {}
	
}
