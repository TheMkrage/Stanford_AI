public class DecisionTree {
	private class Node {

		/* FIELDS */
		private int nodeID;
		private String questOrAns = null;
		private Node[] answers = null; // Leaf
		private Node nextQuestion = null; // Internal
		private int entries = 0;

		/* CONSTRUCTOR */

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
			}
			return "Answer: " + questOrAns + " ID: " + nodeID;
		}
	}

	private Node rootNode;

	public void addNewLeafNode(int parentID, int newID, String answer) {
		if (parentID == 1) {
			rootNode.addAnswer(new Node(newID, answer, -1));
		} else {
			// Find Parent and add question
			searchAndAddAnswer(rootNode, parentID, newID, answer);

		}
	}

	private void searchAndAddAnswer(Node currentNode, int parentID, int newID,
			String answer) {
		if (currentNode.nextQuestion != null)
			System.out.println("I AM TRYING TO FIND " + parentID + " AND I FOUND " + currentNode.nextQuestion.nodeID);
		if (currentNode.nextQuestion != null
				&& currentNode.nextQuestion.nodeID == parentID) {
			currentNode.nextQuestion.addAnswer(new Node(newID, answer, -1));
		} else {
			for (int i = 0; i < currentNode.entries; i++) {
				if (currentNode.answers[i].nextQuestion != null) {
					searchAndAddAnswer(currentNode.answers[i].nextQuestion,
							parentID, newID, answer);
				}
			}
		}

	}

	public void addNewInternalNode(int parentID, int newID, String question,
			int options) {
		if (parentID == 0) {
			System.out.println("FIRST QUESTION : "  + question);
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
			System.out.println("LOOPING?");
			System.out.println("ANS: " + ans.nodeID + " PARENT: " + parentID);
			if (ans.nodeID == parentID) {
				System.out.println("FOUND MATCH");
				currentNode.answers[i].nextQuestion = new Node(newID, question,
						options);
				return;
			} else if (currentNode.answers[i].nextQuestion != null){
				// look in children if not right one
				searchAndAddQuestion(currentNode.answers[i].nextQuestion,
						parentID, newID, question, options);
			}
		}
	}

	public void printNode(String tag, Node currentNode) {

		// Check for empty node

		if (currentNode == null)
			return;

		// Output

		System.out.println("[" + tag + "] nodeID = " + currentNode.nodeID
				+ ", question/answer = " + currentNode.questOrAns);

		System.out.println(currentNode.entries);
		
		for (int i = 0; i < currentNode.entries; i++) {
			printNode("A: " , currentNode.answers[i]);
		}
		
		if (currentNode.nextQuestion != null)  {
			printNode("Q: " , currentNode.nextQuestion);
		}

	}

	public void print() {
		printNode("1", rootNode);
	}
}
