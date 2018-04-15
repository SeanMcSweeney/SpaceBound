// commented*

package Main;

// import

import javax.swing.JFrame;

public class Window {
	
	// make the screen
	
	public static void main(String[] args) 
	{
		// name of window and created 
		JFrame window = new JFrame("Space Bound");
		
		// close when exited
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// can't be resized
		window.setResizable(false);
		
		// layer to hold the objects
		window.setContentPane(new InWindow());
		
		// lets you size 
		window.pack();
		
		// must be visible 
		window.setVisible(true);
		
		
	}
	
}
