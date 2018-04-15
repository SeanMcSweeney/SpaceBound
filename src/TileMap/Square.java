// commented *

package TileMap;

// imports

import java.awt.image.BufferedImage;

public class Square {
	
	// variables
	
	private BufferedImage i;
	private int f;
	
	// each square type
	
	public static final int RIGHT = 0;
	
	public static final int WRONG = 1;
	
	public Square(BufferedImage i, int f) {
		this.i = i;
		this.f = f;
	}
	
	// getters 
	
	public BufferedImage getI() 
	{ 
		return i; 
	}
	
	public int getT() 
	{ 
		return f; 
	}
	
}
