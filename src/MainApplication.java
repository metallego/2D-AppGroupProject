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
	private ControlsPane control; 
	private OptionsPane options; 
	private InGameOptionsPane igOptions; 
	private int count = 0;
	private boolean scrollState = false;
	private int rightLeftScroll = 0;
	private Timer attackTimer;
	public static final int timerWoken = 50;
	private boolean isLeft = false;
	private boolean isRight = false;
	private boolean attackIsPressed = false;
	private int numTimesCalled = 1;


	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		control = new ControlsPane(this);
		options = new OptionsPane(this);
		igOptions = new InGameOptionsPane(this);
		switchToMenu();
		hero = new Hero();
		enemy = new Enemy(); 
		environment = new Environment(this,hero);
		chest = new Chest(); 
		environment.addEnemy( enemy );
		environment.addChest(chest);

		attackTimer = new Timer(timerWoken, this);
		attackTimer.start();


		while(true) {
			if(( hero.image.getX() > WINDOW_WIDTH - SCROLL_BUFFER) && hero.getSpeed() > 0)
				scrollState = true;
			else if(( hero.image.getX() < SCROLL_BUFFER) && hero.getSpeed() < 0 )
				scrollState = true;
			else
				scrollState = false;
			hero.move(scrollState);
			environment.update(scrollState);
			pause(30);
		}


	}

	public void actionPerformed (ActionEvent e) {

		if(attackIsPressed&&isRight)	{
			hero.image.setImage("hero_attack_right" + numTimesCalled  + ".jpg");
			pause(15);
			print(numTimesCalled);
			numTimesCalled++;
		}
		//		else if(attackIsPressed&&isLeft) {
		//			hero.image.setImage("hero_attack_right" + numTimesCalled  + ".jpg");
		//			pause(15);
		//			print(numTimesCalled);
		//			numTimesCalled++;
		//		}

		if(numTimesCalled > 5) {
			attackIsPressed = false; 
			numTimesCalled = 1;
		}

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

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.startMoveLeft();
			hero.image.setImage("hero_run_left1.jpg");
			enemy.moveRight();
			print("Move left\n");
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// this is for the key event for the right arrow key
			hero.startMoveRight();
			hero.image.setImage("hero_run_right1.jpg");
			enemy.moveLeft();
			print("Move right\n");
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			hero.jump();
			print("jump\n"); 
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
	}






}
