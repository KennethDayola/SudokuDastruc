package OtherComponents;

import Main.Main;
import Main.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static Panel p;
    private static int cellSizeX;
    private static int cellSizeY;
    public static void drawGrid(Graphics g, Panel panel, int[][] board) {

        p = panel;
        int gridSize = 6;
        int playingRectWidth = panel.getPlayingRect().width;
        int playingRectHeight = panel.getPlayingRect().height;
        cellSizeX = playingRectWidth / gridSize;
        cellSizeY = playingRectHeight / gridSize;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int x = panel.getPlayingRect().x + j * cellSizeX + cellSizeX / 2;
                int y = panel.getPlayingRect().y + i * cellSizeY + cellSizeY / 2 + 5;

                if (board[i][j] != 0) {
                    // Set font properties for bold numbers
                    Font font = new Font("Comic Sans MS", Font.BOLD, 25);
                    g2d.setFont(font);
                    g2d.setColor(new Color(139, 69, 19));

                    // Draw the number from the board array
                    String number = Integer.toString(board[i][j]);
                    g2d.drawString(number, x - g2d.getFontMetrics().stringWidth(number) / 2, y + 5);
                } else {
                    // Find the existing JTextField for this cell, if any
                    JTextField existingTextField = findTextField(panel.getTextFields(), i, j, panel);


                    if (existingTextField == null) {
                        // Draw placeholder text field only if it doesn't exist
                        JTextField textField = new JTextField();
                        textField.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                        textField.setForeground(new Color(139, 69, 19));
                        textField.setBounds(x - cellSizeX / 4, y - cellSizeY / 4, cellSizeX / 2, cellSizeY / 2 - 5);
                        textField.setHorizontalAlignment(JTextField.CENTER);
                        textField.setDocument(new JTextFieldLimit(1));
                        int finalI = i;
                        int finalJ = j;
                        TextFieldInputChecker inputChecker = new TextFieldInputChecker(i, j, board, textField);
                        textField.getDocument().addDocumentListener(inputChecker);
                        textField.addActionListener(e -> handleTextFieldAction(panel, board, textField, finalI, finalJ));

                        panel.add(textField);
                        panel.textFields.add(textField);
                    } else {
                        existingTextField.setBounds(x - cellSizeX / 4, y - cellSizeY / 4, cellSizeX / 2, cellSizeY / 2 - 5);
                    }
                }
            }
        }

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.setColor(Color.BLACK);

        g2d.setColor(new Color(139, 69, 19));
        g2d.setStroke(new BasicStroke(0.5f));

        for (int i = 0; i <= gridSize; i++) {
            int x = panel.getPlayingRect().x + i * cellSizeX;
            int y = panel.getPlayingRect().y + i * cellSizeY;

            g2d.drawLine(x, panel.getPlayingRect().y, x, panel.getPlayingRect().y + playingRectHeight);
            g2d.drawLine(panel.getPlayingRect().x, y, panel.getPlayingRect().x + playingRectWidth, y);
        }

        Stroke originalStroke = g2d.getStroke();

        g2d.setStroke(new BasicStroke(3.0f));

        int extraLineYThird = panel.getPlayingRect().y + 2 * cellSizeY;
        g2d.drawLine(panel.getPlayingRect().x, extraLineYThird, panel.getPlayingRect().x + playingRectWidth, extraLineYThird);

        int extraLineYFifth = panel.getPlayingRect().y + 4 * cellSizeY;
        g2d.drawLine(panel.getPlayingRect().x, extraLineYFifth, panel.getPlayingRect().x + playingRectWidth, extraLineYFifth);

        int middleX = panel.getPlayingRect().x + gridSize / 2 * cellSizeX;
        g2d.drawLine(middleX, panel.getPlayingRect().y, middleX, panel.getPlayingRect().y + playingRectHeight);

        g2d.setStroke(originalStroke);
    }

    private static JTextField findTextField(List<JTextField> textFields, int row, int col, Panel p) {
        for (JTextField textField : textFields) {
            int x = (textField.getX() + textField.getWidth() / 2 - p.getPlayingRect().x) / cellSizeX;
            int y = (textField.getY() + textField.getHeight() / 2 - p.getPlayingRect().y) / cellSizeY;

            if (x >= 0 && x < 6 && y >= 0 && y < 6 && x == col && y == row) {
                return textField;
            }
        }
        return null;
    }

    private static void handleTextFieldAction(Panel panel, int[][] board, JTextField textField, int row, int col) {
        TextFieldInputChecker inputChecker = new TextFieldInputChecker(row, col, board, textField);
        inputChecker.updateBoard();
        String input = textField.getText();
        if (TextFieldInputChecker.isValidInput(input)) {
            int number = Integer.parseInt(input);
            if (Sudocode.isValid6x6(row, col, number)) {
                board[row][col] = number;
                if (Sudocode.isSolved6x6()) {
                    panel.uiAni.isHover = false;
                    panel.getMain().updateState(Main.STATE.COMPLETE);}
            } else {
                System.out.println("Invalid input! Try again.");
                textField.setText("");
            }
        } else {
            System.out.println("Invalid input format! Try again.");
            textField.setText("");
        }
    }
    public static void removeTextFieldsAtTop(List<JTextField> textFields, Panel p) {
        // Create a list to store text fields to be removed
        List<JTextField> textFieldsToRemove = new ArrayList<>();

        // Define the maximum y-coordinate for text fields to be considered at the top
        int maxYForRemoval = p.getPlayingRect().y;

        // Iterate through text fields and identify those at the top
        for (JTextField textField : textFields) {
            int textFieldY = textField.getY() + textField.getHeight() / 2;

            // Check if the text field is above the maxYForRemoval
            if (textFieldY < maxYForRemoval) {
                textFieldsToRemove.add(textField);
            }
        }

        textFieldsToRemove.forEach(p::remove);
        textFields.removeAll(textFieldsToRemove);
    }
}

