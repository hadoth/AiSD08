package graph.bow;

import utils.StringFormatter;
import utils.storage.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Karol Pokomeda on 2017-06-01.
 */
public class ListBowGraph<T> implements MyBowGraph<T> {
    private List<Vertex<T>> vertices;

    public ListBowGraph() {
        this.vertices = new ArrayList<>();
    }

    @Override
    public boolean addVertex(T t) {
        return !this.hasVertex(t) && this.vertices.add(new Vertex<T>(t));
    }

    @Override
    public boolean addBow(T t1, T t2, int weight) {
        if (weight == 0) return false;
        Optional<Vertex<T>> start = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t1)).findFirst();
        Optional<Vertex<T>> end = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t2)).findFirst();
        return start.isPresent() && end.isPresent() && !start.get().hasNeighbour(t2) && start.get().addNeighbour(end.get(), weight);
    }

    @Override
    public boolean deleteVertex(T t) {
        Optional<Vertex<T>> vertexToDelete = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t)).findFirst();
        return vertexToDelete.isPresent() && isFree(vertexToDelete.get()) && this.vertices.remove(vertexToDelete.get());
    }

    @Override
    public boolean deleteBow(T t1, T t2) {
        Optional<Vertex<T>> start = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t1)).findFirst();
        Optional<Vertex<T>> end = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t2)).findFirst();
        return start.isPresent() && end.isPresent() && start.get().hasNeighbour(t2) && start.get().deleteNeighbour(t2);
    }

    @Override
    public boolean hasVertex(T t) {
        return this.vertices.stream().anyMatch((Vertex<T> vertex) -> vertex.getValue().equals(t));
    }

    @Override
    public boolean hasBow(T t1, T t2) {
        Optional<Vertex<T>> start = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t1)).findFirst();
        return start.isPresent() && start.get().hasNeighbour(t2);
    }

    @Override
    public int bowWeight(T t1, T t2) {
        Optional<Vertex<T>> start = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t1)).findFirst();
        Optional<Vertex<T>> end = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t2)).findFirst();
        return start.isPresent() && end.isPresent() && start.get().hasNeighbour(t2) ? start.get().getNeighbourWeight(end.get()) : 0;
    }

    @Override
    public int vertexCount() {
        return this.vertices.size();
    }

    @Override
    public int bowCount() {
        int result = 0;
        for (Vertex<T> vertex : this.vertices) {
            result += vertex.getNeighbours().size();
        }
        return result;
    }

    @Override
    public List<T> BFS(T t, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        List<Vertex<T>> verticesToCheck = new ArrayList<>();
        Vertex<T> processedVertex;

        if (this.hasVertex(t)) {
            processedVertex = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t)).findFirst().get();
            result.add(t);
            if (predicate.test(t)) {
                return result;
            }
            verticesToCheck.addAll(processedVertex.getNeighbours());

            while (!verticesToCheck.isEmpty()) {
                processedVertex = verticesToCheck.remove(0);
                if (!result.contains(processedVertex.getValue())) {
                    result.add(processedVertex.getValue());
                    if (predicate.test(processedVertex.getValue())) {
                        return result;
                    }
                    verticesToCheck.addAll(processedVertex.getNeighbours());
                }
            }
        }
        return new ArrayList<T>();
    }

    @Override
    public List<T> DFS(T t, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        List<Vertex<T>> verticesToCheck = new ArrayList<>();
        Vertex<T> processedVertex;

        if (this.hasVertex(t)) {
            processedVertex = this.vertices.stream().filter((Vertex<T> vertex) -> vertex.getValue().equals(t)).findFirst().get();
            result.add(t);
            if (predicate.test(t)) {
                return result;
            }
            verticesToCheck.addAll(processedVertex.getNeighbours());

            while (!verticesToCheck.isEmpty()) {
                processedVertex = verticesToCheck.remove(verticesToCheck.size() - 1);
                if (!result.contains(processedVertex.getValue())) {
                    result.add(processedVertex.getValue());
                    if (predicate.test(processedVertex.getValue())) {
                        return result;
                    }
                    verticesToCheck.addAll(processedVertex.getNeighbours());
                }
            }
        }
        return new ArrayList<T>();
    }

    private boolean isFree(Vertex<T> vertexToCheck) {
        if (vertexToCheck.getNeighbours().size() != 0) {
            return false;
        }
        for (Vertex<T> vertex : this.vertices) {
            if (vertex.hasNeighbour(vertexToCheck.getValue())) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        int maxLength = this.vertices.get(0).getValue().toString().length();
        int pretender;
        for (int i = 0; i < this.vertices.size(); i++) {
            pretender = this.vertices.get(i).getValue().toString().length();
            if (pretender > maxLength) {
                maxLength = pretender;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(StringFormatter.center("", maxLength)).append(" ");
        for (Vertex<T> vertex : this.vertices) {
            result.append(" ").append(StringFormatter.center(vertex.getValue().toString(), maxLength)).append(" ");
        }
        for (Vertex<T> vertex : this.vertices) {
            result.append('\n');
            result.append(StringFormatter.center(vertex.getValue().toString(), maxLength)).append(" ");
            for (Vertex<T> neighbour : this.vertices){
                result.append("[").append(StringFormatter.center(Integer.toString(vertex.getNeighbourWeight(neighbour)), maxLength)).append("]");
            }
        }
        return result.toString();
    }
}
