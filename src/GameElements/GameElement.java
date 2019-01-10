package GameElements;

import Coordinates.Point3D;

/**
 * Game element interface to represent game objects.
 */
public interface GameElement
{
    public Point3D getLocation();

    public int getID();
}
