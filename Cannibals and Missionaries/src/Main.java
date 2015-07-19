import java.util.LinkedList;

public class Main {

	static LinkedList<Node> list = new LinkedList<Node>();
	static Node initialNode = new Node(3, 3, 0, 0, true, null);

	public static void main(String[] args) {
		list.add(initialNode);

		while (true) {
			Node testNode = list.pop();
			if (!isDone(testNode)) {
				if (testNode.isOnRightSide) {
					list.add(new Node(testNode.cr - 1, testNode.mr,
							testNode.cl + 1, testNode.ml, false, testNode));
					list.add(new Node(testNode.cr, testNode.mr - 1,
							testNode.cl, testNode.ml + 1, false, testNode));
					list.add(new Node(testNode.cr - 2, testNode.mr,
							testNode.cl + 2, testNode.ml, false, testNode));
					list.add(new Node(testNode.cr, testNode.mr - 2,
							testNode.cl, testNode.ml + 2, false, testNode));
					list.add(new Node(testNode.cr - 1, testNode.mr - 1,
							testNode.cl + 1, testNode.ml + 1, false, testNode));
				} else if (!testNode.isOnRightSide) {
					list.add(new Node(testNode.cr + 1, testNode.mr,
							testNode.cl - 1, testNode.ml, true, testNode));
					list.add(new Node(testNode.cr, testNode.mr + 1,
							testNode.cl, testNode.ml - 1, true, testNode));
					list.add(new Node(testNode.cr + 2, testNode.mr,
							testNode.cl - 2, testNode.ml, true, testNode));
					list.add(new Node(testNode.cr, testNode.mr + 2,
							testNode.cl, testNode.ml - 2, true, testNode));
					list.add(new Node(testNode.cr + 1, testNode.mr + 1,
							testNode.cl - 1, testNode.ml - 1, true, testNode));
				}

			} else {
				System.out.println("DONE" + testNode.toString());
			}
		}
	}

	private static boolean isDone(Node n) {

		if (n.cl == 3 && n.ml == 3) {
			return true;
		}
		return false;
	}

}
