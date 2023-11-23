package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Main.Sudoku.HEIGHT_DEFAULT;
import static Main.Sudoku.WIDTH_DEFAULT;

public class Panel extends JPanel implements MouseListener {

    public javax.swing.JPanel jPanel2;
    private Sudoku sudoku;
    private JLabel aboveContentLabel;
    private Rectangle hitbox; // The hitbox area

    public Panel(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.setPreferredSize(new java.awt.Dimension(WIDTH_DEFAULT, HEIGHT_DEFAULT));
        initComponents();

        // Add mouse listener to the panel
        addMouseListener(this);
    }
    public Panel(){

    }

    private void initComponents() {
        aboveContentLabel = new JLabel("Above Content Label");
        aboveContentLabel.setForeground(Color.WHITE); // Set the text color
        aboveContentLabel.setBounds(10, 10, 200, 20); // Set the position and size
        this.add(aboveContentLabel); // Add the label to the main panel (this)

        // Define the hitbox area
        hitbox = new Rectangle(50, 50, 100, 50); // Adjust the coordinates and size of the hitbox
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        if (sudoku.getState() == Sudoku.STATE.GAME) {
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/res/background.png"));
            Image background = backgroundIcon.getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            g2d.draw(hitbox);
            // Add game-specific drawing logic here
        } else if (sudoku.getState() == Sudoku.STATE.MENU) {
            MenuPanel.drawMenu(g);

        }
    }

    // Implementing MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if the mouse click is within the hitbox
        if (hitbox.contains(e.getPoint())) {
            // Perform the action when the hitbox is clicked
            System.out.println("Hitbox clicked! Performing action...");
            // Add your action code here
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement if needed
    }
}