package graph.bow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Karol Pokomeda on 2017-06-01.
 */
public class IncidenceBowGraph<T> implements MyBowGraph<T> {
    private List<T> vertexList;
    private int[][] incidenceMatrix;

    public IncidenceBowGraph() {
        this.vertexList = new ArrayList<>();
        this.incidenceMatrix = new int[0][0];
    }

    @Override
    public boolean addVertex(T t) {
        if (!this.vertexList.contains(t)) {
            int[][] newMatrix = this.incidenceMatrix.length != 0 ? new int[this.incidenceMatrix.length + 1][this.incidenceMatrix[0].length] : new int[1][0];
            for (int i = 0; i < this.incidenceMatrix.length; i++) {
                for (int j = 0; j < this.incidenceMatrix[i].length; j++) {
                    newMatrix[i][j] = this.incidenceMatrix[i][j];
                }
            }
            this.incidenceMatrix = newMatrix;
            return this.vertexList.add(t);
        }
        return false;
    }

    @Override
    public boolean addBow(T t1, T t2, int weight) {
        int start;
        int end;
        if (
                (start = this.vertexList.indexOf(t1)) >= 0
                        && (end = this.vertexList.indexOf(t2)) >= 0
                        && !this.hasBow(t1, t2)
                ) {
            int[][] newMatrix = new int[this.incidenceMatrix.length][this.incidenceMatrix[0].length+1];
            for (int i = 0; i < this.incidenceMatrix.length; i++) {
                for (int j = 0; j < this.incidenceMatrix[i].length; j++) {
                    newMatrix[i][j] = this.incidenceMatrix[i][j];
                }
            }
            this.incidenceMatrix = newMatrix;
            this.incidenceMatrix[start][this.incidenceMatrix[start].length - 1] = weight;
            this.incidenceMatrix[end][this.incidenceMatrix[end].length - 1] = -weight;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteVertex(T t) {
        int vertexIndex = this.vertexList.indexOf(t);
        if (vertexIndex < 0) {
            return false;
        }
        for (int i = 0; i < this.incidenceMatrix[vertexIndex].length; i++){
            if (this.incidenceMatrix[vertexIndex][i] != 0){
                return false;
            }
        }
        this.vertexList.remove(vertexIndex);
        int[][] newMatrix = this.incidenceMatrix.length == 1 ?
                new int[0][0]
                : new int[this.incidenceMatrix.length][this.incidenceMatrix[0].length];
        for (int i = 0, iPrim = 0; i < newMatrix.length; i++, iPrim++){
            for (int j = 0; j < newMatrix[0].length; j++){
                if (i == vertexIndex){
                    iPrim++;
                }
                newMatrix[i][j] = this.incidenceMatrix[iPrim][j];
            }
        }
        return true;
    }

    @Override
    public boolean deleteBow(T t1, T t2) {
        int start;
        int end;
        if ((start = this.vertexList.indexOf(t1)) >= 0
                && (end = this.vertexList.indexOf(t2)) >= 0){
            int bowIndex = -1;
            for (int i = 0; i < this.incidenceMatrix[start].length; i++){
                if (this.incidenceMatrix[start][i] != 0 && this.incidenceMatrix[start][i] == -this.incidenceMatrix[end][i]){
                    bowIndex = i;
                    break;
                }
            }
            if (bowIndex >= 0){
                int[][] newMatrix = new int[this.incidenceMatrix.length][this.incidenceMatrix[0].length-1];
                for (int i = 0; i < newMatrix.length; i++){
                    for (int j = 0, jPrim = 0; j < newMatrix[0].length; j++, jPrim++){
                        if (j == bowIndex) {
                            jPrim++;
                        }
                        newMatrix[i][j] = this.incidenceMatrix[i][jPrim];
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasVertex(T t) {
        return this.vertexList.contains(t);
    }

    @Override
    public boolean hasBow(T t1, T t2) {
        int start;
        int end;
        if ((start = this.vertexList.indexOf(t1)) >= 0
                && (end = this.vertexList.indexOf(t2)) >= 0){
            for (int i = 0; i < this.incidenceMatrix[start].length; i++){
                if (this.incidenceMatrix[start][i] != 0 && this.incidenceMatrix[start][i] == -this.incidenceMatrix[end][i]){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int bowWeight(T t1, T t2) {
        int start;
        int end;
        if ((start = this.vertexList.indexOf(t1)) >= 0
                && (end = this.vertexList.indexOf(t2)) >= 0){
            for (int i = 0; i < this.incidenceMatrix[start].length; i++){
                if (this.incidenceMatrix[start][i] != 0 && this.incidenceMatrix[start][i] == -this.incidenceMatrix[end][i]){
                    return this.incidenceMatrix[start][i];
                }
            }
        }
        return 0;
    }

    @Override
    public int vertexCount() {
        return this.vertexList.size();
    }

    @Override
    public int bowCount() {
        return this.incidenceMatrix.length != 0 ? this.incidenceMatrix[0].length : 0;
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
