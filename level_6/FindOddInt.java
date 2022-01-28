/*
    Given an array of integers, find the one that appears an odd number of times.

    There will always be only one integer that appears an odd number of times.

    Examples:

    [7] should return 7, because it occurs 1 time (which is odd).
    [0] should return 0, because it occurs 1 time (which is odd).
    [1,1,2] should return 2, because it occurs 1 time (which is odd).
    [0,1,0,1,0] should return 0, because it occurs 3 times (which is odd).
    [1,2,2,3,3,3,4,3,3,3,2,2,1] should return 4, because it appears 1 time (which is odd).
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindOddInt {
    public static void main(String[] args) {

        System.out.println(findIt(new int[]{224, 889, 305, 611, 338, 485, 162, 473,
                542, 230, 173, 137, 66, 162, 470, 915, 374, 317, 317, 5, 613, 555, 230, 473, 399,
                607, 613, 399, 152, 576, 790, 5, 542, 491, 169, 970, 169, 895, 224, 889, 790, 305,
                89, 659, 611, 173, 670, 997, 66, 607, 485, 729, 491, 659, 576, 152, 895, 470, 374,
                729, 970, 89, 137, 997, 670, 555, 915}));
    }

    public static int findIt(int[] a) {
        int result = 0;
        if (a.length == 1) {
            return a[0];
        } else {
            List<Integer> input = new ArrayList<>();
            for (int value : a) {
                input.add(value);
            }
            Collections.sort(input);
            System.out.println(input);
            int sum = 1;
            for (int i = 0; i < input.size() - 1; i++) {
                if (input.get(i).equals(input.get(i + 1))) {
                    sum++;
                } else {
                    if (sum % 2 != 0) {
                        result = input.get(i);
                    }
                    sum = 1;
                    if (i == input.size() - 2) {
                        result = input.get(i + 1);
                    } else if (!input.get(i + 1).equals(input.get(i + 2))) {
                        return input.get(i + 1);
                    }
                }
            }
        }
        return result;
    }
}