package GameElements;

import Coordinates.MyCoords;
import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Main game element class, object represents the player of the game.
 */
public class Player implements GameElement
{
    public static final BufferedImage player = initImg();
    private Point3D location;
    private double speed;
    private double radius;

    /**
     * Default constructor
     *
     * @param location Location
     * @param speed    Speed of player (20)
     * @param radius   Radius of player (1)
     */
    public Player(Point3D location, double speed, double radius)
    {
        this.location = location;
        this.speed = speed;
        this.radius = radius;
    }

    /**
     * Parse info from string
     *
     * @param lineElements String to parse
     */
    public Player(String[] lineElements)
    {
        if (!(lineElements[2].equals("0.0") && lineElements[3].equals("0.0")))
        {
            location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
            speed = Double.parseDouble(lineElements[5]);
            radius = Double.parseDouble(lineElements[6]);
        }
    }

    /**
     * Empty constructor
     */
    public Player()
    {
        this.location = null;
        this.speed = 0;
        this.radius = 0;
    }

    /**
     * Copy constructor
     *
     * @param player
     */
    public Player(Player player)
    {
        this.location = new Point3D(player.getLocation());
        this.speed = player.getSpeed();
        this.radius = player.getRadius();
    }

    // Getters & Setters \\
    public Point3D getLocation()
    {
        return location;
    }

    @Override
    public int getID()
    {
        return 0;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    // Methods \\

    /**
     * Get the angle between player and the point
     *
     * @param point Point to calculate
     * @return angle
     */
    public double angelToMove(Point3D point)
    {
        return MyCoords.azimuth_elevation_dist(this.location, point)[0];
    }

    /**
     * Image builder
     *
     * @return Image of player
     */
    private static BufferedImage initImg()
    {
        try
        {
            return ImageIO.read(new File("./data/icons/player.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
