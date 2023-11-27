package Main;

public class Main implements Runnable{
    public static final int WIDTH_DEFAULT = 1280;
    public static final int HEIGHT_DEFAULT = 720;
    private Panel panel;
    private Frame frame;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 200;

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
        startGameLoop();
    }
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
            }
        }
    }
    public STATE getState() {
        return state;
    }

    public static void main(String[] args) {
        new Main();
    }
}
