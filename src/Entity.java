import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;


public class Entity extends GraphicsProgram {

	private static final int HEIGHT = 100;
	private static final int WIDTH = 100;

	private static final int MAX_SPEED = 5; //should be 3 but set it so that we can test jump 
	private static final int MAX_VERT_SPEED = 8;
	private static final double ACCELERATION = .35;
	private double vertSpeed = 0;
	private double speed = 0;


	private int HP; // health points
	public static int attack;
	private EntityType type;
	private boolean left = true;
	private boolean right = false;


	private MainApplication program; 
	private boolean shouldMoveLeft = false;
	private boolean shouldMoveRight = false;
	private boolean jumping = false;
	private boolean death = false;
	public GImage image;


	public GImage getImage() {
		return image;
	}

	public void setImage(GImage image) {
		this.image = image;
	}

	public Entity()
	{


	}

	public void takeDamage(double damage) {
		HP -= damage;
		System.out.println("HP: ");
		System.out.println(HP);
		System.out.println("DAMAGE: ");
		System.out.println(damage);
		
		if(HP <= 0) {
			death = true;
		}
	}

	public void setType(EntityType t) {
		type = t;

	}
	public EntityType getType() {

		return type;
	}

	public void setDeath(boolean d) {
		death = d;
	}
	
	public boolean getDeath() {
		
		return death;
	}
	public int getAttack() {
		
		return attack;
	}
	public void setAttack(int atk) {
		
		attack = atk;
	}

	public int getHP() {
		
		return HP;
	}
	public void setHP(int health) {
		
		HP = health;
	}

	public int getX() {

		return (int) image.getX();
	}

	public int getY() {

		return (int) image.getY();
	}


	public void move( boolean b ) {

		applyDecisions(b);
		if( !b )
			image.move(speed,0);
		image.move( 0, -vertSpeed );

	}

	public void jump() {
		if(vertSpeed == 0) {
			vertSpeed = MAX_VERT_SPEED;
			image.move(0,-MAX_VERT_SPEED);
			jumping = true;
		}
	}

	public void stopJumping(double y) {
		vertSpeed = 0;
		image.setLocation(image.getX(),y-image.getHeight());
		jumping = false;

	}

	public void moveRight(boolean b)
	{

		// this is for the key event for the right arrow key
		if( !b )
			image.move(speed, 0);

		speed += ACCELERATION;
		speed = Math.min(speed, MAX_SPEED);

	}


	public void moveLeft(boolean b)
	{

		// this is for the key event for the left arrow key
		if( !b )
			image.move(speed, 0);
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

	public void applyDecisions(boolean b)
	{
		if( shouldMoveLeft )
			moveLeft(b);
		if( shouldMoveRight )
			moveRight(b);
	}

	public void applyGravity(double g) {
		vertSpeed -= g;	

	}

	public Point2D.Double getBottomFeet()
	{
		return new Point2D.Double( image.getX() + image.getWidth()/2,
				image.getY() + image.getHeight());
	}

	public double getSpeed()
	{
		return speed;
	}

	public double getVertSpeed()
	{
		return vertSpeed;
	}
	
	public boolean getJumping()
	{
	    return jumping;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}






}
