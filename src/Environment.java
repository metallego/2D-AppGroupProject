import acm.graphics.*;
import acm.program.*;


public class Environment
{
    private Hero hero;
    private double FRICTION = .1;
    private double GRAVITY = .4;

    public Environment(Hero h)
    {
        hero = h;
    }
    
    public void update()
    {
        hero.move();
        hero.applyFriction(FRICTION);
        //hero.applyGravity(GRAVITY);
    }
    
}
