package ua.procamp.streams.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream intStream;
    private IntStream emptyStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        int[] emptyArr = {};
        intStream = AsIntStream.of(intArr);
        emptyStream = AsIntStream.of(emptyArr);
    }

    @Test
    public void testFilter() {
        System.out.println("stream - filter");
        int[] expResult = new int[]{1, 2, 3};
        int[] result = intStream.filter(x -> x > 0).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testToArray() {
        System.out.println("stream - toArray");
        int[] streamToArray = intStream.toArray();
        boolean ifArray = streamToArray.getClass().isArray();
        assertTrue(ifArray);
    }

    @Test
    public void testForEach() {
        System.out.println("stream - forEach");
        String expResult = "-10123";
        StringBuilder str = new StringBuilder();
        intStream.forEach(str::append);
        assertEquals(expResult, str.toString());
    }

    @Test
    public void testMap() {
        System.out.println("stream - map");
        int[] expResult = new int[]{-10, 0, 10, 20, 30};
        int[] result = intStream.map(x -> x * 10).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        System.out.println("stream - reduce");
        int expResult = 16;
        int result = intStream.reduce(0, (x, y) -> x * y + 1);
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        System.out.println("stream - sum");
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSumThrowIllegalArgumentException() {
        System.out.println("stream - sum IllegalArgumentException");
        double result = emptyStream.sum();
    }

    @Test
    public void testCount() {
        System.out.println("stream - count");
        long expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        System.out.println("stream - max");
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxThrowIllegalArgumentException() {
        System.out.println("stream - max IllegalArgumentException");
        double result = emptyStream.max();
    }

    @Test
    public void testMin() {
        System.out.println("stream - min");
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinThrowIllegalArgumentException() {
        System.out.println("stream - min IllegalArgumentException");
        double result = emptyStream.min();
    }


    @Test
    public void testAverage() {
        System.out.println("stream - average");
        double expResult = 1;
        double result = intStream.average();
        assertEquals(expResult, result, 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAverageThrowIllegalArgumentException() {
        System.out.println("stream - average IllegalArgumentException");
        double result = emptyStream.average();
    }


    @Test
    public void testFlatMap() {
        int[] expResult = new int[]{0, 1, 2, 3, 4, 5, 8, 9, 10};
        int[] result = intStream.filter(x -> x > 0) // 1, 2, 3
                .map(x -> x * x) // 1, 4, 9
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)) // 0, 1, 2, 3, 4, 5, 8, 9, 10
                .toArray();
        assertArrayEquals(expResult, result);
    }
}
