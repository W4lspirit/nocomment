package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dijkstra.Edge;
import dijkstra.Graph;
import dijkstra.Grid;

public class DijkstraAlgorithm {
	private boolean isRHM = true;
	private final List<Grid> nodes;
	private final List<Edge> edges;
	private Set<Grid> settledNodes;
	private Set<Grid> unSettledNodes;
	private Map<Grid, Grid> predecessors;
	private Map<Grid, Integer> distance;

	public DijkstraAlgorithm(Graph graph) {
		// create a copy of the array so that we can operate on this array
		this.nodes = new ArrayList<Grid>(graph.getGrids());
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Grid source) {
		settledNodes = new HashSet<Grid>();
		unSettledNodes = new HashSet<Grid>();
		distance = new HashMap<Grid, Integer>();
		predecessors = new HashMap<Grid, Grid>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			// first case frist node
			Grid node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Grid node) {
		List<Grid> adjacentNodes = getNeighbors(node);
		for (Grid target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private int getDistance(Grid node, Grid target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		// TODO gen id check weight
		int weight =target.getWeight();
		if (isRHM) {
			weight = 1;
		}
		edges.add(new Edge("", node, target, weight));
		throw new RuntimeException("Should not happen");
	}

	private List<Grid> getNeighbors(Grid node) {
		List<Grid> neighbors = new ArrayList<Grid>();
		neighbors.addAll(node.getNeighbors());
		return neighbors;
	}

	private Grid getMinimum(Set<Grid> vertexes) {
		Grid minimum = null;
		for (Grid vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Grid vertex) {
		return settledNodes.contains(vertex);
	}

	private int getShortestDistance(Grid destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Grid> getPath(Grid target) {
		LinkedList<Grid> path = new LinkedList<Grid>();
		Grid step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

}