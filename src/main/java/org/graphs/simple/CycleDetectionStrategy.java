package org.graphs.simple;

interface CycleDetectionStrategy {

    <E extends Edge<T>, T> boolean detectCycle(Graph<T, E> g);
}
