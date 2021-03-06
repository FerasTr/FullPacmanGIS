package GUI;

import Algorithm.AutoMode;
import Coordinates.*;
import GameElements.*;
import GameElements.Box;
import GameMap.Map;
import ToServer.EndDialog;
import ToServer.HandleServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * JPanel for displaying the gameSettings
 */
public class PlaygroundPanel extends JPanel
{
    private final int WIDTH = 1433;
    private final int HEIGHT = 642;
    // Variables \\
    private BufferedImage myImage;
    private int imgW, imgH;
    private Map map;
    private Game gameSettings = null;
    MouseListener mouseClick;
    MainFrame mainFrame;
    // Icons
    private final BufferedImage playerIcon = Player.player;
    private final BufferedImage pacIcon = Pacman.pac;
    private final BufferedImage ghostIcon = Ghost.ghost;
    private final BufferedImage fruitIcon = Fruit.fruit;

    /**
     * Game board constructor
     *
     * @param battlegroundMap Map to use as reference
     */
    public PlaygroundPanel(Map battlegroundMap)
    {
        map = battlegroundMap;
        myImage = map.getMapPath();
        imgW = myImage.getWidth();
        imgH = myImage.getHeight();
    }

    // Paint \\

    /**
     * Paint the game to the board
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
        drawGame(g);
    }


    private void drawGame(Graphics g)
    {
        if (gameSettings != null)
        {
            // Display gameSettings elements \\
            // Game bots
            Vector<GameElement> objects = gameSettings.CopyPacmans();
            drawIcons(g, objects);
            objects = gameSettings.CopyFruits();
            drawIcons(g, objects);
            objects = gameSettings.CopyGhostBots();
            drawIcons(g, objects);
            // Game box
            Vector<Box> obst = gameSettings.getObstacles();
            drawBoxes(g, obst);
            // Player
            Player player = gameSettings.getPlayer();
            if (player.getLocation() != null)
            {
                drawIcon(g, player);
            }
        }
    }

    /**
     * Show the end game dialog
     */
    public void ShowScore()
    {
        double[] result = EndDialog.GetAvg(EndDialog.GetMap(gameSettings.getFileName()));
        GameOver go = new GameOver(mainFrame, result[0], result[1], result[2]);
    }

    /**
     * Draw each icon for the object collection
     *
     * @param objects Game elements to draw
     */
    private void drawIcons(Graphics g, Vector<GameElement> objects)
    {
        if (objects.size() != 0)
        {
            for (GameElement obj : objects)
            {
                drawIcon(g, obj);
            }
        }
    }

    /**
     * Draw obstacles
     *
     * @param obst Object to draw
     */
    private void drawBoxes(Graphics g, Vector<Box> obst)
    {
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

    /**
     * Draw the icon
     *
     * @param obj Object to draw
     */
    private void drawIcon(Graphics g, GameElement obj)
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
        else if (obj instanceof Ghost)
        {
            tempIcon = rescaleIcon(ghostIcon);
        }
        else if (obj instanceof Player)
        {
            tempIcon = rescaleIcon(playerIcon);
        }
        g.drawImage(tempIcon, (int) locationInPixel.x() - (tempIcon.getWidth() / 2),
                (int) locationInPixel.y() - (tempIcon.getHeight() / 2), this);
    }

    // Methods \\

    /**
     * Update game board using a new game, update game parent for dialog
     *
     * @param parent       Main JFrame to update
     * @param gameSettings Game to update
     */
    public void updateGame(MainFrame parent, Game gameSettings)
    {
        mainFrame = parent;
        // Linking both games, should be used once
        this.gameSettings = gameSettings;
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

    /**
     * Mouse Listener to add player to the map
     */
    public void addPlayer()
    {
        mouseClick = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Point3D clickLocation = new Point3D(e.getX(), e.getY(), 0);
                Point3D p = pointBeforeResize(clickLocation);
                Point3D inGPS = map.pixleToGPS(p);
                HandleServer.setLocation(inGPS);
                Player toAdd = new Player(inGPS, 20, 1);
                gameSettings.setPlayer(toAdd);
                System.out.println();
                System.out.println("PLAYER ADDED " + " ==> GIS: [" + inGPS.x() + "," + inGPS.y() + "]");
                System.out.println();
                repaint();
                removeMouseListener(mouseClick);
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        };
        this.addMouseListener(mouseClick);
    }

    /**
     * Start manual game using a thread for moving the player
     */
    public void manualGame()
    {

        ManualThread steps = new ManualThread();
        Thread move = new Thread(steps);
        move.start();

        mouseClick = new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                Point3D clickLocation = new Point3D(e.getX(), e.getY(), 0);
                Point3D p = pointBeforeResize(clickLocation);
                Point3D inGPS = map.pixleToGPS(p);
                double angle = gameSettings.getPlayer().angelToMove(inGPS);
                steps.setDegree(angle);
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        };
        this.addMouseListener(mouseClick);
    }

    /**
     * Stop listening
     */
    public void stopMouseListen()
    {
        removeMouseListener(mouseClick);
    }

    /**
     * Start auto game using a new thread.
     */
    public void autoGame()
    {
        AutoMode.Algorithm(gameSettings);
        repaint();
        simulateRun();
    }

    /**
     * Start thread to simulate the board
     */
    private void simulateRun()
    {
        RealTime simulation = new RealTime(this);
        Thread thread = new Thread(simulation);
        thread.start();
    }

    public Game getGameSettings()
    {
        return gameSettings;
    }

    /**
     * Manual thread for moving the pac at all time
     */
    class ManualThread implements Runnable
    {
        private double degree;

        public ManualThread()
        {

        }

        public double getDegree()
        {
            return degree;
        }

        public void setDegree(double degree)
        {
            this.degree = degree;
        }

        @Override
        public void run()
        {
            while (HandleServer.play.isRuning())
            {

                HandleServer.play(getDegree());
                repaint();
                try
                {
                    Thread.sleep(144);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
            stopMouseListen();
            ShowScore();
        }
    }

}
