import acm.graphics.*;

import acm.program.*;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;


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
	private boolean preserveLevel = false;
	//just a default value here for now
	private int winCoinAmount = 1;

	public Environment(MainApplication p)
	{
	    program = p;
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
	
	public void addPlatform( int i, int j, int k, int l, boolean b )
	{
	    Platform p = new Platform(i*platformWidth, j*platformHeight, k*platformWidth, l*platformHeight);
	    platforms.add( p );
	    if( b )
	        p.setWinning( b );
	    System.out.println("Added Platform");
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
	public void particle(String attack, GImage part, GLabel atkLabel) {

		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		program.add(part);
		program.add(atkLabel);
		Runnable task = () -> SwingUtilities.invokeLater(() -> program.remove(part));
		Runnable task1 = () -> SwingUtilities.invokeLater(() -> program.remove(atkLabel));
		executor.schedule(task, 200, TimeUnit.MILLISECONDS);
		executor.schedule(task1, 200, TimeUnit.MILLISECONDS);
	}

	public void checkForEntity(GRectangle bounds) {
		for (Enemy e:enemies) {
			GRectangle rect = e.image.getBounds();
			if(bounds.intersects(rect)){
				e.takeDamage(hero.getAttack());
				String atkNum = Double.toString(hero.getAttack());
				GImage particle = new GImage("hit_particle.jpg",e.image.getX()+ e.image.getWidth()/5, e.image.getY()+e.image.getHeight()/4);
				GLabel attackLabel = new GLabel(atkNum,e.image.getX()+ e.image.getWidth()/3, e.image.getY()+e.image.getHeight()/2); 
				attackLabel.setColor(Color.white);
				attackLabel.setFont((new Font("Times New Roman", Font.BOLD, 14)));
				println("enemy was attacked");
				particle(atkNum, particle, attackLabel);
				
				
			}

		}

		for (Chest c:chest) {
			GRectangle rect = c.image.getBounds();
			if(bounds.intersects(rect)){
				c.takeDamage(hero.getAttack());
				println("chest was attacked");
			}
		}

	}



	public void addEnemy( Enemy e)
	{
		enemies.add(e);
		System.out.println("Added Enemy");
	}

	public void addChest(Chest c)
	{
		chest.add(c); 
		System.out.println("Added Chest");
	}

	public void addLoot( Loot l )
	{
	    lootList.add(l);
	    System.out.println("Added Coin");
	}
	
	public void addHero( Hero h )
	{
	    hero = h;
	    System.out.println( "Added Hero" );
	}

	public boolean update(boolean b)
	{
	    completed = false;
	    if(b)
	    {
            scroll();
	    }
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
		{
		    l.pickUp( hero, program );
		   
		}
		ArrayList<Loot> temp = new ArrayList<Loot>(); 
		for(Loot l: lootList)
		{
			temp.add(l);
		}
		for(Loot l: temp)
		{
				
			if(l.isCollected)
			{
				lootList.remove(l);
			}
				
			if(hero.coins == 1)
			{
				coins.get(0).image.setImage("coin_token.jpg");
				coins.get(0).image.setSize(heartSlotWidthHeight, heartSlotWidthHeight);
			}
			else if(hero.coins == 2)
			{
				coins.get(1).image.setImage("coin_token.jpg");
				coins.get(1).image.setSize(heartSlotWidthHeight, heartSlotWidthHeight);
			}
			else if(hero.coins == 3)
			{
				coins.get(2).image.setImage("coin_token.jpg");
				coins.get(2).image.setSize(heartSlotWidthHeight, heartSlotWidthHeight);
			}
		}
			
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
		for( Chest c: chest )
		    c.image.move( -hero.getSpeed(), 0 );
		for( Loot l: lootList )
		    l.image.move( -hero.getSpeed(), 0 );
	}
	
	public void emptyLists()
	{
	    if(!preserveLevel)
	    {
    	    chest.clear();
	        enemies.clear();
	        platforms.clear();
	        lootList.clear();
	    }
	}
	
	public void setPreserve( boolean b )
	{
	    preserveLevel = b;
	}
	
	public boolean getPreserve()
	{
	    return preserveLevel;
	}
	
	public ArrayList<Loot> getLootList()
	{
	    return lootList;
	}

}
