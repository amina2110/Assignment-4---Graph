package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Vertex<T> {
    private T data;
    public Map<Vertex<T>, Double> adjacentVertices;

    public Vertex(T data) {
        this.data = data;
        adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<T> destination, double weight){
        adjacentVertices.put(destination, weight);
    }

    public boolean hasEdgeTo(T v){
        return adjacentVertices.containsKey(new Vertex<>(v));
    }



    public Double getWeight(Vertex<T> target){
        return adjacentVertices.get(target);
    }

    public T getData(){
        return data;
    }
    public void setData(T data){
        this.data = data;
    }

    public String toString(){
        return (String)data;
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Vertex<T> vertex = (Vertex<T>)o;
        return Objects.equals(data, vertex.data);
    }

    public int hashCode(){
        return Objects.hash(data, adjacentVertices);
    }

}
