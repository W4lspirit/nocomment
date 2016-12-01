package interfaces;

import dijkstra.Grid;
import model.Vehicule;


public interface IMovementStrat {
    Grid moveBackward(Grid grid, Vehicule vehicule, int quantity);

    Grid moveForward(Grid grid, Vehicule vehicule, int quantity);
}
