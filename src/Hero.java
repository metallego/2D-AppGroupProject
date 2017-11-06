import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;


public class Hero {
	
	public int Hero_x; 
	public int Hero_y;
	
	
	private static final int HEIGHT = 10;
	private static final int MAX_SPEED = 2;
	private static final double ACCELERATION = .2;
	
	private int level;
	private int HP; // health points
	private int attack;
	private int exp; //experience
	private String name;
	private BufferedImage Hero; 
	
	public Hero()
	{
		try
		{
			Hero = ImageIO.read(getClass().getResourceAsStream("hero.jpg"));
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void moveRight()
	{
		Hero_x += ACCELERATION; 
	}
	
	public void moveLeft()
	{
		Hero_x -= ACCELERATION;
	}
	
    // TO DO add?? the variables: entityType, knockback, inventory
	
	
	
	
	
	
	
	
	
}
