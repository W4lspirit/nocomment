package gridTest;

import org.junit.Test;

import dijkstra.Grid;

public class GridPrintingTest {

	@Test
	public void test() {
		Grid g = new Grid("puzzles/débutant/jam1.txt");
		g.print();
		Grid f = (Grid) g.clone();
		System.out.println();
		f.test();
		f.print();
	}
}

