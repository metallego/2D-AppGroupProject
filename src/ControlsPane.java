import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect; 

public class ControlsPane extends GraphicsPane{
	
	private MainApplication program; 
	private GButton Up_Key; 
	private GButton Right_Key;
	private GButton Left_Key;
	private GButton Down_Key; 
	private GButton Spacebar; 
	private GButton attack; 
	private GButton back; 

	public ControlsPane(MainApplication app)
	{
		program = app;
		Up_Key = new GButton ("Jump - W / ^", 150, 150, 100, 50); 
		Right_Key = new GButton ("Move Right - D / >", 250, 200, 100, 50); 
		Left_Key = new GButton ("Move Left - A / <", 50, 200, 100, 50); 
		Down_Key = new GButton("Jump Down - S / v", 150, 200, 100, 50);
		
		Spacebar = new GButton("Jump - spacebar", 400, 300, 100, 50);
		
		attack = new GButton ("Attack - Z / J", 600, 200, 100, 50); 
		
		back = new  GButton("back to menu", 0, 0, 100, 50); 
		
		
		
	}
	
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(Up_Key);
		program.add(Right_Key);
		program.add(Left_Key);
		program.add(Down_Key);
		program.add(Spacebar);
		program.add(attack);
		program.add(back);
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(Up_Key);
		program.remove(Right_Key);
		program.remove(Left_Key);
		program.remove(Down_Key);
		program.remove(Spacebar);
		program.remove(attack);
		program.remove(back);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == back) {
			program.switchToMenu();
		}
	}
	

}
