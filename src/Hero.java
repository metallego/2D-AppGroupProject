import java.awt.event.KeyEvent;

import java.awt.geom.Point2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

import acm.graphics.*;
import acm.program.*;

import java.util.ArrayList;


public class Hero extends Entity{
	public static int coins = 0;	
	private static int exp; //experience
	private static int level = 1;

	
	public int getExp() {
		return exp;
	}

	public static void addExp(int expNum) {
		exp += expNum;
		
		if(exp % 10 == 0 && exp != 0) {
			levelUp();
		}
	}
	
	
	private static void levelUp() {
		level++;
		setAttack();
	}

	public int getLevel() {
		return level;
	}
	
	public static void setAttack() {
		attack *= 0.5*level;
	}
	

	public Hero()
	{
		image = new GImage("hero_idle_right.jpg", 50, 400); 
		this.setType(EntityType.HERO);

	}
	
	public Hero(int i, int j)
    {
        image = new GImage("hero_idle_right.jpg", i, j); 
        this.setType(EntityType.HERO);
        System.out.println("Created new Hero");
        this.setAttack(5);
    }


	

	
	public int getCoins()
	{
	    return coins;
	}
	
	public static void addCoin()
	{
	    coins ++;
	}
	
	public void resetCoins()
	{
	    coins = 0;
	}


}