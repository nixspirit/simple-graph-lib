# simple-graph-lib
All the classes intentionally are placed under the same package to hide the implementation from the user.

The user could use only the methods defined in the `Graph` interface plus the `Edge`, `WeightedEdge` and `Vertex` classes.

Gradle Lombok plugin was used to avoid writing boilerplate code.

## The library supports the following graph types:
1. Directed
2. Undirected
3. Directed Weighted
4. Undirected Weighted

## A new graph could be created as follows:
1. Directed Unweighted
    `UnweightedGraph<Integer> graph = UnweightedGraph.newDirected();`

2. Undirected Unweighted
    `UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();`

3. Directed Weighted
    `WeightedGraph<Integer> graph = WeightedGraph.newDirected();`

4. Undirected Weighted
    `WeightedGraph<Integer> graph = WeightedGraph.newUndirected();`

A new vertex could be added as follows:
    `graph.addVertex(1)`

Depending on a graph type a new edge could be added either via `graph.addEdge(1, 2)` or `graph.addEdge(1, 2, weight)`

In order to find a path call `graph.getPath(3,5)` where 3 and 5 are vertices values. It will return a collection of edges which lie on the path.
* a) For Unweighted graphs the simple DFS traverse is used to perform searching, see `SimplePathFindingStrategy`
* b) For Weighted graphs a simplified implementation of Bellman–Ford algorithm is used, see `BellmanFordPathFindingInWeightedGraphStrategy`
    
    **TODO: At the moment a simplified version of negative cycle detection is used**

`Graph.hasCycles()` returns true if the given graph contains a cycle.

`Graph.transform()` allows transforming every Vertices' value according to the user-defined function

## Thread Safety.
We assume that there are multiple readers and a very few writers so
`ReentrantReadWriteLock`  (see `AbstractGraph` class) is used to provide fast reading along with some level of consistency in the case when the graph or its vertices are being changed.

`Edge` - is an immutable class

`Vertex` - can be changed from the inside of the package. The value of Vertex could be immutable class so 'volatile' wouldn't be enough.

See the Graph interface for more methods and tests for more information on usage.
