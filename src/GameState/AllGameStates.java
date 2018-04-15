// This class contains all of the game states and is constantly called by other states to set switch and load states
// commented*

package GameState;

// imports

import GameState.igControls;
import Main.InWindow;

public class AllGameStates {

	// Declare the variables 
	
	private TheGameState[] gStates;
	private int cState;
	private igControls igcontrols;
	private boolean igc;
	public static final int NUMGAMESTATES = 5;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int LEVEL2STATE = 2;
	public static final int CONTROLSSTATE = 3;
	public static final int WINSTATE = 4;

	
	public AllGameStates() {
		
		// the game state array is created with the number of gamestates (3 in this case)
		gStates = new TheGameState[NUMGAMESTATES];
		
		//(Not for game state for the in game controls)
		igcontrols = new igControls(this);
		igc = false;
		
		//starting state
		cState = MENUSTATE;
		loadState(cState);
		
	}
	
	//load the states
	
	private void loadState(int state) {
		
		if(state == MENUSTATE)
			gStates[state] = new MainMenu(this);
		
		if(state == LEVEL1STATE)
			gStates[state] = new Level1(this);
		
		if(state == LEVEL2STATE)
			gStates[state] = new Level2(this);
		
		if(state == CONTROLSSTATE)
			gStates[state] = new Controls(this);
		
		if(state == WINSTATE)
			gStates[state] = new BossBeaten(this);
	    
	}
	
	//unload the states if gstates is null (no state)
	
	private void unloadState(int state) {
		gStates[state] = null;
	}
	
	// for transfering between states unloads and reloads states
	
	public void setState(int state) {
		unloadState(cState);
		cState = state;
		loadState(cState);
	}
	
	// set the ingame controls
	
	public void setIgc(boolean b) { igc = b; }
	
	public void upd() {
		// sets the in game controls and game states
		if(igc) {
			igcontrols.upd();
			return;
		}
		if(gStates[cState] != null) gStates[cState].upd();
	}
	
	public void draw(java.awt.Graphics2D dr) {
		
		// Draws the in game controls class
		
		if(igc) {
			igcontrols.draw(dr);
			return;
		}
		if(gStates[cState] != null) gStates[cState].draw(dr);
		else {
			dr.setColor(java.awt.Color.BLACK);
			dr.fillRect(0, 0, InWindow.WIDTH, InWindow.HEIGHT);
		}
	}
	
	// null in this class but compulsory
	
	public void keyPressed(int k) {
		gStates[cState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gStates[cState].keyReleased(k);
	}				
	}
	










