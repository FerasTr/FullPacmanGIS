package GameElements;

import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pacman implements GameElement
{
    public static final BufferedImage pac = initImg();
    private int ID = -1;
    private Point3D location;
    private double speed;
    private double radius;
    private double score = 0;

    // Constructor \\
    public Pacman(double lat, double lon, double alt, double speed, double radius)
    {
        String location_string = "(" + lat + "," + lon + "," + alt + ")";
        this.location = new Point3D(location_string);
        this.speed = speed;
        this.radius = radius;
    }

    public Pacman(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        speed = Double.parseDouble(lineElements[5]);
        radius = Double.parseDouble(lineElements[6]);
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

    public void setLocation(Point3D location)
    {
        this.location = location;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getRadius()
    {
        return radius;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double score)
    {
        this.score += score;
    }

    // Methods \\

    public void resetCounter()
    {
        ID = -1;
    }

    private static BufferedImage initImg()
    {
        try
        {
            return ImageIO.read(new File("./data/icons/cm.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
