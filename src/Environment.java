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
	private ArrayList<Loot> lootList = new ArrayList<Loot>();
	private ArrayList <HeartSlots> hearts = new ArrayList <HeartSlots>(); 
	private ArrayList <CoinSlots> coins = new ArrayList <CoinSlots>(); 
	private double platformWidth = 100;
	private double platformHeight = 50;
	private double heartSlotX = 990; 
	private double heartSlotY = 0; 
	private double heartSlotWidthHeight = 30; 
	private double coinSlotY = 40; 
	private double FRICTION = .1;
	private double GRAVITY = .3;
	private double groundY = 400;
	private boolean completed = false;
	//just a default value here for now
	private int winCoinAmount = 1;

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
			//debug code for testing if level completion works
			if( i == 7 )
			    p.setWinning( true );
		}
	}
	
	public void setUpHeartSlots()
	{
		for (int i = 0; i < 3; i++)
		{
			HeartSlots s = new HeartSlots(((i * 30) + heartSlotX), heartSlotY, heartSlotWidthHeight, heartSlotWidthHeight); 
			hearts.add(s); 
			hearts.get(i).drawHearts(program, hearts.get(i));
			
		}
	}
	
	public void setUpCoinSlots()
	{
		for (int i = 0; i < 3; i++)
		{
			CoinSlots c = new CoinSlots((i * 30) + heartSlotX, coinSlotY, heartSlotWidthHeight, heartSlotWidthHeight); 
			coins.add(c);
			coins.get(i).drawCoins(program, coins.get(i));
		}
	}

	public void checkForEntity(GRectangle bounds, double attack) {
		for (Enemy e:enemies) {
			if(bounds.contains(e.getX()+e.getWidth()/2,e.getY()+e.getHeight()/2)) {
				e.takeDamage(attack);
				println("attacked!!!! ENEMY");
			}
		
		}
		
		for (Chest c:chest) {
			bounds.contains(c.getX()+c.getWidth()/2,c.getY()+c.getHeight()/2);
			c.takeDamage(attack);
			println("attacked!!!! CHEST");

		}
		
	}
	
	
	
	public void addEnemy( Enemy e)
	{
		enemies.add(e);
	}
	
	public void addChest(Chest c)
	{
		chest.add(c); 
	}
	
	public void addLoot( Loot l )
	{
	    lootList.add(l);
	}

	public boolean update(boolean b)
	{
	    completed = false;
	    if(b)
            scroll();
		hero.applyFriction(FRICTION);
		hero.applyGravity(GRAVITY);
		hero.applyDecisions(b);

		for( Platform p: platforms )
            if ((p.isUnderneath(hero.getBottomFeet()))&& hero.getVertSpeed() < 0)
            {
                hero.stopJumping( p.getY() );
                if( p.checkWin( hero, winCoinAmount ))
                    completed = true;
            }
		for( Loot l: lootList )
		    l.pickUp( hero, program );
		if(hero.getY() >= groundY) {
			hero.stopJumping(groundY+hero.image.getHeight());
		}
        if( completed )
            return true;
        return false;
        
  
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
	
	public ArrayList<GImage> getPlatformImage()
	{
		ArrayList<GImage> boxes = new ArrayList<GImage>(); 
		for (Platform p: platforms)
		{
			boxes.add(p.getGImage()); 
		}
		return boxes; 
	}
	
	public ArrayList<HeartSlots> getHeartSlots()
	{
		return hearts; 
	}

	public ArrayList<GImage> getHeartSlotImage()
	{
		ArrayList <GImage> heartSlots = new ArrayList <GImage>();
		for (HeartSlots s: hearts)
		{
			heartSlots.add(s.getGImage());
		}
		return heartSlots; 
	}
	
	public ArrayList<GImage> getCoinSlotImage()
	{
		ArrayList <GImage> coinSlots = new ArrayList <GImage>(); 
		for (CoinSlots c: coins)
		{
			coinSlots.add(c.getGImage());
		}
		return coinSlots; 
	}
	
	public ArrayList<CoinSlots> getCoinSlots()
	{
		return coins; 
	}
	
	
	
	public void scroll()
	{
	    for( Platform p: platforms )
	        p.getGImage().move( -hero.getSpeed(), 0 );
	    for( Enemy e: enemies)
	        e.image.move( -hero.getSpeed(), 0 );
	}

}
