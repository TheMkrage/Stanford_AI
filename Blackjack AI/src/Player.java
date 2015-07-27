import java.util.ArrayList;

public class Player {
	private String playerName;
	protected ArrayList<Card> playerCards = new ArrayList<Card>();
	private boolean turn = false;

	Player(String name) {
		playerName = name;
	}

	public Card getCard(int index) {
		return playerCards.get(index);
	}

	public void addCard(Card card) {
		playerCards.add(card);
	}

	public void clearCards() {
		playerCards.clear();
	}

	public int countCards() {
		return playerCards.size();
	}

	public String getName() {
		return playerName;
	}

	public void playerStand() {
		turn = false;
	}

	public void playerTurn() {
		turn = true;
	}

	public boolean getTurn() {
		return turn;
	}

	public void say(String string) {
		System.out.println(getName() + ": " + string);
	}

	public int getCardCount() {
		// TODO Auto-generated method stub
		return playerCards.size();
	}

	public String getScore() {
		int score = 0;
		for (Card x: playerCards)  {
			//System.out.println(x + " WITH VALUE " + x.getValue());
			score += x.getValue();
		}
		if(score == 21)
			return "BLACKJACK!";
		else if(score > 21) 
			return "AWWW, you busted with " + score;
		else {
			return"You got " + score + "!";
		}
	}
	
	protected double getScoreNum() {
		double score = 0;
		for (Card x: playerCards)  {
			//System.out.println(x + " WITH VALUE " + x.getValue());
			score += x.getValue();
		}
		return score;
	}

}