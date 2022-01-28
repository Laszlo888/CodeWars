/*
    A format for expressing an ordered list of integers is to use a comma separated list of either individual integers
    or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'.
    The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers.
    For example "12,13,15-17"

    Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

    Example:

    Solution.rangeExtraction(new int[] {-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
     returns "-6,-3-1,3-5,7-11,14,15,17-20"
*/

import java.util.ArrayList;
import java.util.List;

public class RangeExtraction {
    public static void main(String[] args) {
        System.out.println(rangeExtraction(new int[]{-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18,19, 21}));
    }

    public static String rangeExtraction(int[] arr) {
        String result = "";
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + 1 == arr[i + 1]) {
                if (i == arr.length - 2) {
                    temp.add(arr[i]);
                    if (temp.size() == 1) {
                        result += arr[i] + "," + arr[i + 1];
                    } else {
                        result += temp.get(0) + "-" + arr[i + 1];
                    }
                    temp.clear();
                } else {
                    temp.add(arr[i]);
                    System.out.println(temp);
                }
            } else {
                if (i == arr.length - 2) {
                    if (temp.size() == 0) {
                        result += arr[i] + "," + arr[i + 1];
                    } else if (temp.size() == 1) {
                        result += arr[i - 1] + "," + arr[i] + "," + arr[i + 1];
                        temp.clear();
                    } else {
                        result += temp.get(0) + "-" + arr[i] + "," + arr[i + 1];
                        temp.clear();
                    }
                } else {
                    if (temp.size() == 0) {
                        result += arr[i] + ",";
                    } else if (temp.size() == 1) {
                        result += arr[i - 1] + "," + arr[i] + ",";
                        temp.clear();
                    } else {
                        result += temp.get(0) + "-" + arr[i] + ",";
                        temp.clear();
                    }
                }
            }
        }
        return result;
    }
}