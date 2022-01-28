/*
    You will have to calculate fib(n) where:

    fib(0) := 0
    fib(1) := 1
    fin(n + 2) := fib(n + 1) + fib(n)

    Write an algorithm that can handle n up to 2_000_000.

    Your algorithm must output the exact integer answer, to full precision.
    Also, it must correctly handle negative numbers as input.

    positive: Fn = Fn-1 + Fn-2
    negative: Fn = Fn+2 - Fn+1
    -1: 1-0 = 1, -2: 0-1= -1, -3: 1-(-1) = 2, -4: -1-2 = -3, -5: 2-(-3) = 5 ....
*/

import java.math.BigInteger;

public class TheMillionthFibonacci {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(fib(BigInteger.valueOf(-1)));
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start));
    }

    public static BigInteger fib(BigInteger n) {
        if (n.compareTo(BigInteger.valueOf(0)) == 0) {
            return BigInteger.valueOf(0);
        }
        int input = n.intValue();
        BigInteger[][] result = new BigInteger[2][2];
        BigInteger[][] tempResult = new BigInteger[2][2];
        BigInteger[][] basicMatrix = new BigInteger[2][2];
        BigInteger[][] tempMatrix = new BigInteger[2][2];
        if (n.compareTo(BigInteger.valueOf(0)) > 0) {
            basicMatrix[0][0] = BigInteger.valueOf(1);
            basicMatrix[0][1] = BigInteger.valueOf(1);
            basicMatrix[1][0] = BigInteger.valueOf(1);
            basicMatrix[1][1] = BigInteger.valueOf(0);

            while (input > 0) {
                if (input % 2 != 0) {
                    if (result[0][0] == null) {
                        result[0][0] = basicMatrix[0][0];
                        result[1][0] = basicMatrix[1][0];
                        result[0][1] = basicMatrix[0][1];
                        result[1][1] = basicMatrix[1][1];
                    } else {
                        tempResult[0][0] = result[0][0].multiply(basicMatrix[0][0]).add(result[0][1].multiply(basicMatrix[1][0]));
                        tempResult[1][0] = result[1][0].multiply(basicMatrix[0][0]).add(result[1][1].multiply(basicMatrix[1][0]));
                        tempResult[0][1] = result[0][0].multiply(basicMatrix[0][1]).add(result[0][1].multiply(basicMatrix[1][1]));
                        tempResult[1][1] = result[1][0].multiply(basicMatrix[0][1]).add(result[1][1].multiply(basicMatrix[1][1]));
                        result[0][0] = tempResult[0][0];
                        result[0][1] = tempResult[0][1];
                        result[1][0] = tempResult[1][0];
                        result[1][1] = tempResult[1][1];
                    }
                }
                tempMatrix[0][0] = basicMatrix[0][0].multiply(basicMatrix[0][0]).add(basicMatrix[0][1].multiply(basicMatrix[1][0]));
                tempMatrix[1][0] = basicMatrix[1][0].multiply(basicMatrix[0][0]).add(basicMatrix[1][1].multiply(basicMatrix[1][0]));
                tempMatrix[0][1] = basicMatrix[0][0].multiply(basicMatrix[0][1]).add(basicMatrix[0][1].multiply(basicMatrix[1][1]));
                tempMatrix[1][1] = basicMatrix[1][0].multiply(basicMatrix[0][1]).add(basicMatrix[1][1].multiply(basicMatrix[1][1]));

                basicMatrix[0][0] = tempMatrix[0][0];
                basicMatrix[0][1] = tempMatrix[0][1];
                basicMatrix[1][0] = tempMatrix[1][0];
                basicMatrix[1][1] = tempMatrix[1][1];

                input /= 2;
            }
            return result[1][0];
        } else {
            basicMatrix[0][0] = BigInteger.valueOf(0);
            basicMatrix[0][1] = BigInteger.valueOf(1);
            basicMatrix[1][0] = BigInteger.valueOf(1);
            basicMatrix[1][1] = BigInteger.valueOf(-1);

            while (input < 0) {
                if (input % 2 != 0) {
                    if (result[0][0] == null) {
                        result[0][0] = basicMatrix[0][0];
                        result[1][0] = basicMatrix[1][0];
                        result[0][1] = basicMatrix[0][1];
                        result[1][1] = basicMatrix[1][1];
                    } else {
                        tempResult[0][0] = result[0][0].multiply(basicMatrix[0][0]).add(result[0][1].multiply(basicMatrix[1][0]));
                        tempResult[1][0] = result[1][0].multiply(basicMatrix[0][0]).add(result[1][1].multiply(basicMatrix[1][0]));
                        tempResult[0][1] = result[0][0].multiply(basicMatrix[0][1]).add(result[0][1].multiply(basicMatrix[1][1]));
                        tempResult[1][1] = result[1][0].multiply(basicMatrix[0][1]).add(result[1][1].multiply(basicMatrix[1][1]));
                        result[0][0] = tempResult[0][0];
                        result[0][1] = tempResult[0][1];
                        result[1][0] = tempResult[1][0];
                        result[1][1] = tempResult[1][1];
                    }
                }
                tempMatrix[0][0] = basicMatrix[0][0].multiply(basicMatrix[0][0]).add(basicMatrix[0][1].multiply(basicMatrix[1][0]));
                tempMatrix[1][0] = basicMatrix[1][0].multiply(basicMatrix[0][0]).add(basicMatrix[1][1].multiply(basicMatrix[1][0]));
                tempMatrix[0][1] = basicMatrix[0][0].multiply(basicMatrix[0][1]).add(basicMatrix[0][1].multiply(basicMatrix[1][1]));
                tempMatrix[1][1] = basicMatrix[1][0].multiply(basicMatrix[0][1]).add(basicMatrix[1][1].multiply(basicMatrix[1][1]));

                basicMatrix[0][0] = tempMatrix[0][0];
                basicMatrix[0][1] = tempMatrix[0][1];
                basicMatrix[1][0] = tempMatrix[1][0];
                basicMatrix[1][1] = tempMatrix[1][1];

                input /= 2;
            }
            return result[1][0];
        }
    }
}