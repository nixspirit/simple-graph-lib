package org.graphs.simple;

public class PathFinderStrategyNotDefinedException extends RuntimeException {

    PathFinderStrategyNotDefinedException() {
        super("The strategy for the given graph was not defined so we cannot perform search");
    }
}
