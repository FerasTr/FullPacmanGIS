package GUI;

import Coordinates.Point3D;
import GameElements.*;
import GameElements.Box;
import GameMap.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * JPanel for displaying the game
 */
public class PlaygroundPanel extends JPanel
{
    private final int WIDTH = 1433;
    private final int HEIGHT = 642;
    // Variables \\
    private BufferedImage myImage;
    private int imgW, imgH;
    private Map map;
    private Game game = null;
    // Icons
    BufferedImage pacIcon = Pacman.pac;
    BufferedImage ghostIcon = Ghost.ghost;
    BufferedImage fruitIcon = Fruit.fruit;

    public PlaygroundPanel(Map battlegroundMap)
    {
        map = battlegroundMap;
        myImage = map.getMapPath();
        imgW = myImage.getWidth();
        imgH = myImage.getHeight();
    }

    // Paint \\
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
        drawGame(g);
    }

    private void drawGame(Graphics g)
    {
        if (game != null)
        {
            // Display game elements \\
            // Game spirits
            ArrayList<GameElement> objects = game.getPacmanBots();
            drawIcon(g, objects);
            objects = game.getFruits();
            drawIcon(g, objects);
            objects = game.getGhostBots();
            drawIcon(g, objects);
            // Game box
            ArrayList<Box> obst = game.getObstecales();
            if (obst.size() != 0)
            {
                for (Box box : obst)
                {
                    Point3D maxPixel = map.gpsToPixle(box.getMax());
                    maxPixel = pointAfterResize(maxPixel);
                    Point3D minPixel = map.gpsToPixle(box.getMin());
                    minPixel = pointAfterResize(minPixel);
                    Point pointMax = new Point(maxPixel.ix(), maxPixel.iy());
                    Point pointMin = new Point(minPixel.ix(), minPixel.iy());

                    Rectangle rect = new Rectangle(pointMax);
                    rect.add(pointMin);

                    g.fillRect(rect.x, rect.y, rect.width, rect.height);
                }
            }
        }
    }

    private void drawIcon(Graphics g, ArrayList<GameElement> objects)
    {
        if (objects.size() != 0)
        {
            for (GameElement obj : objects)
            {
                Point3D locationInGPS = obj.getLocation();
                Point3D locationInPixel = map.gpsToPixle(locationInGPS);
                locationInPixel = pointAfterResize(locationInPixel);
                BufferedImage tempIcon = null;
                if (obj instanceof Pacman)
                {
                    tempIcon = rescaleIcon(pacIcon);
                }
                else if (obj instanceof Fruit)
                {
                    tempIcon = rescaleIcon(fruitIcon);
                }
                else
                {
                    tempIcon = rescaleIcon(ghostIcon);
                }
                g.drawImage(tempIcon, (int) locationInPixel.x() - (tempIcon.getWidth() / 2),
                        (int) locationInPixel.y() - (tempIcon.getHeight() / 2), this);
            }
        }
    }

    // Methods \\
    public void updateGame(Game gameSettings)
    {
        this.game = gameSettings;
        repaint();
    }

    /**
     * Scaling the point to the required size before the resize.
     */
    public Point3D pointBeforeResize(Point3D p)
    {
        int paneW = this.getWidth();
        int paneH = this.getHeight();
        int x = (int) (p.x() * imgW / paneW);
        int y = (int) (p.y() * imgH / paneH);
        return new Point3D(x, y, 0);
    }

    /**
     * Scaling the point after the resize.
     */
    public Point3D pointAfterResize(Point3D p)
    {
        int paneW = this.getWidth();
        int paneH = this.getHeight();
        int x = (int) (p.x() * paneW / imgW);
        int y = (int) (p.y() * paneH / imgH);
        return new Point3D(x, y, 0);
    }

    /**
     * Rescale each icon
     */
    private BufferedImage rescaleIcon(BufferedImage icon)
    {
        double dx = this.getWidth() / (double) WIDTH;
        double dy = this.getHeight() / (double) HEIGHT;

        double x = icon.getWidth() * dx;
        double y = icon.getHeight() * dy;
        Image tmp = icon.getScaledInstance((int) x, (int) y, icon.SCALE_SMOOTH);

        BufferedImage dimg = new BufferedImage((int) x, (int) y, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;

    }

}
