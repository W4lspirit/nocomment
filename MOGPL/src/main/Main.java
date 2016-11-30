package main;

import dijkstra.DijkstraAlgorithm;
import dijkstra.Grid;

public class Main {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Invalid argument");
			return;
		}
		// generer un nom id
		Grid grid = new Grid(args[0]);
		if (!grid.checkNonSense()) {
			boolean isRHM = Boolean.getBoolean(args[1]);
			DijkstraAlgorithm algorithm = new DijkstraAlgorithm(isRHM);
			algorithm.execute(grid);
		} else {
			System.err.println("Incorrect File");
		}

	}

}
