package ua.procamp.streams;

import ua.procamp.streams.stream.AsIntStream;
import ua.procamp.streams.stream.IntStream;

import java.util.ArrayList;
import java.util.List;

public class StreamApp {

    public static int streamOperations(IntStream intStream) {
        //IntStream intStream = AsIntStream.of(-1, 0, 1, 2, 3); // input values
        int res = intStream
                .filter(x -> x > 0) // 1, 2, 3
                .map(x -> x * x) // 1, 4, 9
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)) // 0, 1, 2, 3, 4, 5, 8, 9, 10
                .reduce(0, (a, b) -> a + b); // 42
        return res;
    }

    public static int[] streamToArray(IntStream intStream) {
        int[] intArr = intStream.toArray();
        return intArr;
    }

    public static String streamForEach(IntStream intStream) {
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        return str.toString();
    }






    public static int localTest(IntStream intStream) {
        IntStream stream = AsIntStream.of(-1, 0, 1, 2, 3); // input values
        stream.filter(x -> x > 0);

        int[] a = AsIntStream.of(-1, 0, 1, 2, 3).filter(x -> x > 0).toArray();
        System.out.println(a[0]);
        System.out.println(a[1]);
//

        return 1;
    }


    @Override
    public String toString() {
        List list = new ArrayList();
        list.stream();
        return super.toString();
    }
}
