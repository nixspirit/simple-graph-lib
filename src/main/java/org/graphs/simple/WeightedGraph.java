package org.graphs.simple;

/**
 * Represents Weighted Directed or Undirected graph.
 * A new instance can be created via newUndirected() or newDirected() methods.
 * <p>
 * At the moment undirected graphs use the following
 * a) to find path - Bellman-Ford algorithm
 * b) to find cycles - BFS approach
 *
 * @param <T> type of the vertex
 */
public interface WeightedGraph<T> extends Graph<T, WeightedEdge<T>> {

    /**
     * Adds an edge to the given graph.
     *
     * @param from   value to be used to find the vertex
     * @param to     value to be used to find the vertex
     * @param weight edge's weight
     */
    boolean addEdge(T from, T to, int weight);

    /**
     * @param <T> type of vertices
     * @return a new instance of undirected weighted graph
     */
    static <T> WeightedGraph<T> newUndirected() {
        return new UndirectedWeightedGraph<T>(new BellmanFordPathFindingInWeightedGraphStrategy<>());
    }

    /**
     * Returns a new instance of directed weighted graph.
     *
     * @param <T> type of vertices
     * @return a new instance of directed weighted graph
     */
    static <T> WeightedGraph<T> newDirected() {
        return new DirectedWeightedGraph<T>(new BellmanFordPathFindingInWeightedGraphStrategy<>());
    }

}
