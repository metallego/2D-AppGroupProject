import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;


public class Hero extends GraphicsProgram{

	private static final int HEIGHT = 10;
	private static final int MAX_SPEED = 5; //should be 3 but set it so that we can test jump 
	private static final int MAX_VERT_SPEED = 8;
	private static final double ACCELERATION = .7;
	private double vertSpeed = 0;
	private double speed = 0;
	
	private int level;
	private int HP; // health points
	private int attack;
	private int exp; //experience
	private String name;
	private MainApplication program; 
	private boolean shouldMoveLeft = false;
	private boolean shouldMoveRight = false;
	public GImage heroImg;

	public Hero()
	{
		heroImg = new GImage("hero.jpg", 50, 400); 
		heroImg.setSize(100,100);
	}
	
	public int getY() {
		
		return (int) heroImg.getY();
	}
	
	public double getSpeed()
	{
	    return speed;
	}


	public void move() {

		heroImg.move(speed,-vertSpeed);

	}

	public void jump() {
		if(vertSpeed == 0) {
			vertSpeed = MAX_VERT_SPEED;
			heroImg.move(0,-MAX_VERT_SPEED);
		}
	}
	
	public void stopJumping(double y) {
		vertSpeed = 0;
		heroImg.setLocation(heroImg.getX(),y-heroImg.getHeight());
		
	}

	public void moveRight()
	{

		// this is for the key event for the right arrow key
		heroImg.move(speed, 0);

		speed += ACCELERATION;
		speed = Math.min(speed, MAX_SPEED);

	}


	public void moveLeft()
	{

		// this is for the key event for the left arrow key
		heroImg.move(speed, 0);
		speed -= ACCELERATION;
		speed = Math.max(speed, -MAX_SPEED);
	}


	// TO DO add?? the variables: entityType, knockback, inventory

	public void applyFriction(double f) {
		if(speed > 0) {
			speed-= f;
			speed = Math.max(speed, 0);
		}
		else if(speed < 0) {
			speed +=f;
			speed = Math.min(speed, 0);
		}


	}
	
	public void startMoveLeft()
    {
        shouldMoveLeft = true;
    }
    
    public void startMoveRight()
    {
        shouldMoveRight = true;
    }
    
    public void stopMoveLeft()
    {
        shouldMoveLeft = false;
    }
    
    public void stopMoveRight()
    {
        shouldMoveRight = false;
    }
    
    public void applyDecisions()
    {
        if( shouldMoveLeft )
            moveLeft();
        if( shouldMoveRight )
            moveRight();
    }

	public void applyGravity(double g) {
			vertSpeed -= g;	

	}
	
	public Point2D.Double getBottomFeet()
	{
	    return new Point2D.Double( heroImg.getX() + heroImg.getWidth()/2,
	                               heroImg.getY() + heroImg.getHeight());
	}


}