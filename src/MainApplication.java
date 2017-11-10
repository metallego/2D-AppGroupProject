import java.awt.*;
import java.awt.event.KeyEvent;

import acm.graphics.*;
import acm.program.*;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
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
	private int count = 0;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		control = new ControlsPane(this);
		options = new OptionsPane(this);
		switchToMenu();
		hero = new Hero();
		enemy = new Enemy(); 
		chest = new Chest(); 
		environment = new Environment(hero);
		environment.addEnemy( enemy );
		environment.setupPlatforms();
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

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.moveLeft();
			enemy.moveRight();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// this is for the key event for the right arrow key
			hero.moveRight();
			enemy.moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			hero.jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			switchToScreen(menu);
		}
	}






}
