package GameElements;

import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fruit implements GameElement
{
    public static final BufferedImage fruit = initImg();
    public int ID = -1;
    private Point3D location;
    private double weight;
    String name;

    // Constructor \\

    public Fruit(Point3D location, double weight)
    {
        this.location = location;
        this.weight = weight;
    }

    public Fruit(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        weight = Double.parseDouble(lineElements[5]);
        name = "fruit_" + this.ID;
    }

    public Fruit(Fruit fruit)
    {
        this.ID = fruit.getID();
        this.location = new Point3D(fruit.getLocation());
        this.weight = fruit.getWeight();
        this.name = fruit.getName();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public void setLocation(Point3D location)
    {
        this.location = location;
    }

    public double getWeight()
    {
        return weight;
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
            return ImageIO.read(new File("./data/icons/fruit.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean equal(Fruit fruit)
    {
        if (this.getID() == fruit.getID())
        {
            return true;
        }
        return false;
    }
}
