package dijkstra;

import model.Position;
import model.Vehicule;
import model.VehiculeFactory;

import java.awt.Graphics;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Grid implements Cloneable {
	/*******************************************************/
	/*-------------------PARAMETERS------------------------*/
	/*******************************************************/
	private static final String _G = "g";
	private static final String SEPARATOR = " ";
	private static final Position finalPosition = new Position(2, 4);

	private int nbRow, nbCol, weight;

	private List<List<String>> grid;

	private Map<String, Vehicule> nameToVehicule;

	/*******************************************************/
	/*-------------------CONSTRUCTORS----------------------*/

	/*******************************************************/

	public Grid(Grid g) {
		this.nbCol = g.nbCol;
		this.nbRow = g.nbRow;
		this.grid = g.grid.stream()
				.map(r -> r.stream().map(String::new).collect(Collectors.toCollection(ArrayList::new)))
				.collect(Collectors.toCollection(ArrayList::new));
		this.nameToVehicule = new HashMap<>();
		g.nameToVehicule.forEach((s, vehicule) -> this.nameToVehicule.put(s, VehiculeFactory.newVehicule(vehicule)));
	}

	public Grid(String source) {
		nameToVehicule = new HashMap<>();
		try {
			FileReader reader = new FileReader(source);
			readHeader(reader);
			FileReader reader1 = new FileReader(source);
			readGrid(reader1);
			initNameToVehiculeMap();
		} catch (FileNotFoundException e) {
			System.out.println("Grid.Grid() File exception");
		}
	}

	/*******************************************************/
	/*-------------------PRINCIPAL METHODS-----------------*/

	/*******************************************************/

	public List<Grid> getNeighbors() {
		List<Grid> n = new ArrayList<>();
		nameToVehicule.values().forEach(v -> n.addAll(v.getVehiculeDeplacements(this)));
		return n;
	}

	private void readHeader(Reader source) {
		try (BufferedReader reader = new BufferedReader(source)) {
			List<String> size = reader.lines().findFirst().map(line -> Arrays.asList(line.split(SEPARATOR))).get();
			if (size.size() == 2) {
				nbRow = Integer.valueOf(size.get(0));
				nbCol = Integer.valueOf(size.get(1));
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private void readGrid(Reader source) {
		try (BufferedReader reader = new BufferedReader(source)) {
			grid = reader.lines().skip(1).map(line -> Arrays.asList(line.split(SEPARATOR)))
					.collect(Collectors.toList());

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	/*******************************************************/
	/*-------------------USEFUL METHODS--------------------*/

	/*******************************************************/
	private void initNameToVehiculeMap() {
		for (int r = 0; r < nbRow; r++) {
			for (int c = 0; c < nbCol; c++) {
				if (!isEmpty(r, c)) {
					Vehicule v = null;
					String s = getInGrid(r, c);
					if (nameToVehicule.containsKey(s)) {
						continue;
					}
					String right = getInGrid(r, c + 1);
					String down = getInGrid(r + 1, c);

					if (s.equals(right)) {
						v = VehiculeFactory.HVehicule(s, r, c);
					}
					if (s.equals(down)) {
						v = VehiculeFactory.VVehicule(s, r, c);
					}

					nameToVehicule.put(s, v);
				}

			}
		}
	}

	/**
	 * Check if the grid has been correctly init
	 *
	 * @return true if the grid has some issue false otherwise
	 */
	public boolean checkNonSense() {
		Vehicule g = nameToVehicule.get(_G);
		return g == null || g.getPosition().row != 2;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grid == null) ? 0 : grid.hashCode());
		result = prime * result + nbCol;
		result = prime * result + nbRow;
		result = prime * result + weight;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
		if (grid == null) {
			if (other.grid != null)
				return false;
		} else {
			for (Map.Entry<String, Vehicule> e : nameToVehicule.entrySet()) {
				if (!e.getValue().getPosition().equals(other.getVehicule(e.getKey()).getPosition())) {
					return false;
				}
			}

		}

		return true;
	}

	public String toStringLight() {
		StringBuilder stringBuilder = new StringBuilder();
		grid.forEach(row -> {
			row.forEach(c -> stringBuilder.append(String.format("%11s", c)));
			stringBuilder.append("\n");
		});

		return stringBuilder.toString();
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		grid.forEach(row -> {
			row.forEach(c -> {
				stringBuilder.append(c);
				stringBuilder.append(" ");
			});
			stringBuilder.trimToSize();
			stringBuilder.append("\n");
		});

		return stringBuilder.toString();
	}

	public void test() {
		grid.get(0).set(0, "ab");
	}

	/*******************************************************/
	/*-------------------GETTER/SETTER---------------------*/

	/*******************************************************/
	/**
	 * Overwrite the value at the pos @row @col with @s
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 * @param s
	 *            the String
	 */
	public void insertInGrid(int row, int col, String s) {
		if (row < nbRow && col < nbCol) {
			grid.get(row).set(col, s);
		}
	}

	/**
	 * Get the value at the pos @row @col
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 * @return s the String
	 */
	private String getInGrid(int row, int col) {
		if (row >= nbRow || col >= nbCol) {
			return "";
		}
		return grid.get(row).get(col);
	}

	/**
	 * Get vehicule by name
	 *
	 * @param name
	 *            vehicule name
	 * @return the vehicule
	 */
	public Vehicule getVehicule(String name) {
		return nameToVehicule.get(name);

	}

	public boolean isEmpty(int row, int col) {
		return grid.get(row).get(col).equals("0");
	}

	/**
	 * @return the nbRow
	 */
	public int getNbRow() {
		return nbRow;
	}

	/**
	 * @return the nbCol
	 */
	public int getNbCol() {
		return nbCol;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return true if the g_car is at the finalPosition
	 */
	public boolean isSolution() {
		Vehicule v = nameToVehicule.get(_G);
		return v.getPosition().equals(finalPosition);

	}

	public List<List<String>> getGrid() {
		return grid;

	}

}
