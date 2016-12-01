package test.grid;

import org.junit.Test;

import dijkstra.Grid;

public class GridPrintingTest {

    @Test
    public void test() {
        Grid g = new Grid("puzzles/débutant/jam1.txt");
        System.out.println(g);
        Grid f = new Grid(g);
        f.test();
        System.out.println(f);
        System.out.println(g);
    }
}

