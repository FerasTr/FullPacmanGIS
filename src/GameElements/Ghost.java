package GameElements;

import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the ghost of the game, it follows the player around to eat him
 */
public class Ghost implements GameElement
{
    public static final BufferedImage ghost = initImg();
    private int ID;
    private Point3D location;
    private double speed;
    private double radius;

    // Constructors \\

    /**
     * Parse info from string
     *
     * @param lineElements String to parse
     */
    public Ghost(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        speed = Double.parseDouble(lineElements[5]);
        radius = Double.parseDouble(lineElements[6]);
    }

    /**
     * Copy constructor
     *
     * @param ghost
     */
    public Ghost(Ghost ghost)
    {
        this.ID = ghost.getID();
        this.location = new Point3D(ghost.getLocation());
        this.speed = ghost.getSpeed();
        this.radius = ghost.getRadius();
    }

    // Getters & Setters \\
    public int getID()
    {
        return ID;
    }

    @Override
    public Point3D getLocation()
    {
        return location;
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
     * Build image
     *
     * @return Image of ghost
     */
    private static BufferedImage initImg()
    {
        try
        {
            return ImageIO.read(new File("./data/icons/ghost.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
