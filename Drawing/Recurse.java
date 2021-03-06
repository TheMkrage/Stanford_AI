import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Recurse extends GraphicsProgram {

	// constant pause time
	private static final int PAUSE_TIME = 20;

	// Initial Ball Radius
	private static final int BALL_RADIUS = 200;

	// Our Recursion Tree Depth
	private static final int DEPTH = 7;

	// Basic screen settings
	public static final int APPLICATION_WIDTH = 1440;
	public static final int APPLICATION_HEIGHT = 980;

	// start our Recurse objects
	public static void main(String[] args) {
		new Recurse().start(args);
	}

	// main code logic here:
	public void run() {
		triangleSplit(
				50,
				addBall(APPLICATION_WIDTH / 2, APPLICATION_HEIGHT / 2, 100,
						100, Color.BLUE, true));
	}

	/**
	 * @param n
	 *            is how many splits we want to do.
	 * @param ball
	 *            is the first ball to be split
	 * @return void Method takes a ball and splits it into four
	 */
	public void triangleSplit(int depth, GOval ball) {
		System.out.println(depth);
		if (depth <= 0) {
			return;
		} 
			remove(ball);
			int subOne = depth - 1;
			triangleSplit(
					subOne,
					addBall(ball.getX() + 50, ball.getY(),
							ball.getWidth() , ball.getHeight() ,
							Color.BLUE, true));
			triangleSplit(
					subOne,
					addBall(ball.getX() - 50, ball.getY(),
							ball.getWidth() , ball.getHeight(),
							Color.BLUE, true));
			triangleSplit(
					subOne,
					addBall(ball.getX(), ball.getY() - 50,
							ball.getWidth() , ball.getHeight() ,
							Color.BLUE, true));
	}

	/**
	 * 
	 * @param x
	 *            ball x location
	 * @param y
	 *            ball y location
	 * @param width
	 *            ball
	 * @param height
	 *            ball
	 * @param color
	 *            ball
	 * @param filled
	 *            ball
	 * @return the ball to add
	 */
	public GOval addBall(double x, double y, double width, double height,
			Color color, boolean filled) {
		System.out.println("HOWDY");
		GOval aball = new GOval(x, y, width, height);
		aball.setFilled(filled);
		aball.setColor(color);
		add(aball);
		return aball;
	}

}
