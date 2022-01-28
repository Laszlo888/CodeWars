/*
    Digital root is the recursive sum of all the digits in a number.

    Given n, take the sum of the digits of n. If that value has more than one digit,
    continue reducing in this way until a single-digit number is produced. The input will be a non-negative integer.

    Examples:
    16  -->  1 + 6 = 7
    942  -->  9 + 4 + 2 = 15  -->  1 + 5 = 6
    132189  -->  1 + 3 + 2 + 1 + 8 + 9 = 24  -->  2 + 4 = 6
    493193  -->  4 + 9 + 3 + 1 + 9 + 3 = 29  -->  2 + 9 = 11  -->  1 + 1 = 2
*/

public class SumOfDigitsDigitalRoot {
    public static void main(String[] args) {
        System.out.println(digital_root(654));
    }
    public static int digital_root(int n) {
        String input = String.valueOf(n);
        if (input.length() == 1) {
            return n;
        }
        int digit;
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            digit = Character.digit(input.charAt(i), 10);
            sum += digit;
        }
        return digital_root(sum);
    }
}