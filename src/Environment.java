import acm.graphics.*;

import acm.program.*;

import java.util.ArrayList;


public class Environment extends GraphicsProgram
{
    private MainApplication program;
	private Hero hero;
	private Enemy enemy; 
	private ArrayList<Chest> chest = new ArrayList<Chest>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private double platformWidth = 100;
	private double platformHeight = 50;
	private double FRICTION = .1;
	private double GRAVITY = .3;
	private double groundY = 400;

	public Environment(MainApplication p, Hero h)
	{
	    program = p;
		hero = h;
	}
	
	
	public void setupPlatforms() { 
		for(int i = 0; i < 10; i++) {
			Platform p = new Platform(i*platformWidth, i*platformHeight, platformWidth, platformHeight);
			platforms.add(p);
			platforms.get(i).drawPlatform( program, platforms.get( i ) );
		}
	}

	public void addEnemy( Enemy e)
	{
		enemies.add( e );
	}
	
	public void addChest(Chest c)
	{
		chest.add(c); 
	}

	public void update(boolean b)
	{
	    if( b )
            scroll();
		hero.applyFriction(FRICTION);
		hero.applyGravity(GRAVITY);
		hero.applyDecisions(b);

		for( Platform p: platforms )
            if (p.isUnderneath(hero.getBottomFeet()))
                hero.stopJumping( p.getY() );
		if(hero.getY() >= groundY) {
			hero.stopJumping(groundY+hero.image.getHeight());
		}

	}
	
	public ArrayList<Platform> getPlatforms()
	{
	    return platforms;
	}
	
	public ArrayList<GRect> getPlatformRects()
	{
	    ArrayList<GRect> boxes = new ArrayList<GRect>();
	    for( Platform p: platforms )
	        boxes.add( p.getGRect() );
	    return boxes;
	}
	
	public void scroll()
	{
	    for( Platform p: platforms )
	        p.getGRect().move( -hero.getSpeed(), 0 );
	    for( Enemy e: enemies)
	        e.enemyImage.move( -hero.getSpeed(), 0 );
	}

}
