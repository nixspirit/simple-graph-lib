package org.graphs.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class TestCycleFinding {


    @Test
    public void testFindCycleWhenNoCycles() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        Assert.assertFalse(graph.isDirected());

        Assert.assertTrue(graph.addEdge(1, 2));
        Assert.assertTrue(graph.addEdge(2, 3));
        Assert.assertTrue(graph.addEdge(1, 4));

        Assert.assertEquals(
                "1->2, 4, \n" +
                        "2->1, 3, \n" +
                        "3->2, \n" +
                        "4->1, ",
                graph.toString());

        Assert.assertFalse(graph.hasCycles());
    }

    @Test
    public void testFindCycleWhenCycleExists() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();
        Assert.assertFalse(graph.isDirected());

        Assert.assertTrue(graph.addEdge(1, 2));
        Assert.assertTrue(graph.addEdge(2, 3));
        Assert.assertTrue(graph.addEdge(3, 1));

        Assert.assertEquals(
                "1->2, 3, \n" +
                        "2->1, 3, \n" +
                        "3->1, 2, ",
                graph.toString());

        Assert.assertTrue(graph.hasCycles());
    }
}
