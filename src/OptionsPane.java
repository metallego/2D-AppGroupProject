import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import java.io.*; 
import static java.lang.System.*; 

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class OptionsPane extends GraphicsPane{
	
	public int SCREEN_WIDTH = 1080;
	public int SCREEN_HEIGHT = 600; 
	
	private MainApplication program; 
	private GRect volume_box;
	private GLabel volume; 
	private GButton on; 
	private GButton off; 
	private GButton backMenu;
	private GImage background; 
	private GButton backGame;
	private GLabel reset; 
	private GRect screen_box;
	private GLabel screen_res;
	private GButton screen1;
	private GButton screen2;
	private GButton screen3;
	private GButton screen4; 
	// add the screen resolution option here with GButton
	public boolean isOff = false; 

	public OptionsPane (MainApplication app) //change the x and y values so that they will hopefully be placed correctly based on the width/height
	{
		program = app; 
		
		//volume_box = new GRect (100, 100, 100, 50);
		//volume = new GLabel ("Volume", 125, 130);
		volume_box = new GRect ((program.WINDOW_WIDTH/10.8), 100, 100, 50);
		volume = new GLabel ("Volume", (program.WINDOW_WIDTH/8.64), 130);
		
		backMenu = new GButton ("Back to Menu", 0, 0, 100, 50); // this will remain unchanged just because it stays in the top let corner regardless of screen size 
		backGame = new GButton ("Back to Game", 500, 350, 100, 50);
		
		//on = new GButton ("ON", 775, 100, 100, 50);
		//off = new GButton ("OFF", 875, 100, 100, 50); 
		on = new GButton ("ON", (program.WINDOW_WIDTH/1.4), 100, 100, 50);
		off = new GButton ("OFF", (program.WINDOW_WIDTH/1.2), 100, 100, 50); 
		
		// added new GRect, GLabel, and GButtons so that I can make it easier for the user to access and choose which screen they would prefer to play on
		screen_box = new GRect((program.WINDOW_WIDTH/12), 300, 150, 50);
		screen_res = new GLabel ("Screen Resolution", (program.WINDOW_WIDTH/9.84), 330);
		reset = new GLabel("Changing the resolution will require a restart of the game to apply the change", (program.WINDOW_WIDTH/10),370); 
		//used the same idea as to figure out where the buttons should be placed 
		screen1 = new GButton("800x600", (program.WINDOW_WIDTH/1.4), 300, 100, 50);
		screen2 = new GButton("960x720", (program.WINDOW_WIDTH/1.2), 300, 100, 50);
		screen3 = new GButton("1024x768", (program.WINDOW_WIDTH/1.4), 350, 100, 50);
		screen4 = new GButton("1280x960", (program.WINDOW_WIDTH/1.2), 350, 100, 50);
		
		background = new GImage("options_Background.jpg", 0, 0);
		//background.setSize(1080, 600);
		background.setSize(program.WINDOW_WIDTH, program.WINDOW_HEIGHT);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see GraphicsPane#showContents()
	 * ideas 
	 * - volume toggle or slider? (toggle would probably be easier if we are being realistic)
	 * - a way to change screen size?
	 * - a way to toggle color blind mode? (extra most likely imo)
	 * - a way to 
	 */
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		program.add(volume_box);
		program.add(volume);
		program.add(backMenu);
		//program.add(backGame);
		program.add(on);
		program.add(off); 
		program.add(screen_box);
		program.add(screen_res);
		program.add(screen1);
		program.add(screen2);
		program.add(screen3);
		program.add(screen4);
		program.add(reset);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
		program.remove(volume_box);
		program.remove(volume);
		program.remove(backMenu);
		//program.remove(backGame);
		program.remove(on);
		program.remove(off);
		program.remove(screen_box);
		program.remove(screen_res);
		program.remove(screen1);
		program.remove(screen2);
		program.remove(screen3);
		program.remove(screen4);
		program.remove(reset);
	}
	//write function is added here to allow the users choice to be remembered so it can be booted up once reset.
	public void write() {
		try {
			FileWriter fw = new FileWriter("screen_res.txt");
			PrintWriter pw = new PrintWriter(fw); 
			
			pw.println(SCREEN_WIDTH + " " + SCREEN_HEIGHT); //takes the current/chosen resolution and writes it in
			
			pw.close();
		} catch(IOException e){
			out.println("ERROR finding file");
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == backMenu) {
		    program.currentLevel = 1;
			program.switchToMenu();
			AudioPlayer audio = AudioPlayer.getInstance();
			if(isOff)
				audio.stopSound("sounds", "Intro Song.mp3");
		}
		else if (obj == on)
		{
			on.setFillColor(Color.gray);
			off.setFillColor(Color.white);
			AudioPlayer audio = AudioPlayer.getInstance();
			audio.playSound("sounds", "Intro Song.mp3");
			isOff = false; 
		}
		else if (obj == off)
		{
			off.setFillColor(Color.gray);
			on.setFillColor(Color.white);
			AudioPlayer audio = AudioPlayer.getInstance();
			audio.stopSound("sounds", "Intro Song.mp3");
			isOff = true; 
		}
		else if (obj == backGame)
		{
			program.switchToSome();
		}
		else if (obj == screen1) {
			SCREEN_WIDTH = 800; 
			SCREEN_HEIGHT = 600; 
			screen1.setFillColor(Color.gray);
			screen2.setFillColor(Color.white);
			screen3.setFillColor(Color.white);
			screen4.setFillColor(Color.white);
			write();
			System.exit(0); // here I want to reset the app so that the changes take place
		}
		else if (obj == screen2) {
			SCREEN_WIDTH = 960; 
			SCREEN_HEIGHT = 720; 
			screen2.setFillColor(Color.gray);
			screen1.setFillColor(Color.white);
			screen3.setFillColor(Color.white);
			screen4.setFillColor(Color.white);
			write();
			System.exit(0);
		}
		else if (obj == screen3) {
			SCREEN_WIDTH = 1024; 
			SCREEN_HEIGHT = 768; 
			screen3.setFillColor(Color.gray);
			screen2.setFillColor(Color.white);
			screen1.setFillColor(Color.white);
			screen4.setFillColor(Color.white);
			write();
			System.exit(0);
		}
		else if (obj == screen4) {
			SCREEN_WIDTH = 1280; 
			SCREEN_HEIGHT = 960; 
			screen4.setFillColor(Color.gray);
			screen2.setFillColor(Color.white);
			screen3.setFillColor(Color.white);
			screen1.setFillColor(Color.white);
			write();
			System.exit(0);
		}
	}
	
	

}
