package com.company;

import java.util.*;

public class MyWeightedGraph<T> {
    private final boolean undirected;
    private Map<T ,Vertex<T>> map = new HashMap<>();

    public MyWeightedGraph() {
        this.undirected = true;
    }

    public MyWeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(T v) {
        map.put(v, new Vertex<>(v));
    }

    public void addEdge(T source, T dest, double weight) {

        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops

        map.get(source).addAdjacentVertex(new Vertex<>(dest), weight);

        if (undirected)
            map.get(dest).addAdjacentVertex(new Vertex<>(source), weight);
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).adjacentVertices.size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(T v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(T source, T dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).hasEdgeTo(dest);
    }

    public Iterable<Vertex<T>> adjacencyList(T v) {
        if (!hasVertex(v)) return null;
        List<Vertex<T>> vertices = new LinkedList<>();
        for (Vertex<T> e : map.get(v).adjacentVertices.keySet()) {
            vertices.add(e);
        }
        return vertices;
    }

    public Iterable<Vertex<T>> getEdges(T v) {
        if (!hasVertex(v)) return null;
        return map.get(v).adjacentVertices.keySet();
    }
}
