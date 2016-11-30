package interfaces;

import algo.Vehicule;
import dijkstra.Grid;

/**
 * Created by Ismael on 26/11/2016.
 */
public interface IMovementStrat {
    Grid moveBackward(Grid grid, Vehicule vehicule, int quantity);

    Grid moveForward(Grid grid, Vehicule vehicule, int quantity);
}
