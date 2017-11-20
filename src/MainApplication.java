import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import acm.graphics.*;
import acm.program.*;

public class MainApplication extends GraphicsApplication  implements ActionListener{
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
	private OptionsPane options; 
	private InGameOptionsPane igOptions; 
	private LevelCompletePane winScreen;
	private int count = 0;
	private boolean scrollState = false;
	private boolean completed = false;
	private Timer attackTimer;
	public static final int timerWoken = 50;
	private boolean isLeft = false;
	private boolean isRight = false;
	private boolean attackIsPressed = false;
	private boolean jumpIsPressed = false;
	private int numTimesCalled = 1;
	private int runFrames = 1;
	private int jumpFrames = 1; 


	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		control = new ControlsPane(this);
		options = new OptionsPane(this);
		igOptions = new InGameOptionsPane(this);
		winScreen = new LevelCompletePane(this);
		switchToMenu();
		hero = new Hero();
		//everything below here is going to need to be refactored when Text File Level Loading is implemented
		enemy = new Enemy(); 
		environment = new Environment(this,hero);
		chest = new Chest(); 
		coin = new Loot();
		environment.addEnemy( enemy );
		environment.addChest( chest );
		environment.addLoot( coin );
		attackTimer = new Timer(timerWoken, this);
		attackTimer.start();
		
        while ( true )
        {
            pause(30);
            while ( !completed )
            {
                if ( ( hero.image.getX() > WINDOW_WIDTH - SCROLL_BUFFER ) && hero.getSpeed() > 0 ) scrollState = true;
                else if ( ( hero.image.getX() < SCROLL_BUFFER ) && hero.getSpeed() < 0 ) scrollState = true;
                else scrollState = false;
                hero.move( scrollState );
                completed = environment.update( scrollState );
                pause( 30 );
            }
            winScreen.setScore( hero.getCoins() );
            switchtoLevelComplete();
        }

	}

	public void actionPerformed (ActionEvent e) {

		if(attackIsPressed&&isRight)	{
			hero.image.setImage("hero_attack_right" + numTimesCalled  + ".jpg");
			pause(15);
			print(numTimesCalled);
			numTimesCalled++;
		}
		else if(attackIsPressed&&isLeft) {
			hero.image.setImage("hero_attack_left" + numTimesCalled  + ".jpg");
			pause(15);
			print(numTimesCalled);
			numTimesCalled++;
		}

		if(numTimesCalled > 5) {
			attackIsPressed = false; 
			numTimesCalled = 1;
		}

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
		switch(count % 2) {
		//case 0: audio.stopSound("sounds", "r2d2.mp3"); break;
		//case 1: audio.stopSound("sounds", "somethinlikethis.mp3"); break;
		}
		count++;

		switchToScreen(menu);
	}

	public void switchToSome() {

		AudioPlayer audio = AudioPlayer.getInstance();
		switch(count % 2) {
		//case 0: audio.playSound("sounds", "r2d2.mp3"); break;
		//case 1: audio.playSound("sounds", "somethinlikethis.mp3"); break;
		}

		switchToScreen(somePane);
	}

	public void switchtoGame()
	{
		//switchToScreen(hero);
	}

	public void switchtoControls()
	{
		switchToScreen(control); 
	}

	public void switchtoOptions()
	{
		switchToScreen(options);
	}

	public void switchtoIGOptions()
	{
		switchToScreen(igOptions); 
	}
	
	public void switchtoLevelComplete()
	{
	    completed = false;
	    hero.resetCoins();
	    switchToScreen(winScreen);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.startMoveLeft();
			hero.image.setImage("hero_run_left" + runFrames + ".jpg");
			//pause(15); 
			enemy.moveRight();
			print(runFrames);
			print("Move left\n");
			runFrames++; 
			
			if (runFrames > 10)
			{
				runFrames = 1; 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// this is for the key event for the right arrow key
			hero.startMoveRight();
			hero.image.setImage("hero_run_right" + runFrames + ".jpg");
			enemy.moveLeft();
			print(runFrames);
			print("Move right\n");
			runFrames++; 
			
			if (runFrames > 10)
			{
				runFrames = 1; 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && isLeft) {
			hero.jump();
			hero.image.setImage("hero_jump_left" + jumpFrames + ".jpg");
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
			print(jumpFrames);
			print("jump right\n"); 
			jumpFrames++;
			
			if (jumpFrames > 10)
			{
				jumpFrames = 1; 
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_Z) { 	
			attackTimer.start();
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
			hero.stopMoveLeft();
			isRight = false;
			isLeft = true; 
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hero.image.setImage("hero_idle_right.jpg");
			// this is for the key event for the right arrow key
			hero.stopMoveRight();
			isRight = true; 
			isLeft = false; 
		}

		if(e.getKeyCode() == KeyEvent.VK_Z) {
			//hero.attack();
			attackIsPressed = true;

			if (isRight)
			{
				//hero.image.setImage("hero_attack_right1.jpg");

			}
			else if (isLeft)
			{
				//	hero.image.setImage("hero_attack_left1.jpg");
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jumpIsPressed = true; 
		}
	}






}
