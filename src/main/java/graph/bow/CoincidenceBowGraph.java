package graph.bow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Karol Pokomeda on 2017-06-01.
 */
public class CoincidenceBowGraph<T> implements MyBowGraph<T> {
    private List<T> vertexList;
    private int[][] coincidenceMatrix;

    public CoincidenceBowGraph(){
        this.vertexList = new ArrayList<>();
        this.coincidenceMatrix = new int[0][0];
    }

    @Override
    public boolean addVertex(T t) {
        if (!this.vertexList.contains(t)){
            int[][] newMatrix = this.coincidenceMatrix.length !=0 ? new int[this.coincidenceMatrix.length+1][this.coincidenceMatrix[0].length] : new int[1][0];
            return this.vertexList.add(t);
        }
        return false;
    }

    @Override
    public boolean addBow(T t1, T t2, int weight) {
        return this.vertexList.contains(t1)
                && this.vertexList.contains(t2);
    }

    @Override
    public boolean deleteVertex(T t) {
        return false;
    }

    @Override
    public boolean deleteBow(T t1, T t2) {
        return false;
    }

    @Override
    public boolean hasVertex(T t) {
        return false;
    }

    @Override
    public boolean hasBow(T t1, T t2) {
        return false;
    }

    @Override
    public int bowWeight(T t1, T t2) {
        return 0;
    }

    @Override
    public int vertexCount() {
        return 0;
    }

    @Override
    public int bowCount() {
        return 0;
    }

    @Override
    public MyBowGraph<T> MST() {
        return null;
    }

    @Override
    public List<T> BFS(T t, Predicate<T> predicate) {
        return null;
    }

    @Override
    public List<T> DFS(T t, Predicate<T> predicate) {
        return null;
    }
}
