package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import dijkstra.DijkstraAlgorithm;
import dijkstra.Grid;
import main.html.HtmlWriter;

public class Main {
	private static List<String> list = new ArrayList<>();
	private static boolean debug = true;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Invalid argument");
			return;
		}

		if (args.length == 3) {
			launchDijkstra(args[0], Boolean.valueOf(args[1]), Boolean.valueOf(args[2]));
		}
		if (args.length == 1) {
			testAll();
		}

	}

	public static void testAll() {
		try (Stream<Path> paths = Files.walk(Paths.get("puzzles"))) {
			List<String> files = new ArrayList<>();
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					files.add(filePath.toString());
				}
			});
			files.forEach(f -> {
				debug();
				launchDijkstra(f, true, false);
				launchDijkstra(f, false, false);
			});
			writeStat();
		} catch (IOException e) {
			System.err.println("Error while retrieving all the file");
		}

	}

	private static void debug() {
		debug = false;

	}

	private static void launchDijkstra(String f, boolean rhm, boolean write) {
		Grid grid = new Grid(f);
		if (!grid.checkNonSense()) {

			DijkstraAlgorithm algorithm = new DijkstraAlgorithm(rhm);
			long start = System.currentTimeMillis();
			algorithm.execute(grid);
			long end = System.currentTimeMillis();
			

			int nbSolutions = algorithm.getNbSolutions();
			int value = algorithm.getValue(path, rhm);
			long temps = end - start;
			LinkedList<Grid> path = writeShortestPath(f, algorithm, write);
			append("fichier " + f + " nbSolutions " + nbSolutions + " RHM " + rhm + " Valeur " + value + " Temps "
					+ temps + "\n");
		} else {
			System.err.println("Incorrect File");
		}
	}

	private static void append(String e) {
		list.add(e);
	}

	private static void writeStat() {
		try {

			String fResName = "puzzles/sol";
			File file = new File(fResName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			list.forEach(p -> {
				try {
					bw.write(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static LinkedList<Grid> writeShortestPath(String path, DijkstraAlgorithm algorithm, boolean write, boolean rhm, String f, String nbSolutions, String value, String temps) {
		LinkedList<Grid> paths = algorithm.getShortestPath();
		if (write) {
			try {

				File file = new File(path + "_sol.html");

				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(HtmlWriter.start());
				bw.write("<h2>");
				bw.write("fichier " + f + " nbSolutions " + nbSolutions + " RHM " + rhm + " Valeur " + value + " Temps "
						+ temps + "\n");
				bw.write("</h2>");
				paths.forEach(p -> {

					try {
						bw.write(HtmlWriter.newTable());
						bw.write(HtmlWriter.table(p));
						bw.write(HtmlWriter.endTable());
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			
				bw.write(HtmlWriter.stop());
				bw.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (debug) {
				paths.forEach(System.out::println);
			}
		}
		return paths;
	}
}
