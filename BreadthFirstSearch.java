package com.company;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> extends Search<T>{

    public BreadthFirstSearch(MyGraph<T> graph, Vertex<T> source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(MyGraph<T> graph, Vertex<T> current) {
        marked.add(current);
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(current);
        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();
            for (T vertex : graph.adjacencyList(v))
                if (!marked.contains(vertex)) {
                    marked.add(new Vertex<>(vertex));
                    edgeTo.put(new Vertex<>(vertex), v);
                    queue.add(new Vertex<>(vertex));
                }
        }
    }
}
