import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;

public class Enemy extends Entity{
	/*
	 * need move, height, accel
	 * speed, level, hp, attacks, exp, 
	 */
	private static final int HEIGHT = 10;
	private static final int MAX_SPEED = 10;
	private static final double ACCELERATION = .7;
	
	private double speed = 0;
	private int level;
	private int HP; // health points
	private int attack;
	private int exp; //experience
	
	public GImage enemyImage; 
	
	public Enemy()
	{
		enemyImage = new GImage ("bread.jpg", 400, 400); 
		enemyImage.setSize(100, 100);
	}
	
	public void move()
	{
		enemyImage.move(speed, 0); 
	}
	
	public void moveLeft()
	{
		if (enemyImage.getX() > (enemyImage.getX() + 10))
		{
			enemyImage.move(speed, 0);
			speed -= ACCELERATION;
			speed = Math.max(speed, -MAX_SPEED);
		}
		else if (enemyImage.getX() == enemyImage.getX())
		{
			enemyImage.move(speed, 0);
			speed -= ACCELERATION;
			speed = Math.max(speed, -MAX_SPEED);
		}
	}
	
	public void moveRight()
	{
		if (enemyImage.getX() < (enemyImage.getX() - 10))
		{
			enemyImage.move(speed, 0);
			speed += ACCELERATION;
			speed = Math.min(speed, MAX_SPEED);
		}
		else if (enemyImage.getX() == enemyImage.getX())
		{
			enemyImage.move(speed, 0);
			speed += ACCELERATION;
			speed = Math.min(speed, MAX_SPEED);
		}
	}
	

}
