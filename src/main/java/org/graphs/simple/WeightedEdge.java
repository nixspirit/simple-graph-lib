package org.graphs.simple;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents a weighted edge which holds from/to and the cost of the path.
 * This is immutable class.
 *
 * @param <T> type of Vertex
 */
@Slf4j
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public final class WeightedEdge<T> extends Edge<T> {

    public WeightedEdge(Vertex<T> from, Vertex<T> to, int weight) {
        super(from, to);
        this.weight = weight;
    }

    private final int weight;
}
