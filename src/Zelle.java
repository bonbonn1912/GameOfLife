import java.awt.Color;
import java.awt.Graphics;

public class Zelle {
    private int x;
    private int y;
    private boolean lebend;
    private boolean nextround;
    static int size;
    static boolean grid = true;


    public Zelle(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean isLebend() {
        return lebend;
    }


    public void setLebend(boolean lebend) {
        this.lebend = lebend;
    }


    public void setNextRound(boolean nextround) {
        this.nextround = nextround;
    }

    public void nextRound() {
        lebend = nextround;
    }

    public void draw(Graphics g) {
        if (grid == true) {
            g.setColor(Color.BLACK);
            g.drawRect(x * size, y * size, size, size);
            if (lebend == true) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x * size + 1, y * size + 1, size - 1, size - 1);
        } else {
            if (lebend == true) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x * size, y * size, size, size);
        }
    }
}

