import java.util.Scanner;

public class Sudocode {

    public static int[][] board6x6 = {
            {5, 4, 0, 1, 0, 0},
            {6, 0, 0, 0, 4, 5},
            {4, 0, 0, 6, 0, 0},
            {0, 6, 1, 0, 5, 4},
            {2, 3, 0, 0, 0, 1},
            {1, 0, 0, 0, 3, 2}
    };

    static boolean isValid6x6(int row, int col, int num) {
        // Check if the number already exists in the same row
        for (int j = 0; j < 6; j++) {
            if (board6x6[row][j] == num && j != col) {
                return false;
            }
        }

        // Check if the number already exists in the same column
        for (int i = 0; i < 6; i++) {
            if (board6x6[i][col] == num && i != row) {
                return false;
            }
        }

        // Check if the number already exists in the same 2x3 box
        int boxRow = (row / 2) * 2;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 2; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board6x6[i][j] == num && (i != row || j != col)) {
                    return false;
                }
            }
        }

        return true;
    }
    static boolean isSolved6x6() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (board6x6[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


}