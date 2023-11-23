package Main;

import OtherComponents.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static Main.Main.HEIGHT_DEFAULT;
import static Main.Main.WIDTH_DEFAULT;

public class Panel extends JPanel {

    private Main main;
    private JLabel aboveContentLabel;
    private Rectangle playHitbox, menuHitbox, quitHitbox, playingRect;

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
        playingRect = new Rectangle(611, 121, 408, 510);
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

            // Draw the outer rectangles
            g2d.draw(playHitbox);
            g2d.draw(menuHitbox);
            g2d.draw(quitHitbox);
            g2d.setColor(Color.RED);
            g2d.draw(playingRect);

            // Draw a 6x6 grid within playingRect
            int gridSize = 6;
            int playingRectWidth = playingRect.width;
            int playingRectHeight = playingRect.height;
            int cellSizeX = playingRectWidth / gridSize;
            int cellSizeY = playingRectHeight / gridSize;

            g2d.setColor(Color.RED);
            for (int i = 0; i <= gridSize; i++) {
                int x = playingRect.x + i * cellSizeX;
                int y = playingRect.y + i * cellSizeY;

                // Draw vertical grid lines
                g2d.drawLine(x, playingRect.y, x, playingRect.y + playingRectHeight);

                // Draw horizontal grid lines
                g2d.drawLine(playingRect.x, y, playingRect.x + playingRectWidth, y);
            }

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