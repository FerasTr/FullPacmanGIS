package GameElements;

import Coordinates.MyCoords;
import Coordinates.Point3D;

public class Player implements GameElement
{
    private Point3D location;
    private double speed;
    private double radius;

    public Player(Point3D location, double speed, double radius)
    {
        this.location = location;
        this.speed = speed;
        this.radius = radius;
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
}
