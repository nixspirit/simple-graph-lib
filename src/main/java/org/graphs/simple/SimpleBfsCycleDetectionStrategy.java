package org.graphs.simple;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.graphs.simple.TraverseState.*;

/**
 * Implements searching for a cycle using BFS traverse.
 */
class SimpleBfsCycleDetectionStrategy implements CycleDetectionStrategy {

    @Override
    public <E extends Edge<T>, T> boolean detectCycle(Graph<T, E> g) {
        if (g == null || g.isEmpty())
            return false;

        Map<Vertex<T>, TraverseState> states = new HashMap<>();
        for (Vertex<T> vertex : g.getVertices())
            states.putIfAbsent(vertex, UNVISITED);

        for (Vertex<T> vertex : g.getVertices()) {
            if (states.get(vertex) == UNVISITED && hasCycle(g, vertex, states))
                return true;
        }

        return false;
    }

    private <E extends Edge<T>, T> boolean hasCycle(Graph<T, E> g, Vertex<T> vertex, Map<Vertex<T>, TraverseState> states) {

        Queue<Vertex<T>> q = new LinkedList<>();
        q.add(vertex);
        states.put(vertex, VISITING);

        while (!q.isEmpty()) {

            Vertex<T> current = q.remove();

            for (E edges : g.getAdjacent(current)) {

                Vertex<T> neighbour = edges.getTo();
                if (states.get(neighbour) == UNVISITED) {
                    states.put(neighbour, VISITING);
                    q.add(neighbour);

                } else if (states.get(neighbour) == VISITING)
                    return true;
            }

            states.put(current, VISITED);
        }

        return false;
    }
}
