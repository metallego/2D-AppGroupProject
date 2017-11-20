import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;



public class Platform {
	private GRect box;
	private static final int buffer = 10;
	private boolean winning = false;

	public Platform(double x, double y, double width, double height) {
		box = new GRect(x ,y , width, height);

	}

	public GRect getGRect() {

		return box;
	}
	
	public void drawPlatform(MainApplication program, Platform p)
	{
	    program.add(p.getGRect());
	}
	
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
