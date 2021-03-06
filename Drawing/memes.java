import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;


public class memes extends GraphicsProgram {

	//constant pause time
	private static final int PAUSE_TIME = 20;
	
	//Basic screen settings
	public static final int APPLICATION_WIDTH = 405;
	public static final int APPLICATION_HEIGHT = 405;
	private GLabel topText;
	private GLabel bottomText;
	
	
	public static void main(String[] args) {
		new memes().start(args);
	}

	//main code logic here:
	public void run() 
	{
		GImage image = new GImage("Me Gusta.png");
		image.setSize(APPLICATION_WIDTH - 25, APPLICATION_HEIGHT - 25);
		add(image);
		Scanner scan = new Scanner(System.in);
		System.out.println("TOP LINE PLZ");
		String top = scan.nextLine();
		System.out.println("BOT LINE PLZ");
		String bot = scan.nextLine();
		topText = new GLabel(top);
		bottomText = new GLabel(bot);
		topText.setFont(new Font("Sans-Serif", Font.BOLD, 30));
		topText.setLocation((APPLICATION_WIDTH/2) - top.length() * 8 , 80);
		bottomText.setFont(new Font("Sans-Serif", Font.BOLD, 30));
		bottomText.setLocation((APPLICATION_WIDTH/2) - bot.length() * 8, 350);
		add(topText);
		add(bottomText);
	}
}
