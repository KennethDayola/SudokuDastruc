package Main;

import OtherComponents.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel {

    private static final Rectangle playButton = new Rectangle((int) (Main.WIDTH_DEFAULT / 2.45), 420, 290, 117);
    private static final Rectangle quitButton = new Rectangle((int) (Main.WIDTH_DEFAULT * 0.705), 420, 290, 117);
    private static Panel p;

    // New variables for GIF
    private static JLabel gifLabel;
    private static Timer gifTimer;



    public MenuPanel(Panel panel) {
        p = panel;
        menuStart();
        MouseInputs mouseInputs = new MouseInputs(p, this);

        p.addMouseListener(mouseInputs);

        initGifTimer();
    }

    private void menuStart() {
        p.setFocusable(true);
    }

    public static void drawMenu(Graphics g) {
        if (Main.state == Main.STATE.MENU) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, p.getWidth(), p.getHeight());

            if (gifLabel != null) {
                gifLabel.setBounds(0, 0, p.getWidth(), p.getHeight());
                p.add(gifLabel);
            }
        }
    }

    public static Rectangle getPlayButton() {
        return playButton;
    }

    public Rectangle getQuitButton() {
        return quitButton;
    }

    public static void stopDrawingMenu() {

        if (gifTimer != null) {
            gifTimer.stop();
        }
    }

    private void initGifTimer() {
        gifLabel = new JLabel();
        gifTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ImageIcon gifIcon = new ImageIcon(MenuPanel.class.getResource("/res/sudokuMenu.gif"));
                gifLabel.setIcon(gifIcon);
            }
        });

        gifTimer.start();
    }
}