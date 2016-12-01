package dijkstra;

import model.TimeException;
import model.Timer;

import java.util.*;
import java.util.stream.Collectors;

public class DijkstraAlgorithm {
    private boolean isRHM = true;
    private List<Edge> edges;
    private Set<Grid> settledNodes;
    private Set<Grid> unSettledNodes;
    private Map<Grid, Grid> predecessors;
    private Map<Grid, Integer> distance;
    private List<Grid> finalDestinationGrid;

    public DijkstraAlgorithm(boolean isRHM) {
        this.isRHM = isRHM;
        edges = new ArrayList<>();
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        finalDestinationGrid = new ArrayList<>();
    }

    public void execute(Grid source) {
        Timer.start();
        distance.put(source, 0);
        unSettledNodes.add(source);

        while (unSettledNodes.size() > 0) {
            // first case first node
            // debug purpose System.out.println(unSettledNodes.size());
            try {
                Grid node = getMinimum(unSettledNodes);
                settledNodes.add(node);
                unSettledNodes.remove(node);
                findMinimalDistances(node);
            } catch (TimeException e) {
                break;
            }

        }
    }

    private void findMinimalDistances(Grid node) throws TimeException {
        Timer.check();
        List<Grid> adjacentNodes = getNeighbors(node);
        adjacentNodes.stream()
                .filter(target -> getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target))
                .forEach(target -> {
                    distance.put(target, getShortestDistance(node) + getDistance(node, target));
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                });

    }

    private int getDistance(Grid node, Grid target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)
                    || edge.getSource().equals(target) && edge.getDestination().equals(node)) {
                return edge.getWeight();
            }
        }

        int weight = target.getWeight();
        if (isRHM) {
            weight = 1;
        }
        edges.add(new Edge(node, target, weight));
        edges.add(new Edge(target, node, weight));
        return weight;
    }

    private List<Grid> getNeighbors(Grid node) {
        List<Grid> neighborsTemp = edges.stream()
                .filter(edge -> edge.getSource().equals(node) && !isSettled(edge.getDestination()))
                .map(Edge::getDestination).collect(Collectors.toList());

        node.getNeighbors().forEach(g -> {
            int index = neighborsTemp.indexOf(g);
            if (index < 0) {
                if (g.isSolution() && !finalDestinationGrid.contains(g)) {
                    finalDestinationGrid.add(g);
                }
                neighborsTemp.add(g);
            } else {
                neighborsTemp.get(index).setWeight(g.getWeight());
            }
        });

        return neighborsTemp;
    }

    private Grid getMinimum(Set<Grid> grids) throws TimeException {
        Timer.check();
        Grid minimum = null;
        for (Grid grid : grids) {
            if (minimum == null) {
                minimum = grid;
            } else {
                if (getShortestDistance(grid) < getShortestDistance(minimum)) {
                    minimum = grid;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Grid grid) {
        return settledNodes.contains(grid);
    }

    private int getShortestDistance(Grid destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Grid> getPath(Grid target) {
        LinkedList<Grid> path = new LinkedList<>();
        Grid step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    public LinkedList<Grid> getShortestPath() {
        List<LinkedList<Grid>> linkedLists = new ArrayList<>();
        finalDestinationGrid.forEach(g -> linkedLists.add(getPath(g)));
        int minSize = Integer.MAX_VALUE;
        LinkedList<Grid> list = null;
        for (LinkedList<Grid> g : linkedLists) {
            if (list == null) {
                minSize = g.size();
                list = g;
            } else {
                if (g.size() < minSize) {
                    minSize = g.size();
                    list = g;

                }
            }
        }
        return list;
    }

    public int getValue(LinkedList<Grid> path, boolean isRHM2) {
        if (isRHM2) {
            return path.size();
        }
        int max = 0;
        for (int i = 0; i < path.size() - 1; ++i) {
            Grid v, c;
            v = path.get(i);
            c = path.get(i + 1);
            int index = edges.indexOf(new Edge(c, v, 0));
            if (index > 0) {
                max += edges.get(index).getWeight();
            }

        }

        return max;
    }

    public int getNbSolutions() {
        return finalDestinationGrid.size();
    }

}