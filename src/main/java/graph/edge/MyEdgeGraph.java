package graph.edge;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Karol Pokomeda on 2017-05-30.
 */
public interface MyEdgeGraph<T> {
    boolean addVertex(T t);

    boolean addEdge(T t1, T t2, int weight);

    boolean deleteVertex(T t);

    boolean deleteEdge(T t1, T t2);

    boolean hasVertex(T t);

    boolean hasEdge(T t1, T t2);

    int edgeWeight(T t1, T t2);

    int vertexCount();

    int edgeCount();

    MyEdgeGraph<T> MST();

    List<T> BFS(T t, Predicate<T> predicate);

    List<T> DFS(T t, Predicate<T> predicate);
}
