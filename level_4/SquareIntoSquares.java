/*
    Task:
    Given a positive integral number n, return a strictly increasing sequence (list/array/string depending on the language) of numbers,
    so that the sum of the squares is equal to n^2.

    If there are multiple solutions (and there will be), return as far as possible the result with the largest possible values:

    Examples:
    decompose(11) must return [1,2,4,10].
    Note that there are actually two ways to decompose 11^2, 11^2 = 121 = 1 + 4 + 16 + 100 = 1^2 + 2^2 + 4^2 + 10^2
    but don't return [2,6,9], since 9 is smaller than 10.

    For decompose(50) don't return [1, 1, 4, 9, 49]
    but [1, 3, 5, 8, 49] since [1, 1, 4, 9, 49] doesn't form a strictly increasing sequence.

    Note:
    Neither [n] nor [1,1,1,…,1] are valid solutions.
    If no valid solution exists, return null.

    The function "decompose" will take a positive integer n and return the decomposition of N = n^2:

    decompose 50 returns "1 3 5 8 49"
    decompose 4  returns null
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class SquareIntoSquares {
    public static void main(String[] args) {
        System.out.println(decompose(22L));
    }

    public static String decompose(long n) {
        StringBuilder result = new StringBuilder();
        if (n >= 0L && n < 5L) {
            return null;
        } else {
            Long squareToGetFromSums = n * n;

            List<Long> squares = new ArrayList<>();
            for (long i = 1L; i < n; i++) {
                squares.add(i * i);
            }

            uppest:
            for (int k = squares.size() - 1; k >= 1; k--) {
                long subtracted = squareToGetFromSums - squares.get(k);
                int baseNumber = (int) Math.sqrt(squares.get(k));

                int sqrtSubt = (int) Math.sqrt(subtracted);
                for (long i = sqrtSubt; i >= 1; i--) {
                    List<Long> trys = new ArrayList<>();
                    if (i != baseNumber) {
                        trys = reducer(subtracted, subtracted, i, i, baseNumber, 0, new ArrayList<>(), new TreeSet<>());
                    }
                    if (!trys.isEmpty()) {
                        Collections.sort(trys);
                        for (int j = 0; j < trys.size(); j++) {
                            result.append(trys.get(j) + " ");
                        }
                        result.append(baseNumber);
                        break uppest;
                    }
                    System.out.println((i * i) + " kombik: " + trys);
                }
            }
        }
        if (result.toString().isEmpty()) {
            return null;
        } else {
            return result.toString().trim();
        }
    }

    public static List<Long> reducer(long subtr, long wWN, long number, long originalNumber, int baseNumber, int summa, List<Long> res, TreeSet<Long> aUN) {
        List<Long> result = res;
        TreeSet<Long> alreadyUsedNumbers = aUN;
        if (subtr < 4 || number < 2) {
            if (wWN == summa + 1) {
                if (baseNumber != 1) {
                    result.add(1L);
                }
                return result;
            } else {
                result.clear();
                return result;
            }
        }
        int sum = summa;
        sum += number * number;
        result.add(number);
        alreadyUsedNumbers.add(number);
        if (sum == wWN) {
            return result;
        }
        long newSubtr = subtr - (number * number);
        long newNumber = (long) Math.sqrt(newSubtr);

        while (newNumber == originalNumber || alreadyUsedNumbers.contains(newNumber) || newNumber == baseNumber) {
            newNumber--;

        }
        return reducer(newSubtr, wWN, newNumber, originalNumber, baseNumber, sum, result, alreadyUsedNumbers);
    }
}