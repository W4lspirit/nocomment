package dijkstra;

public class Edge {
    private static int idGen = 0;
    private final String id;
    private final Grid source;
    private final Grid destination;
    private final int weight;

    public Edge(String id, Grid source, Grid destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Grid getDestination() {
        return destination;
    }

    public Grid getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }

    public static String getNextId() {
        ++idGen;
        return idGen + "";
    }
}
