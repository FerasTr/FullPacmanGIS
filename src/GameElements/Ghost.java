package GameElements;

import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ghost implements GameElement
{
    public static final BufferedImage ghost = initImg();
    private int ID = -1;
    private Point3D location;
    private double speed;
    private double radius;

    public Ghost(Point3D location, double speed, double radius)
    {
        this.location = location;
        this.speed = speed;
        this.radius = radius;
        ID++;
    }

    public Ghost(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        speed = Double.parseDouble(lineElements[5]);
        radius = Double.parseDouble(lineElements[6]);
    }

    public int getID()
    {
        return ID;
    }

    @Override
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

    private static BufferedImage initImg()
    {
        try
        {
            return ImageIO.read(new File("./data/icons/antimage.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
