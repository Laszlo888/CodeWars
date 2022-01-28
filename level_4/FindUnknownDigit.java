/*
    Form:
    (number)[operator](number)=(number)

    He (archaeologist professor) has converted all of the runes he knows into digits.
    The only operators he knows are addition (+),subtraction(-), and multiplication (*),
    so those are the only ones that will appear. Each number will be in the range from -1_000_000 to 1_000_000,
    and will consist of only the digits 0-9, possibly a leading -,
    and maybe a few ?s. If there are ?s in an expression, they represent a digit rune
    that the professor doesn't know (never an operator, and never a leading -).
    All of the ?s in an expression will represent the same digit (0-9), and it won't be one of the other given digits in the expression.
    No number will begin with a 0 unless the number itself is 0, therefore 00 would not be a valid number.

    Given an expression, figure out the value of the rune represented by the question mark.
    If more than one digit works, give the lowest one. If no digit works, well, that's bad news for the professor -
    it means that he's got some of his runes wrong. output -1 in that case.

    Example:
    123*45?=5?088    ?=6
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindUnknownDigit {
    public static void main(String[] args) {
        System.out.println(solveExpression("123*45?=5?088"));
    }

    public static int solveExpression(final String expression) {
        int missingDigit = -1;
        String regex = "-?[?1-9][?0-9]{0,6}[-*+]-?[?1-9][?0-9]{0,6}=-?[?1-9][?0-9]{0,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcherText = pattern.matcher(expression);
        boolean textMatches = matcherText.matches();
        if (!textMatches) {
            return -1;
        } else {
            // list for possible numbers
            List<Integer> pnAll = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
            String stringPn = expression.replaceAll("[^\\d.]", "");
            for (int h = 0; h < stringPn.length(); h++) {
                int t = Integer.parseInt(String.valueOf(stringPn.charAt(h)));
                if (pnAll.contains(t)) {
                    pnAll.remove(Integer.valueOf(String.valueOf(stringPn.charAt(h))));
                }
            }

            int indexOfPlusSign = expression.indexOf('+');
            int indexOfMultiplySign = expression.indexOf('*');
            int indexOfMinusOperator;
            String fNumber = "";
            String sNumber = "";
            int indexOfEqualSign = expression.indexOf("=");
            String stringResult = expression.substring(indexOfEqualSign + 1);

            if (!(indexOfPlusSign == -1)) {
                fNumber += expression.substring(0, indexOfPlusSign);
                sNumber += expression.substring(indexOfPlusSign + 1, indexOfEqualSign);
            } else if (!(indexOfMultiplySign == -1)) {
                fNumber += expression.substring(0, indexOfMultiplySign);
                sNumber += expression.substring(indexOfMultiplySign + 1, indexOfEqualSign);
            } else {
                if (expression.charAt(0) == '-') {
                    indexOfMinusOperator = expression.indexOf("-", 1);
                } else {
                    indexOfMinusOperator = expression.indexOf("-");
                }
                fNumber += expression.substring(0, indexOfMinusOperator);
                sNumber += expression.substring(indexOfMinusOperator + 1, indexOfEqualSign);
            }


            StringBuilder tempResultSb = new StringBuilder(stringResult);
            StringBuilder tempFirstNumberSb = new StringBuilder(fNumber);
            StringBuilder tempSecondNumberSb = new StringBuilder(sNumber);
            int tempRes;
            int tempF;
            int tempS;
            for (int i = 0; i < pnAll.size(); i++) {
                if (stringResult.contains("?")) {
                    questionMarkChangerToInteger(tempResultSb, pnAll.get(i));
                }
                if (fNumber.contains("?")) {
                    questionMarkChangerToInteger(tempFirstNumberSb, pnAll.get(i));
                }
                if (sNumber.contains("?")) {
                    questionMarkChangerToInteger(tempSecondNumberSb, pnAll.get(i));
                }

                if (tempFirstNumberSb.charAt(0) == '0' && tempFirstNumberSb.length() > 1
                        || tempSecondNumberSb.charAt(0) == '0' && tempSecondNumberSb.length() > 1
                        || (tempResultSb.charAt(0) == '0' && tempResultSb.length() > 1)
                        || (tempFirstNumberSb.charAt(0) == '-' && tempFirstNumberSb.charAt(1) == '0')
                        || (tempSecondNumberSb.charAt(0) == '-' && tempSecondNumberSb.charAt(1) == '0')
                        || (tempResultSb.charAt(0) == '-' && tempResultSb.charAt(1) == '0')
                ) {
                    tempFirstNumberSb.delete(0, tempFirstNumberSb.length());
                    tempFirstNumberSb.append(fNumber);
                    tempSecondNumberSb.delete(0, tempSecondNumberSb.length());
                    tempSecondNumberSb.append(sNumber);
                    tempResultSb.delete(0, tempResultSb.length());
                    tempResultSb.append(stringResult);
                    continue;
                }
                tempRes = Integer.parseInt(tempResultSb.toString());
                tempF = Integer.parseInt(tempFirstNumberSb.toString());
                tempS = Integer.parseInt(tempSecondNumberSb.toString());
                if (!(indexOfPlusSign == -1)) {
                    if (tempF + tempS == tempRes) {
                        missingDigit = pnAll.get(i);
                        return missingDigit;
                    }
                } else if (!(indexOfMultiplySign == -1)) {
                    if (tempF * tempS == tempRes) {
                        missingDigit = pnAll.get(i);
                        return missingDigit;
                    }
                } else {
                    if (tempF - tempS == tempRes) {
                        missingDigit = pnAll.get(i);
                        return missingDigit;
                    }
                }
                tempFirstNumberSb.delete(0, tempFirstNumberSb.length());
                tempFirstNumberSb.append(fNumber);
                tempSecondNumberSb.delete(0, tempSecondNumberSb.length());
                tempSecondNumberSb.append(sNumber);
                tempResultSb.delete(0, tempResultSb.length());
                tempResultSb.append(stringResult);
            }
        }
        return missingDigit;
    }

    public static void questionMarkChangerToInteger(StringBuilder sb, int replaceQuestionMarkWithThisNumber) {
        char nb = Character.forDigit(replaceQuestionMarkWithThisNumber, 10);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '?') {
                sb.setCharAt(i, nb);
            }
        }
    }
}