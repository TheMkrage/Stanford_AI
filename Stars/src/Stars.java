import java.math.BigInteger;
import java.util.Scanner;

public class Stars {
	//8.56
	public static void main(String[] args) {
		// String[] list = {"Hello", "World", "In", "A", "Frame"};
		// printListInAFrame( list );
		// giveMeNAndReact();
		// fibNums(1, BigInteger.ZERO, BigInteger.ONE);
		printMonthlyPayment(100, .05, 12);
	}

	// Challenge 1
	private static void giveMeNAndReact() {
		Scanner scan = new Scanner(System.in);
		System.out.println("GIVE ME N");
		int n = scan.nextInt();
		System.out.println("TYPE: \n0 FOR SUMMATION\n1 FOR FACTORIAL");
		System.out.println("ANSWER IS: " + compute(n, scan.nextInt()));

	}

	private static int compute(int n, int code) {
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			if (code == 1) { // Factorial
				if (ans == 0)
					ans = 1;
				ans *= i;
			} else if (code == 0) { // Summation
				ans += i;
			}
		}
		return ans;
	}

	// Challenge 2
	private static void printListInAFrame(String[] list) {
		// Find Longest Word
		int longest = 0;
		for (String str : list) {
			if (str.length() > longest) {
				longest = str.length();
			}
		}
		// First Row of Stars
		printTimes("*", longest + 4);
		System.out.println();
		// The words
		for (String str : list) {
			System.out.print("* " + str);
			printTimes(" ", longest - str.length());
			System.out.println(" *");
		}
		// Last Row of Stars
		printTimes("*", longest + 4);
	}

	private static void printTimes(String str, int times) {
		for (int i = 0; i < times; i++) {
			System.out.print(str);
		}
	}

	// Challenge 3
	private static BigInteger fibNums(int time, BigInteger one, BigInteger two) {
		if (time == 1)
			System.out.println("Num: 1 0");
		System.out.println("NUM: " + (time + 1) + " " + two);
		BigInteger temp = two;
		two = two.add(one);
		one = temp;
		if (time == 99)
			return BigInteger.ZERO;
		return fibNums(++time, one, two);
	}

	// Challenge 4
	private static void printMonthlyPayment(double balance, double apr,
			int months) {
		double totalOwed = balance + ((apr / 12) * months);
		double monthlyWithoutCompound = totalOwed / months;
		System.out.println("You must pay " + totalOwed / months + " for "
				+ months + " months without compound interest");
		double currentTry = monthlyWithoutCompound;
		boolean done = false;
		while (!done) {
			//System.out.println();
			double pastMonth = balance;
			for (int i = 0; i < months; i++) {
				double curMonth = pastMonth + ((apr/12) * pastMonth) - currentTry;
				pastMonth = curMonth;
				//System.out.println("CurrentMonth: " + curMonth);
			}
			//System.out.println("FINAL BALANCE is " + pastMonth);
			if ( pastMonth == 0 || (pastMonth < .01 && pastMonth > -.01)) {
				System.out.println("YOU WIN WITH: " + currentTry);
				return;
			}
			currentTry += .001;		
		}
	}
}
