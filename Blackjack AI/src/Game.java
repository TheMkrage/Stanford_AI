import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	Player player;
	AIPlayer ai;
	Deck deck;
	private ArrayList<Card> faceUpCards;

	public void startGame() {
		deck = new Deck();
		faceUpCards = new ArrayList<Card>();
		deck.shuffle();
		gameLoop();
	}

	private void gameLoop() {
		Scanner scan = new Scanner(System.in);
		boolean playingGames = true;
		boolean currentGameIsRunning = true;
		System.out.println("??: WELCOME TO BLACKJACK!");
		System.out.println("??: WHAT IS YOUR NAME?");
		player = new Player(scan.nextLine());
		ai = new AIPlayer("ROBIE");
		ai.say("HELLO " + player.getName() + "! I AM " + ai.getName());
		player.say("Nice to meet you " + ai.getName());
		ai.say("READY TO PLAY SOME BLACKJACK?");
		pause(2500);
		player.say("Hell yes!");
		pause(1000);
		while (playingGames) {
			System.out.println(" ");
			ai.say("THE GAME IS BEGINNING");
			while (currentGameIsRunning) {
				dealRound();
				player.addCard(deck.dealCard());
				player.addCard(deck.dealCard());

				pause(1000);
				ai.addCard(deck.dealCard());
				ai.addCard(deck.dealCard());
				ai.say("I GOT A " + ai.getCard(0) + " AND A " + ai.getCard(1));
				console("YOU GOT A " + player.getCard(0) + " AND A "
						+ player.getCard(1));
				boolean turnIsLasting = true;
				while (turnIsLasting) { // PLAYER TURN
					console("HIT OR STAND? (h/s)");
					String line = scan.nextLine();
					if (line.equals("h")) {
						player.addCard(deck.dealCard());
						console("You got a "
								+ player.getCard(player.getCardCount() - 1));
					} else if (line.equals("s")) {
						console(player.getScore());
						turnIsLasting = false;
					}
				}
				System.out.println();
				pause(2000);
				ai.say("NOW IT IS MY TURN!");
				turnIsLasting = true;
				while (turnIsLasting) { // AI'S TURN
					ai.say("SHOULD I HIT OR STAND? (h/s)");
					String line = ai.decide(faceUpCards);
					System.out.println("DECIDED: " + line);
					if (line.equals("h")) {
						ai.addCard(deck.dealCard());
						console("AI got a " + ai.getCard(ai.getCardCount() - 1));
					} else if (line.equals("s")) {
						console(ai.getScore());
						turnIsLasting = false;
					}
				}

				// FIND WHO WON
				if (ai.getScoreNum() > 21 && player.getScoreNum() > 21) {
					console("BOTH PLAYERS BUSTED!");
				} else if (ai.getScoreNum() > 21 && player.getScoreNum() <= 21) {
					System.out.println("AI BUSTED! " + player.getName()
							+ " HAS WON!");
				} else if (ai.getScoreNum() <= 21 && player.getScoreNum() > 21) {
					System.out.println(player.getName() + " busted! ai has won!" );
				} else if (ai.getScoreNum() > player.getScoreNum()) {
					console(ai.getName() + " WON WITH " + ai.getScoreNum()
							+ " WHILE YOU HAD " + player.getScoreNum());
					ai.say("LOL");
				} else if (ai.getScoreNum() < player.getScoreNum()) {
					console(player.getName() + " WON WITH "
							+ player.getScoreNum() + " WHILE AI HAD "
							+ ai.getScoreNum());
					player.say("YES!");
				}
				System.out.println("Would you like to play again? (y/n)");
				if (scan.nextLine() == "y") {

				} else {
					currentGameIsRunning = false;
					console("GOODBYE PESKY HUMAN");
				}
			}
		}
	}

	private void dealRound() {
		ai.say("I AM DEALING THE CARDS!");
	}

	private void console(String str) {
		System.out.println("CONSOLE: " + str);
	}

	private void pause(int milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
