/*
	If second word characters are the same as first word characters, return true, if not  return false
*/

import java.util.ArrayList;
import java.util.List;

public class Scramblies {
    public static void main(String[] args) {
        System.out.println(scramble("katas", "steak"));
    }

    public static boolean scramble(String str1, String str2) {
        boolean result = false;
        StringBuilder sb = new StringBuilder(str1);
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < str2.length(); i++) {
            for (int j = 0; j < sb.length(); j++) {
                if (str2.charAt(i) == sb.charAt(j)) {
                    sb.deleteCharAt(j);
                    res.add(true);
                    break;
                }
            }
        }
        if (res.size() == str2.length()) {
            result = true;
        }
        return result;
    }
}