import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid {
    public static void drawGrid(Graphics g, Panel panel, int[][] board) {
        int gridSize = 6;
        int playingRectWidth = panel.getPlayingRect().width;
        int playingRectHeight = panel.getPlayingRect().height;
        int cellSizeX = playingRectWidth / gridSize;
        int cellSizeY = playingRectHeight / gridSize;

        panel.textFields.forEach(panel::remove);
        panel.textFields.clear();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int x = panel.getPlayingRect().x + j * cellSizeX + cellSizeX / 2;
                int y = panel.getPlayingRect().y + i * cellSizeY + cellSizeY / 2 + 5;  // Keep the +5 for numbers

                if (board[i][j] != 0) {
                    // Set font properties for bold numbers
                    Font font = new Font("Comic Sans MS", Font.BOLD, 25);  // Set font to bold
                    g2d.setFont(font);
                    g2d.setColor(new Color(139, 69, 19)); // Brown color

                    // Draw the number from the board array
                    String number = Integer.toString(board[i][j]);
                    g2d.drawString(number, x - g2d.getFontMetrics().stringWidth(number) / 2, y);
                } else {
                    // Draw placeholder text field
                    JTextField textField = new JTextField();
                    textField.setFont(new Font("Comic Sans MS", Font.BOLD, 20)); // Set font for JTextField
                    textField.setForeground(new Color(139, 69, 19)); // Set font color to brown
                    textField.setBounds(x - cellSizeX / 4, y - cellSizeY / 4, cellSizeX / 2, cellSizeY / 2 - 5);  // Adjust the y calculation for the text field
                    textField.setHorizontalAlignment(JTextField.CENTER); // Center align the text
                    textField.setDocument(new JTextFieldLimit(1)); // Limit input to one digit
                    int finalI = i; // Need to be final or effectively final to be used in ActionListener
                    int finalJ = j;
                    textField.getDocument().addDocumentListener(
                            new TextFieldInputChecker(finalI, finalJ, board, textField)
                    );
                    textField.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String input = textField.getText();
                            if (TextFieldInputChecker.isValidInput(input)) {
                                int number = Integer.parseInt(input);
                                if (Sudocode.isValid6x6(finalI, finalJ, number)) {
                                    board[finalI][finalJ] = number;
                                    if (Sudocode.isSolved6x6()) {
                                        System.out.println("Congratulations, you solved the 6x6 puzzle!");
                                        panel.getMain().updateState(Main.STATE.COMPLETE);
                                        // Handle puzzle completion here (e.g., show a dialog, perform further actions)
                                    }
                                } else {
                                    // Handle invalid input (e.g., display a message)
                                    System.out.println("Invalid input! Try again.");
                                    textField.setText(""); // Clear the text field
                                }
                            } else {
                                // Handle invalid input format (e.g., display a message)
                                System.out.println("Invalid input format! Try again.");
                                textField.setText(""); // Clear the text field
                            }
                        }
                    });
                    panel.add(textField);
                    panel.textFields.add(textField);
                }
            }
        }
        // Reset font and color for the remaining drawing
        g2d.setFont(new Font("Arial", Font.PLAIN, 12)); // Set default font
        g2d.setColor(Color.BLACK); // Set default color

        // Add game-specific drawing logic here

        // Draw the grid on top with a bolder stroke
        g2d.setColor(new Color(139, 69, 19)); // Brown color
        g2d.setStroke(new BasicStroke(2.0f)); // Set thicker stroke
        for (int i = 0; i <= gridSize; i++) {
            int x = panel.getPlayingRect().x + i * cellSizeX;
            int y = panel.getPlayingRect().y + i * cellSizeY;

            // Draw vertical grid lines
            g2d.drawLine(x, panel.getPlayingRect().y, x, panel.getPlayingRect().y + playingRectHeight);

            // Draw horizontal grid lines
            g2d.drawLine(panel.getPlayingRect().x, y, panel.getPlayingRect().x + playingRectWidth, y);
        }

        // Reset stroke for subsequent drawings
        g2d.setStroke(new BasicStroke(1.0f));
    }
}
