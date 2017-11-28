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
		program.add(program.hero.image);
		program.add(program.enemy.image);
		//program.environment.setupPlatforms();
		for( int i = 0; i < program.environment.getPlatforms().size(); i++)
            program.add( program.environment.getPlatforms().get(i).getGImage() );
		program.add(program.chest.image);
		program.environment.setUpHeartSlots();
		program.environment.setUpCoinSlots();
		program.add(program.chest.image);
		program.add(exitButton);
		program.add(optionsButton);
		program.add( program.coin.image );
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(program.hero.image);
		program.remove(program.enemy.image);
		program.remove(program.chest.image);
		program.remove(exitButton);
		program.remove(optionsButton);
		
		for( int i = 0; i < program.environment.getPlatforms().size(); i++)
		    program.remove( program.environment.getPlatforms().get(i).getGImage() );
		
		for (int i = 0; i < program.environment.getHeartSlotImage().size(); i++)
			program.remove(program.environment.getHeartSlots().get(i).getGImage());
		
		for (int i = 0; i < program.environment.getCoinSlotImage().size(); i++)
			program.remove(program.environment.getCoinSlots().get(i).getGImage())
			;
		
		program.environment.emptyLists();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == program.hero.image || obj == exitButton) {
		    program.environment.setPreserve( false );
		    program.loadedLevel = false;
			program.switchToMenu();
		}
		if(obj == optionsButton)
		{
		    program.environment.setPreserve( true );
			program.switchtoIGOptions(); 
		}
	}

}
