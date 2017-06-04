package utils;

/**
 * Created by Karol Pokomeda on 2017-05-25.
 */
public class StorageElement<K, V> {
    private K key;
    private V value;

    public StorageElement(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public boolean hasKey(K key) {
        return this.key.equals(key);
    }

    public K getKey() {
        return this.key;
    }

    public V getValue(K key) throws IllegalArgumentException {
        if (this.key.equals(key)) {
            return this.value;
        } else {
            throw new IllegalArgumentException("Wrong key value");
        }
    }
}
