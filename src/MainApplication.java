import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Timer;

import acm.graphics.*;
import acm.program.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainApplication extends GraphicsApplication  implements ActionListener{
	private static final int ENEMY_HEIGHT = 75;
	private static final int ENEMY_WIDTH = 50;
	private static final int CHEST_WIDTH = 50;
	private static final int CHEST_HEIGHT = 50;
	public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 600;
	public static final int SCROLL_BUFFER = 200;

	private SomePane somePane;
	private MenuPane menu;
	public Hero hero; 
	public Enemy enemy; 
	public Platform platform;
	public Chest chest; 
	public Environment environment;
	public Loot coin;
	private ControlsPane control; 
	public OptionsPane options;
	private LevelCompletePane winScreen;
	private GameOverPane gameOver;

	private LevelSelectPane levelSelect; 
	private int count = 0;
	private boolean scrollState = false;
	public boolean completed = false;
	public boolean loadedLevel = false;
	private Timer myTimer;
	public static final int timerWoken = 50;
	private boolean isLeft = false;
	private boolean isRight = false;
	private boolean attackIsPressed = false;
	private boolean jumpIsPressed = false;
	private boolean gameStarted = false;
	public boolean dead = false;
	private int numTimesCalled = 1;
	private int deathCalled = 1;
	private int testcount = 1; 
	private int runFrames = 1;
	private int jumpFrames = 1; 
	private int invulnTimer = 0;
	public int currentLevel = 1;
	public static int hitcount = 0; 
	static boolean isAttacking;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		control = new ControlsPane(this);
		options = new OptionsPane(this);
		winScreen = new LevelCompletePane(this);
		levelSelect = new LevelSelectPane(this); 
		gameOver = new GameOverPane(this);
		switchToMenu();
		//everything below here is going to need to be refactored when Text File Level Loading is implemented
		//hero = new Hero();
		//enemy = new Enemy(); 
		//chest = new Chest(); 
		//coin = new Loot();
		environment = new Environment(this);
		//environment.addEnemy( enemy );
		//environment.addChest( chest );
		//environment.addLoot( coin );
		myTimer = new Timer(timerWoken, this);
		myTimer.start();

		while( !gameStarted )
		{
			pause(30);
		}

		while ( true )
		{
			pause(30);
			System.out.println( "Going into gameplay Loop" );
			while (!completed && !dead)
			{
				if ( ( hero.image.getX() > WINDOW_WIDTH - SCROLL_BUFFER ) && hero.getSpeed() > 0 ) scrollState = true;
				else if ( ( hero.image.getX() < SCROLL_BUFFER ) && hero.getSpeed() < 0 ) scrollState = true;
				else scrollState = false;
				hero.move( scrollState );
				completed = environment.update( scrollState );
				pause( 30 );
			}
			somePane.hideContents();
			winScreen.setScore( hero.getCoins() );
			environment.emptyLists();
			System.out.println(dead);
			if(dead) {
				switchToGameOver();
			}
			else {
				switchtoLevelComplete();
			}
		}
	}



	public void actionPerformed (ActionEvent e) {
		//invincibility timer for hero after getting hit
		invulnTimer += 50;
		if( invulnTimer >= 2000 )
			hero.setInvincible( false );

		//DEATH ANIMATION
		if(environment.checkForDeath() != null) {

			Entity tempEntity = environment.checkForDeath();
			if (tempEntity.getType() == EntityType.HERO) {
				{
					if(tempEntity.isLeft()) {
						tempEntity.image.setImage("hero_death_left" + deathCalled + ".jpg");
						hero.imageName = "hero_death_left" + deathCalled + ".jpg";
						tempEntity.image.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
						deathCalled++;
						if(deathCalled == 9) {
							tempEntity.image.setImage("tombstone.jpg");
							tempEntity.image.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
							somePane.showContents();
							deathCalled = 1;
						}
					}
					else {
						tempEntity.image.setImage("hero_death_right" + deathCalled + ".jpg");
						hero.imageName = "hero_death_right" + deathCalled + ".jpg";
						tempEntity.image.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
						deathCalled++;
						if(deathCalled == 9) {
							tempEntity.image.setImage("tombstone.jpg");
							tempEntity.image.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
							somePane.showContents();
							deathCalled = 1;

						}
					}
				}

			}

			if (tempEntity.getType() == EntityType.ENEMY) {
				if(tempEntity.isLeft()) {
					tempEntity.image.setImage("enemy_left_death" + deathCalled + ".jpg");
					tempEntity.image.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
					deathCalled++;
					if(deathCalled == 4) {
						deathCalled = 1;
						tempEntity.setDeath(false);
						tempEntity.setImage(null);
						Environment.removeEntity(tempEntity);
						Hero.addExp(5);
						somePane.showContents();

						if(Hero.isHeroIsLeveling()) {
							println("LEVELUPP");
							//							environment.levelAnimation();
							//							somePane.showContents();

						}
					}
				}
				else {
					tempEntity.image.setImage("enemy_right_death" + deathCalled + ".jpg");
					tempEntity.image.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
					deathCalled++;
					if(deathCalled == 4) {
						deathCalled = 1;
						tempEntity.setDeath(true);
						Environment.removeEntity(tempEntity);
						Hero.addExp(5);
						somePane.showContents();
						if(Hero.isHeroIsLeveling()) {
							println("LEVELUPP");

							//							environment.levelAnimation();
						}
					}
				}

			}



			else if (tempEntity.getType() == EntityType.CHEST) {
				tempEntity.image.setImage("wooden_chest" + deathCalled + ".jpg");
				tempEntity.image.setSize(CHEST_WIDTH, CHEST_HEIGHT);
				deathCalled++;
				if(deathCalled == 4) {
					deathCalled = 1;
					tempEntity.setDeath(false);
					//tempEntity = null;
					Environment.removeEntity(tempEntity);
					somePane.showContents();
				}
			}



		}


		//ATTACKING ANIMATION


		if(attackIsPressed&&isRight)	{
			hero.image.setImage("hero_attack_right" + numTimesCalled  + ".jpg");
			hero.imageName = "hero_attack_right" + numTimesCalled + ".jpg";
			//chest.image.setImage("wooden_chest" + testcount + ".jpg");


			//set weaponratio
			if(numTimesCalled == 4) {
				//this would be the weaponhitbox for animation 4
				GLine weapon1 = new GLine( hero.getX()+77, hero.getY()+67, hero.getX()+104 ,hero.getY()+22);
				GRectangle hitBox = weapon1.getBounds();
				environment.checkForEntity(hitBox);
				//TESTING
				//				GRect testbox = new GRect (hitBox.getX(), hitBox.getY(), hitBox.getWidth(),hitBox.getHeight());
				//				add(testbox);
			}
			//this would be the weaponhitbox for animation 5
			else if(numTimesCalled == 5) {
				GLine weapon2 = new GLine( hero.getX()+76, hero.getY()+66, hero.getX()+122,hero.getY()+61);
				GRectangle hitBox = weapon2.getBounds();
				environment.checkForEntity(hitBox);
				//TESTING
				//				GRect testbox = new GRect (hitBox.getX(), hitBox.getY(), hitBox.getWidth(),hitBox.getHeight());
				//				add(testbox);
			}

			else{
			}


			pause(15);

			numTimesCalled++;
			testcount++;
		}
		else if(attackIsPressed&&!isRight) {
			if( hero.imageName == "hero_idle_left.jpg" )
				hero.image.move( -45, 0 );
			hero.image.setImage("hero_attack_left" + numTimesCalled  + ".jpg");
			hero.imageName = "hero_attack_left" + numTimesCalled + ".jpg";
			pause(15);
			numTimesCalled++;
		}

		if(numTimesCalled > 5) {
			attackIsPressed = false; 
			numTimesCalled = 1;
		}
		if(numTimesCalled == 4) {
			//this would be the weaponhitbox for animation 4
			GLine weapon1 = new GLine( hero.getX()+9, hero.getY()+21, hero.getX()+37 ,hero.getY()+55);
			GRectangle hitBox = weapon1.getBounds();
			environment.checkForEntity(hitBox);
			//TESTING
			//			GRect testbox = new GRect (hitBox.getX(), hitBox.getY(), hitBox.getWidth(),hitBox.getHeight());
			//			add(testbox);
		}
		//this would be the weaponhitbox for animation 5
		else if(numTimesCalled == 5) {
			GLine weapon2 = new GLine( hero.getX()+6, hero.getY()+60, hero.getX()+47,hero.getY()+62);
			GRectangle hitBox = weapon2.getBounds();
			environment.checkForEntity(hitBox);
			//TESTING
			//			GRect testbox = new GRect (hitBox.getX(), hitBox.getY(), hitBox.getWidth(),hitBox.getHeight());
			//			add(testbox);
		}




		//		if (testcount > 4)
		//		{
		//			testcount = 1; 
		//		}

		/*
		if (jumpIsPressed && isRight)
		{
			hero.image.setImage("hero_jump_left" + jumpFrames + ".jpg");
			pause(15);
			jumpFrames++; 
		}
		else if (jumpIsPressed && isLeft)
		{
			hero.image.setImage("hero_jump_right" + jumpFrames + ".jpg");
			pause(15);
			jumpFrames++;
		}

		if (jumpFrames > 10)
		{
			jumpFrames = 1; 
		}
		 */
	}



	public void switchToMenu() {

		AudioPlayer audio = AudioPlayer.getInstance();
		if(!options.isOff)
		{
			audio.playSound("sounds", "Intro Song.mp3");
		}
		audio.stopSound("sounds", "Level.mp3");
		count++;
		if( hero!= null )
			hero.resetCoins();
		switchToScreen(menu);
	}

	public void switchToSome() {

		AudioPlayer audio = AudioPlayer.getInstance();
		if(!options.isOff)
		{
			audio.playSound("sounds", "Level.mp3");
		}
		audio.stopSound("sounds", "Intro Song.mp3");

		//load level data into environment here
		if(!loadedLevel )
			loadLevel();
		gameStarted = true;
		System.out.println( "Loaded Level" );
		pause(500);
		switchToScreen(somePane);
	}

	public void switchtoGame()
	{
		//switchToScreen(hero);
	}

	public void switchtoLevelSelect()
	{
		switchToScreen(levelSelect); 
	}

	public void switchtoControls()
	{
		switchToScreen(control); 
	}

	public void switchtoOptions()
	{
		switchToScreen(options);
	}

	public void switchToGameOver()
	{
		somePane.hideContents();
		loadedLevel = false;
		completed = false;
		if(dead)
		{
			dead = false;
			hero.hp = 3;
			hero.setDeath( false );
		}
		switchToScreen(gameOver);
	}


	public void switchtoLevelComplete()
	{
		somePane.hideContents();
		loadedLevel = false;
		completed = false;
		if(dead)
		{
			dead = false;
			hero.hp = 3;
			hero.setDeath( false );
		}
		switchToScreen(winScreen);
	}

	public void resetInvulnTimer()
	{
		invulnTimer = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.startMoveLeft();
			hero.image.setImage("hero_run_left" + runFrames + ".jpg");
			hero.imageName = "her_run_left" + runFrames + ".jpg";
			//pause(15); 
			print(runFrames);
			print("Move left\n");
			runFrames++; 

			if (runFrames > 10)
			{
				runFrames = 1; 
			}

			isRight = false;
			isLeft = true; 
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// this is for the key event for the right arrow key
			hero.startMoveRight();
			hero.image.setImage("hero_run_right" + runFrames + ".jpg");
			hero.imageName = "her_run_right" + runFrames + ".jpg";
			print(runFrames);
			print("Move right\n");
			runFrames++; 

			if (runFrames > 10)
			{
				runFrames = 1; 
			}

			isRight = true; 
			isLeft = false; 
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && isLeft) {
			hero.jump();
			hero.image.setImage("hero_jump_left" + jumpFrames + ".jpg");
			hero.imageName = "her_jump_left" + runFrames + ".jpg";
			print(jumpFrames);
			print("jump left\n"); 
			jumpFrames++;

			if (jumpFrames > 10)
			{
				jumpFrames = 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && isRight)
		{
			hero.jump();
			hero.image.setImage("hero_jump_right" + jumpFrames + ".jpg");
			hero.imageName = "her_jump_right" + runFrames + ".jpg";
			print(jumpFrames);
			print("jump right\n"); 
			jumpFrames++;

			if (jumpFrames > 10)
			{
				jumpFrames = 1; 
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_Z) { 	
			myTimer.start();
			print("attack\n");
			//    		hero.attack();

		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			switchToScreen(menu);
		}
	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.image.setImage("hero_idle_left.jpg");
			hero.imageName = "hero_idle_left.jpg";
			hero.stopMoveLeft();
			//isRight = false;
			//isLeft = true; 
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hero.image.setImage("hero_idle_right.jpg");
			hero.imageName = "hero_idle_right.jpg";
			// this is for the key event for the right arrow key
			hero.stopMoveRight();
			//isRight = true; 
			//isLeft = false; 
		}

		if(e.getKeyCode() == KeyEvent.VK_Z) {
			attackIsPressed = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jumpIsPressed = true; 
		}
	}

	public void loadLevel()
	{
		String fileName = "level" + currentLevel + ".txt";
		System.out.println( fileName );
		BufferedReader file = null;
		int z = 0;
		try
		{
			file = new BufferedReader(new FileReader(fileName));
		}
		catch ( FileNotFoundException e1 )
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String currLine = null;
		String[] objAttributes;
		try
		{
			while (( currLine = file.readLine()) != null )
			{
				objAttributes = currLine.split( ",[ ]*" );
				if( objAttributes[0].equals( "platform" ) )
				{
					environment.addPlatform( Integer.parseInt( objAttributes[1] ),
							Integer.parseInt( objAttributes[2] ),
							Integer.parseInt( objAttributes[3] ),
							Integer.parseInt( objAttributes[4] ),
							Boolean.parseBoolean( objAttributes[5] ));
				}
				else if( objAttributes[0].equals( "hero" ))
				{
					hero = new Hero(Integer.parseInt( objAttributes[1] ),
							Integer.parseInt( objAttributes[2] ));
					environment.addHero( hero );
				}
				else if( objAttributes[0].equals( "coin" ))
				{
					coin = new Loot(Integer.parseInt( objAttributes[1] ),
							Integer.parseInt( objAttributes[2] ));
					environment.addLoot( coin );
				}
				else if( objAttributes[0].equals( "chest" ))
				{
					chest = new Chest(Integer.parseInt( objAttributes[1] ),
							Integer.parseInt( objAttributes[2] ));
					environment.addChest( chest );
				}
				else if( objAttributes[0].equals( "enemy" ))
				{
					enemy = new Enemy(Integer.parseInt( objAttributes[1] ),
							Integer.parseInt( objAttributes[2] ));
					environment.addEnemy( enemy );
				}
				else if( objAttributes[0].equals( "other" ))
				{
					environment.setWinCoinAmount( Integer.parseInt( objAttributes[1] ));
				}
				z++;
				System.out.println( "Line# " + z );
				for( int y = 0; y < objAttributes.length; y++ )
				{
					System.out.println( objAttributes[y] );
				}
			}
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadedLevel = true;
	}




}
