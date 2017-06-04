package utils.queue;


import utils.comparator.Comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-29.
 */
public class HeapQueue<T> implements PriorityQueue<T> {
    private List<T> internalList;
    private Comparator<T> comparator;

    public HeapQueue(Comparator<T> comparator){
        this.comparator = comparator;
        this.internalList = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    @Override
    public boolean add(T t) {
        boolean result =  this.internalList.add(t);
        this.swim(this.size()-1);
        return result;
    }

    @Override
    public T remove() {
        swap(this.internalList, 0, this.size() - 1);
        T result = this.internalList.get(this.size() - 1);
        this.internalList.remove(this.size() - 1);
        this.sink(0);
        return result;
    }

    @Override
    public T peek() {
        return this.internalList.get(this.size() - 1);
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void setInternalList(List<T> internalList) {
        this.internalList = internalList;
        this.internalList.clear();
    }

    private void swim(int indexToSwim){
        if (indexToSwim <= 0) return;
        int parentIndex = (indexToSwim-1)/2;
        if (this.comparator.compare(this.internalList.get(indexToSwim), this.internalList.get(parentIndex)) > 0){
            swap(this.internalList, indexToSwim, parentIndex);
            this.swim(parentIndex);
        }
    }

    private void sink(int indexToSink){
        int childIndex = 2 * indexToSink + 1;
        if (childIndex >= this.size()) return;
        if (
            childIndex + 1 < this.size() &&
            this.comparator.compare(this.internalList.get(childIndex), this.internalList.get(childIndex+1)) < 0
        ) childIndex++;
        if (this.comparator.compare(this.internalList.get(indexToSink), this.internalList.get(childIndex)) < 0) {
            swap(internalList, indexToSink, childIndex);
            this.sink(childIndex);
        }
    }

    private static <T> void swap(List<T> list, int left, int right){
        T leftElem = list.get(left);
        list.set(left, list.get(right));
        list.set(right, leftElem);
    }
}
