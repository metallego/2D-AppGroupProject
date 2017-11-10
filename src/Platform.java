import acm.graphics.*;
import acm.program.*;

import javax.imageio.ImageIO;



public class Platform {
	private GRect box;

	public Platform(double x, double y, double width, double height) {
		box = new GRect(x ,y , width, height);

	}

	public GRect getGRect() {

		return box;
	}

}
