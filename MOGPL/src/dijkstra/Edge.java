package dijkstra;

public class Edge {
    private static int idGen = 0;
    private final String id;
    private final Grid source;
    private final Grid destination;
    private final int weight;

    public Edge( Grid source, Grid destination, int weight) {
        this.id = getNextId();
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

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Edge other = (Edge) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
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
