package src;

public class Main {
    public static final int WIDTH_DEFAULT = 1280;
    public static final int HEIGHT_DEFAULT = 720;
    private Panel panel;
    private Frame frame;

    public enum STATE {
        MENU, GAME, COMPLETE
    }

    public static STATE state = STATE.MENU;

    public Main() {
        panel = new Panel(this);
        frame = new Frame(panel);
    }

    public void updateState(STATE newState) {
        this.state = newState;
        panel.revalidate();
        panel.repaint();
    }

    public STATE getState() {
        return state;
    }

    public static void main(String[] args) {
        new Main();
    }
}
