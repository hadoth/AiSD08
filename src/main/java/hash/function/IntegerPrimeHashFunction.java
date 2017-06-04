package hash.function;

import org.apache.commons.math3.primes.Primes;

/**
 * Created by Karol Pokomeda on 2017-05-27.
 */
public class IntegerPrimeHashFunction implements HashFunction<Integer> {

    private int maxValue;
    private int prime;

    public IntegerPrimeHashFunction(int maxValue) {
        this.maxValue = maxValue;
        this.prime = Primes.nextPrime(this.maxValue);
    }

    public int hashRange() {
        return this.maxValue;
    }

    public int hash(Integer integer) {
        return (integer % this.prime) % this.maxValue;
    }
}
