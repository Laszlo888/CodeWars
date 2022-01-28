/*
    Given an array of positive or negative integers: I= [i1,..,in]

    You have to produce a sorted array P of the form:
    [[p, sum of all ij of I for which p is a prime factor (p positive) of ij] ...]

    P will be sorted by increasing order of the prime numbers. The final result has to be given as a string in Java.

    Example:
    I = {12, 15, 28,-99};
    12=2*2*3   15=3*5  28=2*2*7 -99=3*3*11
    result = "(2 40)(3 -72)(5 15)(7 28)(11 -99)"

    Notes:
    It can happen that a sum is 0 if some numbers are negative!
    Example: I = [15, 30, -45]    5 divides 15, 30 and (-45) so 5 appears in the result,
    the sum of the numbers for which 5 is a factor is 0 so we have [5, 0] in the result amongst others.
*/

import java.util.*;

public class SumByFactors {
    public static void main(String[] args) {
        System.out.println(sumOfDivided(new int[]{12, 15, 28,-99}));
    }

    public static String sumOfDivided(int[] l) {
        StringBuilder result = new StringBuilder();

        Map<Integer, Integer> primeFactorsAndSum = new TreeMap<>();
        for (int i = 0; i < l.length; i++) {
            primeDivisorFinder(l[i], primeFactorsAndSum);
        }

        System.out.println("Numbers & PrimeDivisors: " + primeFactorsAndSum);

        for (Map.Entry<Integer, Integer> entry : primeFactorsAndSum.entrySet()) {
            result.append("(" + entry.getKey() + " ").append(entry.getValue() + ")");
        }

        return result.toString();
    }

    public static void primeDivisorFinder(int nb, Map<Integer, Integer> div) {
        boolean isNbPrime = true;
        if (nb == 0 || nb==1) {
        } else {
            int nbTemp = Math.abs(nb);
            if (nbTemp % 2 == 0 && nbTemp!=2) {
                if (div.get(2) != null) {
                    div.put(2, div.get(2) + nb);
                    isNbPrime = false;
                } else {
                    div.put(2, nb);
                    isNbPrime = false;
                }
            }
            for (int j = 3; j < (nbTemp / 2) + 1; j += 2) {
                if (nbTemp % j == 0) {
                    boolean isPrime = true;
                    for (int i = 3; i < (j / 2) + 1; i += 2) {
                        if (j % i == 0) {
                            isPrime = false;
                            break;
                        }
                    }

                    if (isPrime) {
                        if (div.get(j) != null) {
                            div.put(j, div.get(j) + nb);
                            isNbPrime = false;
                        } else {
                            div.put(j, nb);
                            isNbPrime = false;
                        }
                    }
                }
            }
            if (isNbPrime) {
                if (div.get(nbTemp) != null) {
                    div.put(nbTemp, div.get(nbTemp) + nb);
                } else {
                    div.put(nbTemp, nb);
                }
            }
        }
    }
}