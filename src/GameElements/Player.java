package GameElements;

import Coordinates.MyCoords;
import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player implements GameElement
{
    public static final BufferedImage player = initImg();
    private Point3D location;
    private double speed;
    private double radius;

    public Player(Point3D location, double speed, double radius)
    {
        this.location = location;
        this.speed = speed;
        this.radius = radius;
    }

    public Player(String[] lineElements)
    {
        if (!(lineElements[2].equals("0.0") && lineElements[3].equals("0.0")))
        {
            location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
            speed = Double.parseDouble(lineElements[5]);
            radius = Double.parseDouble(lineElements[6]);
        }
    }

    public Player()
    {
        this.location = null;
        this.speed = 0;
        this.radius = 0;
    }

    public Point3D getLocation()
    {
        return location;
    }

    public void setLocation(Point3D location)
    {
        this.location = location;
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
    // Angel between player and point
    public double angelToMove(Point3D point)
    {
        return MyCoords.azimuth_elevation_dist(this.location, point)[0];
    }

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
