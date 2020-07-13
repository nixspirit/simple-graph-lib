package org.graphs.simple;

class DirectedGraph<T> extends AbstractGraph<T, Edge<T>> implements UnweightedGraph<T> {

    DirectedGraph(PathFinderStrategy<T, Edge<T>> pathFinderStrategy) {
        super(pathFinderStrategy);
    }

    /**
     * Adds a singe edge from->to
     *
     * @param from value to be used to find the vertex
     * @param to   value to be used to find the vertex
     * @return true if an edge has been added
     */
    @Override
    public boolean addEdge(T from, T to) {
        Vertex<T> v1 = new Vertex<>(from);
        Vertex<T> v2 = new Vertex<>(to);
        Edge<T> edge = new Edge<>(v1, v2);
        return addEdge(edge);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
