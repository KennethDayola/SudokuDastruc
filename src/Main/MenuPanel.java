package Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class MenuPanel  {

    public static Rectangle playButton = new Rectangle(Main.WIDTH_DEFAULT / 2 + 120, 120, 150, 100);

    public MenuPanel() {
        menuStart();
    }

    private Panel p = new Panel();

    void menuStart() {
        p.setFocusable(true);
    }

    public static void drawMenu(Graphics g) {

        if (Main.state == Main.STATE.MENU) {
            Graphics2D g2d = (Graphics2D) g;
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.BLACK);

            g.drawString("1. Start Game", 50, 50);
            g.drawString("2. Options", 50, 80);
            g.drawString("3. Exit", 50, 110);

            g2d.draw(playButton);
        }
    }
}