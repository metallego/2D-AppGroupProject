import acm.graphics.*;
import acm.program.*;
import java.util.ArrayList;


public class Environment
{
	private Hero hero;
	private Enemy enemy; 
	private ArrayList<Enemy> enemies;
	private double FRICTION = .05;
	private double GRAVITY = .1;
	private double groundY = 400;

	public Environment(Hero h)
	{
		hero = h;
	}
	
	public void addEnemy( Enemy e)
	{
	    enemies.add( e );
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
