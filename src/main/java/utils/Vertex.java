package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-06-01.
 */
public class Vertex<T> {
    private T value;
    private HashMap<Vertex<T>, Integer> neighbours;

    public Vertex(T value) {
        this.value = value;
        this.neighbours = new HashMap<>();
    }

    public List<Vertex<T>> getNeighbours() {
        return new ArrayList<>(this.neighbours.keySet());
    }

    public boolean hasNeighbour(T t) {
        for (Vertex<T> vertex : this.neighbours.keySet()) {
            if (vertex.getValue().equals(t)) return true;
        }
        return false;
    }

    public boolean addNeighbour(Vertex<T> vertex, int weight) {
        if (this.hasNeighbour(vertex.getValue())) {
            return false;
        }
        this.neighbours.put(vertex, weight);
        return true;
    }

    public boolean deleteNeighbour(T t) {
        for (Vertex<T> vertex : this.neighbours.keySet()) {
            if (vertex.getValue().equals(t)){
                this.neighbours.remove(vertex);
                return true;
            }
        }
        return false;
    }

    public int getNeighbourWeight(Vertex<T> vertex){
        return this.neighbours.containsKey(vertex) ? this.neighbours.get(vertex) : 0;
    }

    public T getValue() {
        return this.value;
    }
}
