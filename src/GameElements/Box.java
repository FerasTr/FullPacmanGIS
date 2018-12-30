package GameElements;

import Coordinates.Point3D;

public class Box
{
    private int ID = -1;
    private Point3D min;
    private Point3D max;

    public Box(Point3D min, Point3D max)
    {
        this.min = min;
        this.max = max;
    }

    public Box(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        min = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        max = new Point3D(lineElements[5] + "," + lineElements[6] + ",0");
    }

    public int getID()
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
