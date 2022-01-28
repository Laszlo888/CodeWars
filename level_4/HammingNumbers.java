/*
    Write a function that computes the nth smallest Hamming number.

    The first smallest Hamming number is 1 = 2^0 * 3^0 * 5^0
    The second smallest Hamming number is 2 = 2^1 * 3^0 * 5^0
    The third smallest Hamming number is 3 = 2^0 * 3^1 * 5^0
    The fourth smallest Hamming number is 4 = 2^2 * 3^0 * 5^0
    The fifth smallest Hamming number is 5 = 2^0 * 3^0 * 5^1
*/

import java.util.Arrays;
import java.util.TreeSet;

public class HammingNumbers {
    public static void main(String[] args) {
        System.out.println(hamming(386));
    }

    public static long hamming(int n) {
        TreeSet<Long> hammingNumber = new TreeSet<>(Arrays.asList(2L, 3L, 5L));
        long smallest = 1;
        for (int i = 1; i < n; i++) {
            smallest = hammingNumber.pollFirst();
            hammingNumber.add(smallest * 2);
            hammingNumber.add(smallest * 3);
            hammingNumber.add(smallest * 5);
        }
        return smallest;
    }
}