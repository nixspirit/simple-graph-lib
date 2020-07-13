package org.graphs.simple;

class UndirectedWeightedGraph<T> extends AbstractGraph<T, WeightedEdge<T>> implements WeightedGraph<T> {

    UndirectedWeightedGraph(PathFinderStrategy<T, WeightedEdge<T>> pathFinderStrategy) {
        super(pathFinderStrategy);
    }

    /**
     * Adds two edges: from->to and to->from
     *
     * @param from   value to be used to find the vertex
     * @param to     value to be used to find the vertex
     * @param weight edge's weight
     * @return true if any of edges has been added
     */
    @Override
    public boolean addEdge(T from, T to, int weight) {
        Vertex<T> v1 = new Vertex<>(from);
        Vertex<T> v2 = new Vertex<>(to);
        WeightedEdge<T> edge = new WeightedEdge<>(v1, v2, weight);
        WeightedEdge<T> reversedEdge = new WeightedEdge<>(v2, v1, weight);
        boolean addedDirect = addEdge(edge);
        boolean addedReversed = addEdge(reversedEdge);
        return addedDirect || addedReversed;
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
