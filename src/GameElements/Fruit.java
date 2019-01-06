package GameElements;

import Coordinates.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fruit implements GameElement
{
    // Variables \\
    public static final BufferedImage fruit = initImg();
    public int ID;
    private Point3D location;
    private double weight;
    private String name;

    // Constructor \\

    /**
     * Parse info from the game board.
     *
     * @param lineElements Array to parse
     */
    public Fruit(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        location = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        weight = Double.parseDouble(lineElements[5]);
        name = "fruit_" + this.ID;
    }

    /**
     * Copy Constructor
     *
     * @param fruit Fruit to copy
     */
    public Fruit(Fruit fruit)
    {
        this.ID = fruit.getID();
        this.location = new Point3D(fruit.getLocation());
        this.weight = fruit.getWeight();
        this.name = fruit.getName();
    }

    // Getters & Setters \\

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

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

    public double getWeight()
    {
        return weight;
    }

    // Methods \\

    /**
     * Build image of fruit
     *
     * @return Image
     */
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
