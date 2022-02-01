import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodeTheMorseCodeAdvanced {
    public static void main(String[] args) {
        System.out.println(decodeBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111" +
                "000000110011001111110000001111110011001100000011"));
        System.out.println(decodeMorse(decodeBits("11001100110011000000110000001111110011001111110011111100000000000000110011111100111111" +
                "00111111000000110011001111110000001111110011001100000011")));
    }

    public static String decodeBits(String bits) {
        StringBuilder result = new StringBuilder();
        StringBuilder input = new StringBuilder(bits);
        // delete zeros from front and back
        int indexZerosAtFront = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(0) != '0') {
                break;
            } else {
                if (input.charAt(i) == '0') {
                    indexZerosAtFront = i;
                } else {
                    input.delete(0, indexZerosAtFront + 1);
                    break;
                }
            }
        }
        int indexZerosAtBack = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(input.length() - 1) != '0') {
                break;
            } else {
                if (input.charAt(i) == '0') {
                    indexZerosAtBack = i;
                } else {
                    input.delete(indexZerosAtBack, input.length());
                    break;
                }
            }
        }
        // check if no zeros in input
        if (!input.toString().contains("0")) {
            return ".";
        }

        // check transmission rate
        int trRate = 0;
        StringBuilder rate = new StringBuilder("00");
        Pattern pattern;
        Matcher matcher;
        for (int i = 0; i < 10; i++) {
            rate.insert(1, '1');
            trRate++;
            pattern = Pattern.compile(rate.toString());
            matcher = pattern.matcher(input.toString());
            if (matcher.find()) {
                break;
            }
        }
        
        // if input is only 1 or 2 character, example: 101 (..) it's I or 10001 is EE, so trRate=10
        if (trRate == 10) {
            int count1sBeforeZeros = 0;
            int count1sAfterZeros = 0;
            int countZeros = 0;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '1') {
                    count1sBeforeZeros++;
                } else {
                    break;
                }
            }
            for (int i = input.length() - 1; i >= 0; i--) {
                if (input.charAt(i) == '1') {
                    count1sAfterZeros++;
                } else {
                    break;
                }
            }
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '0') {
                    countZeros++;
                }
            }
            if (count1sAfterZeros == count1sBeforeZeros && count1sBeforeZeros == countZeros) {
                return "..";
            } else if (count1sBeforeZeros == count1sAfterZeros && countZeros > count1sBeforeZeros) {
                return ". .";
            } else if (count1sBeforeZeros == countZeros && count1sAfterZeros > countZeros) {
                return ".-";
            }else if (count1sBeforeZeros == count1sAfterZeros && count1sBeforeZeros > countZeros) {
                return "--";
            }else if (count1sBeforeZeros == countZeros && count1sAfterZeros < countZeros) {
                return "- .";
            }else if (count1sAfterZeros == countZeros && count1sBeforeZeros < countZeros) {
                return ". -";
            }
        }
        
        //  making result
        int counter = 1;
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                counter++;
                if (i == input.length() - 2) {
                    if (counter == trRate) {
                        result.append(".");
                        counter = 1;
                    } else if (counter == 3 * trRate) {
                        result.append("-");
                        counter = 1;
                    }
                }
            } else {
                if (input.charAt(i) == '1') {
                    if (counter == trRate) {
                        result.append(".");
                        counter = 1;
                    } else if (counter == 3 * trRate) {
                        result.append("-");
                        counter = 1;
                    }
                } else {
                    if (counter == 3 * trRate) {
                        result.append(" ");
                    } else if (counter == 7 * trRate) {
                        result.append("   ");
                    } else if (i == input.length() - 2) {
                        result.append(" ").append(".");
                    }
                    counter = 1;
                }

            }
        }
        return result.toString();
    }

    public static String decodeMorse(String morseCode) {
        StringBuilder result = new StringBuilder();
        String[] splitInput = morseCode.split(" ");
        for (int i = 0; i < splitInput.length; i++) {
            if (!splitInput[i].equals("")) {
                result.append(MorseCode.get(splitInput[i]));
            } else if (i < splitInput.length - 1 && splitInput[i].equals("") && splitInput[i + 1].equals("")) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}

class MorseCode {
    public static String get(String morseCode) {
        Map<String, String> morseMap = new HashMap<>() {{
            put(".-", "A");put("-...", "B");put("-.-.", "C");put("-..", "D");put(".", "E");put("..-.", "F");
            put("--.", "G");put("....", "H");put("..", "I");put(".---", "J");put("-.-", "K");put(".-..", "L");
            put("--", "M");put("-.", "N");put("---", "O");put(".--.", "P");put("--.-", "Q");put(".-.", "R");
            put("...", "S");put("-", "T");put("..-", "U");put("...-", "V");put(".--", "W");put("-..-", "X");
            put("-.--", "Y");put("--..", "Z");put(".----", "1");put("..---", "2");put("...--", "3");put("....-", "4");
            put(".....", "5");put("-....", "6");put("--...", "7");put("---..", "8");put("----.", "9");put("-----", "0");
            put(".-.-.-", ".");put("--..--", ",");put("..--..", "?");put("---...", ":");put("-.-.-.", ";");
        }};
        return morseMap.get(morseCode);
    }
}