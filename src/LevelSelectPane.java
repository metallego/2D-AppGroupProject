import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class LevelSelectPane extends GraphicsPane{
	
	private MainApplication program; 
	
	private GButton lvl1; 
	private GButton lvl2;
	private GButton lvl3; 
	
	public LevelSelectPane(MainApplication app)
	{
		program = app; 
		lvl1 = new GButton ("Level 1", 100, 200, 100, 100); 
		lvl1.setFillColor(Color.white);
		
		lvl2 = new GButton ("Level 2", 300, 200, 100, 100); 
		lvl2.setFillColor(Color.white);
		
		lvl3 = new GButton ("Level 3", 500, 200, 100, 100);
		lvl3.setFillColor(Color.white);
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(lvl1);
		program.add(lvl2);
		program.add(lvl3);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(lvl1);
		program.remove(lvl2);
		program.remove(lvl3);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == program.hero.image || obj == lvl1) {
			program.switchToSome();
		}
	}

}
