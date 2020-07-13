package org.graphs.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class TestVertexTransformation {

    @Test
    public void testApplyTransformationToAllVertices() {
        UnweightedGraph<Integer> graph = UnweightedGraph.newUndirected();

        Assert.assertTrue(graph.addEdge(1, 2));
        Assert.assertTrue(graph.addEdge(3, 4));

        graph.transform(v -> v * 10);

        List<Vertex<Integer>> vertexList = graph.getVertices();
        Assert.assertFalse(vertexList.isEmpty());
        Assert.assertEquals(4, vertexList.size());

        // check old values are removed
        Map<Integer, Vertex<Integer>> valToVertexMap = vertexList.stream().collect(Collectors.toMap(Vertex::getValue, v -> v));
        Assert.assertNull(valToVertexMap.get(1));
        Assert.assertNull(valToVertexMap.get(2));
        Assert.assertNull(valToVertexMap.get(3));
        Assert.assertNull(valToVertexMap.get(4));

        // new values exist
        vertexList = graph.getVertices();
        Assert.assertFalse(vertexList.isEmpty());
        Assert.assertEquals(4, vertexList.size());
        valToVertexMap = vertexList.stream().collect(Collectors.toMap(Vertex::getValue, v -> v));

        Assert.assertNotNull(valToVertexMap.get(10));
        Assert.assertNotNull(valToVertexMap.get(20));
        Assert.assertNotNull(valToVertexMap.get(30));
        Assert.assertNotNull(valToVertexMap.get(40));

    }
}
