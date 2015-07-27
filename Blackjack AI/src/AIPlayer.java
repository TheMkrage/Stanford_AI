import java.util.ArrayList;

public class AIPlayer extends Player {

	private ArrayList<Card> knownCards = new ArrayList<Card>();

	AIPlayer(String name) {
		super(name);
	}

	public String decide(ArrayList<Card> faceUpCards) {
		if(getScoreNum() >= 21)
			return "s";
		double heuristic = 0.0;
		ArrayList<Card> tempKnownCards = knownCards;
		if (faceUpCards != null && !faceUpCards.isEmpty())
			tempKnownCards.addAll(faceUpCards);
		heuristic += this.getScoreNum();
		int maxValueToNotBust = (int) (21 - this.getScoreNum());
		int cardsThatWillHelp = maxValueToNotBust * 4;
		for (Card x : knownCards)
			if (x.getValue() <= maxValueToNotBust)
				cardsThatWillHelp--;
		double probability = 10 * (cardsThatWillHelp / 52);
		double chances = probability - heuristic;
		if (chances > 60)
			return "h";
		else
			return "s";
	}

	public void addCard(Card card) {
		playerCards.add(card);
		knownCards.add(card);
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
			return "AWWW, I busted with " + score;
		else {
			return"I got " + score + "!";
		}
	}
}
