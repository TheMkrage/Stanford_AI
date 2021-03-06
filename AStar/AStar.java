import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar implements Comparator<Board> {

	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;

	public AStar(Board initial, Board goal, AStarHeuristic heur) {
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}

	public void search()
	{
		/* Declare and initialize Frontier and Explored data structures */ 
		/* Put start node in Fringe list Frontier */
		PriorityQueue<Board> frontier = new PriorityQueue<Board>(this);
		PriorityQueue<Board> exploredList = new PriorityQueue<Board>(this);
		frontier.add(initialState);
		boolean poop = true;
		while (!frontier.isEmpty() && poop)
		{
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/
			Board n = frontier.poll();
			exploredList.add(n);	

			if (n.equals(goalState))
			{
				System.out.println("FOUND IT");
				n.print();
				poop = false;
				
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
			}
			//print(n);

			ArrayList<Board> successors = n.getSuccessors();
			for (int i = 0 ;i<successors.size(); i++)
			{
				Board n1 = successors.get(i);
				//print(n1);
				/* if n1 is not already in either Frontier or Explored
				      Compute h(n1), g(n1) = g(n)+c(n, n1), f(n1)=g(n1)+h(n1), place n1 in Frontier
				   if n1 is already in either Frontier or Explored
				   */
				if (frontier.contains(n1) ) {
					for (Board b: frontier) {
						if ( b.equals(n1)) {
							if (n1.getGeneration() < b.getGeneration()) {
								frontier.remove(b);
								frontier.add(n1);
							}
						}
					}
					
				}else if( exploredList.contains(n1)) {
					for (Board b: exploredList) {
						if ( b.equals(n1)) {
							if (n1.getGeneration() < b.getGeneration()) {
								exploredList.remove(b);
								frontier.add(n1);
							}
						}
					}
				}else {
					frontier.add(n1);
				}
				/*
				      if g(n1) is lower for the newly generated n1
				          Replace existing n1 with newly generated g(n1), h(n1), set parent of n1 to n
				          if n1 is in Explored list
				              Move n1 from Explored to Frontier list*/
			}
		}
		System.out.println("No Solution");
	}

	public void print(Board n) {
		n.print();
	}

	// -1 if one comes before two in queue
	public int compare(Board one, Board two) {
		if ((this.heuristic.getCost(one, this.goalState) + one.getGeneration()) < (heuristic
				.getCost(two, this.goalState) + two.getGeneration())) {
			return -1;
		} else if ((heuristic.getCost(one, this.goalState) + one
				.getGeneration()) > (heuristic.getCost(two, this.goalState) + two
				.getGeneration())) {
			return 1;
		} else {
			return 0;
		}
	}
}
