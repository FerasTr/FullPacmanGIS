package GameElements;

import Coordinates.Point3D;

public class Fruit implements GameElement
{
    public static int ID = -1;
    private Point3D location;
    private double weight;

    // Constructor \\
    public Fruit(double lat, double lon, double alt, double weight)
    {
        String location_string = "(" + lat + "," + lon + "," + alt + ")";
        this.location = new Point3D(location_string);
        ID++;
        this.weight = weight;
    }

    // Getters & Setters \\
    public static int getID()
    {
        return ID;
    }

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

    public static void resetCounter()
    {
        ID = -1;
    }
}
