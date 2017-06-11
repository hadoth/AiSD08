package utils.storage;

/**
 * Created by Karol Pokomeda on 2017-05-31.
 */
public class Edge<T> implements Comparable<Edge<T>> {
    private T source;
    private T destination;
    private int weight;

    public Edge(T t1, T t2, int weight) {
        this.source = t1;
        this.destination = t2;
        this.weight = weight;
    }

    public T getSource() {
        return this.source;
    }

    public T getDestination() {
        return this.destination;
    }

    public int getWeight() {
        return this.weight;
    }

    public int compareTo(Edge<T> edge) {
        return Integer.compare(this.weight, edge.getWeight());
    }
}
