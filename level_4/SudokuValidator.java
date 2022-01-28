import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
    public static void main(String[] args) {
        System.out.println(check(new int[][]{
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        }));
    }

    public static boolean check(int[][] sudoku) {
        boolean result = true;
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Set<Integer> temp = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // horizontal check
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    return false;
                } else {
                    temp.remove(sudoku[i][j]);
                }
            }
            if (temp.size() > 0) {
                return false;
            }
            temp.clear();
            temp.addAll(numbers);
        }

        // vertical check
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp.remove(sudoku[i][j]);
            }
            if (temp.size() > 0) {
                return false;
            }
            temp.clear();
            temp.addAll(numbers);
        }

        // box check
        for (int m = 0; m < 9; m += 3) {
            for (int k = 0; k < 9; k += 3) {
                for (int i = k; i < k + 3; i++) {
                    for (int j = m; j < m + 3; j++) {
                        temp.remove(sudoku[i][j]);
                    }
                }
                if (temp.size() > 0) {
                    return false;
                }
                temp.clear();
                temp.addAll(numbers);
            }
        }
        return result;
    }
}