import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO; 

public class HeartSlots {
	public GImage image;
	
	public HeartSlots (double x, double y, double width, double height)
	{
		image = new GImage ("heart.jpg", x, y);
		image.setSize(width, height);
		
	}
	
	public GImage getGImage()
	{
		return image; 
	}
	
	public void drawHearts (MainApplication program, HeartSlots hs)
	{
		program.add(hs.getGImage()); 
	}
	
	
}
