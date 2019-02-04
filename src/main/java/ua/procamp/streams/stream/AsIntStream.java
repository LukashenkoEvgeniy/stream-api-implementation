package ua.procamp.streams.stream;
import ua.procamp.streams.function.*;

import java.util.ArrayList;

import static ua.procamp.streams.utils.ArrayUtils.combine;
import static ua.procamp.streams.utils.ArrayUtils.convertArrayListToArray;

public class AsIntStream implements IntStream {

    private int[] values;

    private AsIntStream(int... values) {
        this.values = values;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        if (values.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        return (double) (sum() / count());
    }

    @Override
    public Integer max() {
        if (values.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        return reduce(values[0], (max, nextValue) -> nextValue > max ? nextValue : max);
    }

    @Override
    public Integer min() {
        if (values.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        return reduce(values[0], (min, nextValue) -> nextValue < min ? nextValue : min);
    }

    @Override
    public long count() {
        return values.length;
    }

    @Override
    public Integer sum() {
        if (values.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        return reduce(0, (x,y) -> x + y);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> resultArray = new ArrayList<>();
        this.forEach(value -> {
            boolean test = predicate.test(value);
            if (test) {
                resultArray.add(value);
            }
        });
        return new AsIntStream(convertArrayListToArray(resultArray));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int value : values) {
            action.accept(value);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> resultArray = new ArrayList<>();
        for (int value : values) {
            resultArray.add(mapper.apply(value));
        }
        return new AsIntStream(convertArrayListToArray(resultArray));

    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        int [] test = {};
        for (int value : values) {
            test = combine(test, func.applyAsIntStream(value).toArray());
        }
        return new AsIntStream(test);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (int value : values) {
            identity = op.apply(identity, value);
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        return this.values;
    }

}
