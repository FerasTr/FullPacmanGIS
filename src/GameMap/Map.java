package GameMap;


import Coordinates.MyCoords;
import Coordinates.Point3D;

import java.awt.image.BufferedImage;

/**
 * GameMap class, this is the playground of the game.
 */
public class Map
{
    private BufferedImage map_path;
    private Range mapRange;

    /**
     * GameMap constructor, uses range class for calculations
     */
    public Map(BufferedImage base, Range mapRange)
    {
        map_path = base;
        this.mapRange = mapRange;
    }

    /**
     * Return the map
     */
    public BufferedImage getMapPath()
    {
        return map_path;
    }

    /**
     * Translate a pixel to real world coordinates.
     */
    public Point3D pixleToGPS(Point3D pixelPoint)
    {
        double realW = pixelPoint.x() / map_path.getWidth();
        double realH = pixelPoint.y() / map_path.getHeight();

        double pixelGPSDeltaX = mapRange.getPixelGPSDelta(pixelPoint);
        double deltaX = mapRange.getDeltaX();
        double deltaY = mapRange.getMidDeltaY(pixelGPSDeltaX / deltaX);

        double x = mapRange.getTopX() - (realH * deltaX);
        double y = mapRange.getLeftMid(pixelGPSDeltaX / deltaX).y() + (realW * deltaY);

        return new Point3D(x, y, 0);
    }

    /**
     * Translate a gps point to a pixel on the map.
     */
    public Point3D gpsToPixle(Point3D gpsPoint)
    {
        double deltaX = mapRange.getDeltaX();
        double rel_x = mapRange.getPixelGPSDelta(gpsPoint);

        double deltaY = mapRange.getMidDeltaY(rel_x / deltaX);
        double rel_y = gpsPoint.y() - mapRange.getLeftMid(rel_x / deltaX).y();

        double realW = rel_x / deltaX;
        double realH = rel_y / deltaY;

        int lat = (int) (realH * map_path.getWidth());
        int lon = (int) (realW * map_path.getHeight());

        return new Point3D(lat, lon, 0);
    }

    /**
     * Distance between two pixels on the map.
     */
    public double distBetweenPixles(Point3D pixle1, Point3D pixle2)
    {
        Point3D pixle1ToGPS = pixleToGPS(pixle1);
        Point3D pixle2ToGPS = pixleToGPS(pixle2);
        return MyCoords.distance3d(pixle1ToGPS, pixle2ToGPS);
    }
}