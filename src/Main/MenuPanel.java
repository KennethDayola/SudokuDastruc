package Main;

import OtherComponents.Animations;
import OtherComponents.MouseInputs;

import java.awt.*;


public class MenuPanel {

    private static final Rectangle playButton = new Rectangle((int) (Main.WIDTH_DEFAULT / 2.45), 420, 290, 117);
    private static final Rectangle quitButton = new Rectangle((int) (Main.WIDTH_DEFAULT * 0.705), 420, 290, 117);
    private static Panel p;
    private static Animations ani;

    public MenuPanel(Panel panel) {
        p = panel;
        menuStart();
        MouseInputs mouseInputs = new MouseInputs(p, this);

        p.addMouseListener(mouseInputs);

        ani = new Animations(p);
    }

    private void menuStart() {
        p.setFocusable(true);
    }

    public static void drawMenu(Graphics g) {
        if (Main.state == Main.STATE.MENU) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, p.getWidth(), p.getHeight());

            ani.showMenuBg();
        }
    }

    public static Rectangle getPlayButton() {
        return playButton;
    }

    public Rectangle getQuitButton() {
        return quitButton;
    }

    public static void stopDrawingMenu() {

        if (ani.gifTimer != null) {
            ani.gifTimer.stop();
        }
    }
}