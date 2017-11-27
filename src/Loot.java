import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;

public class Loot extends GraphicsProgram
{
    public GImage image;
    public Loot()
    {
        image = new GImage("coin.jpg", 700, 450);
    }
    
    public void addImage( MainApplication p )
    {
        p.add( image );
    }
    
    public void removeImage( MainApplication p )
    {
        p.remove( image );
    }
    
    public void pickUp( Hero h, MainApplication p )
    {
        Point2D.Double point = new Point2D.Double();
        point = h.getBottomFeet();
        if( image.contains( point.getX(), point.getY() ))
        {
            h.addCoin();
            p.remove( image );
        }
    }
}