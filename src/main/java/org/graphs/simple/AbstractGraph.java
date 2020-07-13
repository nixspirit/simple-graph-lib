package org.graphs.simple;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.UnaryOperator;

abstract class AbstractGraph<T, E extends Edge<T>> implements Graph<T, E> {

    private final Map<Vertex<T>, Set<E>> vertices;
    private final PathFinderStrategy<T, E> pathFinderStrategy;
    private final CycleDetectionStrategy cycleDetectionStrategy;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock r = readWriteLock.readLock();
    private final Lock w = readWriteLock.writeLock();

    AbstractGraph(PathFinderStrategy<T, E> pathFinderStrategy) {
        this.vertices = new HashMap<>();
        this.pathFinderStrategy = pathFinderStrategy;
        this.cycleDetectionStrategy = new SimpleBfsCycleDetectionStrategy();
    }

    @Override
    public boolean addVertex(T value) {
        if (value == null)
            throw new IllegalArgumentException("Vertex cannot be created from the null value");

        return addVertex(new Vertex<>(value));
    }

    @Override
    public boolean addVertex(Vertex<T> v) {
        w.lock();
        try {
            if (vertices.containsKey(v))
                return false;

            vertices.put(v, new HashSet<>());
            return true;
        } finally {
            w.unlock();
        }
    }

    @Override
    public boolean addEdge(E edge) {
        if (edge == null || edge.getFrom() == null || edge.getTo() == null)
            throw new IllegalArgumentException("Edge cannot be null or contain null vertices");

        w.lock();
        try {
            addVertex(edge.getFrom());
            addVertex(edge.getTo());
            return vertices.get(edge.getFrom()).add(edge);
        } finally {
            w.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        r.lock();
        try {
            return vertices.isEmpty();
        } finally {
            r.unlock();
        }
    }

    @Override
    public List<E> getAdjacent(Vertex<T> v) {
        if (v == null)
            throw new IllegalArgumentException("Vertex cannot be null");

        r.lock();
        try {
            Set<E> edges = vertices.get(v);
            return edges == null ?
                    Collections.emptyList() :
                    new ArrayList<>(edges); // edges are immutable so a copy of the array would be enough
        } finally {
            r.unlock();
        }
    }

    @Override
    public List<E> getAdjacent(T v) {
        return getAdjacent(new Vertex<>(v));
    }

    //// returns copy of vertices. Vertex can be amended only from the current package
    @Override
    public List<Vertex<T>> getVertices() {
        r.lock();
        try {
            return new ArrayList<>(vertices.keySet());
        } finally {
            r.unlock();
        }
    }

    @Override
    public Collection<E> getPath(T start, T end) {
        if (start == null || end == null)
            throw new IllegalArgumentException("Cannot perform search for a path if any node is null");

        if (pathFinderStrategy == null)
            throw new PathFinderStrategyNotDefinedException();

        r.lock();
        try {
            return pathFinderStrategy.findPath(this, start, end);
        } finally {
            r.unlock();
        }
    }

    @Override
    public String toString() {
        r.lock();
        try {
            return GraphVisualizer.asString(this);
        } finally {
            r.unlock();
        }
    }

    @Override
    public boolean hasCycles() {
        r.lock();
        try {
            return cycleDetectionStrategy.detectCycle(this);
        } finally {
            r.unlock();
        }
    }

    @Override
    public void transform(UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        w.lock();
        try {
            vertices.forEach((k, v) -> k.setValue(operator.apply(k.getValue())));
        } finally {
            w.unlock();
        }
    }
}
