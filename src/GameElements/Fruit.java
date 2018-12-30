package GameElements;

import Coordinates.Point3D;

public class Fruit implements GameElement
{
    public int ID = -1;
    private Point3D location;
    private double weight;

    // Constructor \\
    public Fruit(double lat, double lon, double alt, double weight)
    {
        String location_string = "w(" + lat + "," + lon + "," + alt + ")";
        this.location = new Point3D(location_string);
        this.weight = weight;
    }

    public Fruit(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        location = new Point3D(lineElements[2] + "," + lineElements[3] + "0");
        weight = Double.parseDouble(lineElements[5]);
    }

    // Getters & Setters \\
    public int getID()
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

    public void resetCounter()
    {
        ID = -1;
    }
}
