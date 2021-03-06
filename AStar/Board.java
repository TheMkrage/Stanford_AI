import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	public static int rows = 3;
	public static int columns = 3;
	private Board parent = null; /* only initial board's parent is null */
	public int[][] tiles;
	private int gen = 1;

	public Board(int[][] cells, Board parent) // Populate states
	{
		this.parent = parent;
		tiles = new int[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				tiles[i][j] = cells[i][j];
			}
	}

	public boolean equals(Object x) // Can be used for repeated state checking
	{
		Board another = (Board) x;
		if (columns != another.columns || rows != another.rows)
			return false;
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}

	public ArrayList<Board> getSuccessors() // Use cyclic ordering for expanding
											// nodes - Right, Down, Left, Up
	{
		ArrayList<Board> arr = new ArrayList<Board>();
		Dimension zero = findZero();
		Board board;
		// System.out.println(zero);
		int[][] newTiles;

		// Move 0 down
		if (zero.width + 1 != columns) {
			newTiles = new int[rows][columns];
			for (int x = 0; x < rows; x++) {
				for (int i = 0; i < columns; i++) {
					newTiles[x][i] = tiles[x][i];
				}
			}
			newTiles[zero.width][zero.height] = newTiles[zero.width + 1][zero.height];
			newTiles[zero.width + 1][zero.height] = 0;
			board = new Board(newTiles, this);
			arr.add(board);
		}

		// Move 0 to UP
		if (zero.width - 1 != -1) {
			newTiles = new int[rows][columns];
			for (int x = 0; x < rows; x++) {
				for (int i = 0; i < columns; i++) {
					newTiles[x][i] = tiles[x][i];
				}
			}
			newTiles[zero.width][zero.height] = newTiles[zero.width - 1][zero.height];
			newTiles[zero.width - 1][zero.height] = 0;

			board = new Board(newTiles, this);
			arr.add(board);
		}

		// Move 0 to Left
		if (zero.height - 1 != -1) {
			newTiles = new int[rows][columns];
			for (int x = 0; x < rows; x++) {
				for (int i = 0; i < columns; i++) {
					newTiles[x][i] = tiles[x][i];
				}
			}
			newTiles[zero.width][zero.height] = newTiles[zero.width][zero.height - 1];
			newTiles[zero.width][zero.height - 1] = 0;

			board = new Board(newTiles, this);
			arr.add(board);
		}
		// Move 0 to right
		//System.out.println(zero.height);
		if (zero.height + 1 != columns) {
			newTiles = new int[rows][columns];
			for (int x = 0; x < rows; x++) {
				for (int i = 0; i < columns; i++) {
					newTiles[x][i] = tiles[x][i];
				}
			}
			newTiles[zero.width][zero.height] = newTiles[zero.width][zero.height + 1];
			newTiles[zero.width][zero.height + 1] = 0;

			board = new Board(newTiles, this);
			arr.add(board);
		}
		return arr;
	}

	public void print() {
		if (parent != null) {
			System.out.println("PARENT: ");
			parent.print();
		}
		System.out.println("ZERO: " + findZero());
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j > 0)
					System.out.print("\t");
				System.out.print(tiles[i][j]);
			}
			System.out.println();
		}
	}

	public void setParent(Board parentBoard) {
		parent = parentBoard;
		gen++;
		parent.setGen(gen);
	}

	public Board getParent() {
		return parent;
	}

	public Dimension findZero() {
		for (int i = 0; i < rows; i++) {
			for (int x = 0; x < columns; x++) {
				if (tiles[i][x] == 0)
					return new Dimension(i, x);
			}
		}
		return null;
	}

	public int getGeneration() {
		return gen;
	}

	public void setGen(int gen2) {
		this.gen = gen2;
	}

}
