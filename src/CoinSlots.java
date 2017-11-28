import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO; 

public class CoinSlots {
	public GImage image;
	
	public CoinSlots (double x, double y, double width, double height)
	{
		image = new GImage ("coin_slot.jpg", x, y);
		image.setSize(width, height);
		
	}
	
	public GImage getGImage()
	{
		return image; 
	}
	
	public void drawCoins (MainApplication program, CoinSlots cs)
	{
		program.add(cs.getGImage()); 
	}
	
	
	
	
}
