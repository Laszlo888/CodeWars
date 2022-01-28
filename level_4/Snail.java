/*
    Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.

    array = [[1,2,3],
            [4,5,6],
            [7,8,9]]
    snail(array) #=> [1,2,3,6,9,8,7,4,5]

    For better understanding, please follow the numbers of the next array consecutively:

    array = [[1,2,3],
            [8,9,4],
            [7,6,5]]
    snail(array) #=> [1,2,3,4,5,6,7,8,9]
*/

import java.util.Arrays;

public class Snail {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(snail(new int[][]
                {{333,22,44}})));
    }

    public static int[] snail(int[][] array) {
        int[] result;
        System.out.println(array[0].length);
        if (array[0].length == 0) {
            result = new int[0];
            return result;
        } else if (array[0].length == 1) {
            result = new int[1];
            result[0] = array[0][0];
            return result;
        } else {
            result = new int[array.length * array[0].length];
            int lastAddedItemIndex = -1;
            int firstRowStart = 0;
            int firstRowEnd = array[0].length;
            int firstRowIndex = 0;
            int lastColumnStart = 1;
            int lastColumnEnd = array.length;
            int lastColumnIndex = array[1].length - 1;
            int lastRowStart = array[array.length - 1].length - 2;
            int lastRowEnd = 0;
            int lastRowIndex = array.length - 1;
            int firstColumnStart = array.length - 2;
            int firstColumnEnd = 1;
            int firstColumnIndex = 0;
            do {
                //first row to right
                for (int i = firstRowStart; i < firstRowEnd; i++) {
                    result[lastAddedItemIndex + 1] = array[firstRowIndex][i];
                    lastAddedItemIndex++;
                }
                firstRowStart++;
                firstRowEnd--;
                firstRowIndex++;

                if (lastAddedItemIndex == (array.length * array[0].length) - 1) {
                    break;
                }

                // last column down
                for (int i = lastColumnStart; i < lastColumnEnd; i++) {
                    result[lastAddedItemIndex + 1] = array[i][lastColumnIndex];
                    lastAddedItemIndex++;
                }
                lastColumnStart++;
                lastColumnEnd--;
                lastColumnIndex--;

                if (lastAddedItemIndex == (array.length * array[0].length) - 1) {
                    break;
                }

                // last row left
                for (int i = lastRowStart; i >= lastRowEnd; i--) {
                    result[lastAddedItemIndex + 1] = array[lastRowIndex][i];
                    lastAddedItemIndex++;
                }
                lastRowStart--;
                lastRowEnd++;
                lastRowIndex--;

                if (lastAddedItemIndex == (array.length * array[0].length) - 1) {
                    break;
                }

                // first column up
                for (int i = firstColumnStart; i >= firstColumnEnd; i--) {
                    result[lastAddedItemIndex + 1] = array[i][firstColumnIndex];
                    lastAddedItemIndex++;
                }
                firstColumnStart--;
                firstColumnEnd++;
                firstColumnIndex++;
            } while (lastAddedItemIndex != (array.length * array[0].length) - 1);
            return result;
        }
    }
}