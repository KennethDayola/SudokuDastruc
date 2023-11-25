import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class MouseInputs implements MouseListener {

    private Panel panel;
    private MenuPanel menuPanel;
    public MouseInputs(Panel panel, MenuPanel menuPanel) {
        this.panel = panel;
        this.menuPanel = menuPanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (Main.state == Main.STATE.GAME) {
            if (panel.getRetryHitbox().contains(e.getPoint())) {
                System.out.println(Arrays.deepToString(Sudocode.board6x6));
            }
            if (panel.getMenuHitbox().contains(e.getPoint())) {
                panel.getMain().updateState(Main.STATE.MENU);
            }
            if (panel.getQuitHitbox().contains(e.getPoint())) {
                System.exit(0);
            }
        }
        if (Main.state == Main.STATE.MENU){
            if (menuPanel.getPlayButton().contains(e.getPoint())) {
                panel.getMain().updateState(Main.STATE.GAME);
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