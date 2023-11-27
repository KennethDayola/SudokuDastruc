package Main;

import OtherComponents.Grid;
import OtherComponents.MouseInputs;
import OtherComponents.MusicMethods;
import OtherComponents.Sudocode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Main.Main.HEIGHT_DEFAULT;
import static Main.Main.WIDTH_DEFAULT;
import static OtherComponents.Animations.HOVER_ANI;

public class Panel extends JPanel {

    private Main main;
    private Rectangle retryHitbox, menuHitbox, quitHitbox, playingRect, lastMenuHitbox, lastQuitHitbox;
    public List<JTextField> textFields;
    private MenuPanel menuPanel;

    public static MusicMethods bgm = new MusicMethods();

    public Panel(Main main) {
        this.main = main;
        this.setPreferredSize(new java.awt.Dimension(WIDTH_DEFAULT, HEIGHT_DEFAULT));
        this.textFields = new ArrayList<>();
        this.setDoubleBuffered(true);
        initComponents();
        MouseInputs mouseInputs = new MouseInputs(this, new MenuPanel(this));
        this.menuPanel = new MenuPanel(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    private void initComponents() {
        retryHitbox = new Rectangle(169, 222, 200, 65);
        menuHitbox = new Rectangle(169, 318, 200, 65);
        quitHitbox = new Rectangle(169, 425, 200, 65);
        playingRect = new Rectangle(611, 121, 408, 510);
        lastMenuHitbox = new Rectangle(500, 398, 260, 67);
        lastQuitHitbox = new Rectangle(560, 490, 142, 51);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (main.getState() == Main.STATE.GAME) {
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/res/background.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            MouseInputs.getUIAni().initGifTimer(HOVER_ANI);

            g2d.setColor(Color.RED);
            g2d.draw(playingRect);

            Grid.drawGrid(g, this, Sudocode.board6x6);


        } else if (main.getState() == Main.STATE.MENU) {
            bgm.loadMusic(MusicMethods.MENU_MUSIC);
            textFields.forEach(this::remove);
            textFields.clear();
            menuPanel.drawMenu(g);
        }
        else if(main.getState() == Main.STATE.COMPLETE){
            textFields.forEach(this::remove);
            textFields.clear();
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/res/completed.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void clearTextFields() {
        textFields.forEach(field -> field.setText(""));
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
    public Rectangle getLastMenuHitbox(){
        return lastMenuHitbox;
    }
    public Rectangle getLastQuitHitbox(){
        return lastQuitHitbox;
    }
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
}