package com.company;
import java.util.*;

public class Search<T> {
    protected int count;
    protected Set<Vertex<T>> marked;
    protected Map<Vertex<T>, Vertex<T>> edgeTo;
    protected final Vertex<T> source;

    public Search(Vertex<T> source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<T> v) {
        return marked.contains(v);
    }

    public Iterable<Vertex<T>> pathTo(T v) {
        if (!hasPathTo(new Vertex<>(v))) return null;
        LinkedList<Vertex<T>> ls = new LinkedList<>();
        for (Vertex<T> i = new Vertex<>(v); i != source; i = edgeTo.get(i)) {
            ls.push(i);
        }
        ls.push(source);

        return ls;
    }

    public int getCount() {
        return count;
    }
}
