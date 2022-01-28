/*
    My friend John and I are members of the "Fat to Fit Club (FFC)".
    John is worried because each month a list with the weights of members is published and each month he is the last on the list
    which means he is the heaviest.

    I am the one who establishes the list so I told him: "Don't worry any more, I will modify the order of the list".
    It was decided to attribute a "weight" to numbers. The weight of a number will be from now on the sum of its digits.

    For example 99 will have "weight" 18, 100 will have "weight" 1 so in the list 100 will come before 99.

    Given a string with the weights of FFC members in normal order can you give this string ordered by "weights" of these numbers?

    Example:

    "56 65 74 100 99 68 86 180 90" ordered by numbers weights becomes:

    "100 180 90 56 65 74 68 86 99"

    When two numbers have the same "weight", let us class them as if they were strings (alphabetical ordering) and not numbers:

    180 is before 90 since, having the same "weight" (9), it comes before as a string.

    All numbers in the list are positive numbers and the list can be empty.

    Notes:
    it may happen that the input string have leading, trailing whitespaces and more than a unique whitespace between two consecutive numbers
*/

import java.util.*;

public class WeightForWeight {
    public static void main(String[] args) {
        System.out.println(orderWeight("103 123 4444 99 2000 22 22"));
    }

    public static String orderWeight(String strng) {
        String[] weights = strng.split(" ");
        Arrays.sort(weights);

        Map<String, Integer> weightsAndDigitSums = new LinkedHashMap<>();
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length(); j++) {
                sum += Character.digit(weights[i].charAt(j), 10);
            }
            weightsAndDigitSums.put(weights[i], sum);
            sum = 0;
        }

        Map<String, Integer> resultMap = new LinkedHashMap<>();
        weightsAndDigitSums.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .forEachOrdered(x -> resultMap.put(x.getKey(), x.getValue()));

        String result = "";

        // is same word twice or more times appears?
        Map<String, Integer> countingAppears = new HashMap<>();
        for (String key : weights) {
            if (countingAppears.get(key) == null) {
                countingAppears.put(key, 1);
            } else {
                countingAppears.put(key, countingAppears.get(key) + 1);
            }
        }

        // creating result
        for (String key : resultMap.keySet()) {
            if(countingAppears.get(key)==1){
                result += key + " ";}
            else{
                int c = countingAppears.get(key);
                for (int j = 0; j < c; j++) {
                    result += key + " ";
                }
            }
        }

        result = result.substring(0, result.length() - 1);
        return result;
    }
}