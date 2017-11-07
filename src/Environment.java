import acm.graphics.*;
import acm.program.*;


public class Environment
{
	private Hero hero;
	private double FRICTION = .1;
	private double GRAVITY = .05;
	private double groundY = 200;

	public Environment(Hero h)
	{
		hero = h;
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
