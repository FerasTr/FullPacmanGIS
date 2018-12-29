package GameElements;

import Coordinates.Point3D;

public class Pacman implements GameElement
{
    public static int ID = -1;
    private Point3D location;
    private double speed;
    private double radius;
    private double score = 0;

    // Constructor \\
    public Pacman(double lat, double lon, double alt, double speed, double radius)
    {
        String location_string = "(" + lat + "," + lon + "," + alt + ")";
        this.location = new Point3D(location_string);
        ID++;
        this.speed = speed;
        this.radius = radius;
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

    public static void resetCounter()
    {
        ID = -1;
    }
}
