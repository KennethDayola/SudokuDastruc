package Main;

import OtherComponents.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Main.Main.HEIGHT_DEFAULT;
import static Main.Main.WIDTH_DEFAULT;

public class Panel extends JPanel {

    private Main main;
    private Rectangle retryHitbox, menuHitbox, quitHitbox, playingRect, lastMenuHitbox, lastQuitHitbox;
    public List<JTextField> textFields;
    private MenuPanel menuPanel;
    public static MusicMethods bgm = new MusicMethods();
    public Animations uiAni = new Animations(this);

    public Panel(Main main) {
        this.main = main;
        this.setPreferredSize(new Dimension(WIDTH_DEFAULT, HEIGHT_DEFAULT));
        this.textFields = new ArrayList<>();
        this.setDoubleBuffered(true);
        initComponents();
        MouseInputs mouseInputs = new MouseInputs(this, new MenuPanel(this));
        this.menuPanel = new MenuPanel(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        bgm.loadMusic(MusicMethods.MENU_MUSIC);
    }

    private void initComponents() {
        retryHitbox = new Rectangle(169, 222, 200, 65);
        menuHitbox = new Rectangle(169, 318, 200, 65);
        quitHitbox = new Rectangle(169, 425, 200, 65);
        playingRect = new Rectangle(611, 121, 408, 510);
        lastMenuHitbox = new Rectangle(500, 398, 260, 67);
        lastQuitHitbox = new Rectangle(560, 490, 142, 51);
        uiAni.loadUiAni();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK); // Set the background color
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the panel with the background color

        if (main.getState() == Main.STATE.GAME) {
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/res/background.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

            g2d.setColor(Color.RED);
            g2d.draw(playingRect);
            Grid.drawGrid(g, this, Sudocode.board6x6);

            if (uiAni.isHover) {
                uiAni.showHover(g);
            }

        } else if (main.getState() == Main.STATE.MENU) {
            clearTextFields();
            menuPanel.drawMenu(g);
        } else if (main.getState() == Main.STATE.COMPLETE) {
            clearTextFields();
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/res/completed.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void clearTextFields() {
        textFields.forEach(field -> remove(field));
        textFields.clear();
    }

    public Main getMain() {
        return main;
    }

    public Rectangle getRetryHitbox() {
        return retryHitbox;
    }

    public Rectangle getMenuHitbox() {
        return menuHitbox;
    }

    public Rectangle getPlayingRect() {
        return playingRect;
    }

    public Rectangle getQuitHitbox() {
        return quitHitbox;
    }

    public Rectangle getLastMenuHitbox() {
        return lastMenuHitbox;
    }

    public Rectangle getLastQuitHitbox() {
        return lastQuitHitbox;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
}
