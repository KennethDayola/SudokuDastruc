package OtherComponents;

import Main.MenuPanel;
import Main.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animations {
    public JLabel gifLabel;
    public Timer gifTimer;
    private Panel p;

    public static final String HOVER_ANI = "/res/hoverAni.gif";
    public static final String MENU_BG = "/res/sudokuMenu.gif";
    public ImageIcon gifIcon;

    public Animations (Panel panel){
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
    public void showMenuBg(){
        initGifTimer(MENU_BG);
        gifLabel.setBounds(0, 0, p.getWidth(), p.getHeight());
        p.add(gifLabel);
    }

    public void showHoverAni(){
        int newWidth = 300;
        int newHeight = 240;
        Image scaledImage = gifIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        ImageIcon scaledGifIcon = new ImageIcon(scaledImage);
        gifLabel.setIcon(scaledGifIcon);

        // Set the bounds for the scaled gifLabel
        gifLabel.setBounds(0, 0, newWidth, newHeight);

        // Add the scaled gifLabel to the panel
        p.add(gifLabel);
    }
}

