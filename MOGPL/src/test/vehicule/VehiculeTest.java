package test.vehicule;

import org.junit.Test;

import model.Position;
import model.Vehicule;

import static org.junit.Assert.assertTrue;

public class VehiculeTest {

    @Test
    public void testEqualsTrue() {
        Vehicule v = new Vehicule("g", 0, 0);
        Vehicule c = new Vehicule("g", 0, 0);
        assertTrue(v.equals(c));

    }

    @Test
    public void testEqualsFalse() {
        Vehicule v = new Vehicule("g", 0, 0);
        Vehicule d = new Vehicule("t3", 0, 0);

        Vehicule c = new Vehicule("g", 1, 0);
        assertTrue(!v.equals(d));
        assertTrue(!v.equals(c));
    }

    @Test
    public void testDeepCopy() {
        Vehicule v = new Vehicule("g", 0, 0);
        Vehicule c = new Vehicule(v);
        assertTrue(v.equals(c));
        c.setPosition(new Position(1, 0));
        assertTrue(!v.equals(c));

    }
}
