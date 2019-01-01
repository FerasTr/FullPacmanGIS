package GameMap;

import Coordinates.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Path
{
    ArrayList<Point3D> points;

    public Path()
    {
        this.points = new ArrayList<>();
    }

    public ArrayList<Point3D> getPoints()
    {
        return points;
    }

    public void setPoints(ArrayList<Point3D> points)
    {
        this.points = points;
    }

    /**
     * Length of the path
     */
    public double length()
    {
        double length = 0;
        if (points.size() <= 1)
        {
            return 0;
        }
        Iterator<Point3D> iter = this.points.iterator();
        Point3D currentPoint = iter.next();
        while (iter.hasNext())
        {
            Point3D nextPoint = iter.next();
            length += MyCoords.distance3d(currentPoint, nextPoint);
            currentPoint = nextPoint;
        }
        return length;
    }

    public void add(Point3D point)
    {
        points.add(point);
    }

    public int size()
    {
        return points.size();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < points.size(); i++)
        {
            sb.append(points.get(i).toFile());
            sb.append('\n');
        }
        return "POINTS IN PATH ==> " + sb.toString();
    }
}
