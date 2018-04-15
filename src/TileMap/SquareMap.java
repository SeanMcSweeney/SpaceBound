// commented *

package TileMap;

// imports

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import Main.InWindow;

public class SquareMap {
	
	// positions
	private double x;
	private double y;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double between;
	
	// map attributes
	private int[][] map;
	private int squareSize;
	private int numberRows;
	private int numberCols;
	private int width;
	private int height;
	
	// square
	private BufferedImage squareset;
	private int numTilesAcross;
	private Square[][] squares;
	
	// draw
	private int rowOffset;
	private int colOffset;
	private int numberRowsToDraw;
	private int numberColsToDraw;
	
	
	// makes the map
	
	public SquareMap(int tileSize) 
	{
		this.squareSize = tileSize;
		numberRowsToDraw = InWindow.HEIGHT / tileSize + 2;
		numberColsToDraw = InWindow.WIDTH / tileSize + 2;
		between = 0.07;
	}
	
	// loads tiles
	
	public void loadTiles(String s) 
	{
		
		// prevents crash 
		// reads image
		
		try 
		{

			squareset = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			numTilesAcross = squareset.getWidth() / squareSize;
			squares = new Square[3][numTilesAcross];
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) 
			{
				subimage = squareset.getSubimage(
							col * squareSize,
							0,
							squareSize,
							squareSize
						);
				squares[0][col] = new Square(subimage, Square.RIGHT);
				subimage = squareset.getSubimage(
							col * squareSize,
							squareSize,
							squareSize,
							squareSize
						);
				squares[1][col] = new Square(subimage, Square.WRONG);
				
				
			}
			
		}
		catch(Exception eX) 
		{
			eX.printStackTrace();
		}
		
	}
	
	// loads the map with images
	
	public void loadMap(String s) 
	{
		
		try 
		{
			
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			// read size for number of rows
			
			numberCols = Integer.parseInt(br.readLine());
			numberRows = Integer.parseInt(br.readLine());
			map = new int[numberRows][numberCols];
			width = numberCols * squareSize;
			height = numberRows * squareSize;
			
			xmin = InWindow.WIDTH - width;
			xmax = 0;
			ymin = InWindow.HEIGHT - height;
			ymax = 0;
			
			String de = "\\s+";
			
			// when row is less than the total rows loop
			
			for(int row = 0; row < numberRows; row++) 
			{
				String line = br.readLine();
				String[] tokens = line.split(de);
				for(int col = 0; col < numberCols; col++) 
				{
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception eX) 
		{
			eX.printStackTrace();
		}
		
	}
	
	// simple Get methods
	
	public int getSquareSize() 
	{ 
		return squareSize; 
	}
	
	public double getx() 
	{ 
		return x; 
	}
	
	public double gety() 
	{ 
		return y; 
	}
	
	public int getWidth() 
	{ 
		return width; 
	}
	
	public int getHeight() 
	{ 
		return height; 
	}
	
	
	public int getType(int row, int col) 
	{
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return squares[r][c].getT();
	}
	
	public void setTween(double d) 
	{ 
		between = d; 
	}
	
	public void setPosition(double x, double y) 
	{
		
		this.x += (x - this.x) * between;
		this.y += (y - this.y) * between;
		
		fixBounds();
		
		colOffset = (int)-this.x / squareSize;
		rowOffset = (int)-this.y / squareSize;
		
	}
	
	private void fixBounds() 
	{
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D dr) 
	{
		
		for(
			int row = rowOffset;
			row < rowOffset + numberRowsToDraw;
			row++) 
		{
			
			if(row >= numberRows) break;
			
			// gets 1 by 1
			
			for(
				int col = colOffset;
				col < colOffset + numberColsToDraw;
				col++) 
			{
				
				if(col >= numberCols) break;
				
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				dr.drawImage(
						squares[r][c].getI(),
					(int)x + col * squareSize,
					(int)y + row * squareSize,
					null
				);
				
			}
			
		}
		
	}
	
}



















