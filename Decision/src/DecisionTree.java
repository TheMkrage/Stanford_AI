public class DecisionTree {
	private class Node {

		/* FIELDS */
		private int nodeID;
		private String questOrAns = null;
		private Node[] answers = null; // Leaf
		private Node nextQuestion = null; // Internal
		private int entries = 0;
		private String[][] responses = null;

		private String getMoreAnswers() {
			int highest = Integer.MIN_VALUE;
			String highestString = "NONE";
			for (String[] x : responses)
				if (Integer.getInteger(x[1]) > highest) {
					highestString = x[0];
					highest = Integer.getInteger(x[1]);
				}
			return highestString;
		}

		/* CONSTRUCTOR */

		public Node(int newNodeID, String newQuestAns, int answersCount,
				String[][] responses) {
			if (answersCount != -1) // Is not an answer
				answers = new Node[answersCount];
			nodeID = newNodeID;
			this.responses = responses;
			questOrAns = newQuestAns;
			entries = 0;
		}

		public Node(int newNodeID, String newQuestAns, int answersCount) {
			if (answersCount != -1) // Is not an answer
				answers = new Node[answersCount];
			nodeID = newNodeID;
			questOrAns = newQuestAns;
			entries = 0;
		}

		private void addAnswer(Node answer) {
			System.out.println("ADDED " + answer);
			answers[entries] = answer;
			entries++;
		}

		public String toString() {
			if (answers != null) {
				return "QUESTION: " + questOrAns + " ID: " + nodeID;
			} else if (responses != null) {
				String toReturn = "Answer: " + questOrAns + " ID: " + nodeID
						+ " ";
				// System.out.println(responses);
				for (String[] x : responses) {
					// for (String i: x)
					// System.out.println(i);
					toReturn += x[0] + ": " + x[1] + " ";
				}
				return toReturn;
			}
			return "Answer: " + questOrAns + " ID: " + nodeID;
		}
	}

	public String giveMeAnswer(ListOfExamples ex, BinaryFeature[] features) {
		String stats = "";
		for (Example x : ex) {
			stats += giveMeAnswer(x, features, rootNode);
		}
		return stats;
	}

	public String giveMeAnswer(Example ex, BinaryFeature[] x, Node n) {
		for (int i = 0; i < ex.size(); i++) {
			String currentAns = ex.get(i);
			BinaryFeature currentFeature = x[i];
			for (Node answerNode : n.answers) {
				if (currentFeature.getName().equalsIgnoreCase(n.questOrAns) && currentAns.equalsIgnoreCase(answerNode.questOrAns)) {
					if(answerNode.nextQuestion != null) {
						giveMeAnswer(ex, x, answerNode.nextQuestion);
					}else {
						return answerNode.getMoreAnswers();
					}
				}
			}
		}
	}

	private Node rootNode;

	public void addNewLeafNode(int parentID, int newID, String answer,
			String[][] responses) {
		if (parentID == 1) {
			rootNode.addAnswer(new Node(newID, answer, -1, responses));
		} else {
			// Find Parent and add question
			searchAndAddAnswer(rootNode, parentID, newID, answer, responses);
		}
	}

	private void searchAndAddAnswer(Node currentNode, int parentID, int newID,
			String answer, String[][] responses) {
		// System.out.println("TEST");
		if (currentNode != null && currentNode.nodeID == parentID) {
			currentNode.addAnswer(new Node(newID, answer, -1, responses));
		} else {
			// System.out.println("looking parentID: " + parentID);
			for (int i = 0; i < currentNode.entries; i++) {
				// System.out.println("LOOKING THROUGH ENTRIES: " +
				// currentNode);
				// System.out.println("LOOKING THROUGH ENTRIES: "
				// + currentNode.answers[i]);
				// System.out.println("LOOKING THROUGH ENTRIES: "
				// + currentNode.answers[i].nextQuestion);
				if (currentNode.answers[i].nextQuestion != null) {
					searchAndAddAnswer(currentNode.answers[i].nextQuestion,
							parentID, newID, answer, responses);
				}
			}
		}

	}

	public void addNewInternalNode(int parentID, int newID, String question,
			int options) {
		if (parentID == 0) {
			System.out.println("FIRST QUESTION : " + question);
			rootNode = new Node(newID, question, options);
		} else {
			// Find Parent and add question
			searchAndAddQuestion(rootNode, parentID, newID, question, options);
		}
	}

	private void searchAndAddQuestion(Node currentNode, int parentID,
			int newID, String question, int options) {
		for (int i = 0; i < currentNode.entries; i++) {
			Node ans = currentNode.answers[i];
			// System.out.println("LOOPING?");
			// System.out.println("ANS: " + ans.nodeID + " PARENT: " +
			// parentID);
			if (ans.nodeID == parentID) {
				// System.out.println("FOUND MATCH");
				currentNode.answers[i].nextQuestion = new Node(newID, question,
						options);
				return;
			} else if (currentNode.answers[i].nextQuestion != null) {
				// look in children if not right one
				searchAndAddQuestion(currentNode.answers[i].nextQuestion,
						parentID, newID, question, options);
			}
		}
	}

	private int tab = 0;
	private int answerTab = 0;

	public void printNode(String tag, Node currentNode) {

		// Check for empty node

		if (currentNode == null)
			return;

		// Output
		if (tab == 0)
			for (int x = 0; x < answerTab; x++)
				System.out.print("	");
		System.out.println(currentNode);
		tab++;
		// System.out.println(currentNode.entries);

		int curTab = tab;
		for (int i = 0; i < currentNode.entries; i++) {
			for (int x = 0; x < curTab; x++)
				System.out.print("	");
			printNode("A: ", currentNode.answers[i]);
			tab = curTab;
			answerTab = tab;
		}

		if (currentNode.nextQuestion != null) {
			for (int x = 0; x < tab; x++)
				System.out.print("	");
			printNode("Q: ", currentNode.nextQuestion);
		} else {
			tab = 0;
		}
	}

	public void print() {
		printNode("Q", rootNode);
	}
}
