import acm.graphics.*;
import acm.program.*;


public class Environment
{
    private Hero hero;
    private double FRICTION = .4;
    private double GRAVITY = .4;

    public Environment(Hero h)
    {
        hero = h;
    }
    
    private void update()
    {
        hero.move();
        hero.applyFriction(FRICTION);
        hero.applyGravity(GRAVITY);
    }
}
