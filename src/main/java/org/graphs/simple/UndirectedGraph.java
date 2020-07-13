package org.graphs.simple;

class UndirectedGraph<T> extends AbstractGraph<T, Edge<T>> implements UnweightedGraph<T> {

    UndirectedGraph(PathFinderStrategy<T, Edge<T>> pathFinderStrategy) {
        super(pathFinderStrategy);
    }

    /**
     * Adds two edges: from->to and to->from
     *
     * @param from   value to be used to find the vertex
     * @param to     value to be used to find the vertex
     * @return true if any of edges has been added
     */
    @Override
    public boolean addEdge(T from, T to) {
        Vertex<T> v1 = new Vertex<>(from);
        Vertex<T> v2 = new Vertex<>(to);
        Edge<T> edge = new Edge<>(v1, v2);
        Edge<T> reversedEdge = new Edge<>(v2, v1);
        boolean addedDirect = addEdge(edge);
        boolean addedReversed = addEdge(reversedEdge);
        return addedDirect || addedReversed;
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
