package org.graphs.simple;

import java.util.Collection;

interface PathFinderStrategy<T, E extends Edge<T>> {

    Collection<E> findPath(Graph<T, E> g, T start, T end);
}
