package OtherComponents;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import Main.Main;
import Main.Panel;
import Main.MenuPanel;

public class MouseInputs implements MouseListener {

    private Panel panel;
    // Use MenuPanel instance variable
    private MenuPanel menuPanel;

    public MouseInputs(Panel panel, MenuPanel menuPanel) {
        this.panel = panel;
        this.menuPanel = menuPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Main.state == Main.STATE.GAME) {
            if (panel.getRetryHitbox().contains(e.getPoint())) {
                panel.clearTextFields();
            }
            if (panel.getMenuHitbox().contains(e.getPoint())) {
                panel.getMain().updateState(Main.STATE.MENU);
            }
            if (panel.getQuitHitbox().contains(e.getPoint())) {
                System.exit(0);
            }
        }
        if (Main.state == Main.STATE.MENU) {
            // Use getMenuPanel() method to get the existing MenuPanel instance
            if (panel.getMenuPanel().getPlayButton().contains(e.getPoint())) {
                MenuPanel.stopDrawingMenu();
                panel.removeAll();
                panel.getMain().updateState(Main.STATE.GAME);

            }
            if (panel.getMenuPanel().getQuitButton().contains(e.getPoint())) {
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