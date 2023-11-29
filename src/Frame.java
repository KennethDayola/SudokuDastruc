import javax.swing.JFrame;

public class Frame {
    private JFrame jframe;

    public Frame(Panel panel) {

        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(panel);
        jframe.setContentPane(panel);

        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);



    }

}