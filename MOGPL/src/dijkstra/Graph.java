package dijkstra;

import java.util.List;

public class Graph {
        private final List<Grid> vertexes;
        private final List<Edge> edges;

        public Graph(List<Grid> vertexes, List<Edge> edges) {
                this.vertexes = vertexes;
                this.edges = edges;
        }

        public List<Grid> getGrids() {
                return vertexes;
        }

        public List<Edge> getEdges() {
                return edges;
        }



}