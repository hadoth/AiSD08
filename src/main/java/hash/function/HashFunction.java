package hash.function;

/**
 * Created by Karol Pokomeda on 2017-05-23.
 */
public interface HashFunction<T> {
    int hashRange();
    int hash(T t);
}
