/*
    Write a function that, given a string of text (possibly with punctuation and line-breaks),
    returns an array of the top-3 most occurring words, in descending order of the number of occurrences.

    Assumptions:

    A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
    Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
    Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
    Matches should be case-insensitive, and the words in the result should be lowercased.
    Ties may be broken arbitrarily.
    If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned,
    or an empty array if a text contains no words.

    Examples:

    top_3_words("In a village of La Mancha, the name of which I have no desire to call to
    mind, there lived not long since one of those gentlemen that keep a lance
    in the lance-rack, an old buckler, a lean hack, and a greyhound for
    coursing. An olla of rather more beef than mutton, a salad on most
    nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
    on Sundays, made away with three-quarters of his income.")
    # => ["a", "of", "on"]

    top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e")
    # => ["e", "ddd", "aa"]

    top_3_words("  //wont won't won't")
    # => ["won't", "wont"]

    For java users, the calls will actually be in the form: TopWords.top3(String s), expecting you to return a List<String>.
*/

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MostFrequentlyUsedWordsInText {

    public static void main(String[] args) {
        System.out.println(top3(
                "In a village of La Mancha, the name of which I have no desire to call to, mind, " +
                        "there lived not long since one of those gentlemen that keep a lance, " +
                        "in the lance-rack, an old buckler, a lean hack, and a greyhound for, " +
                        "coursing. An olla of rather more beef than mutton, a salad on most, " +
                        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra, " +
                        "on Sundays, made away with three-quarters of his income."
        ));
    }

    public static List<String> top3(String s) {
        List<String> result = new ArrayList<>();
        String inputInLowercase = s.toLowerCase();
        String cleanedText = inputInLowercase.replaceAll("[<>.!?$%*&()\",_#@:\\-/;-\\[\\]]", " ");
        String removeUnnecessarySpaces = cleanedText.replaceAll(" +", " ").trim();
        String cleanedTextTrimAgain = removeUnnecessarySpaces.trim();
        String[] words = cleanedTextTrimAgain.split(" ");
        System.out.println("Array: " + Arrays.toString(words));

        String regex = ".*[a-z].*";  // regex to check if string contains any letters
        Pattern pattern = Pattern.compile(regex);  // compiles the regex
        // find match between given string and pattern
        Matcher matcherText = pattern.matcher(words[0]);
        boolean textMatches = matcherText.matches();

        Map<String, Integer> wordsAndCounter = new TreeMap<>();
        if (words.length == 1 && (words[0].equals("") || !textMatches)) {
            return result;
        } else {
            for (int i = 0; i < words.length; i++) {
                if (textMatches) {
                    if (wordsAndCounter.get(words[i]) == null) {
                        wordsAndCounter.put(words[i], 1);
                    } else {
                        wordsAndCounter.put(words[i], wordsAndCounter.get(words[i]) + 1);
                    }
                }
            }
            System.out.println();
            System.out.println(wordsAndCounter);


            HashMap<String, Integer> mapOrderedByValue =
                    wordsAndCounter.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue((Comparator.reverseOrder())))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            System.out.println();
            System.out.println(mapOrderedByValue);

            int counter = 0;
            int thirdValue = 0;
            int index = 0;
            for (Map.Entry<String, Integer> entry : mapOrderedByValue.entrySet()) {
                counter++;
                if (result.size() < 3) {
                    if (counter == 1) {
                        result.add(entry.getKey());
                        thirdValue = entry.getValue();
                    } else {
                        if (entry.getValue() < thirdValue) {
                            result.add(entry.getKey());
                            thirdValue = entry.getValue();
                            index = counter - 1;
                        } else if (entry.getValue() == thirdValue) {
                            result.add(entry.getKey());
                        }
                    }
                } else {
                    if (entry.getValue() == thirdValue) {
                        result.add(entry.getKey());
                    } else {
                        break;
                    }
                }
            }

            if (result.size() > 3) {
                Collections.sort(result.subList(index, result.size()));
                result = result.subList(0, 3);
            }
            return result;
        }
    }
}