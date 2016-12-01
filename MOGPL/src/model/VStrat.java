package model;

import dijkstra.Grid;
import interfaces.IMovementStrat;

public class VStrat implements IMovementStrat {
    @Override
    public Grid moveBackward(Grid grid, Vehicule vehicule, int quantity) {
        Grid clone;
        Position p = vehicule.getPosition();
        int front = p.row + vehicule.getSize() - 1;
        //end-1
        int end = p.row - 1;
        int nextEnd = p.row - quantity;
        int nextfront = front - quantity;
        if (nextEnd < 0) {
            return null;
        }
        for (int k = end; k >= nextEnd; --k) {
            if (!grid.isEmpty(k, p.col)) {
                return null;
            }
        }
        clone = new Grid(grid);
        // clean
        for (int k = front; k > nextfront; --k) {
            clone.insertInGrid(k, p.col, "0");
        }
        // insert
        for (int k = nextfront; k >= nextEnd; --k) {
            clone.insertInGrid(k, p.col, vehicule.getName());
        }
        Vehicule v = clone.getVehicule(vehicule.getName());
        v.setPosition(new Position(nextEnd, p.col));
        clone.setWeight(quantity);
        return clone;
    }

    @Override
    public Grid moveForward(Grid grid, Vehicule vehicule, int quantity) {
        Grid clone;
        Position p = vehicule.getPosition();
        //front+1
        int front = p.row + vehicule.getSize();
        int nextEnd = p.row + quantity;
        int nextfront = front - 1 + quantity;

        if (nextfront >= grid.getNbCol()) {
            return null;
        }
        for (int k = front; k <= nextfront; ++k) {
            if (!grid.isEmpty(k, p.col)) {
                return null;
            }
        }
        clone = new Grid(grid);
        // clean
        for (int k = p.row; k < nextEnd; ++k) {
            clone.insertInGrid(k, p.col, "0");
        }
        // insert
        for (int k = nextEnd; k <= nextfront; ++k) {
            clone.insertInGrid(k, p.col, vehicule.getName());
        }
        Vehicule v = clone.getVehicule(vehicule.getName());
        v.setPosition(new Position(nextEnd, p.col));
clone.setWeight(quantity);
        return clone;
    }
}
