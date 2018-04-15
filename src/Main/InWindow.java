// commented
// screen was mainly taken from tutorial

package Main;

// imports

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;
import GameState.AllGameStates;

@SuppressWarnings("serial")
public class InWindow extends JPanel 
	implements Runnable, KeyListener{
	
	// screen size
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;	
	
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long tTime = 1000 / FPS;
	private BufferedImage i;
	private Graphics2D dr;
	private AllGameStates ags;
	
	public InWindow() 
	// set game size
	{
		super();
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() 
	{
		super.addNotify();
		if(thread == null) 
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	// set screen
	private void init() {
		
		i = new BufferedImage(
					WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB
				);
		dr = (Graphics2D) i.getGraphics();
		
		running = true;
		
		ags = new AllGameStates();
		
	}
	
	public void run()
	{
		
		init();
		
		long startTime;
		long tPassed;
		long waitTime;
		
		// game loop
		while(running) 
		{
			
			startTime = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			tPassed = System.nanoTime() - startTime;
			
			waitTime = tTime - tPassed / 1000000;
			if(waitTime < 0) waitTime = 5;
			
			try 
			{
				Thread.sleep(waitTime);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void update() 
	{
		ags.upd();
	}
	private void draw() 
	{
		ags.draw(dr);
	}
	private void drawToScreen() 
	{
		Graphics dr2 = getGraphics();
		dr2.drawImage(i, 0, 0,
				WIDTH * SCALE, HEIGHT * SCALE,
				null);
		dr2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		ags.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		ags.keyReleased(key.getKeyCode());
	}

}
















