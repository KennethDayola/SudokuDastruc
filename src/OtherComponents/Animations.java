package OtherComponents;

import Main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Animations {
    public boolean isHover = false;
    private Panel p;
    public static final String HOVER_CIRCLING = "/res/hoverAni.png";
    public BufferedImage animation;
    private int currentFrame = 0;
    private int frameWidth = 480;
    private int frameDelay = 1;
    private String hitboxType;
    private int destX, destY;

    public Animations(Panel panel) {
        this.p = panel;

    }

    public void loadUiAni(String filePath) {
        try {
            InputStream inputStream = Panel.class.getResourceAsStream(filePath);
            animation = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception or return a default image
        }
    }

    public void setHover(boolean hover, String hitbox) {
        this.hitboxType = hitbox;
        this.isHover = hover;
        p.repaint();
    }

    public void showHover(Graphics g) {
        if (isHover && animation != null) {
            int frameCount = animation.getWidth() / frameWidth;

            int frameX = currentFrame * frameWidth;

            destX = 0;
            destY = 0;
            int destHeight = 90; // Adjust this value to set the desired height
            int destWidth = (int) ((double) destHeight / animation.getHeight() * frameWidth);

            switchHitbox();

            g.drawImage(animation, destX, destY, destX + destWidth, destY + destHeight,
                    frameX, 0, frameX + frameWidth, animation.getHeight(), p);

            if (System.currentTimeMillis() % frameDelay == 0) {
                currentFrame = (currentFrame + 1) % frameCount;
            }

            p.repaint();
        }
    }

    private void switchHitbox(){
        switch (hitboxType){
            case "Retry":
                destX = 375;
                destY = 205;
                break;
            case "Menu":
                destX = 375;
                destY = 305;
                break;
            case "Quit":
                destX = 375;
                destY = 410;
                break;
            case "lastMenu":
                destX = 770;
                destY = 390;
                break;
            case "lastQuit":
                destX = 715;
                destY = 475;
                break;
        }

    }
}

