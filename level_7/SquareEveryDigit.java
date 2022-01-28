/*
    Welcome. In this kata, you are asked to square every digit of a number and concatenate them.

    For example, if we run 9119 through the function, 811181 will come out, because 92 is 81 and 12 is 1.

    Note: The function accepts an integer and returns an integer
*/

public class SquareEveryDigit {
    public static void main(String[] args) {
        System.out.println(squareDigits(9119));
    }

    public static int squareDigits(int n) {
        String number = String.valueOf(n);
        String result = "";
        for (int i = 0; i < number.length(); i++) {
            int charNumber = Integer.parseInt(String.valueOf(number.charAt(i)));
            int square = charNumber * charNumber;
            result += String.valueOf(square);
        }
        return Integer.parseInt(result);
    }
}