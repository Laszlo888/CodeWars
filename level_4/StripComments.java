/*
    Complete the solution so that it strips all text that follows any of a set of comment markers passed in.
    Any whitespace at the end of the line should also be stripped out.

    Example:
    Given an input string of:

    apples, pears # and bananas
    grapes
    bananas !apples

    The output expected would be:

    apples, pears
    grapes
    bananas

    The code would be called like so:

    var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
    // result should == "apples, pears\ngrapes\nbananas"
*/

import java.util.ArrayList;
import java.util.List;

public class StripComments {
    public static void main(String[] args) {
        System.out.println(stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}));
    }

    public static String stripComments(String text, String[] commentSymbols) {
        String[] rows = text.split("\n");
        List<String> separated = new ArrayList<>();
        boolean isSymbolInRow = false;
        for (int j = 0; j < rows.length; j++) {
            for (int i = 0; i < commentSymbols.length; i++) {
                int index = rows[j].indexOf(commentSymbols[i]);
                if (index > -1) {
                    separated.add(rows[j].substring(0, index).replaceAll("\\s+$", ""));
                    isSymbolInRow = true;
                    continue;
                }
            }
            if (isSymbolInRow == false) {
                separated.add(rows[j].replaceAll("\\s+$", ""));
            } else {
                isSymbolInRow = false;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < separated.size(); i++) {
            result.append(separated.get(i) + "\n");
        }
        result.delete(result.length() - 1, result.length());

        return result.toString();
    }
}