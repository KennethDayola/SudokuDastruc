package OtherComponents;

import Main.Panel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Animations {
    public boolean isHover = false;
    private static Panel p;
    public static final String HOVER_CIRCLING = "/res/hoverAni.png";
    public static final String INVALID_IMG = "/res/invalidImg.png";
    public BufferedImage animation;
    public static BufferedImage invalidImg;
    private int currentFrame = 0;
    private int frameWidth = 480;
    private int frameDelay = 1;
    private String hitboxType;
    private int destX, destY;
    private static Timer invalidImgTimer;
    public static boolean showInvalidImg = false;

    public Animations(Panel panel) {
        this.p = panel;

    }

    public void loadUiAni(String filePath) {
        try {
            InputStream inputStream = Panel.class.getResourceAsStream(filePath);
            animation = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadInvalidImg(){
        try {
            InputStream inputStream = Panel.class.getResourceAsStream(INVALID_IMG);
            BufferedImage originalImage = ImageIO.read(inputStream);
            int w = originalImage.getWidth();
            int h = originalImage.getHeight();
            invalidImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(0.5, 0.5);
            AffineTransformOp scaleOp =
                    new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
            invalidImg = scaleOp.filter(originalImage, invalidImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawInvalidImg(Graphics g) {
        if (showInvalidImg){
            g.drawImage(invalidImg, 935, 15, p);
            if (!invalidImgTimer.isRunning()) {
                invalidImgTimer.start();
            }
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

    public static void initInvalidImgTimer() {
        invalidImgTimer = new Timer(2000, e -> {
            showInvalidImg = false;
            invalidImgTimer.stop();
            p.repaint();
        });
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

