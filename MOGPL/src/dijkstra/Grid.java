package dijkstra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import algo.Position;
import algo.Vehicule;

public class Grid implements Cloneable {
	/*******************************************************/
	/*-------------------PARAMETERS------------------------*/
	/*******************************************************/
	private static final String SEPARATOR = " ";
	

	private List<List<String>> grid;
	int nbRow, nbCol, weight;

	/*******************************************************/
	/*-------------------PRINCIPAL METHODS-----------------*/

	/*******************************************************/

	public Grid(String source) {
		try {
			FileReader reader = new FileReader(source);
			readHeader(reader);
			FileReader reader1 = new FileReader(source);
			readGrid(reader1);

		} catch (FileNotFoundException e) {
			System.out.println("Grid.Grid() File exception");
		}

	}

	/*******************************************************/
	/*-------------------TODO -----------------*/
	/*******************************************************/
	private Map<String, Vehicule> nameToVehicule;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
	
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	//
	private void moveVehiculeToPosition(String name, int x, int y) {
		// check if possible othewise throw exception
		nameToVehicule.get(name).setPosition(new Position(x, y));
		//

	}

	public List<Grid> getNeighbors() {
		List<Grid> d = new ArrayList<>();
		nameToVehicule.values().forEach(v -> d.addAll(v.getVehiculeDeplacements(this)));
		return d;
	}

	/*******************************************************/
	/*-------------------USEFUL METHODS--------------------*/
	/*******************************************************/

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

	// https://github.com/olbat/rushhour/blob/master/src/GUI/RushHourFrame.java
	public void print() {
		grid.forEach(row -> {
			row.forEach(c -> System.out.print(String.format("%11s", c)));
			System.out.println();
		});
	}

	public void test() {
		grid.get(0).set(0, "ab");
	}

	@Override
	public Object clone() {
		// ... TODO
		Grid clone = new Grid();
		clone.nbCol = nbCol;
		clone.nbRow = nbRow;
		clone.grid = grid.stream()
				.map(r -> r.stream().map(String::new).collect(Collectors.toCollection(ArrayList::new)))
				.collect(Collectors.toCollection(ArrayList::new));
		return clone;
	}

	/*******************************************************/
	/*-------------------GETTER/SETTER---------------------*/

	/*******************************************************/

	public Vehicule getVehicule(String name) {
		return nameToVehicule.get(name);

	}

	private Position getVehiculePosition(String name) {
		return nameToVehicule.get(name).getPosition();

	}

	private List<Vehicule> getVehicules() {
		return (List<Vehicule>) nameToVehicule.values();
	}

	public boolean isEmpty(int row, int col) {
		return grid.get(row).get(col).equals("0");
	}

	public boolean isTruck(int row, int col) {
		String truck = grid.get(row).get(col);
		return truck.length() >= 2 && truck.charAt(0) == 't';
	}

	public boolean isCar(int row, int col) {
		String car = grid.get(row).get(col);
		return car.length() >= 2 && car.charAt(0) == 'c';
	}

	public String getVal(int row, int col) {
		return grid.get(row).get(col);
	}

	public int getNbRow() {
		return nbRow;
	}

	public int getNbCol() {
		return nbCol;
	}

	public void insert(int row, int col, String s) {
		grid.get(row).set(col, s);
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

}
