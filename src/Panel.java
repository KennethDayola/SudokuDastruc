import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Panel extends JPanel {

    private Main main;
    private Rectangle retryHitbox, menuHitbox, quitHitbox, playingRect, lastMenuHitbox, lastQuitHitbox;
    public List<JTextField> textFields;
    private MenuPanel menuPanel;

    public static MusicMethods bgm = new MusicMethods();
    public static MusicMethods successSound = new MusicMethods();
    public static MusicMethods wrongSound = new MusicMethods();
    public Animations uiAni = new Animations(this);

    public Panel(Main main) {
        this.main = main;
        this.setPreferredSize(new java.awt.Dimension(Main.WIDTH_DEFAULT, Main.HEIGHT_DEFAULT));
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
        uiAni.loadUiAni(Animations.HOVER_CIRCLING);
        Animations.loadInvalidImg();
        Animations.initInvalidImgTimer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        if (main.getState() == Main.STATE.GAME) {
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/background.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

            g2d.setColor(Color.RED);
            g2d.draw(playingRect);


            Grid.drawGrid(g, this, Sudocode.board6x6);
            Grid.removeTextFieldsAtTop(textFields, this);
            uiAni.showHover(g);
            Animations.drawInvalidImg(g);

        } else if (main.getState() == Main.STATE.MENU) {
            bgm.loadMusic(MusicMethods.MENU_MUSIC);
            textFields.forEach(this::remove);
            textFields.clear();
            menuPanel.drawMenu(g);
        }
        else if(main.getState() == Main.STATE.COMPLETE){
            bgm.stop();
            this.removeAll();
            successSound.loadMusic(MusicMethods.SUCCESS_MUSIC);
            Sudocode.resetBoard();
            ImageIcon lastBgIcon = new ImageIcon(getClass().getResource("/completed.png"));
            Image background = lastBgIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            this.repaint();
            uiAni.showHover(g);
        }
    }

    public void clearTextFields() { textFields.forEach(field -> field.setText("")); }
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
    public Rectangle getLastMenuHitbox() {
        return lastMenuHitbox;
    }
    public Rectangle getLastQuitHitbox() {
        return lastQuitHitbox;
    }
    public Rectangle getQuitHitbox() {
        return quitHitbox;
    }
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
    public List<JTextField> getTextFields() {
        return (List<JTextField>) textFields;
    }
}