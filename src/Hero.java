import java.awt.event.KeyEvent;

import java.awt.geom.Point2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import acm.graphics.*;
import acm.program.*;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Hero extends Entity{
	public static int coins = 0;	
	private static int exp; //experience
	private static int level = 1;
	public static int hp = 3;
	private static boolean invincible = false;	
	public String imageName;

	

	

	public Hero()
	{
		image = new GImage("hero_idle_right.jpg", 50, 400); 
		this.setType(EntityType.HERO);
		imageName = new String("hero_idle_right.jpg" );

	}
	
	public Hero(int i, int j)
    {
        image = new GImage("hero_idle_right.jpg", i, j);
        this.setType(EntityType.HERO);
        imageName = new String("hero_idle_right.jpg" );
        System.out.println("Created new Hero");
        this.setAttack(5);
        this.setDeath( false );
    }
	

	public void updateHP() {
		// MAKE THE INVINCIBLITY CHANGE BACK TO VINICIBLE

		
		if(!isInvincible()) {
			hp--;	
			setInvincible(true);
            System.out.println( "Hero took damage.  Current HP: " + hp );
			}

		
		if(hp <= 0) {
		    this.setDeath(true);
		}
	}
	
	public int getExp() {
		return exp;
	}

	public static void addExp(int expNum) {
		exp += expNum;
		System.out.println("exp");

		System.out.println(exp);
		if(exp % 10 == 0 && exp != 0) {
			levelUp();
		}
	}
	
	
	private static void levelUp() {
		level++;
		System.out.println("level");
		System.out.println(level);

		setAttack();
	}

	public int getLevel() {
		return level;
	}
	
	public static void setAttack() {
		attack += 0.5*level;
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

	public static boolean isInvincible() {
		return invincible;
	}

	public static void setInvincible(boolean invincible) {
		Hero.invincible = invincible;
	}





}