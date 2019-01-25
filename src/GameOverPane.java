import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect; 

public class GameOverPane extends GraphicsPane
{
	private MainApplication program;
	private GLabel darn;
	private GLabel scoreLabel;
	private GImage Background; 
	private GButton rect;
	private GButton again; 
	private int score = 0;


	public GameOverPane( MainApplication app )
	{
		program = app;

		Background = new GImage("Game_Over.jpg", 0, 0); 
		Background.setSize(program.WINDOW_WIDTH, program.WINDOW_HEIGHT);
		//darn = new GLabel( "Oh Darn! You lost.", 450, 300 );
		darn = new GLabel( "Oh Darn! You lost.", (program.WINDOW_WIDTH/2.4), (program.WINDOW_HEIGHT/2));
		darn.setFont(new Font("Times New Roman", Font.BOLD, 34));
		darn.setColor(Color.WHITE);
		//scoreLabel = new GLabel( "Score goes here: ", 500, 400 );
		scoreLabel = new GLabel( "Score goes here: ", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/1.5));
		//rect = new GButton("Back to Main Menu", 500, 500, 100, 50);
		rect = new GButton("Back to Main Menu", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/1.2), 100, 50);
		rect.setFillColor(Color.white);
		//again = new GButton("Play Again", 500, 450, 100, 50); 
		again = new GButton("Play Again", (program.WINDOW_WIDTH/2.16), (program.WINDOW_HEIGHT/1.3), 100, 50);
	}

	public void setScore( int i )
	{
		score = i;
		scoreLabel = new GLabel( "Score goes here: " + score, 500, 400 );
	}

	@Override
	public void showContents() 
	{
		program.add(Background);
		program.add( darn );
		program.add( scoreLabel );
		program.add( rect );
		program.add(again); 
	}

	@Override
	public void hideContents()
	{
		program.remove(Background);
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
