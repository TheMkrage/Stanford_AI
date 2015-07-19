
public class Node {
	int ml, mr, cl, cr;
	boolean isOnRightSide;
	Node parentNode;
	public Node(int cr, int mr, int cl, int ml, boolean b, Node parent) {
		this.ml = ml;
		this.mr = mr;
		this.cl = cl;
		this.cr = cr;
		this.isOnRightSide = b;
		this.parentNode = parent;
	}
	
	public String toString() {
		if (parentNode != null) {
			return "LEFT: C: " + cl + " M: " + ml + " RIGHT: C: " + cr + " M: " + mr + "\n" + parentNode.toString();
		}else {
			return "LEFT: C: " + cl + " M: " + ml + " RIGHT: C: " + cr + " M: " + mr + "\n";
		}
	}
	
	
	
}
