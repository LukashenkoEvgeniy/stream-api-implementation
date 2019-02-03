package ua.procamp.streams.iterables;

import ua.procamp.streams.function.IntPredicate;
import ua.procamp.streams.function.IntUnaryOperator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static ua.procamp.streams.utils.ArrayUtils.convertIntToIntegerArrray;

public class MapIterable implements Iterable {

    private List<Integer> iter;
    private IntUnaryOperator predicate;


    public MapIterable(int[] iter, IntUnaryOperator mapper) {
        this.iter = Arrays.asList(convertIntToIntegerArrray(iter));
        this.predicate = mapper;
    }

    @Override
    public Iterator iterator() {
        return new MapIterator();
    }

    class MapIterator implements Iterator{

        private Iterator<Integer> iterator = iter.iterator();
        private Integer next = null;

        MapIterator() {
            getNext();
        }

        private void getNext() {
            next = null;
            while (iterator.hasNext()) {
                Integer temp = iterator.next();
                predicate.apply(temp);
            }
        }

        public boolean hasNext() { return next != null; }

        public Integer next() {
            int temp = next;
            getNext();
            return temp;
        }
    }
}
