package hash.collection;

import hash.function.HashFunction;
import utils.StorageElement;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-05-25.
 */
public class ChainHashMap<K,V> implements MyHashMap<K,V> {
    private ArrayList<StorageElement<K,V>>[] tableOfChains;
    private HashFunction<K> hashFunction;

    public ChainHashMap(HashFunction<K> hashFunction){
        this.hashFunction = hashFunction;
        this.tableOfChains = (ArrayList<StorageElement<K,V>>[]) new ArrayList[this.hashFunction.hashRange()];
    }

    @Override
    public boolean add(K key, V value) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.tableOfChains.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        int indexInChain;
        if (this.tableOfChains[valueIndex] == null){
            this.tableOfChains[valueIndex] = new ArrayList<>();
        } else {
            for (StorageElement<K, V> internalContainer : this.tableOfChains[valueIndex]) {
                if (internalContainer.hasKey(key)) {
                    indexInChain = this.tableOfChains[valueIndex].indexOf(internalContainer);
                    this.tableOfChains[valueIndex].set(indexInChain, new StorageElement<>(key, value));
                    return true;
                }
            }
        }
        this.tableOfChains[valueIndex].add(new StorageElement<>(key, value));
        return true;
    }

    @Override
    public boolean delete(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.tableOfChains.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        int indexInChain = -1;
        for (StorageElement<K,V> internalContainer : this.tableOfChains[valueIndex]) {
            if (internalContainer.hasKey(key)) {
                indexInChain = this.tableOfChains[valueIndex].indexOf(internalContainer);
                this.tableOfChains[valueIndex].remove(indexInChain);
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.tableOfChains.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        int indexInChain;
        for (StorageElement<K,V> internalContainer : this.tableOfChains[valueIndex]) {
            if (internalContainer.hasKey(key)) {
                indexInChain = this.tableOfChains[valueIndex].indexOf(internalContainer);
                return this.tableOfChains[valueIndex].get(indexInChain).getValue(key);
            }
        }
        throw new NullPointerException("There is no value connected with provided key");
    }

    @Override
    public boolean has(K key) {
        int valueIndex = this.hashFunction.hash(key);
        if (valueIndex < 0 || valueIndex >= this.tableOfChains.length) throw new IndexOutOfBoundsException("Wrong hash value for provided key");
        if (this.tableOfChains[valueIndex] == null) return false;
        for (StorageElement<K,V> internalContainer : this.tableOfChains[valueIndex]) {
            if (internalContainer.hasKey(key)) {
                return true;
            }
        }
        return false;
    }
}
