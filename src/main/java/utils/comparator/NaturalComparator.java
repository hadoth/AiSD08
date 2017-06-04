package utils.comparator;

/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class NaturalComparator<T extends Comparable<T>> implements Comparator<T> {

    @Override
    public int compare(T left, T right) {
        return left.compareTo(right);
    }
}
