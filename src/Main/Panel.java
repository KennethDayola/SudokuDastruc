package Main;

import OtherComponents.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static Main.Main.HEIGHT_DEFAULT;
import static Main.Main.WIDTH_DEFAULT;

public class Panel extends JPanel {

    private Main main;
    private JLabel aboveContentLabel;
    private Rectangle playHitbox, menuHitbox, quitHitbox;

    public Panel(Main main) {
        this.main = main;
        this.setPreferredSize(new java.awt.Dimension(WIDTH_DEFAULT, HEIGHT_DEFAULT));
        initComponents();
        MouseInputs mouseInputs = new MouseInputs(this);

        addMouseListener(mouseInputs);
    }

    public Panel() {

    }

    private void initComponents() {
        aboveContentLabel = new JLabel("Above Content Label");
        aboveContentLabel.setForeground(Color.WHITE);
        aboveContentLabel.setBounds(10, 10, 200, 20);
        this.add(aboveContentLabel);

        playHitbox = new Rectangle(169, 222, 200, 65);
        menuHitbox = new Rectangle(169, 318, 200, 65);
        quitHitbox = new Rectangle(169, 425, 200, 65);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        if (main.getState() == Main.STATE.GAME) {
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/res/background.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

            // Define the rectangle for the Sudoku grid
            int gridX = 300; // Adjust the X-coordinate of the grid
            int gridY = 200; // Adjust the Y-coordinate of the grid
            int gridWidth = 350; // Adjust the width of the grid
            int gridHeight = 400; // Adjust the height of the grid

            // Set the color to bright red
            g2d.setColor(Color.RED);

            // Draw Sudoku grid within the specified rectangle
            int gridSize = 9; // Change this to adjust the size of the grid
            int cellSize = gridWidth / gridSize;

            // Draw grid lines
            for (int i = 0; i <= gridSize; i++) {
                g2d.drawLine(gridX + i * cellSize, gridY, gridX + i * cellSize, gridY + gridHeight);
                g2d.drawLine(gridX, gridY + i * cellSize, gridX + gridWidth, gridY + i * cellSize);
            }

            // Reset the color to the default
            g2d.setColor(Color.BLACK);

            g2d.draw(playHitbox);
            g2d.draw(menuHitbox);
            g2d.draw(quitHitbox);
            // Add game-specific drawing logic here
        } else if (main.getState() == Main.STATE.MENU) {
            MenuPanel.drawMenu(g);
        }
    }
    public Main getMain() {
        return main;
    }

    public Rectangle getMenuHitbox() {
        return menuHitbox;
    }

    public Rectangle getQuitHitbox() {
        return quitHitbox;
    }
}