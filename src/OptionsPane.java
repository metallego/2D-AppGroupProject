import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class OptionsPane extends GraphicsPane{
	
	private MainApplication program; 
	private GRect volume_box;
	private GLabel volume; 
	private GButton on; 
	private GButton off; 
	private GButton backMenu;
	private GImage background; 
	private GButton backGame; 
	public boolean isOff = false; 

	public OptionsPane (MainApplication app)
	{
		program = app; 
		
		volume_box = new GRect (100, 100, 100, 50);
		volume = new GLabel ("Volume", 125, 130);
		
		backMenu = new GButton ("Back to Menu", 0, 0, 100, 50);
		backGame = new GButton ("Back to Game", 500, 350, 100, 50);
		
		on = new GButton ("ON", 775, 100, 100, 50);
		off = new GButton ("OFF", 875, 100, 100, 50); 
		
		background = new GImage("options_Background.jpg", 0, 0);
		background.setSize(1080, 600);
		
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
	}
	
	

}
