/*
    Create a RomanNumerals class that can convert a roman numeral to and from an integer value.
    It should follow the API demonstrated in the examples below.
    Modern Roman numerals are written by expressing each digit separately starting with the left most digit
    and skipping any digit with a value of zero.
    In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
    2008 is written as 2000=MM, 8=VIII; or MMVIII.
    1666 uses each Roman symbol in descending order: MDCLXVI.
*/

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {
    public static void main(String[] args) {
        System.out.println(toRoman(8));
        System.out.println(fromRoman("MMMCMXCIV"));
    }

    public static String toRoman(int n) {
        String result = "";
        String input = String.valueOf(n);
        char[] numbers = input.toCharArray();
        if (numbers.length == 4) {
            for (int i = 0; i < Integer.parseInt(String.valueOf(numbers[0])); i++) {
                result += "M";
            }
            result += toRomanHelperFunction(numbers, 1, "C", "D", "M");
            result += toRomanHelperFunction(numbers, 2, "X", "L", "C");
            result += toRomanHelperFunction(numbers, 3, "I", "V", "X");
        } else if (numbers.length == 3) {
            result += toRomanHelperFunction(numbers, 0, "C", "D", "M");
            result += toRomanHelperFunction(numbers, 1, "X", "L", "C");
            result += toRomanHelperFunction(numbers, 2, "I", "V", "X");
        } else if (numbers.length == 2) {
            result += toRomanHelperFunction(numbers, 0, "X", "L", "C");
            result += toRomanHelperFunction(numbers, 1, "I", "V", "X");

        } else if (numbers.length == 1) {
            result += toRomanHelperFunction(numbers, 0, "I", "V", "X");
        }
        return result;
    }

    public static String toRomanHelperFunction(char[] numbers, int indexInCharArray, String one, String five, String toNine) {
        String result = "";
        int nb = Integer.parseInt(String.valueOf(numbers[indexInCharArray]));
        if (nb < 4) {
            for (int i = 0; i < nb; i++) {
                result += one;
            }
        } else if (nb == 4) {
            result += one + five;
        } else if (nb >= 5 && nb < 9) {
            result += five;
            for (int i = 0; i < nb - 5; i++) {
                result += one;
            }
        } else {
            result += one + toNine;
        }
        return result;
    }

    public static int fromRoman(String romanNumeral) {
        int result = 0;
        Map<Character, Integer> convert = new HashMap<>();
        convert.put('I', 1);
        convert.put('V', 5);
        convert.put('X', 10);
        convert.put('L', 50);
        convert.put('C', 100);
        convert.put('D', 500);
        convert.put('M', 1000);
        //add last one charachter
        result += convert.get(romanNumeral.charAt(romanNumeral.length() - 1));
        for (int i = romanNumeral.length() - 2; i >= 0; i--) {
            if (convert.get(romanNumeral.charAt(i)) >= convert.get(romanNumeral.charAt(i + 1))) {
                result += convert.get(romanNumeral.charAt(i));
            } else {
                result -= convert.get(romanNumeral.charAt(i));
            }
        }
        return result;
    }
}