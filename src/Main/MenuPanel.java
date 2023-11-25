package Main;

import OtherComponents.MouseInputs;

import javax.swing.ImageIcon;
import java.awt.*;

public class MenuPanel {

    private static final Rectangle playButton = new Rectangle((int) (Main.WIDTH_DEFAULT / 2.45), 420, 290, 117);
    private static final Rectangle quitButton = new Rectangle((int) (Main.WIDTH_DEFAULT * 0.705), 420, 290, 117);
    private final Panel p;
    private Image gifImage;

    public MenuPanel(Panel panel) {
        this.p = panel;
        menuStart();
        MouseInputs mouseInputs = new MouseInputs(p, this);

        p.addMouseListener(mouseInputs);
    }

    private void menuStart() {
        p.setFocusable(true);
    }

    public void drawMenu(Graphics g) {
        if (Main.state == Main.STATE.MENU) {
            Graphics2D g2d = (Graphics2D) g;

            g.fillRect(0, 0, p.getWidth(), p.getHeight());

            ImageIcon gifIcon = new ImageIcon(MenuPanel.class.getResource("/res/sudokuMenu.gif"));
            gifImage = gifIcon.getImage();
            g.drawImage(gifImage,0,0,null);

        }
    }

    public Rectangle getPlayButton() {
        return playButton;
    }
    public Rectangle getQuitButton(){
        return quitButton;
    }
}