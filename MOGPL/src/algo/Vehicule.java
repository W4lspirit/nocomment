package algo;

import dijkstra.Grid;
import interfaces.IMovementStrat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ismael on 26/11/2016.
 */
public class Vehicule implements Cloneable {
    private IMovementStrat strat;
    private int size;
    private String name;
    private Position position;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicule) {
            Vehicule vehicule = (Vehicule) obj;
            return name.equals(vehicule.name) && position.equals(vehicule.position);
        }

        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Vehicule clone = new Vehicule();
        clone.name = name;
        clone.size = size;
        clone.position = (Position) position.clone();
        clone.strat = strat;
        return clone;
    }

    public Collection<? extends Grid> getVehiculeDeplacements(Grid grid) {
        Set<Grid> d = new HashSet<>();
        int maxQuantity = grid.getNbRow() - size;
        for (int i = 1; i <= maxQuantity; ++i) {
            Grid forwardGrid = moveForward(grid, i);
            Grid backwardGrid = moveBackward(grid, i);


            if (forwardGrid != null) d.add(forwardGrid);
            if (backwardGrid != null) d.add(backwardGrid);
            if (forwardGrid == backwardGrid) break;
        }

        return d;
    }

    private Grid moveBackward(Grid grid, int quantity) {
        Grid backwardGrid = null;
        //TODO test if can move
        if (true) {
            backwardGrid = strat.moveBackward(grid, this, quantity);
        }
        return backwardGrid;
    }

    private Grid moveForward(Grid grid, int quantity) {

        return strat.moveForward(grid, this, quantity);

    }

    /*******************************************************/
    /*-------------------PARAMETERS------------------------*/
    /*******************************************************/
    /*******************************************************/
    /*-------------------PRINCIPAL METHODS-----------------*/
    /*******************************************************/
    /*******************************************************/
    /*-------------------USEFUL METHODS--------------------*/
    /*******************************************************/

    /*******************************************************/
    /*-------------------GETTER/SETTER---------------------*/
    /*******************************************************/

    /**
     * @return the strat
     */
    public IMovementStrat getStrat() {
        return strat;
    }

    /**
     * @param strat the strat to set
     */
    public void setStrat(IMovementStrat strat) {
        this.strat = strat;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }


}
