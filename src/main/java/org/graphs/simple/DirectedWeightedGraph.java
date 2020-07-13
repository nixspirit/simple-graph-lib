package org.graphs.simple;

class DirectedWeightedGraph<T> extends AbstractGraph<T, WeightedEdge<T>> implements WeightedGraph<T> {

    DirectedWeightedGraph(PathFinderStrategy<T, WeightedEdge<T>> pathFinderStrategy) {
        super(pathFinderStrategy);
    }

    /**
     * Adds a singe edge from->to
     *
     * @param from   value to be used to find the vertex
     * @param to     value to be used to find the vertex
     * @param weight edge's weight
     * @return true if an edge has been added
     */
    @Override
    public boolean addEdge(T from, T to, int weight) {
        Vertex<T> v1 = new Vertex<>(from);
        Vertex<T> v2 = new Vertex<>(to);
        WeightedEdge<T> edge = new WeightedEdge<>(v1, v2, weight);
        return addEdge(edge);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
