package model;

import dijkstra.Grid;
import interfaces.IMovementStrat;

public class HStrat implements IMovementStrat {
    @Override
    public Grid moveBackward(Grid grid, Vehicule vehicule, int quantity) {
        Grid clone;
        Position p = vehicule.getPosition();
        int front = p.col + vehicule.getSize() - 1;
        //end-1
        int end = p.col - 1;
        int nextEnd = p.col - quantity;
        int nextFront = front - quantity;
        if (nextFront < 0||nextEnd<0) {
            return null;
        }
        for (int k = end; k >= nextEnd; --k) {
            if (!grid.isEmpty(p.row, k)) {
                return null;
            }
        }
        clone = new Grid(grid);
        // clean
        for (int k = front; k > nextFront; --k) {
            clone.insertInGrid(p.row, k, "0");
        }
        // insert
        for (int k = nextFront; k >= nextEnd; --k) {
            clone.insertInGrid(p.row, k, vehicule.getName());
        }
        Vehicule v = clone.getVehicule(vehicule.getName());
        v.setPosition(new Position(p.row, nextEnd));
        clone.setWeight(quantity);
        return clone;
    }

    @Override
    public Grid moveForward(Grid grid, Vehicule vehicule, int quantity) {
        Grid clone;
        Position p = vehicule.getPosition();
        //front+1
        int front = p.col + vehicule.getSize();
        int nextEnd = p.col + quantity;
        int nextfront = front - 1 + quantity;

        if (nextfront >= grid.getNbCol()) {
            return null;
        }
        for (int k = front; k <= nextfront; ++k) {
            if (!grid.isEmpty(p.row, k)) {
                return null;
            }
        }
        clone = new Grid(grid);
        // clean
        for (int k = p.col; k < nextEnd; ++k) {
            clone.insertInGrid(p.row, k, "0");
        }
        // insert
        for (int k = nextEnd; k <= nextfront; ++k) {
            clone.insertInGrid(p.row, k, vehicule.getName());
        }
        Vehicule v = clone.getVehicule(vehicule.getName());
        v.setPosition(new Position(p.row, nextEnd));
        clone.setWeight(quantity);
        return clone;
    }
}
