/*
    You will be given an array of numbers. You have to sort the odd numbers in ascending order
    while leaving the even numbers at their original positions.

    Examples:
    [7, 1]  =>  [1, 7]
    [5, 8, 6, 3, 4]  =>  [3, 8, 6, 5, 4]
    [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]  =>  [1, 8, 3, 6, 5, 4, 7, 2, 9, 0]
*/

import java.util.*;

public class SortOdd {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArray(new int[]{5, 3, 1, 8, 0})));
    }

    public static int[] sortArray(int[] array) {
        List<Integer> odds = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                odds.add(array[i]);
                indexes.add(i);
            }
        }
        Collections.sort(odds);
        Collections.sort(indexes);
        for (int i = 0; i < indexes.size(); i++) {
            array[indexes.get(i)] = odds.get(i);
        }
        return array;
    }
}