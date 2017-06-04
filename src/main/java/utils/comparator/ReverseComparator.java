package utils.comparator;

/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class ReverseComparator<T> implements Comparator<T> {
    private Comparator<T> internalComparator;

    public ReverseComparator(Comparator<T> internalComparator){
        this.internalComparator = internalComparator;
    }

    @Override
    public int compare(T left, T right) {
        return this.internalComparator.compare(right, left);
    }
}
