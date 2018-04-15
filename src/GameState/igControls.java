/* 
 
  When I was making this class it was almost the exact same as the control class I made as it is just using that class in the game

  
 */

// commented*

package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import GameState.AllGameStates;
import TileMap.BackG;

public class igControls extends TheGameState {
	
	// declare variables 

	private Font plainF;	
	private BackG bg;
	private Color titleColor;
	private Font titleFont;	
	private Color title2Color;
	private Font title2Font;
	
	public igControls(AllGameStates ags) {
		
		// the use of super extends the content that I wrote in the gamestate class
		
		super();
		try {	
			
			// instantiate the classes and set backgrouns and fonts
			
			bg = new BackG("/NewBackgrounds/NewBG.gif", 1);
			bg.setV(-0.1, 0);
			
			titleColor = new Color(0,255,255);			
			titleFont = new Font("Impact", Font.PLAIN, 28);		
			
			title2Color = new Color(255,43,226);			
			title2Font = new Font("Impact", Font.PLAIN, 28);
			
			plainF = new Font("Arial", Font.PLAIN, 12);			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void initialize() {}
	
	public void upd() {
		// update the class
		bg.update();
	}
	
	public void draw(Graphics2D dr) {
		// draw background
				bg.draw(dr);
				
				
				// draw title
				dr.setColor(titleColor);
				dr.setFont(titleFont);
				dr.drawString("Controls", 111, 70);
				
				dr.setColor(title2Color);
				dr.setFont(title2Font);
				dr.drawString("Controls", 110, 70);
				
				// draw controls 
				dr.setFont(plainF);
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

	@Override
	public void keyPressed(int k) {
		
	}

	@Override
	public void keyReleased(int k) {
		
	}
}	
