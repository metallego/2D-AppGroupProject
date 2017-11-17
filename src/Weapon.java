import java.awt.event.KeyEvent;

import java.awt.geom.Point2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;

import java.util.ArrayList;

public class Weapon extends GraphicsProgram {
	private int attack;
	private GImage weapon;
	
	public Weapon(int atk, GImage wpn) {
		attack = atk;
		weapon = wpn;
	}

	public int getAttack() {
		return attack;
	}
	
	public GImage getGImage() {
		return weapon;
	}
	
	
	public void setAttack(int a) {
		attack = a;
		
	}
	
	public void setGImage(GImage img) {
		weapon = img;
		
	}
	
	
	
}
