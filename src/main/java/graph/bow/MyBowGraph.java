package graph.bow;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Karol Pokomeda on 2017-05-30.
 */
public interface MyBowGraph<T> {
    boolean addVertex(T t);

    boolean addBow(T t1, T t2, int weight);

    boolean deleteVertex(T t);

    boolean deleteBow(T t1, T t2);

    boolean hasVertex(T t);

    boolean hasBow(T t1, T t2);

    int bowWeight(T t1, T t2);

    int vertexCount();

    int bowCount();

    List<T> BFS(T t, Predicate<T> predicate);

    List<T> DFS(T t, Predicate<T> predicate);
}
