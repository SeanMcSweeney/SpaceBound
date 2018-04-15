// commented *

package TileMap;

import Main.InWindow;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class BackG {
	
	// Declare the variables 
	
	private BufferedImage pic;
	private double len;
	private double wid;
	private double doubLen;
	private double doubWid;
	private double mScale;
	
	public BackG(String s, double ms) {
		
		// used to read in the image in this case the background
		try {
			pic = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			mScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	// position
	public void setP(double x, double y) {
		this.len = (len * mScale) % InWindow.WIDTH;
		this.wid = (wid * mScale) % InWindow.HEIGHT;
	}
	
	// set the x and y position
	public void setV(double dL, double dW) {
		this.doubLen = dL;
		this.doubWid = dW;
	}
	
	public void update() {
		len += doubLen;
		wid += doubWid;
	}
	
	public void draw(Graphics2D dr) {
		
		// draw the image using the length and width that have been given
		
		dr.drawImage(pic, (int)len, (int)wid, null);
		
		if(len < 0) {
			dr.drawImage(
				pic,
					(int)len + InWindow.WIDTH,
					(int)wid,
					null
			);
		}
		if(len > 0) {
			dr.drawImage(
				pic,
					(int)len - InWindow.WIDTH,
					(int)wid,
					null
			);
		}
	}
	
}







