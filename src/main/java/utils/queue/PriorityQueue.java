package utils.queue;


import utils.comparator.Comparator;

import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-04-28.
 */
public interface PriorityQueue<T>{
    int size();
    boolean isEmpty();
    boolean add(T t);
    T remove();
    T peek();
    void setComparator(Comparator<T> comparator);
    void setInternalList(List<T> internalList);
}
