package interfaces;

import algo.Vehicule;
import dijkstra.Grid;


public interface IMovementStrat {
    Grid moveBackward(Grid grid, Vehicule vehicule, int quantity);

    Grid moveForward(Grid grid, Vehicule vehicule, int quantity);
}
