import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;

public class Chest extends Entity{
		
	public Chest()
	{
		image = new GImage("wooden_chest1.jpg", 500, 400);
		image.setSize(50, 50);
	}
	
	public Chest( int i, int j )
	{
	    image = new GImage("wooden_chest1.jpg", i, j);
        image.setSize(50, 50);
	}

}
