import java.awt.event.KeyEvent;

import java.awt.geom.Point2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

import acm.graphics.*;
import acm.program.*;

import java.util.ArrayList;


public class Hero extends Entity{

	private int coins;
	private GImage currentWeapon;
	private Timer attackTimer;
	public static final int timerWoken = 50;
	private boolean right;
	private double weaponRatio;
	
	private ArrayList<Weapon> inventory = new ArrayList<Weapon>();
	

	public Hero()
	{
		image = new GImage("hero_idle_right.jpg", 50, 400); 
		this.setType(EntityType.HERO);
		image.setSize(100,100);
	}

	public void attack() {
		// TODO Auto-generated method stub
	}
	
	
	
	public void setCurrentWeapon(GImage image, boolean right) {
		if(right) {
			
			
		}
		
		
		if(!right) {// this is the left interaction
			
		}
		
	}
	
	public void setWeaponRatio(double wRatio) {
		
		weaponRatio = wRatio;
	}
	
	public double getWeaponRatio() {
		
		return weaponRatio;
	}
	
	public int getCoins()
	{
	    return coins;
	}
	
	public void addCoin()
	{
	    coins ++;
	}
	
	public void resetCoins()
	{
	    coins = 0;
	}


}