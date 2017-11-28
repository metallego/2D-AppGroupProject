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
		//right now all of these pass a "1" indicating Level 1 should be read in
		//in the future when more levels are added change the number to the level you want to implement.
		if(obj == lvl1) {
		    program.currentLevel = 1;
			program.switchToSome();
		}
		else if(obj == lvl2) {
		    program.currentLevel = 1;
            program.switchToSome();
        }
		else if(obj == lvl3) {
		    program.currentLevel = 1;
            program.switchToSome();
        }
	}

}
