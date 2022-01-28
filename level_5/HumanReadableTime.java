/*
    Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)

    HH = hours, padded to 2 digits, range: 00 - 99
    MM = minutes, padded to 2 digits, range: 00 - 59
    SS = seconds, padded to 2 digits, range: 00 - 59

    The maximum time never exceeds 359999 (99:59:59)

    You can find some examples in the test fixtures.
*/

public class HumanReadableTime {
    public static void main(String[] args) {
        System.out.println(makeReadable(5));
    }

    public static String makeReadable(int seconds) {
        if (seconds == 0) {
            return "00:00:00";
        } else {
            int hour = seconds / 3600;
            int minute = (seconds - (hour * 3600)) / 60;
            int sec = seconds - (hour * 3600) - (minute * 60);
            String h = Integer.toString(hour);
            String m = Integer.toString(minute);
            String s = Integer.toString(sec);

            if (Integer.toString(hour).length() == 1) {
                h = "0" + h;
            }
            if (Integer.toString(minute).length() == 1) {
                m = "0" + m;
            }
            if (Integer.toString(sec).length() == 1) {
                s = "0" + s;
            }
            return h + ":" + m + ":" + s;
        }
    }
}