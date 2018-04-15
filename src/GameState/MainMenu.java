// commented *

package GameState;

// imports

import TileMap.BackG;
import java.awt.*;
import java.awt.event.KeyEvent;
import Audio.GameMusic;

public class MainMenu extends TheGameState {
	
	private BackG bg;
	private BackG bg2;
	private GameMusic bgM;

	// make an array of the choices
	
	private int choice = 0;
	private String[] choices = {
		"Start Game",
		"Controls",
		"Quit"
		
		
	};
	
	private Color tColor;
	private Font tFont;
	private Color t2Color;
	private Font t2Font;
	
	private Font menuF;
	
	public MainMenu(AllGameStates ags) {
		
		this.ags = ags;
		
		// set backgrounds and music and fonts
		
		try {
			
			bgM = new GameMusic("/NewMusic/Menu.wav");
			bgM.start();
			
			
			bg = new BackG("/NewBackgrounds/menuBG.gif", 1);
			bg.setV(-0.1, 0);
			bg2 = new BackG("/NewBackgrounds/spaceship.gif", 1);
			bg2.setV(0.3, 0.05);
			
			tColor = new Color(0,255,255);			
			tFont = new Font(
					"Impact",
					Font.PLAIN,
					28);
			
			t2Color = new Color(255,43,226);			
			t2Font = new Font(
					"Impact",
					Font.PLAIN,
					28);
			
			menuF = new Font("Arial", Font.PLAIN, 12);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

		


	public void initialize() {}
	
	public void upd() {
		bg.update();
		bg2.update();
	}
	
	public void draw(Graphics2D dr) {
		
		
		
	    
		// draw backgrounds
		bg.draw(dr);
		bg2.draw(dr);
		
		// draw titles
		dr.setColor(t2Color);
		dr.setFont(t2Font);
		dr.drawString("Space Bound", 81, 70);
		
		dr.setColor(tColor);
		dr.setFont(tFont);
		dr.drawString("Space Bound", 80, 70);
		
		
		// draw developed by and the choices
		dr.setFont(menuF);
		dr.setColor(Color.white);
		dr.drawString("Developed By Sean McSweeney" , 20, 230 );
		
		//looped until last letter is reached
		// count the choices and set choice as the first. set colour of first as white and others as cyan. Also set the text and add 15 to the x every new option
		
		for(int i = 0; i < choices.length; i++) {
			if(i == choice) {
				dr.setColor(Color.white);
			}
			else {
				dr.setColor(Color.cyan);
			}
			dr.drawString(choices[i], 110, 140 + i * 15);
		}
		
	}
	
	/* 
	  if the choice is = 0 its the first 
	  if the choice is = 1 its the second 
	  if the choice is = 2 its the third
	 */
	 
	
	private void PickChoice() {
		if(choice == 0) {
			ags.setState(AllGameStates.LEVEL1STATE);
			bgM.sDown();
		}
		if(choice == 1) {
			ags.setState(AllGameStates.CONTROLSSTATE);
			bgM.sDown();
		}
		if(choice == 2) {
			System.exit(0);
		}
	}
	
	
	// enter runs the PickChoice method
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			PickChoice();
		}
		
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


	










