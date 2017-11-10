import acm.graphics.*;
import acm.program.*;

import java.util.ArrayList;


public class Environment
{
	private Hero hero;
	private Enemy enemy; 
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private double platformWidth = 100;
	private double platformHeight = 50;
	private double FRICTION = .05;
	private double GRAVITY = .1;
	private double groundY = 400;

	public Environment(Hero h)
	{
		hero = h;
	}
	
	
	public void setupPlatforms() { 
		for(int i = 0; i < 10; i++) {
			Platform p = new Platform(i*platformWidth, i*platformHeight, platformWidth, platformHeight);
			platforms.add(p);
		}
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
