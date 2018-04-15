// comented *

package Entity;

// imports

import Main.InWindow;
import TileMap.SquareMap;
import TileMap.Square;
import java.awt.Rectangle;

// abstract class means methods cannot be instantiated only extended

public abstract class AnyEntity 
{
	
	// the square map attributes
	
	protected SquareMap squareMap;
	protected int squareSize;
	protected double xmp;
	protected double ymp;
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	protected int width;
	protected int height;
	
	// collision x and y
	
	protected int colwidth;
	protected int colheight;
	
	// current column and row
	
	
	protected int cRow;
	protected int cCol;
	protected double xdes;
	protected double ydes;
	protected double xtemp;
	protected double ytemp;
	protected boolean topL;
	protected boolean topR;
	protected boolean bottomL;
	protected boolean bottomR;
	
	//  uses the AllAnimation class
	
	protected AllAnimation anim;
	protected int cAction;
	protected int pAction;
	protected boolean facingR;
	
	//uses the AllMovement class
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	protected double movementSpeed;
	protected double maximumSpeed;
	
	// speed to stop entity moving
	
	protected double sSpeed;
	
	// speed entity falls at
	
	protected double fSpeed;
	
	// max speed to fall
	
	protected double maxFSpeed;
	
	// jump speed 
	
	protected double jStart;
	
	// speed to stop the jump
	
	protected double sJSpeed;
	
	// use of constructor to set the Square ( other known as tile ) 
	
	public AnyEntity(SquareMap sm) 
	{
		squareMap = sm;
		squareSize = sm.getSquareSize(); 
	}
	
	// checks if the shape intersects
	
	public boolean intersects(AnyEntity o) 
	{
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	// rectangle size with the collision boxes
	
	public Rectangle getRectangle() 
	{
		return new Rectangle(
				(int)x - colwidth,
				(int)y - colheight,
				colwidth,
				colheight
		);
	}
	
	// gets the corners of the rectangle by using factors such as the collision and square sizes
	// it gets all the factors that are later declared and puts them together to make the rectangle
	
	public void calculateCorners(double x, double y) 
	{
		
		int leftSquare = (int)(x - colwidth / 2) / squareSize;
		int rightSquare = (int)(x + colwidth / 2 - 1) / squareSize;
		int topSquare = (int)(y - colheight / 2) / squareSize;
		int bottomSquare = (int)(y + colheight / 2 - 1) / squareSize;
		
		int tl = squareMap.getType(topSquare, leftSquare);
		int tr = squareMap.getType(topSquare, rightSquare);
		int bl = squareMap.getType(bottomSquare, leftSquare);
		int br = squareMap.getType(bottomSquare, rightSquare);
		
		topL = tl == Square.WRONG;
		topR = tr == Square.WRONG;
		bottomL = bl == Square.WRONG;
		bottomR = br == Square.WRONG;
		
	}
	
	// check if sm has collisions
	// one of the most used methods out of AnyEntity class as it makes sure the map is what was intended
	
	public void checkSquareMapCollision() 
	{
		
		cCol = (int)x / squareSize;
		cRow = (int)y / squareSize;
		
		xdes = x + dx;
		ydes = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydes);
		
		
		// if dy is less than 0 run
		
		if(dy < 0) 
		{
			// if topL or topR are true run
			
			if(topL || topR) 
			{
				// dy is set to 0 and the y position is the current row * square and collision height divided by 2 to help to make final used square
				
				dy = 0;
				ytemp = cRow * squareSize + colheight / 2;
			}
			else 
			{
				ytemp += dy;
			}
		}
		
		// same as first if statement with different parts
		// falling is set to false as later bl and br to make falling true
		
		if(dy > 0) 
		{
			if(bottomL || bottomR) 
			{
				dy = 0;
				falling = false;
				ytemp = (cRow + 1) * squareSize - colheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		calculateCorners(xdes, y);
		
		// same as first if statement with different parts but on the opposite side
		
		if(dx < 0) 
		{
			if(topL || bottomL) 
			{
				dx = 0;
				xtemp = cCol * squareSize + colwidth / 2;
			}
			else 
			{
				xtemp += dx;
			}
		}
		
		// same as first if statement with different parts but on the opposite side
		
		if(dx > 0) 
		{
			if(topR || bottomR) 
			{
				dx = 0;
				xtemp = (cCol + 1) * squareSize - colwidth / 2;
			}
			else 
			{
				xtemp += dx;
			}
		}
		
		// makes sure that the tile falls from the top to bottom
		
		if(!falling) 
		{
			calculateCorners(x, ydes + 1);
			
			if(!bottomL && !bottomR) 
			{
				falling = true;
			}
		}		
	}
	
	public int getx() 
	{ 
		return (int)x; 
	}
	
	public int gety() 
	{ 
		return (int)y; 
	}
	
	public int getWidth() 
	{ 
		return width; 
	}
	
	public int getHeight() 
	{ 
		return height; 
	}
	
	public int getCWidth() 
	{ 
		return colwidth; 
	}
	
	public int getCHeight() 
	{ 
		return colheight;
	}
	
	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition()
	{
		xmp = squareMap.getx();
		ymp = squareMap.gety();
	}
	
	public void setLeft(boolean b) 
	{ 
		left = b; 
	}
	
	public void setRight(boolean b) 
	{ 
		right = b; 
	}
	
	public void setUp(boolean b) 
	{ 
		up = b; 
	}
	
	public void setDown(boolean b) 
	{ 
		down = b;
	}
	
	public void setJumping(boolean b) 
	{ 
		jumping = b; 
	}
	
	// makes sure that if it is not in window it will not run 
	
	public boolean notOnScreen()
	{
		return x + xmp + width < 0 ||
			x + xmp - width > InWindow.WIDTH ||
			y + ymp + height < 0 ||
			y + ymp - height > InWindow.HEIGHT;
	}
	
	// draw the image
	
	public void draw(java.awt.Graphics2D dr) 
	{
		if(facingR)
		{
			dr.drawImage(
				anim.getI(),
				(int)(x + xmp - width / 2),
				(int)(y + ymp - height / 2),
				null
			);
		}
		else {
			dr.drawImage(
				anim.getI(),
				(int)(x + xmp - width / 2 + width),
				(int)(y + ymp - height / 2),
				-width,
				height,
				null
			);
		}
	}
	
}
















