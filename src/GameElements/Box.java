package GameElements;

import Coordinates.Point3D;
import GameMap.Map;
import GameMap.MapInit;

/**
 * Box object meant to represent the obstacles in the game.
 */
public class Box
{
    // Variables \\
    private int ID;
    private Point3D min;
    private Point3D max;
    Map m = MapInit.ArielMap();

    // Constructor \\

    /**
     * Parse info from the game board.
     * @param lineElements Array to parse
     */
    public Box(String[] lineElements)
    {
        ID = Integer.parseInt(lineElements[1]);
        min = new Point3D(lineElements[2] + "," + lineElements[3] + ",0");
        max = new Point3D(lineElements[5] + "," + lineElements[6] + ",0");
    }

    // Getters & Setters \\
    public int getID()
    {
        return ID;
    }

    public Point3D getMin()
    {
        return min;
    }

    public Point3D getMax()
    {
        return max;
    }

    // Methods \\
    public boolean IsInside(Point3D point)
    {
        Point3D tempMin = m.gpsToPixle(min);
        Point3D tempMax = m.gpsToPixle(max);
        if (point.x() >= tempMin.x() && point.y() <= tempMin.y() && point.x() <= tempMax.x() && point.y() >= tempMax.y())
        {
            return true;
        }
        return false;
    }
}
