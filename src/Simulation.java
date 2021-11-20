
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class Simulation implements KeyListener, MouseMotionListener, MouseListener {
    private Zelle[][] zellen;
    private Random random;
    private int width = GameOfLife.width / Zelle.size;
    private int height = GameOfLife.height / Zelle.size;
    private int generation;
    private boolean go;
    private int button;

    public Simulation() {
        random = new Random();
        zellen = new Zelle[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                zellen[x][y] = new Zelle(x, y);
            }
        }
    }

    public void naechsteGeneration() {
        if (go == true) {
            generation++;

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int zaehler = 0;
                    if (x - 1 >= 0 && y - 1 >= 0) {
                        if (zellen[x - 1][y - 1].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (x - 1 >= 0) {
                        if (zellen[x - 1][y].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (x - 1 >= 0 && y + 1 <= height - 1) {
                        if (zellen[x - 1][y + 1].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (y - 1 >= 0) {
                        if (zellen[x][y - 1].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (y + 1 <= height - 1) {
                        if (zellen[x][y + 1].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (x + 1 <= width - 1 && y - 1 >= 0) {

                        if (zellen[x + 1][y - 1].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (x + 1 <= width - 1) {
                        if (zellen[x + 1][y].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (x + 1 <= width - 1 && y + 1 <= height - 1) {
                        if (zellen[x + 1][y + 1].isLebend()) {
                            zaehler++;
                        }
                    }
                    if (zaehler < 2 || zaehler > 3) {
                        zellen[x][y].setNextRound(false);
                    } else if (zaehler == 2) {
                        zellen[x][y].setNextRound(zellen[x][y].isLebend());
                    } else if (zaehler == 3) {
                        zellen[x][y].setNextRound(true);
                    }
                }
            }
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    zellen[x][y].nextRound();
                }
            }
        }
    }

    public void draw(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                zellen[x][y].draw(g);
            }
        }
        g.setColor(Color.RED);
        g.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        g.drawString("" + generation, 10, 10 + g.getFont().getSize());
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_G) {
            if (Zelle.grid == true) {
                Zelle.grid = false;
            } else {
                Zelle.grid = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    zellen[x][y].setLebend(random.nextBoolean());
                }
            }
            generation = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    zellen[x][y].setLebend(false);
                }
            }
            generation = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (go == true) {
                go = false;
            } else {
                go = true;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        if (go == false) {
            int mx = e.getX() / Zelle.size;
            int my = e.getY() / Zelle.size;
            if (button == 1) {
                zellen[mx - 1][my - 3].setLebend(true);
            } else {
                zellen[mx - 1][my - 3].setLebend(false);

            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        button = e.getButton();
        if (go == false) {
            int mx = e.getX() / Zelle.size;
            int my = e.getY() / Zelle.size;

            if (button == 1) {
                zellen[mx - 1][my - 3].setLebend(true);
            } else {
                zellen[mx - 1][my - 3].setLebend(false);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        button = -1;
    }
} 