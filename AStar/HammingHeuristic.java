
public class HammingHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		int counter = 0;
		for (int i = 0; i < state.rows; i++) {
			for (int x = 0; x < state.columns; x++) {
				if( state.tiles[i][x] != goalState.tiles[i][x]) {
					counter++;
				}
			}
		}
		return counter;
	}
}

