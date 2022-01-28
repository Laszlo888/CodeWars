/*
   Connect four game winner checker after every steps
*/

import java.util.Arrays;
import java.util.List;

public class ConnectFour {
    public static void main(String[] args) {
        List<String> input = Arrays.asList(
                "A_Yellow", "A_Red", "A_Yellow", "F_Red", "A_Yellow", "G_Red", "C_Yellow", "B_Red", "B_Yellow",
                "B_Red", "B_Yellow", "B_Red", "G_Yellow", "C_Red", "C_Yellow", "C_Red", "F_Yellow", "D_Red", "G_Yellow",
                "D_Red", "G_Yellow", "G_Red", "F_Yellow", "D_Red", "D_Yellow","E_Red");
        System.out.println(whoIsWinner(input));
    }

    public static String whoIsWinner(List<String> piecesPositionList) {
        // return "Red" or "Yellow" or "Draw"
        StringBuilder temp = new StringBuilder();
        StringBuilder result = new StringBuilder("Draw");
        String[][] table = new String[6][7];
        for (int i = 0; i < piecesPositionList.size(); i++) {
            StringBuilder tempFill = new StringBuilder(String.valueOf(piecesPositionList.get(i).charAt(2)));
            if (piecesPositionList.get(i).charAt(0) == 'A') {
                fillTable(0, tempFill, table);
            } else if (piecesPositionList.get(i).charAt(0) == 'B') {
                fillTable(1, tempFill, table);
            } else if (piecesPositionList.get(i).charAt(0) == 'C') {
                fillTable(2, tempFill, table);
            } else if (piecesPositionList.get(i).charAt(0) == 'D') {
                fillTable(3, tempFill, table);
            } else if (piecesPositionList.get(i).charAt(0) == 'E') {
                fillTable(4, tempFill, table);
            } else if (piecesPositionList.get(i).charAt(0) == 'F') {
                fillTable(5, tempFill, table);
            } else if (piecesPositionList.get(i).charAt(0) == 'G') {
                fillTable(6, tempFill, table);
            }
            tempFill.delete(0, tempFill.length());

            if (i >= 6) {
                // check horizontal result
                horizontalResultChecker(result, temp, table, 5, 0, 5, 1, 5, 2, 5, 3);

                // check vertical result
                if (result.toString().equals("Draw")) {
                    verticalResultChecker(result, temp, table, 0, 0, 1, 0, 2, 0, 3, 0);
                }

                // check diagonal results one way
                if (result.toString().equals("Draw")) {
                    diagonalResultChecker(result, temp, table, 0, 0, 1, 1, 2, 2, 3, 3);
                }

                // check diagonal results another way
                if (result.toString().equals("Draw")) {
                    diagonalResultChecker(result, temp, table, 0, 3, 1, 2, 2, 1, 3, 0);
                }

                if (!result.toString().equals("Draw")) {
                    break;
                }
            }
        }
        printTable(table);
        return result.toString();
    }

    public static void fillTable(int letterConvertToNumber, StringBuilder rOrY, String[][] table) {
        for (int j = 5; j >= 0; j--) {
            if (table[j][letterConvertToNumber] == null) {
                table[j][letterConvertToNumber] = rOrY.toString();
                break;
            }
        }
    }

    public static void horizontalResultChecker(
            StringBuilder result, StringBuilder temp, String[][] table, int co1, int co2, int co3, int co4, int co5, int co6, int co7, int co8) {
        outer:
        for (int h = 0; h < table.length; h++) {
            for (int i = 0; i < 4; i++) {
                temp.delete(0, temp.length());
                if (table[co1 - h][co2 + i] != null && table[co3 - h][co4 + i] != null && table[co5 - h][co6 + i] != null && table[co7 - h][co8 + i] != null) {
                    temp.append(table[co1 - h][co2 + i]);
                    if (table[co1 - h][co2 + i].equals(table[co3 - h][co4 + i]) && table[co3 - h][co4 + i].equals(table[co5 - h][co6 + i]) && table[co5 - h][co6 + i].equals(table[co7 - h][co8 + i])) {
                        resultChanger(result, temp);
                        break outer;
                    }
                }
            }
        }
    }

    public static void verticalResultChecker(
            StringBuilder result, StringBuilder temp, String[][] table, int co1, int co2, int co3, int co4, int co5, int co6, int co7, int co8) {
        outer:
        for (int i = 0; i < table[0].length; i++) {
            for (int k = 0; k < 3; k++) {
                temp.delete(0, temp.length());
                if (table[co1 + k][co2 + i] != null && table[co3 + k][co4 + i] != null && table[co5 + k][co6 + i] != null &&
                        table[co7 + k][co8 + i] != null) {
                    temp.append(table[co1 + k][co2 + i]);
                    if (table[co1 + k][co2 + i].equals(table[co3 + k][co4 + i]) && table[co3 + k][co4 + i].equals(table[co5 + k][co6 + i]) &&
                            table[co5 + k][co6 + i].equals(table[co7 + k][co8 + i])) {
                        resultChanger(result, temp);
                        break outer;
                    }
                }
            }

        }
    }

    public static void diagonalResultChecker(
            StringBuilder result, StringBuilder temp, String[][] table, int co1, int co2, int co3, int co4, int co5, int co6, int co7, int co8) {
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 4; i++) {
                temp.delete(0, temp.length());
                if (table[co1 + k][co2 + i] != null && table[co3 + k][co4 + i] != null && table[co5 + k][co6 + i] != null && table[co7 + k][co8 + i] != null) {
                    temp.append(table[co1 + k][co2 + i]);
                    if (table[co1 + k][co2 + i].equals(table[co3 + k][co4 + i]) && table[co3 + k][co4 + i].equals(table[co5 + k][co6 + i])
                            && table[co5 + k][co6 + i].equals(table[co7 + k][co8 + i])) {
                        resultChanger(result, temp);
                        break;
                    }
                }
            }
        }
    }

    public static void resultChanger(StringBuilder result, StringBuilder temp) {
        if (temp.toString().equals("R")) {
            result.delete(0, result.length());
            result.append("Red");
        } else if (temp.toString().equals("Y")) {
            result.delete(0, result.length());
            result.append("Yellow");
        }
        temp.delete(0, temp.length());
    }

    //        Print out table
    public static void printTable(String[][] table) {
        for (int k = 0; k < table.length; k++) {
            for (int l = 0; l < table[k].length; l++) {
                if (table[k][l] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(table[k][l] + " ");
                }
            }
            System.out.print("\n");
        }
    }
}