/*
    Create two functions to encode and then decode a string using the Rail Fence Cipher.
    This cipher is used to encode a string by placing each character successively in a diagonal along a set of "rails".
    First start off moving diagonally and down. When you reach the bottom, reverse direction and move diagonally
    and up until you reach the top rail. Continue until you reach the end of the string.
    Each "rail" is then read left to right to derive the encoded string.

    For example, the string "WEAREDISCOVEREDFLEEATONCE" could be represented in a three rail system as follows:

    W       E       C       R       L       T       E
      E   R   D   S   O   E   E   F   E   A   O   C
        A       I       V       D       E       N

    The encoded string would be: "WECRLTEERDSOEEFEAOCAIVDEN"

    Write a function/method that takes 2 arguments, a string and the number of rails, and returns the ENCODED string.

    Write a second function/method that takes 2 arguments, an encoded string and the number of rails, and returns the DECODED string.

    For both encoding and decoding, assume number of rails >= 2 and that passing an empty string will return an empty string.

    Note that the example above excludes the punctuation and spaces just for simplicity.
    There are, however, tests that include punctuation. Don't filter out punctuation as they are a part of the string.
*/

public class RailFenceCipher {
    public static void main(String[] args) {
        System.out.println(decode("WECRLTEERDSOEEFEAOCAIVDEN", 3));
        System.out.println(encode("WEAREDISCOVEREDFLEEATONCE", 4));
    }

    static String decode(String s, int n) {
        char[] result = new char[s.length()];
        int fisrtAndLastRowGapBetweenTwoLetter = 1;
        for (int i = 2; i < n; i++) {
            fisrtAndLastRowGapBetweenTwoLetter += 2;
        }
        int rowGap = fisrtAndLastRowGapBetweenTwoLetter;
        int counterRowChars = 0;
        int startIndexOfARow = 0;
        int secondGapHorizontally = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                for (int j = 0; j < result.length; j += fisrtAndLastRowGapBetweenTwoLetter + 1) {
                    result[j] = s.charAt(counterRowChars);
                    counterRowChars++;
                }
            } else if (i == n) {
                for (int p = n - 1; p < result.length; p += fisrtAndLastRowGapBetweenTwoLetter + 1) {
                    result[p] = s.charAt(counterRowChars);
                    counterRowChars++;
                }
            } else {
                startIndexOfARow++;
                rowGap = rowGap - 2;
                for (int m = startIndexOfARow; m < result.length; m += rowGap + 1) {
                    result[m] = s.charAt(counterRowChars);
                    counterRowChars++;
                    if (m != startIndexOfARow && n != 3) {
                        m += secondGapHorizontally + 1;
                        if (m < result.length) {
                            result[m] = s.charAt(counterRowChars);
                            counterRowChars++;
                        }
                    }
                }
                secondGapHorizontally = secondGapHorizontally + 2;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            res.append(result[i]);
        }

        return res.toString();
    }

    static String encode(String s, int n) {
        char[] result = new char[s.length()];
        int fisrtAndLastRowGapBetweenTwoLetter = 1;
        for (int i = 2; i < n; i++) {
            fisrtAndLastRowGapBetweenTwoLetter += 2;
        }
        int rowGap = fisrtAndLastRowGapBetweenTwoLetter;
        int counterRowChars = 0;
        int startIndexOfARow = 0;
        int secondGapHorizontally = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                for (int j = 0; j < s.length(); j += fisrtAndLastRowGapBetweenTwoLetter + 1) {
                    result[counterRowChars] = s.charAt(j);
                    counterRowChars++;
                }
            } else if (i == n) {
                for (int p = n - 1; p < s.length(); p += fisrtAndLastRowGapBetweenTwoLetter + 1) {
                    result[counterRowChars] = s.charAt(p);
                    counterRowChars++;
                }
            } else {
                startIndexOfARow++;
                rowGap = rowGap - 2;
                for (int m = startIndexOfARow; m < s.length(); m += rowGap + 1) {
                    result[counterRowChars] = s.charAt(m);
                    counterRowChars++;
                    if (m != startIndexOfARow) {
                        m += secondGapHorizontally + 1;
                        if (m < result.length) {
                            result[counterRowChars] = s.charAt(m);
                            counterRowChars++;
                        }
                    }
                }
                secondGapHorizontally = secondGapHorizontally + 2;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            res.append(result[i]);
        }
        return res.toString();
    }
}