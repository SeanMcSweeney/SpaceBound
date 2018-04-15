// commented*

package Entity;

// imports

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PlayerHUD {
	
	private Player1 player1;
	private BufferedImage i;
	private Font Ari;
	private Font Imp;
	
	//get the image and set the fonts
	public PlayerHUD(Player1 p) {
		player1 = p;
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/HUD/hud.gif"));
			Ari = new Font("Arial", Font.PLAIN, 14);
			Imp = new Font("Impact", Font.PLAIN, 14);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//draw the HUD and the timer
	public void draw(Graphics2D dr) {
		
		dr.drawImage(i, 0, 10, null);
		dr.setFont(Ari);
		dr.setColor(Color.WHITE);
		dr.drawString(player1.getHealth() + "/" + player1.getMaxHealth(),30,25);
		dr.drawString(player1.getFire() / 100 + "/" + player1.getMaxFire() / 100, 30, 45);
		dr.setFont(Imp);
		dr.setColor(java.awt.Color.BLACK);
		dr.drawString(player1.getTimeToString(), 294, 45);
		dr.setColor(java.awt.Color.WHITE);
		dr.drawString(player1.getTimeToString(), 292, 45);
		
	}
	
}













