package OtherComponents;

public class Sudocode {

    public static int[][] board6x6 = {
            {5, 4, 0, 1, 0, 0},
            {6, 0, 0, 0, 4, 5},
            {4, 0, 0, 6, 0, 0},
            {0, 6, 1, 0, 5, 4},
            {2, 3, 0, 0, 0, 1},
            {1, 0, 0, 0, 3, 2}
    };
    private static int[][] boardHolder ={
            {5, 4, 0, 1, 0, 0},
            {6, 0, 0, 0, 4, 5},
            {4, 0, 0, 6, 0, 0},
            {0, 6, 1, 0, 5, 4},
            {2, 3, 0, 0, 0, 1},
            {1, 0, 0, 0, 3, 2}
    };

    public static boolean isValid6x6(int row, int col, int num) {
        for (int j = 0; j < 6; j++) {
            if (board6x6[row][j] == num && j != col) {
                return false;
            }
        }

        for (int i = 0; i < 6; i++) {
            if (board6x6[i][col] == num && i != row) {
                return false;
            }
        }

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
    public static boolean isSolved6x6() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (board6x6[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void resetBoard() {
        int rows = boardHolder.length;
        int cols = boardHolder[0].length;
        board6x6 = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board6x6[i][j] = boardHolder[i][j];
            }
        }
    }
}