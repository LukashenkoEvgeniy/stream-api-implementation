package ua.procamp.streams.stream;
import ua.procamp.streams.function.*;
import ua.procamp.streams.iterables.FilterIterable;

import java.util.ArrayList;
import java.util.Iterator;

import static ua.procamp.streams.utils.ArrayUtils.combine;
import static ua.procamp.streams.utils.ArrayUtils.convertArrayListtoArray;

public class AsIntStream implements IntStream {

    private int[] values;
    private ArrayList<Iterable> iterables = new ArrayList<>();

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

    public AsIntStream lazyComputing() {
        ArrayList arrayList = new ArrayList();
        if(iterables.size() > 0){
            iterables.forEach(iterable -> {
                Iterator iterator =  iterable.iterator();
                //TODO There are you need result of interator loop insert into next iterator
                while (iterator.hasNext()){
                    arrayList.add(iterator.next());
                }
            });
            return new AsIntStream(convertArrayListtoArray(arrayList));
        }
        return this;
    }


        @Override
    public long count() {
        AsIntStream lazyComputedResult = lazyComputing();
        return lazyComputedResult.toArray().length;
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
        FilterIterable filterIterable = new FilterIterable(values, predicate);
        iterables.add(filterIterable);
        return this;
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
        return new AsIntStream(convertArrayListtoArray(resultArray));

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
