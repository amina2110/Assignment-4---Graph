package com.company;
import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private Set<Vertex<T>> unsettledNodes;
    private Map<Vertex<T>, Double> distances;
    private MyWeightedGraph<T> graph;


    public DijkstraSearch(MyWeightedGraph<T> graph,T source) {
        super(new Vertex<>(source));
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            Vertex<T> node = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(node);
            unsettledNodes.remove(node);
            for (Vertex<T> target : graph.adjacencyList(node.getData())) {
                if (getShortestDistance(target) > getShortestDistance(node)
                        + getDistance(node, target)) {
                    distances.put(target, getShortestDistance(node)
                            + getDistance(node, target));
                    edgeTo.put(target, node);
                    unsettledNodes.add(target);
                }
            }
        }
    }

    private double getDistance(Vertex<T> node, Vertex<T> target) {

        for (Vertex<T> edge : graph.getEdges(node.getData())) {
            if (edge.adjacentVertices.containsKey(target)) {
                return edge.getWeight(target);
            }
        }

//        Double distance = node.adjacentVertices.get(target);
//        if(distance != null ){
//            return distance;
//        }

        throw new RuntimeException("Not found!");
    }

    private Vertex<T> getVertexWithMinimumWeight(Set<Vertex<T>> vertices) {
        Vertex<T> minimum = null;
        for (Vertex<T> vertex : vertices) {
            if (minimum == null)
                minimum = vertex;
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                    minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex<T> destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}

