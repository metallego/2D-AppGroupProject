import acm.graphics.*;
import acm.program.*;


public class Environment
{
	private Hero hero;
	private Enemy enemy; 
	private double FRICTION = .05;
	private double GRAVITY = .1;
	private double groundY = 400;

	public Environment(Hero h)
	{
		hero = h;
	}
	
	public Environment(Enemy e)
	{
		enemy = e; 
	}

	public void update()
	{

		hero.applyFriction(FRICTION);
		hero.applyGravity(GRAVITY);

		if(hero.getY() >= groundY) {
			hero.stopJumping(groundY);
		}

	}

}
