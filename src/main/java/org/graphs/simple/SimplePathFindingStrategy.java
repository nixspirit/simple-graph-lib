package org.graphs.simple;

import java.util.*;

import static org.graphs.simple.TraverseState.*;

/**
 * Implements searching for a path between two nodes using DFS traverse.
 *
 * @param <T> type of Vertex
 * @param <E> type of Edge
 */
class SimplePathFindingStrategy<T, E extends Edge<T>> implements PathFinderStrategy<T, E> {

    @Override
    public Collection<E> findPath(Graph<T, E> g, T start, T end) {
        if (g == null || g.isEmpty())
            return Collections.emptyList();

        Map<Vertex<T>, TraverseState> states = new HashMap<>();
        List<Vertex<T>> vertices = g.getVertices();

        for (Vertex<T> vertex : vertices)
            states.putIfAbsent(vertex, UNVISITED);

        List<E> edges = new ArrayList<>();
        for (E vertex : g.getAdjacent(start)) {
            if (states.get(vertex.getTo()) == UNVISITED && dfsTraverse(g, vertex, end, states, edges))
                break;
        }

        return edges;
    }

    private boolean dfsTraverse(Graph<T, E> g, E edge, T target, Map<Vertex<T>, TraverseState> states, List<E> result) {
        states.put(edge.getTo(), VISITING);
        result.add(edge);

        if (target.equals(edge.getTo().getValue()))
            return true;

        for (E adjacent : g.getAdjacent(edge.getTo())) {
            if (states.get(adjacent.getTo()) == UNVISITED && dfsTraverse(g, adjacent, target, states, result))
                return true;
        }

        result.remove(result.size() - 1);
        states.put(edge.getTo(), VISITED);
        return false;
    }
}
