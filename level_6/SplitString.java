/*
    Complete the solution so that it splits the string into pairs of two characters.
    If the string contains an odd number of characters then it should replace the missing second character of the final pair
    with an underscore ('_').

    Examples:
    StringSplit.solution("abc") // should return {"ab", "c_"}
    StringSplit.solution("abcdef") // should return {"ab", "cd", "ef"}
*/

import java.util.Arrays;

public class SplitString {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("4pr bkj5nxn5qn 4y3u5nfkw7hp5c6yij")));
    }

    public static String[] solution(String s) {
        String[] input = s.split("");
        String[] result;
        if (s.isEmpty()) {
            return new String[0];
        }
        if (input.length % 2 != 0) {
            result = new String[(input.length + 1) / 2];
        } else {
            result = new String[input.length / 2];
        }
        int counter = 0;
        for (int i = 0; i < input.length; i += 2) {
            if (i == input.length - 1) {
                result[counter] = input[i] + "_";
            } else {
                result[counter] = input[i] + input[i + 1];
                counter++;
            }
        }
        return result;
    }
}