package OtherComponents;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.Main;
import Main.Panel;

public class MouseInputs implements MouseListener {

    private Panel panel;

    public MouseInputs(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (panel.getMenuHitbox().contains(e.getPoint())) {
            panel.getMain().setState(Main.STATE.MENU);
            panel.repaint();
        }
        if (panel.getQuitHitbox().contains(e.getPoint())) {
            System.exit(0);
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