import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;


public class Recurse extends GraphicsProgram {

	//constant pause time
	private static final int PAUSE_TIME = 20;
	
	//Initial Ball Radius
	private static final int BALL_RADIUS = 200;
	
	//Our Recursion Tree Depth
	private static final int DEPTH = 7;
	
	//Basic screen settings
	public static final int APPLICATION_WIDTH = 1440;
	public static final int APPLICATION_HEIGHT = 980;
	
	//start our Recurse objects
	public static void main(String[] args) {
		new Recurse().start(args);
	}
	
	
	//main code logic here:
	public void run() {
		
		
	}
	
	/**
	 * @param n is how many splits we want to do.
	 * @param ball is the first ball to be split
	 * @return void
	 * Method takes a ball and splits it into four
	 */
	public void TriangleSplit(int depth, GOval ball)
	{

	}
	
	/**
	 * 
	 * @param x ball x location
	 * @param y ball y location
	 * @param width ball
	 * @param height ball
	 * @param color ball
	 * @param filled ball
	 * @return the ball to add
	 */
	public GOval addBall(double x,double y, double width, double height,Color color, boolean filled)
	{
		GOval aball = new GOval(x,y,width,height);
		aball.setFilled(filled);
		aball.setColor(color);
		add(aball);
		return aball;
	}
	
}
