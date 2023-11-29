package OtherComponents;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.ExecutionException;

import Main.Main;
import Main.Panel;
import Main.MenuPanel;

import javax.swing.*;

import static OtherComponents.MusicMethods.HOVER_MUSIC;
import static OtherComponents.MusicMethods.CLICK_MUSIC;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private Panel panel;
    private MenuPanel menuPanel;
    private MusicMethods hoverSound = new MusicMethods();
    private MusicMethods clickSound = new MusicMethods();

    public MouseInputs(Panel panel, MenuPanel menuPanel) {
        this.panel = panel;
        this.menuPanel = menuPanel;
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Main.state == Main.STATE.GAME) {
            if (panel.getRetryHitbox().contains(e.getX(), e.getY())) {
                panel.uiAni.setHover(true,"Retry");
                hoverSound.loadMusic(HOVER_MUSIC);
            } else if (panel.getMenuHitbox().contains(e.getX(), e.getY())) {
                panel.uiAni.setHover(true,"Menu");
                hoverSound.loadMusic(HOVER_MUSIC);
            } else if (panel.getQuitHitbox().contains(e.getX(), e.getY())) {
                panel.uiAni.setHover(true,"Quit");
                hoverSound.loadMusic(HOVER_MUSIC);
            } else {
                panel.uiAni.isHover = false;
                hoverSound.setMusicLoaded(false);
            }
        } else if (Main.state == Main.STATE.MENU)
            if (panel.getMenuPanel().getPlayButton().contains(e.getX(), e.getY())) {
                MenuPanel.isHovering = false;
                MenuPanel.isHovering = true;
                MenuPanel.buttonType = "menuPlay";
                hoverSound.loadMusic(HOVER_MUSIC);
            }else if( panel.getMenuPanel().getQuitButton().contains(e.getX(), e.getY())){
                MenuPanel.isHovering = false;
                MenuPanel.isHovering = true;
                MenuPanel.buttonType = "menuQuit";
                hoverSound.loadMusic(HOVER_MUSIC);
            }else {
                MenuPanel.isHovering = false;
                hoverSound.setMusicLoaded(false);
            }
        if (Main.state == Main.STATE.COMPLETE) {
            if (panel.getLastMenuHitbox().contains(e.getX(), e.getY())) {
                panel.uiAni.setHover(true,"lastMenu");
                hoverSound.loadMusic(HOVER_MUSIC);
            } else if(panel.getLastQuitHitbox().contains(e.getX(), e.getY())){
                panel.uiAni.setHover(true,"lastQuit");
                hoverSound.loadMusic(HOVER_MUSIC);
            }else {
                panel.uiAni.isHover = false;
                hoverSound.setMusicLoaded(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Main.state == Main.STATE.GAME) {
            if (panel.getRetryHitbox().contains(e.getPoint())) {
                clickSound.loadMusic(CLICK_MUSIC);
                panel.clearTextFields();
            }
            if (panel.getMenuHitbox().contains(e.getPoint())) {
                panel.uiAni.isHover = false;
                clickSound.loadMusic(CLICK_MUSIC);
                panel.bgm.stop();
                panel.bgm.loadMusic(MusicMethods.MENU_MUSIC);
                panel.getMain().updateState(Main.STATE.MENU);
            }
            if (panel.getQuitHitbox().contains(e.getPoint())) {
                clickSound.loadMusic(CLICK_MUSIC);
                System.exit(0);
            }
        }
        if (Main.state == Main.STATE.MENU) {
            if (panel.getMenuPanel().getPlayButton().contains(e.getPoint())) {
                clickSound.loadMusic(CLICK_MUSIC);
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Perform loading tasks here
                        MenuPanel.stopDrawingMenu();
                        panel.removeAll();
                        panel.getMain().updateState(Main.STATE.GAME);

                        panel.bgm.stop();
                        panel.bgm.loadMusic(MusicMethods.GAME_MUSIC);

                        return null;
                    }

                    @Override
                    protected void done() {
                        // This is called on the EDT after doInBackground completes
                        try {
                            get();  // Retrieve any exceptions that might have occurred
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                // Execute the SwingWorker
                worker.execute();
            }
            if (panel.getMenuPanel().getQuitButton().contains(e.getPoint())) {
                clickSound.loadMusic(CLICK_MUSIC);
                System.exit(0);
            }

        }
        if (Main.state == Main.STATE.COMPLETE) {
            if (panel.getLastMenuHitbox().contains(e.getPoint())) {
                clickSound.loadMusic(CLICK_MUSIC);
                panel.getMain().updateState(Main.STATE.MENU);
            }
            if (panel.getLastQuitHitbox().contains(e.getPoint())) {
                clickSound.loadMusic(CLICK_MUSIC);
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

