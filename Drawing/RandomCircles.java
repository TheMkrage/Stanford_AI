import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class RandomCircles extends GraphicsProgram {

	private static final int PAUSE_TIME = 100;
	private static final int MAX_RADIUS = 100;
	private static final int MIN_DIAMETER = 50;
	private static final int DIAM_DIFF = 40;
	private RandomGenerator rgen = new RandomGenerator();
	GOval oval;
	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 600;

	public static void main(String[] args) {
		new RandomCircles().start(args);
	}

	public void run() {
		while (true) {
			int circle = rgen.nextInt(MIN_DIAMETER, MAX_RADIUS * 2);
			GOval oval = new GOval(circle, circle);
			oval.setLocation(
					 rgen.nextInt(0,
							APPLICATION_WIDTH), rgen.nextInt(0,
							APPLICATION_HEIGHT));
			oval.setColor(rgen.nextColor());
			oval.setFilled(true);			
			add(oval);
			
			int newDiam = circle/2;
			int dif = circle - newDiam;
			double offset = Math.sin((Math.PI/4)) * (dif/Math.sin((Math.PI/2)));
			GOval donut = new GOval(newDiam, newDiam);
			donut.setLocation(oval.getX() + oval.getHeight()/4, oval.getY() + oval.getHeight()/4);
			donut.setColor(Color.WHITE);
			donut.setFilled(true);
			add(donut);
			pause (PAUSE_TIME);
		}
	}

}
