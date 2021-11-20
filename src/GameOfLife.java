import javax.swing.JFrame;

public class GameOfLife {
    static int width;
    static int height;

    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        f.setLocation(0, 0);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);

        width = f.getWidth();
        height = f.getHeight();
        f.createScreen();

        long lastFrame = System.currentTimeMillis();
        while (true) {
            long thisFrame = System.currentTimeMillis();
            double tslf = (double) ((thisFrame - lastFrame) / 1000.0);
            lastFrame = thisFrame;
            f.naechsteGeneration(tslf);
            f.repaint();
        }
    }
} 