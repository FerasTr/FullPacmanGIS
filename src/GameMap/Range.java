package GameMap;

import Coordinates.MyCoords;
import Coordinates.Point3D;

/**
 * Range class to build the map borders.
 */
public class Range
{
    // Variables \\
    private Point3D top_left;
    private Point3D top_right;
    private Point3D bot_left;
    private Point3D bot_right;
    // Constructors \\

    /**
     * Map range constructor
     *
     * @param top_left_x  Top left gps coords x
     * @param top_left_y  Top left gps coords y
     * @param bot_right_x Bot right gps coords x
     * @param bot_right_y Bot right gps coords y
     */
    public Range(double top_left_x, double top_left_y, double bot_right_x, double bot_right_y)
    {
        top_left = new Point3D(top_left_x, top_left_y, 0);
        bot_right = new Point3D(bot_right_x, bot_right_y, 0);
        top_right = new Point3D(top_left_x, bot_right_y, 0);
        bot_left = new Point3D(bot_right_x, top_left_y, 0);
    }

    // Methods \\

    // Getters & Setters \\
    public double getTopX()
    {
        return top_left.x();
    }

    public double getBotX()
    {
        return bot_left.x();
    }

    public double getLeftY()
    {
        return top_left.y();
    }

    public double getRightY()
    {
        return bot_left.y();
    }

    public Point3D getTopLeftPoint()
    {
        return top_left;
    }

    public Point3D getTopRightPoint()
    {
        return top_right;
    }

    public Point3D getBotRightPoint()
    {
        return bot_right;
    }

    public Point3D getBotLeftPoint()
    {
        return bot_left;
    }

    /**
     * Delta between the X.
     *
     * @return Delta of the main points
     */
    public double getDeltaX()
    {
        return top_left.x() - bot_right.x();
    }

    /**
     * Delta between the Y.
     *
     * @return Delta of the main points
     */
    public double getDeltaY()
    {
        return top_left.y() - bot_right.y();
    }

    /**
     * Delta between the Y, using a relative value.
     *
     * @return Delta of the main points
     */
    public double getMidDeltaY(double relative)
    {
        Point3D gpsLeft = getLeftMid(relative);
        Point3D gpsRight = getRightMid(relative);
        return gpsRight.y() - gpsLeft.y();

    }

    /**
     * Middle vector using the relative value.
     *
     * @param relative Ratio to use
     * @return Vector of the mid left point
     */
    public Point3D getLeftMid(double relative)
    {
        return MyCoords.midGPS(relative, top_left, bot_left);

    }

    /**
     * Middle vector using the relative value.
     *
     * @param relative Ratio to use
     * @return Vector of the mid right point
     */
    public Point3D getRightMid(double relative)
    {
        return MyCoords.midGPS(relative, top_right, bot_right);
    }

    /**
     * Delta using pixels.
     *
     * @return Delta between the pixels
     */
    public double getPixelGPSDelta(Point3D pointInPixel)
    {
        return top_left.x() - pointInPixel.x();
    }
}
