import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;


public class Hero extends GraphicsProgram{

	public int Hero_x; 
	public int Hero_y;


	private static final int HEIGHT = 10;
	private static final int MAX_SPEED = 2;
	private static final double ACCELERATION = .2;

	private int speed = 0;
	private int level;
	private int HP; // health points
	private int attack;
	private int exp; //experience
	private String name;
	private BufferedImage Hero; 
	private MainApplication program; 
	GImage hero; 

	public Hero()
	{
		hero = new GImage("hero.jpg", 50, 50); 
	}

	
	public void move() {
		hero.move(speed,0);
	}


	public void moveRight()
	{
		//Hero_x += ACCELERATION;
		
		// this is for the key event for the right arrow key
		hero.move(MAX_SPEED, 0);
		
		Hero_x += ACCELERATION;
		speed = Math.min(speed, MAX_SPEED);
		
	}


	public void moveLeft()
	{
		
		// this is for the key event for the left arrow key
		hero.move(MAX_SPEED, 0);
		Hero_x -= ACCELERATION;
		speed = Math.max(speed, -MAX_SPEED);
	}

	// TO DO add?? the variables: entityType, knockback, inventory



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// this is for the key event for the right arrow key
			moveRight();
}

	}


}
