import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect; 

public class LevelCompletePane extends GraphicsPane
{
    private MainApplication program;
    private GLabel congrats;
    private GLabel scoreLabel;
    private GButton rect;
    private int score = 0;
    
    
    public LevelCompletePane( MainApplication app )
    {
        program = app;
        congrats = new GLabel( "Congrtatulations you win!", 475, 200 );
        scoreLabel = new GLabel( "Score goes here: ", 500, 400 );
        rect = new GButton("Back to Main Menu", 500, 500, 100, 50);
        rect.setFillColor(Color.white);
    }
    
    public void setScore( int i )
    {
        score = i;
        scoreLabel = new GLabel( "Score goes here: " + score, 500, 400 );
    }
    
    @Override
    public void showContents() 
    {
        program.add( congrats );
        program.add( scoreLabel );
        program.add( rect );
    }
    
    @Override
    public void hideContents()
    {
        program.remove( congrats );
        program.add( scoreLabel );
        program.add( rect );
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        GObject obj = program.getElementAt(e.getX(), e.getY());
        if( obj == rect )
            program.switchToMenu();
    }
    
}
