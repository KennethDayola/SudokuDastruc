package Main;

import OtherComponents.Grid;
import OtherComponents.MouseInputs;
import OtherComponents.Sudocode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Main.Main.HEIGHT_DEFAULT;
import static Main.Main.WIDTH_DEFAULT;

public class Panel extends JPanel {

    private Main main;
    private Rectangle retryHitbox, menuHitbox, quitHitbox, playingRect;
    public List<JTextField> textFields;

    public Panel(Main main) {
        this.main = main;
        this.setPreferredSize(new java.awt.Dimension(WIDTH_DEFAULT, HEIGHT_DEFAULT));
        this.textFields = new ArrayList<>();
        initComponents();
        MouseInputs mouseInputs = new MouseInputs(this, new MenuPanel(this));

        addMouseListener(mouseInputs);
    }

    public Panel() {

    }

    private void initComponents() {
        retryHitbox = new Rectangle(169, 222, 200, 65);
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
        MenuPanel menuPanel = new MenuPanel(this);
        if (main.getState() == Main.STATE.GAME) {
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/res/background.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

            g2d.setColor(Color.RED);
            g2d.draw(playingRect);

            Grid.drawGrid(g, this, Sudocode.board6x6);

        } else if (main.getState() == Main.STATE.MENU) {
            textFields.forEach(this::remove);
            textFields.clear();
            menuPanel.drawMenu(g);
        }
        else if(main.getState() == Main.STATE.COMPLETE){
            textFields.forEach(this::remove);
            textFields.clear();
            g.drawString("1. COMPLETED!", 50, 50);
        }
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
}