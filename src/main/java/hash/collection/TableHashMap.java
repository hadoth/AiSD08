package hash.collection;

import hash.function.HashFunction;
import utils.storage.StorageElement;

/**
 * Created by Karol Pokomeda on 2017-05-24.
 */
public class TableHashMap<K,V> implements MyHashMap<K,V> {
    private HashFunction<K> hashFunction;
    private StorageElement<K,V>[] keyValueTable;

    public TableHashMap(HashFunction<K> hashFunction){
        this.hashFunction = hashFunction;
        this.keyValueTable = (StorageElement<K,V>[]) new StorageElement[this.hashFunction.hashRange()];
    }

    @Override
    public boolean add(K key, V value) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.keyValueTable.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        int index;
        for (int i = 0; i < this.keyValueTable.length; i++){
            index = (i+valueIndex)%this.keyValueTable.length;
            if (this.keyValueTable[index] == null || this.keyValueTable[index].hasKey(key)){
                this.keyValueTable[index] = new StorageElement<>(key, value);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.keyValueTable.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        int index;
        for (int i = 0; i < this.keyValueTable.length; i++){
            index = (i+valueIndex)%this.keyValueTable.length;
            if (this.keyValueTable[index].hasKey(key)){
                this.keyValueTable[index] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.keyValueTable.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        int index;
        for (int i = 0; i < this.keyValueTable.length; i++){
            index = (i+valueIndex)%this.keyValueTable.length;
            if (this.keyValueTable[index] != null && this.keyValueTable[index].hasKey(key)){
                return this.keyValueTable[index].getValue(key);
            }
        }
        throw new NullPointerException("There is no value connected with provided key");
    }

    @Override
    public boolean has(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.keyValueTable.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        int index;
        for (int i = 0; i < this.keyValueTable.length; i++){
            index = (i+valueIndex)%this.keyValueTable.length;
            if (this.keyValueTable[index] != null && this.keyValueTable[index].hasKey(key)){
                return true;
            }
        }
        return false;
    }
}
