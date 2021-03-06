package graph.edge;

import utils.StringFormatter;
import utils.comparator.NaturalComparator;
import utils.comparator.ReverseComparator;
import utils.queue.HeapQueue;
import utils.queue.PriorityQueue;
import utils.storage.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Karol Pokomeda on 2017-06-06.
 */
public class IncidenceEdgeGraph<T> implements MyEdgeGraph<T> {
    private List<T> vertexList;
    private int[][] incidenceMatrix;

    public IncidenceEdgeGraph() {
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
    public boolean addEdge(T t1, T t2, int weight) {
        int start;
        int end;
        if (
                (start = this.vertexList.indexOf(t1)) >= 0
                        && (end = this.vertexList.indexOf(t2)) >= 0
                        && !this.hasEdge(t1, t2)
                ) {
            int[][] newMatrix = new int[this.incidenceMatrix.length][this.incidenceMatrix[0].length + 1];
            for (int i = 0; i < this.incidenceMatrix.length; i++) {
                for (int j = 0; j < this.incidenceMatrix[i].length; j++) {
                    newMatrix[i][j] = this.incidenceMatrix[i][j];
                }
            }
            this.incidenceMatrix = newMatrix;
            this.incidenceMatrix[start][this.incidenceMatrix[start].length - 1] = weight;
            this.incidenceMatrix[end][this.incidenceMatrix[end].length - 1] = weight;
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
        for (int i = 0; i < this.incidenceMatrix[vertexIndex].length; i++) {
            if (this.incidenceMatrix[vertexIndex][i] != 0) {
                return false;
            }
        }
        this.vertexList.remove(vertexIndex);
        int[][] newMatrix = this.incidenceMatrix.length == 1 ?
                new int[0][0]
                : new int[this.incidenceMatrix.length - 1][this.incidenceMatrix[0].length];
        for (int i = 0, iPrim = 0; i < newMatrix.length; i++, iPrim++) {
            for (int j = 0; j < newMatrix[0].length; j++) {
                if (i == vertexIndex) {
                    iPrim++;
                }
                newMatrix[i][j] = this.incidenceMatrix[iPrim][j];
            }
        }
        return true;
    }

    @Override
    public boolean deleteEdge(T t1, T t2) {
        int start;
        int end;
        if ((start = this.vertexList.indexOf(t1)) >= 0
                && (end = this.vertexList.indexOf(t2)) >= 0) {
            int bowIndex = -1;
            for (int i = 0; i < this.incidenceMatrix[start].length; i++) {
                if (this.incidenceMatrix[start][i] != 0 && this.incidenceMatrix[start][i] == this.incidenceMatrix[end][i]){
                    bowIndex = i;
                    break;
                }
            }
            if (bowIndex >= 0) {
                int[][] newMatrix = new int[this.incidenceMatrix.length][this.incidenceMatrix[0].length - 1];
                for (int i = 0; i < newMatrix.length; i++) {
                    for (int j = 0, jPrim = 0; j < newMatrix[0].length; j++, jPrim++) {
                        if (j == bowIndex) {
                            jPrim++;
                        }
                        newMatrix[i][j] = this.incidenceMatrix[i][jPrim];
                    }
                }
                this.incidenceMatrix = newMatrix;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasVertex(T t) {
        return this.vertexList.contains(t);
    }

    @Override
    public boolean hasEdge(T t1, T t2) {
        int start;
        int end;
        if ((start = this.vertexList.indexOf(t1)) >= 0
                && (end = this.vertexList.indexOf(t2)) >= 0) {
            for (int i = 0; i < this.incidenceMatrix[start].length; i++) {
                if (this.incidenceMatrix[start][i] != 0 && this.incidenceMatrix[start][i] == this.incidenceMatrix[end][i]){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int edgeWeight(T t1, T t2) {
        int start;
        int end;
        if ((start = this.vertexList.indexOf(t1)) >= 0
                && (end = this.vertexList.indexOf(t2)) >= 0) {
            for (int i = 0; i < this.incidenceMatrix[start].length; i++) {
                if (this.incidenceMatrix[start][i] != 0 && this.incidenceMatrix[start][i] == this.incidenceMatrix[end][i]){
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
    public int edgeCount() {
        return this.incidenceMatrix.length != 0 ? this.incidenceMatrix[0].length : 0;
    }

    @Override
    public MyEdgeGraph<T> MST() {
        // create empty graph for new tree representation
        MyEdgeGraph<T> result = new MatrixEdgeGraph<T>();
        if (this.vertexCount() == 0) return result;

        // add first vertex to the tree
        T vertexToAdd = this.vertexList.get(0);
        result.addVertex(vertexToAdd);

        // add edges connected with first element to the heap queue
        PriorityQueue<Edge<T>> edgesToCheck = new HeapQueue<Edge<T>>(new ReverseComparator<Edge<T>>(new NaturalComparator<Edge<T>>()));
        for (T neighbour : this.vertexList) {
            if (this.hasEdge(vertexToAdd, neighbour)) {
                edgesToCheck.add(new Edge<>(vertexToAdd, neighbour, this.edgeWeight(vertexToAdd, neighbour)));
            }
        }

        while (!edgesToCheck.isEmpty()) {
            Edge<T> edge = edgesToCheck.remove();
            if (!result.hasVertex(edge.getDestination())) {
                result.addVertex(edge.getDestination());
                result.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
                vertexToAdd = edge.getDestination();
                for (T neighbour : this.vertexList) {
                    if (this.hasEdge(vertexToAdd, neighbour)) {
                        edgesToCheck.add(new Edge<>(vertexToAdd, neighbour, this.edgeWeight(vertexToAdd, neighbour)));
                    }
                }
            }
        }

        if (result.vertexCount() != this.vertexCount()) {
            throw new NullPointerException("Graph is not consistent");
        }
        return result;
    }

    @Override
    public List<T> BFS(T t, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        List<Integer> vertexIndexesToCheck = new ArrayList<>();
        int checkedVertexIndex = this.vertexList.indexOf(t);
        T checkedVertex;
        if (this.vertexList.contains(t)) {
            result.add(t);
            if (predicate.test(t)) {
                return result;
            }
            for (int i = 0; i < this.incidenceMatrix[checkedVertexIndex].length; i++) {
                if (this.incidenceMatrix[checkedVertexIndex][i] != 0) {
                    for (int j = 0; j < this.incidenceMatrix.length; j++) {
                        if (this.incidenceMatrix[j][i] != 0 && j != checkedVertexIndex) {
                            vertexIndexesToCheck.add(j);
                            break;
                        }
                    }
                }
            }

            while (!vertexIndexesToCheck.isEmpty()){
                checkedVertexIndex = vertexIndexesToCheck.remove(0);
                checkedVertex = this.vertexList.get(checkedVertexIndex);
                if (!result.contains(checkedVertex)){
                    result.add(checkedVertex);
                    if (predicate.test(checkedVertex)) {
                        return result;
                    }
                    for (int i = 0; i < this.incidenceMatrix[checkedVertexIndex].length; i++) {
                        if (this.incidenceMatrix[checkedVertexIndex][i] != 0) {
                            for (int j = 0; j < this.incidenceMatrix.length; j++) {
                                if (this.incidenceMatrix[j][i] != 0 && j != checkedVertexIndex) {
                                    vertexIndexesToCheck.add(j);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<T> DFS(T t, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        List<Integer> vertexIndexesToCheck = new ArrayList<>();
        int checkedVertexIndex = this.vertexList.indexOf(t);
        T checkedVertex;
        if (this.vertexList.contains(t)) {
            result.add(t);
            if (predicate.test(t)) {
                return result;
            }
            for (int i = 0; i < this.incidenceMatrix[checkedVertexIndex].length; i++) {
                if (this.incidenceMatrix[checkedVertexIndex][i] != 0) {
                    for (int j = 0; j < this.incidenceMatrix.length; j++) {
                        if (this.incidenceMatrix[j][i] != 0 && j != checkedVertexIndex) {
                            vertexIndexesToCheck.add(j);
                            break;
                        }
                    }
                }
            }

            while (!vertexIndexesToCheck.isEmpty()){
                checkedVertexIndex = vertexIndexesToCheck.remove(vertexIndexesToCheck.size() - 1);
                checkedVertex = this.vertexList.get(checkedVertexIndex);
                if (!result.contains(checkedVertex)){
                    result.add(checkedVertex);
                    if (predicate.test(checkedVertex)) {
                        return result;
                    }
                    for (int i = 0; i < this.incidenceMatrix[checkedVertexIndex].length; i++) {
                        if (this.incidenceMatrix[checkedVertexIndex][i] != 0) {
                            for (int j = 0; j < this.incidenceMatrix.length; j++) {
                                if (this.incidenceMatrix[j][i] != 0 && j != checkedVertexIndex) {
                                    vertexIndexesToCheck.add(j);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public String toString(){
        int maxLength = this.vertexList.get(0).toString().length();
        int pretender;
        for (int i = 1; i < this.vertexList.size(); i++) {
            pretender = this.vertexList.get(i).toString().length();
            if (pretender > maxLength) {
                maxLength = pretender;
            }
        }
        for (int i = 0; i < this.incidenceMatrix.length; i++){
            for (int j = 0; j < this.incidenceMatrix[i].length; j++) {
                pretender = String.valueOf(this.incidenceMatrix[i][j]).length();
                if (pretender > maxLength) {
                    maxLength = pretender;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(StringFormatter.center("", maxLength)).append(" ");
        for (T vertex : this.vertexList) {
            result.append(" ").append(StringFormatter.center(vertex.toString(), maxLength)).append(" ");
        }
        for (T vertex : this.vertexList) {
            result.append('\n');
            result.append(StringFormatter.center(vertex.toString(), maxLength)).append(" ");
            for (T neighbour : this.vertexList){
                int bowWeight = this.edgeWeight(vertex, neighbour);
                result.append("[").append(StringFormatter.center(Integer.toString(bowWeight), maxLength)).append("]");
            }
        }
        return result.toString();
    }
}
