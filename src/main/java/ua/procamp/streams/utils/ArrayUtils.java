package ua.procamp.streams.utils;

import java.util.Iterator;
import java.util.List;

public class ArrayUtils{
        public static int[] convertArrayListtoArray(List<Integer> integers) {
            int[] result = new int[integers.size()];
            Iterator<Integer> iterator = integers.iterator();
            for (int i = 0; i < result.length; i++) {
                result[i] = iterator.next();
            }
            return result;
        }

        public static int[] combine(int[] a, int[] b){
            int length = a.length + b.length;
            int[] result = new int[length];
            System.arraycopy(a, 0, result, 0, a.length);
            System.arraycopy(b, 0, result, a.length, b.length);
            return result;
        }

        public static Integer[] convertIntToIntegerArrray(int[] incomingIntArray){
            Integer[] integerArray = new Integer[incomingIntArray.length];
            int i = 0;
            for (int value : incomingIntArray) {
                integerArray[i++] = value;
            }
            return integerArray;
        }

}
