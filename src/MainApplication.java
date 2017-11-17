import java.awt.*;
import java.awt.event.KeyEvent;

import acm.graphics.*;
import acm.program.*;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 600;

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
		while(true) {
			hero.move();
			environment.update();
			pause(30);
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
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// this is for the key event for the right arrow key
			hero.startMoveRight();
			hero.image.setImage("hero_run_right1.jpg");
			enemy.moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			hero.jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			switchToScreen(menu);
		}
		if (e.getKeyCode() == KeyEvent.VK_Z)
		{
			hero.image.setImage("hero_attack_right1.jpg");
		}
	}
	
	@Override
	public void keyReleased( KeyEvent e )
	{
	    if(e.getKeyCode() == KeyEvent.VK_LEFT) {
	    	hero.image.setImage("hero_idle_left.jpg");
            hero.stopMoveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	hero.image.setImage("hero_idle_right.jpg");
            // this is for the key event for the right arrow key
            hero.stopMoveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_Z) { 	
//        		hero.attack();
        	hero.image.setImage("hero_idle_right.jpg");
        }
	}






}
