/*
    The number u(0) = 1 is the first one in u.
    For each x in u, then y = 2 * x + 1 and z = 3 * x + 1 must be in u too.
    There are no other numbers in u.
    No duplicates.

    Example: u = [1, 3, 4, 7, 9, 10, 13, 15, 19, 21, 22, 27, ...]
    * y=2*1+1=3, z=3*1+1=4
    * y=2*3+1=7, z=3*3+1=10
    * y=2*4+1=9, z=3*4+1=13
    * y=2*7+1=15, z=3*7+1=22
    * y=2*9+1=19, z=3*9+1=28
    * y=2*10+1=21, z=3*10+1=31
    * etc.

    Question for example which is the 10th number in row (counting from zero). It is 22.
*/

import java.util.Set;
import java.util.TreeSet;

public class TwiceLinear {

    public static void main(String[] args) {
        System.out.println(dblLinear(60000));
    }

    public static int dblLinear(int n) {
        TreeSet<Integer> numbersSet = new TreeSet<>();
        numbersSet.add(1);

        for (int i = 0; i < n; i++) {
            adderToSet(numbersSet, numbersSet.pollFirst());
        }
        return numbersSet.pollFirst();
    }

    public static void adderToSet(Set<Integer> numbers, int current) {
        int y = 2 * current + 1;
        int z = 3 * current + 1;
        numbers.add(y);
        numbers.add(z);
    }
}