package algo;

import dijkstra.Grid;

public class Main {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Invalid argument");
			return;
		}
		// generer un nom id
		Grid grid = new Grid(args[0]);
		boolean isRHM = Boolean.getBoolean(args[1]);
	}

}
