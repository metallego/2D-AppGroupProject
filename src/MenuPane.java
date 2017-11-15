import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class MenuPane extends GraphicsPane{
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GButton rect;
	private GButton options; 
	private GButton exit; 
	private GLabel title; 
	private GButton controls;
	private GImage background; 
	
	
	public MenuPane(MainApplication app) {
		program = app;
		rect = new GButton("Start", 350, 200, 100, 50);
		rect.setFillColor(Color.white);
		
		options = new GButton("Options", 350, 275, 100, 50);
		options.setFillColor(Color.white);
		
		exit = new GButton("Exit", 350, 425, 100, 50);
		exit.setFillColor(Color.WHITE);
		
		title = new GLabel("Golden Boy", 315, 150);
		title.setFont(new Font("Times New Roman", Font.BOLD, 34));
		
		controls = new GButton("Controls", 350, 350, 100, 50);
		controls.setFillColor(Color.white);
		
		background = new GImage("background01.jpg", 0, 0);
		background.setSize(800, 600);
		
		}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(rect);
		program.add(options);
		program.add(exit);
		program.add(title);
		program.add(controls);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(rect);
		program.remove(options);
		program.remove(exit);
		program.remove(title);
		program.remove(controls);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == rect) {
			program.switchToSome();
		}
		else if(obj == options) {
			program.switchtoOptions();
		}
		else if(obj == exit) {
			System.exit(0);
		}
		else if (obj == controls)
		{
			program.switchtoControls();
		}
	}
	
	
}
