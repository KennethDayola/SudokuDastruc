import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuPanel {

    private static final Rectangle playButton = new Rectangle((int) (Main.WIDTH_DEFAULT / 2.45), 420, 290, 117);
    private static final Rectangle quitButton = new Rectangle((int) (Main.WIDTH_DEFAULT * 0.705), 420, 290, 117);
    private static Panel p;

    private static JLabel gifLabel;
    private static Timer gifTimer;
    private static JLabel imageLabel = new JLabel();
    private static boolean imageLabelInitialized = false;

    public static boolean isHovering = false;
    public static String buttonType = new String();


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

    public static void displayHover() {

        if (isHovering && !imageLabelInitialized) {
            if (buttonType.equals("menuPlay")) {
                imageLabel.setBounds(645, 350, 100, 100);
            }
            if (buttonType.equals("menuQuit")) {
                imageLabel.setBounds(1022, 350, 100, 100);
            }
            p.add(imageLabel);
            imageLabelInitialized = true;
        }
        else if (!isHovering){
            p.remove(imageLabel);
            imageLabelInitialized = false;
        }
    }

    public static void drawMenu(Graphics g) {
        if (Main.state == Main.STATE.MENU) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, p.getWidth(), p.getHeight());

            gifLabel.setBounds(0, 0, p.getWidth(), p.getHeight());
            p.add(gifLabel);
            displayHover();
            loadArrowHover();
        }
    }

    private static void loadArrowHover(){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(MenuPanel.class.getResource("/arrow.png")));
        Image image = imageIcon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
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

                ImageIcon gifIcon = new ImageIcon(MenuPanel.class.getResource("/sudokuMenu.gif"));
                gifLabel.setIcon(gifIcon);
            }
        });

        gifTimer.start();
    }

    public static Rectangle getPlayButton() {
        return playButton;
    }
    public Rectangle getQuitButton() {
        return quitButton;
    }
}