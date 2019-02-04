package ua.procamp.streams.utils;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import static ua.procamp.streams.utils.ArrayUtils.combine;
import static ua.procamp.streams.utils.ArrayUtils.convertArrayListToArray;


public class ArrayUtilsTest {

    @Test
    public void testConvertArrayListToArray() {
        System.out.println("ConvertArrayListToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int anExpResult : expResult) {
            arrayList.add(anExpResult);
        }
        int [] resultArray = convertArrayListToArray(arrayList);
        assertArrayEquals(expResult, resultArray);
    }

    @Test
    public void testCombine() {
        System.out.println("combine");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] firstArray = {-1, 0};
        int[] secondArray = {1, 2, 3};
        int [] resultArray = combine(firstArray, secondArray);
        assertArrayEquals(expResult, resultArray);
    }

}

