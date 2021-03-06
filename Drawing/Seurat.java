import acm.graphics.*;
import acm.util.*;
import acm.program.*;
import java.awt.*;

public class Seurat extends GraphicsProgram {

	private static final int NUM_PICTURES = 1;
	private static final int SPLOTCH_DIAMETER = 6;
	private RandomGenerator rgen = new RandomGenerator();

	public static void main(String[] args) {
		new Seurat().start(args);
	}

	public void run() {

		GImage image = new GImage("pacman.jpg");
		add(image);

		for (int x = 0; x < image.getWidth(); x+=5) {
			for ( int y = 0; y < image.getHeight(); y+=5) {
				GOval oval = new GOval(5, 5);
				oval.setLocation(x, y);
				oval.setFilled(true);
				oval.setColor(getColorAt(image, x, y));
				add (oval);
			}
		}

		//this.getColorAt(image, 60, 100);
	}

	/**
	 * 
	 * @param image
	 * @param x
	 *            of a pixel
	 * @param y
	 *            of a pixel
	 * @return the color found at a specific pixel
	 */
	private Color getColorAt(GImage image, int x, int y) {
		// feel free to ignore how the program is looking up the pixel color
		// this syntax is not very nice and you don't need to understand it.
		return new Color(image.getPixelArray()[y][x]);
	}

}
