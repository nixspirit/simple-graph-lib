package org.graphs.simple;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * It is a helper class which represents graphs or its parts as strings.
 */
@Slf4j
final class GraphVisualizer {

    private GraphVisualizer() {

    }

    static <T, E extends Edge<T>> String asString(Graph<T, E> g) {

        if (g == null || g.getVertices() == null || g.getVertices().isEmpty())
            return "";

        boolean first = true;
        StringBuilder s = new StringBuilder();

        List<Vertex<T>> vertices = g.getVertices();
        vertices.sort(Comparator.comparingInt(Vertex::hashCode));

        for (Vertex<T> vertex : vertices) {

            if (!first)
                s.append("\n");

            s.append(vertex.getValue()).append("->");

            List<E> adjacent = g.getAdjacent(vertex);
            adjacent.sort(Comparator.comparing(Edge::hashCode));

            for (E edge : adjacent) {
                s.append(edge.getTo().getValue());
                if (edge instanceof WeightedEdge)
                    s.append("(w").append(((WeightedEdge<T>) edge).getWeight()).append(")");
                s.append(", ");
            }

            first = false;
        }
        return s.toString();
    }

    public static <T> String pathAsString(Collection<Edge<T>> path) {
        if (path == null || path.isEmpty())
            return "";

        boolean first = true;
        StringBuilder s = new StringBuilder();
        for (Edge<T> e : path) {
            if (first) {
                s.append(e.getFrom().getValue());
                first = false;
            }

            s.append("->").append(e.getTo().getValue());
        }
        return s.toString();
    }
}
