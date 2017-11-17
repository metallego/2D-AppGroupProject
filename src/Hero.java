import java.awt.event.KeyEvent;

import java.awt.geom.Point2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import acm.graphics.*;
import acm.program.*;

import java.util.ArrayList;


public class Hero extends Entity{
	
	private int coin;
	private GImage currentWeapon;
	
	private ArrayList<Weapon>  inventory = new ArrayList<Weapon>();
	

	public Hero()
	{
		image = new GImage("hero_idle.jpg", 50, 400); 
		this.setType(EntityType.HERO);
		image.setSize(100,100);
	}

	public void attack() {
		// TODO Auto-generated method stub
	}


}