import java.util.ArrayList;
import java.util.List;

/* Solving Josephus problem */

public class Josephus {
    public static void main(String[] args) {
        System.out.println(josephusSurvivor(25, 5));
    }

    public static int josephusSurvivor(final int n, final int k) {
        if (k == 1) {
            return n;
        } else {
            int result = 0;
            List<Integer> participants = new ArrayList<>();
            for (int i = 1; i < n + 1; i++) {
                participants.add(i);
            }

            int numberOfAlive;
            int counterStepForward = 0;
            do {
                numberOfAlive = 0;
                for (int i = 0; i < participants.size(); i++) {
                    if (participants.get(i) != null) {
                        counterStepForward++;
                        if (counterStepForward == k) {
                            participants.set(i, null);
                            counterStepForward = 0;
                        }
                    }
                }

                // counting alive people
                for (int j = 0; j < participants.size(); j++) {
                    if (participants.get(j) != null) {
                        numberOfAlive++;
                        result = participants.get(j);
                    }
                }
            } while (numberOfAlive > 1);
            return result;
        }
    }
}