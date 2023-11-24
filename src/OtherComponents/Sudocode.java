package OtherComponents;

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

    private static void play6x6(Scanner scanner) {
        while (true) {
            printBoard6x6();
            System.out.print("Enter row number (1-6) or 0 to quit: ");
            int row = scanner.nextInt();
            if (row == 0) {
                break;
            }
            System.out.print("Enter column number (1-6): ");
            int col = scanner.nextInt();
            System.out.print("Enter a number (1-6) or 0 to clear: ");
            int num = scanner.nextInt();
            if (isValid6x6(row - 1, col - 1, num)) {
                board6x6[row - 1][col - 1] = num;
            } else {
                System.out.println("Invalid move! Try again.");
            }
            if (isSolved6x6()) {
                System.out.println("Congratulations, you solved the 6x6 puzzle!");
                break;
            }
        }
    }



    private static void printBoard6x6() {
        System.out.println("-------------------");
        for (int i = 0; i < 6; i++) {
            System.out.print("| ");
            for (int j = 0; j < 6; j++) {
                System.out.print(board6x6[i][j] + " ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % 2 == 0) {
                System.out.println("-------------------");
            }
        }
    }



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
    private static boolean isSolved6x6() {
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