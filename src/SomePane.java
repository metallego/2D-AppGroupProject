import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GButton exitButton; 
	private GImage background; 


	public SomePane(MainApplication app) {
		this.program = app;
		exitButton = new GButton ("exit", 0, 0, 50, 50); 
		background = new GImage("background.jpg", 0, 0);
		//background.setSize(1080, 600);
		background.setSize(program.WINDOW_WIDTH, program.WINDOW_HEIGHT);
	}
	



	@Override
	public void showContents() {
		program.add(background);

		for(Chest e:program.environment.getChestList()) {
			GImage temp = e.getImage();
			program.add(temp);
		}

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

		
		program.environment.setUpHeartSlots();
		program.environment.setUpCoinSlots();
		program.add(exitButton);
		program.add(program.hero.image);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(program.hero.image);
		program.remove(program.chest.image);
		program.remove(exitButton);

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
		if(obj == exitButton) {
			program.environment.setPreserve( false );
			program.loadedLevel = false;
			AudioPlayer audio = AudioPlayer.getInstance();
			if(program.options.isOff)
				audio.stopSound("sound", "Level.mp3");
			program.switchToMenu();
			
				
		}
	}

}
