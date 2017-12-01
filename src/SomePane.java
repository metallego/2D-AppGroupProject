import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GButton exitButton; 
	private GButton optionsButton; 
	private InGameOptionsPane merp; 
	private GImage background; 


	public SomePane(MainApplication app) {
		this.program = app;
		exitButton = new GButton ("exit", 0, 0, 50, 50); 
		optionsButton = new GButton ("Options", 0, 550, 50, 50); 
		background = new GImage("background.jpg", 0, 0);
		background.setSize(1080, 600);
	}



	@Override
	public void showContents() {
		program.add(background);
		program.add(program.hero.image);

		for(Platform e:program.environment.getPlatforms()) {
			GImage temp = e.getGImage();
			program.add(temp);
		}


		for(Loot e:program.environment.getLootList()) {
			GImage temp = e.image;
			program.add(temp);
		}

		for(Entity e:program.environment.getEnemyList()) {
			GImage temp = e.getImage();
			program.add(temp);
		}

		program.add(program.chest.image);
		program.environment.setUpHeartSlots();
		program.environment.setUpCoinSlots();
		program.add(program.chest.image);
		program.add(exitButton);
		program.add(optionsButton);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(program.hero.image);
		program.remove(program.chest.image);
		program.remove(exitButton);
		program.remove(optionsButton);

		for(HeartSlots e:program.environment.getHeartSlots()) {
			GImage temp = e.getGImage();
			program.remove(temp);
		}
		for(CoinSlots e:program.environment.getCoinSlots()) {
			GImage temp = e.getGImage();
			program.remove(temp);
		}

		for(Platform e:program.environment.getPlatforms()) {
			GImage temp = e.getGImage();
			program.remove(temp);
		}


		for(Loot e:program.environment.getLootList()) {
			GImage temp = e.image;
			program.remove(temp);
		}

		for(Entity e:program.environment.getEnemyList()) {
			GImage temp = e.getImage();
			program.remove(temp);
		}

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
