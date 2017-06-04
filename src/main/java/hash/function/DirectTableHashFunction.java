package hash.function;

/**
 * Created by Karol Pokomeda on 2017-05-27.
 */
public class DirectTableHashFunction implements HashFunction<Integer> {
    private int minIndexNumber;
    private int maxIndexNumber;

    public DirectTableHashFunction(int minIndexNumber, int maxIndexNumber){
        this.minIndexNumber = minIndexNumber;
        this.maxIndexNumber = maxIndexNumber;
    }

    @Override
    public int hashRange() {
        return this.maxIndexNumber - this.minIndexNumber + 1;
    }

    @Override
    public int hash(Integer value) {
        return value - this.minIndexNumber;
    }
}