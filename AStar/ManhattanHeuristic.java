
public class ManhattanHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		int counter = 0;
		
		// Loop to find each tiles heuristic
		for (int i = 0; i < state.rows; i++) {
			for (int x = 0; x < state.columns; x++) {
				int currentNumber = state.tiles[i][x];
				boolean found = false;
				// Loop to find where that same num is located on goal state
				for (int i2 = 0; i2 < state.rows && !found; i2++) {
					for (int x2 = 0; x2 < state.columns && !found; x2++) {
						if ( goalState.tiles[i2][x2] == currentNumber ) {
							found = true;
							counter += Math.abs(i2 - i) + Math.abs(x2 - x);
						}
					}
				}
			}
		}
		return counter;
	}
}

