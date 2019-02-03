package ua.procamp.streams.iterables;

import ua.procamp.streams.function.IntPredicate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static ua.procamp.streams.utils.ArrayUtils.convertIntToIntegerArrray;

public class FilterIterable implements Iterable {

    private List<Integer> iter;
    private IntPredicate predicate;


    public FilterIterable(int[] iter, IntPredicate predicate) {
        //TODO Convert to ArrayList
        this.iter = Arrays.asList(convertIntToIntegerArrray(iter));
        this.predicate = predicate;
    }

    @Override
    public Iterator iterator() {
        return new FilterIterator();
    }

    class FilterIterator implements Iterator{

        private Iterator<Integer> iterator = iter.iterator();
        private Integer next = null;

        FilterIterator() {
            getNext();
        }

        private void getNext() {
            next = null;
            while (iterator.hasNext()) {
                Integer temp = iterator.next();
                if (predicate.test(temp)) {
                    next = temp;
                    break;
                }
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
