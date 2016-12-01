package model;

public class VehiculeFactory {

    /*******************************************************/
    /*-------------------PRINCIPAL METHODS-----------------*/

    /*******************************************************/
    public static Vehicule HVehicule(String s, int row, int col) {
        Vehicule v = new Vehicule(s, row, col);
        v.setStrat(new HStrat());
        return v;

    }

    public static Vehicule VVehicule(String s, int row, int col) {
        Vehicule v = new Vehicule(s, row, col);
        v.setStrat(new VStrat());
        return v;
    }

    public static Vehicule newVehicule(Vehicule v) {
        return new Vehicule(v);
    }

}
