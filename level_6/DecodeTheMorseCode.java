/*
    In this kata you have to write a simple Morse code decoder.
    While the Morse code is now mostly superseded by voice and digital data communication channels,
    it still has its use in some applications around the world.

    The Morse code encodes every character as a sequence of "dots" and "dashes".
    For example, the letter A is coded as ·-, letter Q is coded as --·-, and digit 1 is coded as ·----.
    The Morse code is case-insensitive, traditionally capital letters are used. When the message
    is written in Morse code, a single space is used to separate the character codes and 3 spaces are used to separate words.
    For example, the message HEY JUDE in Morse code is ···· · -·--   ·--- ··- -·· ·.

    NOTE: Extra spaces before or after the code have no meaning and should be ignored.

    In addition to letters, digits and some punctuation, there are some special service codes,
    the most notorious of those is the international distress signal SOS (that was first issued by Titanic),
    that is coded as ···---···.
    These special codes are treated as single special characters, and usually are transmitted as separate words.

    Your task is to implement a function that would take the morse code as input and return a decoded human-readable string.

    For example:

    MorseCodeDecoder.decode(".... . -.--   .--- ..- -.. .")
    should return "HEY JUDE"

    The Morse code table is preloaded for you as a dictionary, feel free to use it:
    Java: MorseCode.get(".--")
*/

import java.util.HashMap;
import java.util.Map;

public class DecodeTheMorseCode {
    public static void main(String[] args) {
        System.out.println(decode(".... . -.--   .--- ..- -.. ."));
    }

    public static String decode(String morseCode) {
        StringBuilder result = new StringBuilder();
        String[] splitInput = morseCode.split(" ");
        for (int i = 0; i < splitInput.length; i++) {
            if (!splitInput[i].equals("")) {
                result.append(MorseCode.get(splitInput[i]));
            } else if (i < splitInput.length - 1 && splitInput[i].equals("") && splitInput[i + 1].equals("")) {
                result.append(" ");
            }
        }
        return result.toString().trim();
    }
}

class MorseCode {
    public static String get(String morseCode) {
        Map<String, String> morseMap = new HashMap<>() {{
            put(".-", "A"); put("-...", "B"); put("-.-.", "C"); put("-..", "D"); put(".", "E"); put("..-.", "F");
            put("--.", "G"); put("....", "H"); put("..", "I"); put(".---", "J"); put("-.-", "K"); put(".-..", "L");
            put("--", "M"); put("-.", "N"); put("---", "O"); put(".--.", "P"); put("--.-", "Q"); put(".-.", "R");
            put("...", "S"); put("-", "T"); put("..-", "U"); put("...-", "V"); put(".--", "W"); put("-..-", "X");
            put("-.--", "Y"); put("--..", "Z"); put(".----", "1"); put("..---", "2"); put("...--", "3"); put("....-", "4");
            put(".....", "5"); put("-....", "6"); put("--...", "7"); put("---..", "8"); put("----.", "9"); put("-----", "0");
        }};

        return morseMap.get(morseCode);
    }
}