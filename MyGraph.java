package com.company;

import java.util.*;


public class MyGraph<T> {
    private final boolean undirected;
    protected Map<Vertex<T>, List<Vertex<T>>> map = new HashMap<>();

    public MyGraph() {
        this.undirected = true;
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(T v) {
        map.put(new Vertex<>(v), new LinkedList<>());
    }

    public void addEdge(T source, T dest) {

        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops

        map.get(new Vertex<>(source)).add(new Vertex<>(dest));

        if (undirected)
            map.get(new Vertex<>(dest)).add(new Vertex<>(source));
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<T> v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(T v) {
        return map.containsKey(new Vertex<>(v));
    }


    public boolean hasEdge(T source, T dest) {
        if (!hasVertex(source)) return false;
        return map.get(new Vertex<>(source)).contains(new Vertex<>(dest));
    }

    public Iterable<T> adjacencyList(Vertex<T> v) {
        if (!hasVertex(v.getData())) return null;

        return (Iterable<T>) map.get(v);
    }
}
