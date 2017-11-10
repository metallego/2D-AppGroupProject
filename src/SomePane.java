import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	//private GImage img;
	
	public SomePane(MainApplication app) {
		this.program = app;
		//img = new GImage("hero.jpg", 100, 100);
	}
	
	@Override
	public void showContents() {
		program.add(program.hero.heroImg);
		program.add(program.enemy.enemyImage);
		program.add(program.chest.chestImage);
	}

	@Override
	public void hideContents() {
		program.remove(program.hero.heroImg);
		program.remove(program.enemy.enemyImage);
		program.remove(program.chest.chestImage);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == program.hero.heroImg) {
			program.switchToMenu();
		}
	}

}
