package graph.bow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Karol Pokomeda on 2017-05-30.
 */
public class MatrixBowGraph<T> implements MyBowGraph<T> {
    private int[][] neighbourMatrix;
    private List<T> vertices;

    public MatrixBowGraph() {
        this.neighbourMatrix = new int[0][0];
        this.vertices = new ArrayList<>();
    }

    @Override
    public boolean addVertex(T t) {
        if (this.vertices.contains(t)) {
            return false;
        } else {
            int[][] newMatrix = new int[this.vertexCount() + 1][this.vertexCount() + 1];
            for (int i = 0; i < this.vertexCount(); i++) {
                for (int j = 0; j < this.vertexCount(); j++) {
                    newMatrix[i][j] = this.neighbourMatrix[i][j];
                }
            }
            this.vertices.add(t);
            this.neighbourMatrix = newMatrix;
            return true;
        }
    }

    @Override
    public boolean addBow(T t1, T t2, int weight) {
        int fromIndex = this.vertices.indexOf(t1);
        int toIndex = this.vertices.indexOf(t2);
        if (fromIndex < 0 || toIndex < 0 || this.neighbourMatrix[fromIndex][toIndex] != 0) {
            return false;
        }
        this.neighbourMatrix[fromIndex][toIndex] = weight;
        return true;
    }

    @Override
    public boolean deleteVertex(T t) {
        // get index of vertex to delete
        int index = this.vertices.indexOf(t);

        // check if there are no edges connected with vertex to delete; if so return false
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.neighbourMatrix[index][i] != 0 || this.neighbourMatrix[i][index] != 0) {
                return false;
            }
        }

        // create new neighbour matrix...
        int[][] newMatrix = new int[this.neighbourMatrix.length - 1][this.neighbourMatrix.length - 1];

        // ...and copy old matrix omitting values related to deleted edge
        for (int i = 0, iPrim = 0; i < newMatrix.length; i++, iPrim++) {
            if (iPrim == index) {
                iPrim++;
            }
            for (int j = 0, jPrim = 0; j < newMatrix.length; j++, jPrim++) {
                if (jPrim == index) {
                    jPrim++;
                }
                newMatrix[i][j] = this.neighbourMatrix[iPrim][jPrim];
            }
        }

        //remove edge from edge list
        this.vertices.remove(index);
        return true;
    }

    @Override
    public boolean deleteBow(T t1, T t2) {
        int fromIndex = this.vertices.indexOf(t1);
        int toIndex = this.vertices.indexOf(t2);
        if (fromIndex < 0 || toIndex < 0 || this.neighbourMatrix[fromIndex][toIndex] == 0) {
            return false;
        }
        this.neighbourMatrix[fromIndex][toIndex] = 0;
        return true;
    }

    @Override
    public boolean hasVertex(T t) {
        return (this.vertices.contains(t));
    }

    @Override
    public boolean hasBow(T t1, T t2) {
        int fromIndex = this.vertices.indexOf(t1);
        int toIndex = this.vertices.indexOf(t2);
        return !(fromIndex < 0 || toIndex < 0 || this.neighbourMatrix[fromIndex][toIndex] == 0);
    }

    @Override
    public int bowWeight(T t1, T t2) {
        int fromIndex = this.vertices.indexOf(t1);
        int toIndex = this.vertices.indexOf(t2);
        return this.neighbourMatrix[fromIndex][toIndex];
    }

    @Override
    public int vertexCount() {
        return this.vertices.size();
    }

    @Override
    public int bowCount() {
        int result = 0;
        for (int i = 0; i < this.neighbourMatrix.length; i++) {
            for (int j = 0; j < this.neighbourMatrix.length; j++) {
                if (this.neighbourMatrix[i][j] != 0) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public List<T> BFS(T t, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        List<Integer> vertexIndexesToCheck = new ArrayList<>();
        int checkedVertexIndex = this.vertices.indexOf(t);
        T checkedVertex;


        if (this.vertices.contains(t)) {
            result.add(t);
            if (predicate.test(t)) {
                return result;
            }
            for (int i = 0; i < this.vertexCount(); i++) {
                if (this.neighbourMatrix[checkedVertexIndex][i] != 0) {
                    vertexIndexesToCheck.add(i);
                }
            }

            while (!vertexIndexesToCheck.isEmpty()) {
                checkedVertexIndex = vertexIndexesToCheck.remove(0);
                checkedVertex = this.vertices.get(checkedVertexIndex);
                if (!result.contains(checkedVertex)) {
                    result.add(checkedVertex);
                    if (predicate.test(checkedVertex)) {
                        return result;
                    }
                    for (int i = 0; i < this.vertexCount(); i++) {
                        if (this.neighbourMatrix[checkedVertexIndex][i] != 0) {
                            vertexIndexesToCheck.add(i);
                        }
                    }
                }
            }
        }
        return new ArrayList<T>();
    }

    @Override
    public List<T> DFS(T t, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        List<Integer> vertexIndexesToCheck = new ArrayList<>();
        int checkedVertexIndex = this.vertices.indexOf(t);
        T checkedVertex;


        if (this.vertices.contains(t)) {
            result.add(t);
            if (predicate.test(t)) {
                return result;
            }
            for (int i = 0; i < this.vertexCount(); i++) {
                if (this.neighbourMatrix[checkedVertexIndex][i] != 0) {
                    vertexIndexesToCheck.add(i);
                }
            }

            while (!vertexIndexesToCheck.isEmpty()) {
                checkedVertexIndex = vertexIndexesToCheck.remove(vertexIndexesToCheck.size() - 1);
                checkedVertex = this.vertices.get(checkedVertexIndex);
                if (!result.contains(checkedVertex)) {
                    result.add(checkedVertex);
                    if (predicate.test(checkedVertex)) {
                        return result;
                    }
                    for (int i = 0; i < this.vertexCount(); i++) {
                        if (this.neighbourMatrix[checkedVertexIndex][i] != 0) {
                            vertexIndexesToCheck.add(i);
                        }
                    }
                }
            }
        }
        return new ArrayList<T>();
    }

    public String toString() {
        int maxLength = this.vertices.get(0).toString().length();
        int pretender;
        for (int i = 0; i < this.vertices.size(); i++) {
            pretender = this.vertices.get(i).toString().length();
            if (pretender > maxLength) {
                maxLength = pretender;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(center("", maxLength)).append(" ");
        for (T t : this.vertices) {
            result.append(" ").append(center(t.toString(), maxLength)).append(" ");
        }
        for (int i = 0; i < this.vertexCount(); i++) {
            result.append('\n');
            result.append(center(this.vertices.get(i).toString(), maxLength)).append(" ");
            for (int j = 0; j < this.vertexCount(); j++) {
                result.append("[").append(center(String.valueOf(this.neighbourMatrix[i][j]), maxLength)).append("]");
            }
        }
        return result.toString();
    }

    private static String center(String string, int length) {
        if (length < string.length())
            throw new IllegalArgumentException("String is longer than the designated placeholder");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < (length - string.length()) / 2; i++) result.append(" ");
        result.append(string);
        while (result.length() < length) result.append(" ");
        return result.toString();
    }
}
