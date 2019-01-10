package GameElements;

import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the pac bot of the game.
 */
public class Pacman implements GameElement
{
    public static final BufferedImage pac = initImg();
    private int ID;
    private Point3D location;
    private double speed;
    private double radius;

    // Constructor \\

    /**
     * Parse info from string
     *
     * @param lineElements String to parse
     */
    public Pacman(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        speed = Double.parseDouble(lineElements[5]);
        radius = Double.parseDouble(lineElements[6]);
    }

    /**
     * Copy constructor
     *
     * @param pac Pac to copy
     */
    public Pacman(Pacman pac)
    {
        this.ID = pac.getID();
        this.location = new Point3D(pac.getLocation());
        this.speed = pac.getSpeed();
        this.radius = pac.getRadius();
    }

    // Getters & Setters \\
    @Override
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

    public double getRadius()
    {
        return radius;
    }

    // Methods \\

    /**
     * Image builder
     *
     * @return Image of bot
     */
    private static BufferedImage initImg()
    {
        try
        {
            return ImageIO.read(new File("./data/icons/pacman.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
