package Main;

import java.util.Scanner;

public class Main {
    public static final int WIDTH_DEFAULT = 1280;
    public static final int HEIGHT_DEFAULT = 720;
    private Panel panel;
    private Frame frame;

    public enum STATE {
        MENU, GAME
    }

    public static STATE state = STATE.GAME;

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

    // Method to update the game state based on user actions
    public void updateState(STATE newState) {
        this.state = newState;
        panel.repaint();  // Repaint the panel when the state changes
    }

    public STATE getState() {
        return state;
    }
    public void setState(STATE newState) {
        state = newState;
    }

    public static void main(String[] args) {
        new Main();
    }
}
