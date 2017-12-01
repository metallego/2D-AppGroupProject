import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect; 

public class GameOverPane extends GraphicsPane
{
	private MainApplication program;
	private GLabel darn;
	private GLabel scoreLabel;
	private GButton rect;
	private GButton again; 
	private int score = 0;


	public GameOverPane( MainApplication app )
	{
		program = app;
		darn = new GLabel( "Oh Darn! You lost.", 475, 200 );
		scoreLabel = new GLabel( "Score goes here: ", 500, 400 );
		rect = new GButton("Back to Main Menu", 500, 500, 100, 50);
		rect.setFillColor(Color.white);
		again = new GButton("Play Again", 500, 450, 100, 50); 
	}

	public void setScore( int i )
	{
		score = i;
		scoreLabel = new GLabel( "Score goes here: " + score, 500, 400 );
	}

	@Override
	public void showContents() 
	{
		program.add( darn );
		program.add( scoreLabel );
		program.add( rect );
		program.add(again); 
	}

	@Override
	public void hideContents()
	{
		program.remove( darn );
		program.remove( scoreLabel );
		program.remove( rect );
		program.remove(again);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == again)
		{

			program.switchToSome();
		}
		if( obj == rect )
			program.switchToMenu();
	}

}
