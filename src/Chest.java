import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;

public class Chest extends GraphicsProgram{
	
	private static final int HEIGHT = 10;
	private static final int MAX_SPEED = 10;
	private static final double ACCELERATION = .7;
	
	private double speed = 0;
	private int level;
	private int HP; // health points
	private int attack;
	private int exp; //experience
	
	public GImage chestImage; 
	
	public Chest()
	{
		chestImage = new GImage("chest.jpg", 500, 400);
		chestImage.setSize(50, 50);
	}

}
