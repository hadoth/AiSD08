package hash.collection;

import hash.function.HashFunction;

/**
 * Created by Karol Pokomeda on 2017-05-23.
 */
public interface MyHashMap<K, V> {
    boolean add(K key, V value);
    boolean delete(K key);
    V get(K key) throws NullPointerException;
    boolean has(K key);
}
