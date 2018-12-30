package GameElements;

import Coordinates.Point3D;

public class Ghost implements GameElement
{
    private static int ID = -1;
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
}
