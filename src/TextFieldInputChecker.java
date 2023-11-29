import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class TextFieldInputChecker implements DocumentListener {

    private int row;
    private int col;
    private int[][] board;
    private JTextComponent textField;

    public TextFieldInputChecker(int row, int col, int[][] board, JTextComponent textField) {
        this.row = row;
        this.col = col;
        this.board = board;
        this.textField = textField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateBoard();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateBoard();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}

    void updateBoard() {
        Document document = textField.getDocument();
        try {
            String input = document.getText(0, document.getLength());
            if (isValidInput(input)) {
                int number = Integer.parseInt(input);
                if (isValid6x6(row, col, number)) {
                    board[row][col] = number;
                } else {
                    Animations.showInvalidImg = true;
                    SwingUtilities.invokeLater(() -> {
                        textField.setText("");
                    });
                    textField.getParent().repaint();
                    Panel.wrongSound.loadMusic(MusicMethods.WRONG_MUSIC);
                }
            } else {
                Animations.showInvalidImg = true;
                SwingUtilities.invokeLater(() -> {
                    textField.setText("");
                    Panel.wrongSound.loadMusic(MusicMethods.WRONG_MUSIC);
                });
                textField.getParent().repaint();
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    static boolean isValidInput(String input) {
        try {
            int number = Integer.parseInt(input);
            return number >= 1 && number <= 6;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isValid6x6(int row, int col, int num) {
        for (int j = 0; j < 6; j++) {
            if (board[row][j] == num && j != col) {
                return false;
            }
        }

        for (int i = 0; i < 6; i++) {
            if (board[i][col] == num && i != row) {
                return false;
            }
        }

        int boxRow = (row / 2) * 2;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 2; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num && (i != row || j != col)) {
                    return false;
                }
            }
        }

        return true;
    }
}
