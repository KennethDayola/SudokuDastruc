package Main;

import javax.swing.*;
import java.util.Scanner;

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
        int i;
        Scanner sc = new Scanner(System.in);

        i = sc.nextInt();
        switch (i) {
            case 1:
                state = STATE.GAME;
                System.out.println("aaa");
                panel.repaint();
                break;
            case 2:
                state = STATE.MENU;
                break;
        }
    }

    public void updateState(STATE newState) {
        this.state = newState;
        SwingUtilities.invokeLater(() -> {
            panel.revalidate();
            panel.repaint();
        });
    }

    public STATE getState() {
        return state;
    }

    public static void main(String[] args) {
        new Main();
    }
}
