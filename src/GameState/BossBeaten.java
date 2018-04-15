// commented*

package GameState;

// imports

import TileMap.BackG;
import java.awt.*;
import java.awt.event.KeyEvent;
import Audio.GameMusic;

public class BossBeaten extends TheGameState {
	
	private BackG bg;
	private BackG bg2;
	private BackG bg3;
	private GameMusic bgM;

	// one choice to quit
	private int choice = 0;
	private String[] choices = 
	{
		"Quit"	
	};
	
	// set backgrounds and music
	public BossBeaten(AllGameStates ags) {
		
		this.ags = ags;
		
		try {
			
			bgM = new GameMusic("/NewMusic/Menu.wav");
			bgM.start();
			
			
			bg = new BackG("/NewBackgrounds/menuBG.gif", 1);
			bg.setV(-0.1, 0);
			bg2 = new BackG("/NewBackgrounds/Congratulations.gif", 1);
			bg2.setV(0, 0);
			bg3 = new BackG("/NewBackgrounds/confetti.gif", 1);
			bg3.setV(0.1, 0);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

		


	public void initialize() {}
	
	public void upd() {
		bg.update();
		bg2.update();
		bg3.update();
	}
	
	public void draw(Graphics2D dr) {
		
		
		
	    
		// draw backgrounds
		bg.draw(dr);
		bg2.draw(dr);
		bg3.draw(dr);

		
		
		//looped until last letter is reached
		// count the choices and set choice as the first. set colour of first as white and others as cyan. Also set the text and add 15 to the x every new option
		
		for(int i = 0; i < choices.length; i++) {
			if(i == choice) {
				dr.setColor(Color.white);
			}
			else {
				dr.setColor(Color.cyan);
			}
			dr.drawString(choices[i], 150, 160 + i * 15);
		}
		
	}
	
	/* 
	  if the choice is = 0 its the first 
	  if the choice is = 1 its the second 
	  if the choice is = 2 its the third
	 */
	 
	
	private void PickChoice() {
	/*	if(choice == 0) {
			ags.setState(AllGameStates.MENUSTATE);
			bgM.sDown();
		} */
		if(choice == 0) {
			System.exit(0);
		}
	}
	
	
	// enter runs the PickChoice method
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			PickChoice();
		}
	
		// not really needed but kept in for future ideas such as a shop
	// up takes away 1 from i ( if choice = 1 up would make it 0)
		
		if(k == KeyEvent.VK_UP) {
			choice--;
			if(choice == -1) {
				choice = choices.length - 1;
			}
		}
	// down adds 1 to i ( if choice = 1 up would make it 2)
		if(k == KeyEvent.VK_DOWN) {
			choice++;
			if(choice == choices.length) {
				choice = 0;
			}
		}
		
		if(k == KeyEvent.VK_M) bgM.end();
		if(k == KeyEvent.VK_P) bgM.continueClip();
		if(k == KeyEvent.VK_9) bgM.start();
	}
	public void keyReleased(int k) {}
	
	
	
	
}


	










