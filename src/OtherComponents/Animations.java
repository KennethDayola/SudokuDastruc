package OtherComponents;

import Main.MenuPanel;
import Main.Panel;
import Main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class Animations {
    public JLabel gifLabel;
    public Timer gifTimer;
    public boolean isHover = false;
    private Panel p;

    public static final String HOVER_ARROW = "/res/hoverArrow.png";
    public static final String MENU_BG = "/res/sudokuMenu.gif";
    public ImageIcon gifIcon;
    public BufferedImage hoverArrow;

    public Animations(Panel panel) {
        this.p = panel;

    }

    public void initGifTimer(String filePath) {
        gifLabel = new JLabel();
        gifTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gifIcon = new ImageIcon(MenuPanel.class.getResource(filePath));
                gifLabel.setIcon(gifIcon);
            }
        });

        gifTimer.start();
    }

    public void loadUiAni() {
        try {
            InputStream inputStream = Panel.class.getResourceAsStream(HOVER_ARROW);
            hoverArrow = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception or return a default image
        }
    }

    public void showMenuBg() {
        initGifTimer(MENU_BG);
        gifLabel.setBounds(0, 0, p.getWidth(), p.getHeight());
        p.add(gifLabel);
    }

    public void showHover(Graphics g) {
    }
}

