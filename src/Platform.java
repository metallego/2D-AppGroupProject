import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;



public class Platform {
	private GRect box;
	private static final int buffer = 15;
	private boolean winning = false;
	private GImage image; 

	public Platform(double x, double y, double width, double height) {
		box = new GRect(x ,y , width, height);
		image = new GImage ("platform.jpg", x, y);
		image.setSize(width, height);
	}

	public GRect getGRect() {

		return box;
	}
	
	public GImage getGImage()
	{
		return image; 
	}

	
	public void drawPlatform(MainApplication program, Platform p)
	{
	    //program.add(p.getGRect());
	    program.add(p.getGImage());
	}

	/*
	public int getY()
	{
	    return (int)box.getY();
	}
	
	public boolean isUnderneath( Point2D.Double p)
	{
	    if( p.getX() >= box.getX() && p.getX() <= box.getX() + box.getWidth() )
	    {
	        double diff = p.getY() - box.getY();
	        return diff <= buffer && diff >= 0;
	    }
	    return false;
	}
	*/
	
	public int getY()
	{
	    return (int)image.getY();
	}
	
	public boolean isUnderneath( Point2D.Double p)
	{
	    if( p.getX() >= image.getX() && p.getX() <= image.getX() + image.getWidth() )
	    {
	        double diff = p.getY() - image.getY();
	        return diff <= buffer && diff >= 0;
	    }
	    return false;
	}

	
	public void setWinning( boolean b )
	{
	    winning = b;
	}
	
	public boolean checkWin( Hero h, int i )
	{
	    if( isUnderneath( h.getBottomFeet()) && winning && (h.getCoins() >= i) )
	    {
	        return true;
	    }
	    return false;
	}

}
