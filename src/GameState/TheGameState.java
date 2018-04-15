// commented*

package GameState;

// abstract class means no instantiation 

public abstract class TheGameState {
	
	// AllGameStates is protected as it is only to be accessible in the same package in the same package
	// as these declared variables are abstract they cannot instantiated they can only be extended!!!
	// declared variables
	
	protected AllGameStates ags;
	public abstract void initialize();
	public abstract void upd();
	public abstract void draw(java.awt.Graphics2D dr);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}
