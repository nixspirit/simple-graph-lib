package org.graphs.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class TestPathFinding {

    @Test
    public void testFindPathEmptyGraph() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        Assert.assertTrue(graph.isEmpty());
        Collection<Edge<Integer>> path = graph.getPath(1, 4);
        Assert.assertNotNull(path);
        Assert.assertTrue(path.isEmpty());
    }

    @Test
    public void testFindPathToNonExistingNode() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        Collection<Edge<Integer>> path = graph.getPath(2, 5);
        Assert.assertNotNull(path);
        Assert.assertTrue(path.isEmpty());
    }

    @Test
    public void testFindPathToExistingNodeInUndirectedGraph() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        Collection<Edge<Integer>> path = graph.getPath(3, 4);
        Assert.assertNotNull(path);
        Assert.assertFalse(path.isEmpty());
        Assert.assertEquals("3->2->1->4", GraphVisualizer.pathAsString(path));

        path = graph.getPath(4, 3);
        Assert.assertNotNull(path);
        Assert.assertFalse(path.isEmpty());

        Assert.assertEquals("4->1->2->3", GraphVisualizer.pathAsString(path));
    }

    @Test
    public void testFindPathToExistingNodeInDirectedGraph() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newDirected();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);

        Collection<Edge<Integer>> path = graph.getPath(4, 3);
        Assert.assertNotNull(path);
        Assert.assertFalse(path.isEmpty());
        Assert.assertEquals("4->1->2->3", GraphVisualizer.pathAsString(path));

        path = graph.getPath(3, 4);
        Assert.assertNotNull(path);
        Assert.assertTrue(path.isEmpty());
    }

    @Test
    public void testFindPathToExistingNodeInGraphWithCycles() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(4, 5);

        Collection<Edge<Integer>> path = graph.getPath(4, 1);
        Assert.assertNotNull(path);
        Assert.assertFalse(path.isEmpty());
        Assert.assertEquals("4->2->1", GraphVisualizer.pathAsString(path));

        path = graph.getPath(1, 5);
        Assert.assertNotNull(path);
        Assert.assertFalse(path.isEmpty());
        Assert.assertEquals("1->2->3->4->5", GraphVisualizer.pathAsString(path));
    }

    @Test
    public void testFindPathToExistingNodeInGraphWithNoCycles() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);

        Collection<Edge<Integer>> path = graph.getPath(1, 5);
        Assert.assertNotNull(path);
        Assert.assertFalse(path.isEmpty());
        Assert.assertEquals(3, path.size());
        Assert.assertEquals("1->2->4->5", GraphVisualizer.pathAsString(path));
    }
}
