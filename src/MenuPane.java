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
	private GButton lvlSelect; 
	
	
	public MenuPane(MainApplication app) {
		program = app;
		
		//rect = new GButton("Start", 500, 200, 100, 50);
		rect = new GButton("Start", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/3), 100, 50);
		rect.setFillColor(Color.white);
		
		//options = new GButton("Options", 500, 275, 100, 50);
		options = new GButton("Options", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/2.15), 100, 50);
		options.setFillColor(Color.white);
		
		//exit = new GButton("Exit", 500, 500, 100, 50);
		exit = new GButton("Exit", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/1.2), 100, 50);
		exit.setFillColor(Color.WHITE);
		
		//title = new GLabel("Golden Boy", 465, 150);
		title = new GLabel("Golden Boy", (program.WINDOW_WIDTH/2.32), (program.WINDOW_HEIGHT/4));
		title.setFont(new Font("Times New Roman", Font.BOLD, 34));
		
		//controls = new GButton("Controls", 500, 350, 100, 50);
		controls = new GButton("Controls", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/1.7), 100, 50);
		controls.setFillColor(Color.white);
		
		background = new GImage("background01.jpg", 0, 0);
		//background.setSize(1080, 600); 
		background.setSize(program.WINDOW_WIDTH, program.WINDOW_HEIGHT);
		
		//lvlSelect = new GButton ("Level Select", 500, 425, 100, 50);
		lvlSelect = new GButton ("Level Select", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/1.4), 100, 50);
		lvlSelect.setFillColor(Color.WHITE);
		}
	
	@Override
	public void showContents() {
		program.add(background);
		program.add(rect);
		program.add(options);
		program.add(exit);
		program.add(title);
		program.add(controls);
		program.add(lvlSelect);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(rect);
		program.remove(options);
		program.remove(exit);
		program.remove(title);
		program.remove(controls);
		program.remove(lvlSelect);
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
		else if (obj == lvlSelect)
		{
			program.switchtoLevelSelect();
		}
	}
	
	
}
