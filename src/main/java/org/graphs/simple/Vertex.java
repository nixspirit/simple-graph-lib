package org.graphs.simple;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents a weighted edge which holds from/to and the cost of the path.
 *
 * @param <T> type of Vertex
 */
@Slf4j
@EqualsAndHashCode
@Getter
@ToString
public final class Vertex<T> {

    private volatile T value;

    public Vertex(T val) {
        this.value = val;
    }

    void setValue(T value) {
        this.value = value;
    }
}
