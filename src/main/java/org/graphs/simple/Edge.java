package org.graphs.simple;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents an edge between two  vertices.
 * This class is immutable.
 *
 * @param <T> type of Vertex
 */
@Slf4j
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Edge<T> {
    private final Vertex<T> from;
    private final Vertex<T> to;
}
