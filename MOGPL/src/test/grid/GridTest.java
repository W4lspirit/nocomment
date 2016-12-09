package test.grid;

import dijkstra.Grid;
import main.html.HtmlWriter;
import model.Vehicule;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GridTest {
    @Test
    public void testEqualsTrue() {
        Grid g = new Grid("puzzles/débutant/jam1.txt");
        System.out.println(g);
        Grid f = new Grid(g);
        assertTrue(g.equals(f));
    }

    @Test
    public void testInsertEqualsTrue() {
        Grid g = new Grid("puzzles/débutant/jam1.txt");
        System.out.println(g);
        Grid f = new Grid(g);
        f.insertInGrid(7, 9, "s");
        assertTrue(g.equals(f));
    }

    @Test
    public void testInsertEqualsFalse() {
        Grid g = new Grid("puzzles/débutant/jam1.txt");
        System.out.println(g);
        Grid f = new Grid(g);
        assertTrue(f.getVehicule("t4") != null);
        assertTrue(f.getVehicule("c3") != null);
        assertTrue(f.getVehicule("t3") != null);
        assertTrue(f.getVehicule("c2") != null);
        assertTrue(f.getVehicule("t2") != null);
        assertTrue(f.getVehicule("c1") != null);
        assertTrue(f.getVehicule("t1") != null);
        assertTrue(f.getVehicule("g") != null);

    }

    @Test
    public void testGridVEqualsTrue() {
        Grid g = new Grid("puzzles/d�butant/jam1.txt");
        System.out.println(g);
        System.out.println(HtmlWriter.table(g));
        Grid f = new Grid(g);
        Vehicule kk = g.getVehicule("t4");
        assertTrue(kk.getSize() == 3);
        Vehicule k = f.getVehicule("t4");
        assertTrue(k.getSize() == 3);
        assertTrue(k.equals(kk));

    }

}

