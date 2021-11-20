import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Frame extends JFrame {
    private Screen s;
    private Simulation sim;
    private double tslu;
    private double Sequenzzeit;


    public Frame() {
        String input = JOptionPane.showInputDialog(this, "Zellengröße?");
        try {
            Zelle.size = Integer.parseInt(input);
        } catch (Exception e) {
            System.exit(0);
        }

        input = JOptionPane.showInputDialog(this, "Sequenzzeit?");
        try {
            Sequenzzeit = Double.parseDouble(input);
        } catch (Exception e) {
            System.exit(0);
        }


    }


    public void createScreen() {
        sim = new Simulation();

        addKeyListener(sim);
        addMouseListener(sim);
        addMouseMotionListener(sim);

        s = new Screen();
        s.setSize(this.getSize());
        add(s);

    }

    public void naechsteGeneration(double tslf) {
        tslu += tslf;
        if (tslu >= Sequenzzeit) {
            sim.naechsteGeneration();
            tslu = 0;
        }
    }

    public void repaint() {
        s.repaint();
    }

    private class Screen extends JLabel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            sim.draw(g);
        }
    }
}
