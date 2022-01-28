/*
   Given an array (arr) as an argument complete the function countSmileys that should return the total number of smiling faces.

    Rules for a smiling face:

    Each smiley face must contain a valid pair of eyes. Eyes can be marked as : or ;
    A smiley face can have a nose but it does not have to. Valid characters for a nose are - or ~
    Every smiling face must have a smiling mouth that should be marked with either ) or D

    No additional characters are allowed except for those mentioned.

    Valid smiley face examples: :) :D ;-D :~)
    Invalid smiley faces: ;( :> :} :]
    Example:

    countSmileys([':)', ';(', ';}', ':-D']);       // should return 2;
    countSmileys([';D', ':-(', ':-)', ';~)']);     // should return 3;
    countSmileys([';]', ':[', ';*', ':$', ';-D']); // should return 1;

    Note

    In case of an empty array return 0. You will not be tested with invalid input (input will always be an array).
    Order of the face (eyes, nose, mouth) elements will always be the same.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountTheSmileyFaces {
    public static void main(String[] args) {
        System.out.println(countSmileys(new ArrayList<>() {{
            add(":)");
            add(";(");
            add(";}");
            add(":-D");
        }}));
    }

    public static int countSmileys(List<String> arr) {
        Pattern patternFor2 = Pattern.compile("[:;][)D]");
        Pattern patternFor3 = Pattern.compile("[:;][\\-~][)D]");
        Matcher matcherFor2;
        Matcher matcherFor3;
        int counter = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).length() == 2) {
                matcherFor2 = patternFor2.matcher(arr.get(i));
                if (matcherFor2.find()) {
                    counter++;
                }
            } else if (arr.get(i).length() == 3){
                matcherFor3 = patternFor3.matcher(arr.get(i));
                if (matcherFor3.find()) {
                    counter++;
                }
            }else{}
        }
        return counter;
    }
}