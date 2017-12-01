import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;

public class Enemy extends Entity{
		
	public Enemy()
	{
		image = new GImage ("enemy_left1.jpg", 400, 400); 
		image.setSize(50, 75);
		this.setHP(10);
		this.setType(EntityType.ENEMY);
	}
	
	public Enemy( int i, int j )
	{
	    image = new GImage ("enemy_left1.jpg", i, j); 
        image.setSize(50, 75);
		this.setType(EntityType.ENEMY);
	}
	
	
	
	
	
	
	
//	
//	public void move()
//	{
//		image.move(speed, 0); 
//	}
//	
//	public void moveLeft()
//	{
//		if (image.getX() > (image.getX() + 10))
//		{
//			image.move(speed, 0);
//			speed -= ACCELERATION;
//			speed = Math.max(speed, -MAX_SPEED);
//		}
//		else if (image.getX() == image.getX())
//		{
//			image.move(speed, 0);
//			speed -= ACCELERATION;
//			speed = Math.max(speed, -MAX_SPEED);
//		}
//	}
//	
//	public void moveRight()
//	{
//		if (image.getX() < (image.getX() - 10))
//		{
//			image.move(speed, 0);
//			speed += ACCELERATION;
//			speed = Math.min(speed, MAX_SPEED);
//		}
//		else if (image.getX() == image.getX())
//		{
//			image.move(speed, 0);
//			speed += ACCELERATION;
//			speed = Math.min(speed, MAX_SPEED);
//		}
//	}
}
