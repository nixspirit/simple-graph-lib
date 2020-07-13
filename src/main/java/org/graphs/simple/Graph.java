package org.graphs.simple;

import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * The general contract for all types of graphs implemented.
 *
 * @param <T> the value which Vertex holds
 * @param <E> the type of an edge
 */
interface Graph<T, E extends Edge<T>> {

    /**
     * Adds a vertex to the graph with the given value
     *
     * @param value which a new vertex holds.
     */
    boolean addVertex(T value);

    /**
     * Adds a vertex to the graph
     *
     * @param vertex the new verted to be added.
     */
    boolean addVertex(Vertex<T> vertex);

    /**
     * Adds an edge.
     *
     * @param edge to be added
     */
    boolean addEdge(E edge);

    /**
     * Returns a list of edges between 2 vertices
     *
     * @param start of the path.
     * @param end   of the path.
     * @return a collection of edges if a path exists otherwise an empty list will be returned.
     */
    Collection<E> getPath(T start, T end);

    /**
     * @return true if the given graph has cycles
     */
    boolean hasCycles();

    /**
     * Traverse function that will take a user defined function and apply it on every vertex of the graph.
     */
    void transform(UnaryOperator<T> t);

    /**
     * @return true if the graph contains any vertices
     */
    boolean isEmpty();

    /**
     * @return true if the given graph is directed.
     */
    boolean isDirected();

    /**
     * @return a copy of vertices' collection
     */
    List<Vertex<T>> getVertices();

    /**
     * @param v the vertex for which the adjacent edges will be found
     * @return a copy of the adjacent edges collection
     */
    List<E> getAdjacent(Vertex<T> v);

    /**
     * @param v value which will be used to fina a vertex.
     * @return a copy of the adjacent edges collection.
     */
    List<E> getAdjacent(T v);

}
