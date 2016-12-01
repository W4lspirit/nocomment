package main;

import dijkstra.DijkstraAlgorithm;
import dijkstra.Grid;

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

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid argument");
            return;
        }
        // generer un nom id
        launchDijkstra(args[0], Boolean.valueOf(args[1]));

    }

    public void m() {
        try (Stream<Path> paths = Files.walk(Paths.get("puzzles"))) {
            List<String> files = new ArrayList<>();
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(filePath.toString());
                }
            });
            files.forEach(f -> {
                launchDijkstra(f, true);
                launchDijkstra(f, false);
            });
        } catch (IOException e) {
            System.err.println("Error while retrieving all the file");
        }

    }

    private static void launchDijkstra(String f, boolean b) {
        Grid grid = new Grid(f);
        if (!grid.checkNonSense()) {

            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(b);
            long start = System.currentTimeMillis();
            algorithm.execute(grid);
            long end = System.currentTimeMillis();
            LinkedList<Grid> path = write(f, algorithm);

            int nbSolutions = algorithm.getNbSolutions();
            int value = algorithm.getValue(path, b);
            long temps = end - start;
            int nbConfR = 0;
            System.out.println("fichier " + f + " nbSolutions " + nbSolutions + " RHC " + b + " Valeur "
                    + value + " Temps " + temps + " nbConfR " + nbConfR);
        } else {
            System.err.println("Incorrect File");
        }
    }

    private static LinkedList<Grid> printShortestPath(DijkstraAlgorithm algorithm) {
        LinkedList<Grid> path = algorithm.getShortestPath();
        path.forEach(System.out::println);
        return path;
    }

    private static LinkedList<Grid> write(String path, DijkstraAlgorithm algorithm) {
        LinkedList<Grid> paths = algorithm.getShortestPath();
        try {

            File file = new File(path + "_sol");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            paths.forEach(p -> {
                try {
                    bw.write(p.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }
}
