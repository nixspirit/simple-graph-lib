package org.graphs.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestFindingInWeightedGraphStrategy {

    @Test
    public void testPathNotFound() {
        WeightedGraph<Integer> graph = WeightedGraph.newUndirected();

        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 4, 7);
        graph.addEdge(1, 3, 3);
        graph.addEdge(3, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(5, 6, 2);

        List<WeightedEdge<Integer>> path = new ArrayList<>(graph.getPath(1, 5));
        Assert.assertTrue(path.isEmpty());
    }

    @Test
    public void testPathFound() {
        WeightedGraph<Integer> graph = WeightedGraph.newUndirected();

        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 4, 7);
        graph.addEdge(1, 3, 3);
        graph.addEdge(3, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(2, 5, 2);
        graph.addEdge(4, 5, 2);

        Assert.assertEquals(
                "1->2(w5), 3(w3), 4(w7), \n" +
                        "2->1(w5), 4(w3), 5(w2), \n" +
                        "3->1(w3), 4(w1), \n" +
                        "4->1(w7), 2(w3), 3(w1), 5(w2), \n" +
                        "5->2(w2), 4(w2), ",
                graph.toString());

        List<WeightedEdge<Integer>> path = new ArrayList<>(graph.getPath(1, 5));
        Assert.assertFalse(path.isEmpty());
        Assert.assertEquals(3, path.size());

        // edge 1->3 with weight 3
        Assert.assertEquals(1, path.get(0).getFrom().getValue().intValue());
        Assert.assertEquals(3, path.get(0).getTo().getValue().intValue());
        Assert.assertEquals(3, path.get(0).getWeight());

        // edge 3->4 with weight 1
        Assert.assertEquals(3, path.get(1).getFrom().getValue().intValue());
        Assert.assertEquals(4, path.get(1).getTo().getValue().intValue());
        Assert.assertEquals(1, path.get(1).getWeight());

        // edge 4->5 with weight 2
        Assert.assertEquals(4, path.get(2).getFrom().getValue().intValue());
        Assert.assertEquals(5, path.get(2).getTo().getValue().intValue());
        Assert.assertEquals(2, path.get(2).getWeight());
    }

    @Test
    public void testPathNotFoundWithNegativeCycles() {
        WeightedGraph<Integer> graph = WeightedGraph.newUndirected();

        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, -7);

        //a negative cycle 2->3->4->2 with length -4.
        List<WeightedEdge<Integer>> path = new ArrayList<>(graph.getPath(1, 4));
        Assert.assertTrue(path.isEmpty());
    }
}
