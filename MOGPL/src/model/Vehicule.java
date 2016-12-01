package model;

import java.util.ArrayList;
import java.util.List;

import dijkstra.Grid;
import interfaces.IMovementStrat;


public class Vehicule {
    private static final int _TRUCK_SIZE = 3;
    private static final int _CAR_SIZE = 2;

    /*******************************************************/
    /*-------------------PARAMETERS------------------------*/
    /*******************************************************/
    private IMovementStrat strat;
    private int size;
    private String name;
    private Position position;

    public Vehicule(String s, int row, int col) {
        name = s;
        position = new Position(row, col);
        size = getSize(name);
    }


    public Vehicule(Vehicule v) {
        this.name = v.name;
        this.size = v.size;
        this.position = new Position(v.position);
        this.strat = v.strat;
    }

    /*******************************************************/
    /*-------------------PRINCIPAL METHODS-----------------*/

    /*******************************************************/
    private Grid moveBackward(Grid grid, int quantity) {
        return strat.moveBackward(grid, this, quantity);
    }

    private Grid moveForward(Grid grid, int quantity) {
        return strat.moveForward(grid, this, quantity);
    }

    // TODO change SET
    public  List<Grid> getVehiculeDeplacements(Grid grid) {
        List<Grid> deplacements = new ArrayList<>();
        int maxQuantity = grid.getNbRow() - size;
        for (int i = 1; i <= maxQuantity; ++i) {
            Grid forwardGrid = moveForward(grid, i);
            Grid backwardGrid = moveBackward(grid, i);

            if (forwardGrid != null)
                deplacements.add(forwardGrid);
            if (backwardGrid != null)
                deplacements.add(backwardGrid);
            if (forwardGrid == backwardGrid)
                break;
        }

        return deplacements;
    }

    /*******************************************************/
    /*-------------------USEFUL METHODS--------------------*/

    /*******************************************************/


    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + size;
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicule other = (Vehicule) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        return size == other.size;
    }

    /*******************************************************/
	/*-------------------GETTER/SETTER---------------------*/

    /*******************************************************/
    private static int getSize(String vehiculeName) {
        int size = 0;
        switch (vehiculeName.charAt(0)) {
            case 't':
                size = _TRUCK_SIZE;
                break;

            case 'c':
                size = _CAR_SIZE;
                break;

            case 'g':
                size = _CAR_SIZE;
                break;

            default:
                break;
        }
        return size;
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
     * @return the name
     */
    public String getName() {
        return name;
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
