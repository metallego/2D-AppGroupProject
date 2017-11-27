import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	//private GImage img;
	private GButton exitButton; 
	private GButton optionsButton; 
	private InGameOptionsPane merp; 
	private GImage background; 
	private GImage heart1; 
	private GImage heart2;
	private GImage heart3; 
	
	
	public SomePane(MainApplication app) {
		this.program = app;
		exitButton = new GButton ("exit", 0, 40, 50, 50); 
		optionsButton = new GButton ("Options", 0, 550, 50, 50); 
		//img = new GImage("hero.jpg", 100, 100);
		background = new GImage("background.jpg", 0, 0);
		background.setSize(1080, 600);
		
		heart1 = new GImage ("heart.jpg", 0, 0);
		heart1.setSize(30, 30);
		
		heart2 = new GImage ("heart.jpg", 30, 0);
		heart2.setSize(30, 30);
		
		heart3 = new GImage ("heart.jpg", 60, 0);
		heart3.setSize(30, 30);
	}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(program.hero.image);
		program.add(program.enemy.enemyImage);
		program.environment.setupPlatforms();
		program.add(program.chest.chestImage);
		program.add(exitButton);
		program.add(optionsButton);
		program.add( program.coin.image );
		program.add(heart1);
		program.add(heart2);
		program.add(heart3); 
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(program.hero.image);
		program.remove(program.enemy.enemyImage);
		for( int i = 0; i < program.environment.getPlatforms().size(); i++)
		    program.remove( program.environment.getPlatforms().get(i).getGImage() );
		program.remove(program.chest.chestImage);
		program.remove(exitButton);
		program.remove(optionsButton);
		program.remove(heart1);
		program.remove(heart2);
		program.remove(heart3);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == program.hero.image || obj == exitButton) {
			program.switchToMenu();
		}
		if(obj == optionsButton)
		{
			program.switchtoIGOptions(); 
		}
	}

}
