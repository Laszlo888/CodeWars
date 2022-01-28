/*
    Return the number (count) of vowels in the given string.

    We will consider a, e, i, o, u as vowels for this Kata (but not y).

    The input string will only consist of lower case letters and/or spaces.
*/

public class VowelCount {
    public static void main(String[] args) {
        System.out.println(getCount("aztamindenit"));
    }

    public static int getCount(String str) {
        int vowelsCount = 0;
        char[] vow = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < vow.length; i++) {
            for (int j = 0; j < str.length(); j++) {
                if (vow[i]==(str.charAt(j))) {
                    vowelsCount++;
                }
            }
        }
        return vowelsCount;
    }
}