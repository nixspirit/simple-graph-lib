package org.graphs.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class TestGraphCreation {

    @Test
    public void testCreateUndirectedGraph() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        Assert.assertFalse(graph.isDirected());
        Assert.assertTrue(graph.isEmpty());

        Assert.assertTrue(graph.addVertex(1));
        Assert.assertTrue(graph.addVertex(2));
        Assert.assertTrue(graph.addEdge(1, 2));
        Assert.assertTrue(graph.addEdge(3, 4));
        Assert.assertFalse(graph.addEdge(3, 4));
        Assert.assertFalse(graph.isEmpty());

        Assert.assertEquals(4, graph.getVertices().size());
        Assert.assertEquals(1, graph.getAdjacent(1).size());
        Assert.assertEquals(1, graph.getAdjacent(2).size());
        Assert.assertEquals(1, graph.getAdjacent(3).size());
        Assert.assertEquals(1, graph.getAdjacent(4).size());
    }

    @Test
    public void testCreateDirectedGraph() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newDirected();
        Assert.assertTrue(graph.isDirected());
        Assert.assertTrue(graph.isEmpty());

        Assert.assertTrue(graph.addVertex(1));
        Assert.assertTrue(graph.addVertex(2));
        Assert.assertTrue(graph.addEdge(1, 2));
        Assert.assertTrue(graph.addEdge(3, 4));
        Assert.assertFalse(graph.addEdge(3, 4));
        Assert.assertFalse(graph.isEmpty());

        Assert.assertEquals(4, graph.getVertices().size());
        Assert.assertEquals(1, graph.getAdjacent(1).size());
        Assert.assertEquals(0, graph.getAdjacent(2).size());
        Assert.assertEquals(1, graph.getAdjacent(3).size());
        Assert.assertEquals(0, graph.getAdjacent(4).size());
    }

    @Test
    public void testCreateUndirectedWightedGraph() {
        WeightedGraph<Integer> graph = WeightedGraph.newUndirected();
        Assert.assertFalse(graph.isDirected());
        Assert.assertTrue(graph.isEmpty());

        Assert.assertTrue(graph.addVertex(1));
        Assert.assertTrue(graph.addVertex(2));
        Assert.assertTrue(graph.addEdge(1, 2, 10));
        Assert.assertTrue(graph.addEdge(3, 4, 20));
        Assert.assertFalse(graph.addEdge(3, 4, 20)); // won't be added
        Assert.assertTrue(graph.addEdge(3, 4, 30)); // will be added
        Assert.assertFalse(graph.isEmpty());

        Assert.assertEquals(4, graph.getVertices().size());
        Assert.assertEquals(1, graph.getAdjacent(1).size());
        Assert.assertEquals(1, graph.getAdjacent(2).size());
        Assert.assertEquals(2, graph.getAdjacent(3).size());
        Assert.assertEquals(2, graph.getAdjacent(4).size());
    }

    @Test
    public void testCreateDirectedWightedGraph() {
        WeightedGraph<Integer> graph = WeightedGraph.newDirected();
        Assert.assertTrue(graph.isDirected());
        Assert.assertTrue(graph.isEmpty());

        Assert.assertTrue(graph.addVertex(1));
        Assert.assertTrue(graph.addVertex(2));
        Assert.assertTrue(graph.addEdge(1, 2, 10));
        Assert.assertTrue(graph.addEdge(3, 4, 20));
        Assert.assertFalse(graph.addEdge(3, 4, 20)); // won't be added
        Assert.assertTrue(graph.addEdge(3, 4, 30)); // will be added
        Assert.assertFalse(graph.isEmpty());

        Assert.assertEquals(4, graph.getVertices().size());
        Assert.assertEquals(1, graph.getAdjacent(1).size());
        Assert.assertEquals(0, graph.getAdjacent(2).size());
        Assert.assertEquals(2, graph.getAdjacent(3).size());
        Assert.assertEquals(0, graph.getAdjacent(4).size());
    }

}
