/*
    Given the triangle of consecutive odd numbers:

             1
          3     5
       7     9    11
   13    15    17    19
21    23    25    27    29
...

    Calculate the sum of the numbers in the nth row of this triangle (starting at index 1)
    e.g.: (Input --> Output)
    1 -->  1
    2 --> 3 + 5 = 8
*/

public class SumOfOddNumbers {
    public static void main(String[] args) {
        System.out.println(rowSumOddNumbers(42));
    }

    public static int rowSumOddNumbers(int n) {
        int result = 0;
        int minNumberInRow = 3;
        int maxNumberInRow = 5;
        if (n == 1) {
            return 1;
        } else if (n > 1) {
            for (int i = 2; i < n; i++) {
                minNumberInRow = minNumberInRow + 2 * i;
            }
            for (int i = 3; i < n+1; i++) {
                maxNumberInRow = maxNumberInRow + 2 * i;
            }
            for (int i = minNumberInRow; i <= maxNumberInRow; i += 2) {
                result += i;
            }
        }
        return result;
    }
}