package Algorithm;

import GameElements.*;
import Coordinates.*;
import GameMap.Path;

import java.util.ArrayList;

//TODO implement the auto mode
final public class AutoMode
{
    private static Game toRun;

    private AutoMode()
    {
    }

    // Find closest fruit
    public static int CloseFruit(Player p)
    {
        int targetIndex = -1;
        ArrayList<GameElement> targets = toRun.getFruits();
        if (targets.size() > 0)
        {
            targetIndex = 0;
            double minDistance = MyCoords.distance3d(p.getLocation(), targets.get(targetIndex).getLocation());

            for (int i = 1; i < targets.size(); ++i)
            {
                double currentDistance = MyCoords.distance3d(p.getLocation(), targets.get(targetIndex).getLocation());
                if (currentDistance < minDistance)
                {
                    minDistance = currentDistance;
                    targetIndex = i;
                }
            }
        }
        return targetIndex;
    }

    public static void Algorithm(Game game)
    {
        toRun = new Game(game);
        Player player = toRun.getPlayer();
        while (!toRun.getFruits().isEmpty())
        {
            Fruit target = (Fruit) toRun.getFruits().get(CloseFruit(player));
            Path path = GetPath(target, player);
        }
    }

    public static Path GetPath(Fruit target, Player player)
    {
        Path path = new Path();
        Point3D targetLocation = target.getLocation();
        Point3D playerLocation = new Point3D(player.getLocation());
        path.add(playerLocation);
        while (MyCoords.distance3d(playerLocation, targetLocation) > 3)
        {
            double a = MyCoords.azimuth_elevation_dist(player.getLocation(), targetLocation)[0];
            a = MyCoords.deg2Rad(a);
            Point3D vec = new Point3D(Math.cos(a) * 2, Math.sin(a) * 2, 0);
            Point3D point = MyCoords.add(playerLocation, vec);
            System.out.println(point.toFile());
            path.add(point);
            playerLocation = new Point3D(point);
        }
        path.add(targetLocation);
        return path;
    }
}
