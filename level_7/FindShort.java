/*
    Simple, given a string of words, return the length of the shortest word(s).

    String will never be empty and you do not need to account for different data types.
*/

public class ShortestWord {
    public static void main(String[] args) {
        System.out.println(findShort("bitcoin take over the world maybe who knows perhaps"));
    }

    public static int findShort(String s) {
        String[] words = s.split(" ");
        int maxLength = 100;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() < maxLength) {
                maxLength = words[i].length();
            }
        }
        return maxLength;
    }
}