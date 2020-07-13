package org.graphs.simple;

/**
 * Represents Unweighted Directed or Undirected graph.
 * A new instance can be created via newUndirected() or newDirected() methods.
 * <p>
 * At the moment undirected graphs use the following
 * a) to find path - DFS approach
 * b) to find cycles - BFS approach
 *
 * @param <T> type of the vertex
 */
public interface UnweightedGraph<T> extends Graph<T, Edge<T>> {

    /**
     * Adds an edge to the given graph.
     *
     * @param from value to be used to find the vertex
     * @param to   value to be used to find the vertex
     */
    boolean addEdge(T from, T to);

    static <T> UnweightedGraph<T> newUndirected() {
        return new UndirectedGraph<T>(new SimplePathFindingStrategy<>());
    }

    static <T> UnweightedGraph<T> newDirected() {
        return new DirectedGraph<T>(new SimplePathFindingStrategy<>());
    }

}
