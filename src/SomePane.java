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
	
	public SomePane(MainApplication app) {
		this.program = app;
		exitButton = new GButton ("exit", 0, 0, 50, 50); 
		optionsButton = new GButton ("Options", 0, 550, 50, 50); 
		//img = new GImage("hero.jpg", 100, 100);
		background = new GImage("background.jpg", 0, 0);
		background.setSize(1080, 600);
	}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(program.hero.heroImg);
		program.add(program.enemy.enemyImage);
		program.environment.setupPlatforms();
		program.add(program.chest.chestImage);
		program.add(exitButton);
		program.add(optionsButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(program.hero.heroImg);
		program.remove(program.enemy.enemyImage);
		for( int i = 0; i < program.environment.getPlatforms().size(); i++)
		    program.remove( program.environment.getPlatforms().get(i).getGRect() );
		program.remove(program.chest.chestImage);
		program.remove(exitButton);
		program.remove(optionsButton);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == program.hero.heroImg || obj == exitButton) {
			program.switchToMenu();
		}
		if(obj == optionsButton)
		{
			program.switchtoIGOptions(); 
		}
	}

}
