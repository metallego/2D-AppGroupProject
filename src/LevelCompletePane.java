import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect; 

public class LevelCompletePane extends GraphicsPane
{
    private MainApplication program;
    private GLabel congrats;
    private GImage background; 
    private GLabel scoreLabel;
    private GButton rect;
    private GButton next; 
    private GLabel credits; 
    private int score = 0;
    
    
    public LevelCompletePane( MainApplication app )
    {
        program = app;
        background = new GImage("You_win.jpg", 0, 0); 
        //background.setSize(1080, 600);
        background.setSize(program.WINDOW_WIDTH, program.WINDOW_HEIGHT);
        congrats = new GLabel( "Congrtatulations you win!", 475, 200 );
        congrats.setColor(Color.white);
        scoreLabel = new GLabel( "Score goes here: ", 500, 400 );
        scoreLabel.setColor(Color.white);
        rect = new GButton("Back to Main Menu", 500, 500, 100, 50);
        rect.setFillColor(Color.white);
        next = new GButton("Next Level", 500, 450, 100, 50); 
        credits = new GLabel("Game made by: Jeremy, Nathan, Antonio Team LTS. We Hope you enjoyed our game", 300, 400);
        credits.setColor(Color.white);
    }
    
    public void setScore( int i )
    {
        score = i;
        scoreLabel = new GLabel( "Score goes here: " + score, 500, 400 );
    }
    
    @Override
    public void showContents() 
    {
    		program.add(background);
        program.add( congrats );
        program.add( scoreLabel );
        program.add( rect );
        program.add(next); 
        if(program.currentLevel == 3)
        		program.add(credits);
    }
    
    @Override
    public void hideContents()
    {
    		program.remove(background);
        program.remove( congrats );
        program.remove( scoreLabel );
        program.remove( rect );
        program.remove(next);
        if(program.currentLevel == 3)
        		program.remove(credits);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        GObject obj = program.getElementAt(e.getX(), e.getY());
        if(obj == next)
        {
        		if(program.currentLevel == 1)
        		{
        			program.currentLevel = 2;
        		}
        		else if (program.currentLevel == 2)
        		{
        			program.currentLevel = 3; 
        		}
        		
        		program.switchToSome();
        }
        if( obj == rect )
            program.switchToMenu();
    }
    
}
