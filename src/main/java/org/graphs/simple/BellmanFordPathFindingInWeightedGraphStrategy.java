package org.graphs.simple;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.graphs.simple.TraverseState.UNVISITED;
import static org.graphs.simple.TraverseState.VISITED;

/**
 * An implementation of the Bellman–Ford algorithm using BFS and a queue.
 * Negative cycles detection is simplified as follows:
 * - it checks if we loop V*V times then possible there is a negative cycle.
 *
 * @param <T> Vertex type
 * @param <E> Edge's type
 */
class BellmanFordPathFindingInWeightedGraphStrategy<T, E extends WeightedEdge<T>> implements PathFinderStrategy<T, WeightedEdge<T>> {

    @Override
    public Collection<WeightedEdge<T>> findPath(Graph<T, WeightedEdge<T>> g, T start, T end) {
        if (g == null || g.isEmpty())
            return Collections.emptyList();

        List<Vertex<T>> vertices = g.getVertices();
        Vertex<T> startVertex = null;
        Vertex<T> endVertex = null;

        // Initialize states and start/end vertices
        Map<Vertex<T>, TraverseState> states = new HashMap<>();
        for (Vertex<T> vertex : vertices) {

            if (vertex.getValue().equals(start))
                startVertex = vertex;

            if (vertex.getValue().equals(end))
                endVertex = vertex;

            states.putIfAbsent(vertex, UNVISITED);
        }

        // if we haven't found start or end vertices then there is no path for sure
        if (startVertex == null || endVertex == null)
            return Collections.emptyList();

        // initialize all the vertices with the default infinite distance
        Map<Vertex<T>, Integer> distances = new HashMap<>();
        for (Vertex<T> vertex : vertices)
            distances.putIfAbsent(vertex, Integer.MAX_VALUE);

        // set 0 distance at the start
        distances.put(startVertex, 0);

        // track the last min edge
        Map<Vertex<T>, WeightedEdge<T>> minEdges = new HashMap<>();
        // when the currentRound becomes equal to the maxRounds the loop will be over
        int maxRounds = vertices.size() * (vertices.size() - 1);
        AtomicInteger currentRound = new AtomicInteger(0);

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(startVertex);
        states.put(startVertex, VISITED);

        // reducing the distance
        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll();
            states.put(v, UNVISITED);

            if (!tryReduceDistanceToAdjacent(g, v, queue, distances, minEdges, states, currentRound, maxRounds))
                return Collections.emptyList();
        }

        // restore the path
        return restoreMinPath(startVertex, endVertex, minEdges);
    }

    private boolean tryReduceDistanceToAdjacent(Graph<T, WeightedEdge<T>> g,
                                                Vertex<T> from,
                                                Queue<Vertex<T>> queue,
                                                Map<Vertex<T>, Integer> distances,
                                                Map<Vertex<T>, WeightedEdge<T>> minEdge,
                                                Map<Vertex<T>, TraverseState> states,
                                                AtomicInteger currentRound, int maxRounds) {

        for (WeightedEdge<T> e : g.getAdjacent(from)) {
            Vertex<T> to = e.getTo();

            int distFrom = distances.get(from);
            int distTo = distances.get(to);

            // override the minimum distance
            if (distFrom + e.getWeight() < distTo) {
                distTo = distFrom + e.getWeight();
                distances.put(to, distTo);

                // keep the min edge to restore the path later on
                minEdge.put(to, e);

                // make it available for the next round
                if (states.get(to) == UNVISITED) {
                    queue.add(to);
                    states.put(to, VISITED);
                }
            }

            if (currentRound.incrementAndGet() == maxRounds) {
                // TODO: simplified negative cycle detection.
                // It is very simple check if the given graph has a negative cycle
                // it based on a fact that if after V(V-1) check we are keep reducing the distance
                // then perhaps the graph has a negative cycle
                return false;
            }
        }

        return true;
    }

    private Collection<WeightedEdge<T>> restoreMinPath(Vertex<T> startVertex,
                                                       Vertex<T> endVertex,
                                                       Map<Vertex<T>, WeightedEdge<T>> edges) {
        WeightedEdge<T> current = edges.get(endVertex);
        // There is no path between the vertices
        if (current == null)
            return Collections.emptyList();

        // restore the path
        List<WeightedEdge<T>> result = new ArrayList<>();
        while (!current.getFrom().equals(startVertex)) {
            result.add(current);
            current = edges.get(current.getFrom());
        }
        result.add(current);

        // return it in the reverse order
        Collections.reverse(result);
        return result;
    }
}
