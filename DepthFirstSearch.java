package com.company;
public class DepthFirstSearch<T> extends Search<T> {
    public DepthFirstSearch(MyGraph<T> graph, Vertex<T> source) {
        super(source);
        dfs(graph, source);
    }

    private void dfs(MyGraph<T> graph, Vertex<T> current) {
        marked.add(current);
        count++;
        for (T v : graph.adjacencyList(current)) {
            if (!marked.contains(new Vertex<>(v))) {
                edgeTo.put(new Vertex<>(v), current);
                dfs(graph, new Vertex<>(v));
            }
        }
    }

}
