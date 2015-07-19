import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class EmbeddedRectangles extends GraphicsProgram {
	
	//use for generating random numbers.
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private static final int MAX_RECTANGLES = 10;
	private static final int FRAME_DELAY = 10000;
	
	public static void main(String[] args) {
		new EmbeddedRectangles().start(args);
	}
	
	public void run() {
		
	}
	
}
