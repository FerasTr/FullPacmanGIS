package GameElements;

import Coordinates.Point3D;

public class Box implements GameElement
{
    private static int ID = -1;
    private Point3D min;
    private Point3D max;

    public Box(Point3D min, Point3D max)
    {
        this.min = min;
        this.max = max;
        ID++;
    }

    public static int getID()
    {
        return ID;
    }

    public Point3D getMin()
    {
        return min;
    }

    public void setMin(Point3D min)
    {
        this.min = min;
    }

    public Point3D getMax()
    {
        return max;
    }

    public void setMax(Point3D max)
    {
        this.max = max;
    }
}
