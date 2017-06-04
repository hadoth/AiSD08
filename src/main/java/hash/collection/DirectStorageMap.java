package hash.collection;

import hash.function.HashFunction;

/**
 * Created by Karol Pokomeda on 2017-05-24.
 */
public class DirectStorageMap<K,V> implements MyHashMap<K,V> {
    private HashFunction<K> hashFunction;
    private V[] valueTable;

    public DirectStorageMap(HashFunction<K> hashFunction){
        this.hashFunction = hashFunction;
        this.valueTable = (V[]) new Object[this.hashFunction.hashRange()];
    }

    @Override
    public boolean add(K key, V value) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.valueTable.length) return false;
        if (this.valueTable[valueIndex] != null) return false;
        this.valueTable[valueIndex] = value;
        return true;
    }

    @Override
    public boolean delete(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.valueTable.length || this.valueTable[valueIndex] == null) return false;
        this.valueTable[valueIndex] = null;
        return true;
    }

    @Override
    public V get(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.valueTable.length) throw new IndexOutOfBoundsException("Illegal key value");
        if (this.valueTable[valueIndex] == null) throw new NullPointerException("There is no value connected with provided key");
        return this.valueTable[valueIndex];
    }

    @Override
    public boolean has(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.valueTable.length) throw new IndexOutOfBoundsException("Illegal key value");
        if (this.valueTable[valueIndex] == null) return false;
        return true;
    }
}