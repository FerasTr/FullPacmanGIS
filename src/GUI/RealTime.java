package GUI;

import Algorithm.AutoMode;
import Coordinates.MyCoords;
import Coordinates.Point3D;
import GameElements.*;
import GameMap.Map;
import GameMap.MapInit;
import ToServer.HandleServer;
import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.Vector;

public class RealTime implements Runnable
{
    private PlaygroundPanel simulationBoard;
    private Game game;
    private Map m = MapInit.ArielMap();

    /**
     * RealTime constructor
     */
    public RealTime(PlaygroundPanel pg)
    {
        this.simulationBoard = pg;
        this.game = pg.getGameSettings();
    }

    /**
     * Thread run method
     */
    @Override
    public void run()
    {
        Graph G = game.getGraph();
        // Check if the game is done
        while (HandleServer.play.isRuning())
        {
            Fruit currentFruit = getBestTarget();

            Node bestTarget = G.getNodeByName(currentFruit.getName());

            ArrayList<String> shortestPath = bestTarget.getPath();

            // Path
            for (int i = 1; i < shortestPath.size(); i++)
            {
                Point3D inGPS = GetPointFromPath(shortestPath.get(i));
                MoveToNextPoint(inGPS);
            }
            // Direct line
            Point3D inGPS = currentFruit.getLocation();
            MoveToNextPoint(inGPS);

            // Update graph again
            AutoMode.Algorithm(game);
            G = game.getGraph();
        }
        System.out.println("***GAME IS DONE***");
    }

    /**
     * Get the point from the path
     *
     * @param name to parse
     * @return point
     */
    private Point3D GetPointFromPath(String name)
    {
        String[] nameSplit = name.split("_");
        if (nameSplit[0].equals("box"))
        {
            int id = Integer.parseInt(nameSplit[2]);
            Box box = null;
            for (Box temp : game.getObstacles())
            {
                if (temp.getID() == id)
                {
                    box = temp;
                    break;
                }
            }

            Point3D max = box.getMax();
            Point3D min = box.getMin();

            max = m.gpsToPixle(max);
            min = m.gpsToPixle(min);

            if (nameSplit[1].equals("topRight"))
            {
                return m.pixleToGPS(new Point3D(max.x() + 3, max.y() - 3, 0));
            }

            if (nameSplit[1].equals("topLeft"))
            {
                return m.pixleToGPS(new Point3D(min.x() - 3, max.y() - 3, 0));
            }

            if (nameSplit[1].equals("botRight"))
            {
                return m.pixleToGPS(new Point3D(max.x() + 3, min.y() + 3, 0));
            }

            if (nameSplit[1].equals("botLeft"))
            {
                return m.pixleToGPS(new Point3D(min.x() - 3, min.y() + 3, 0));
            }
        }
        else
        {
            System.out.println("ITS A FRUIT");
        }
        return null;
    }

    /**
     * Move the player in an angle to the next node
     *
     * @param inGPS Point to move to
     */
    private void MoveToNextPoint(Point3D inGPS)
    {
        while (MyCoords.distance3d(inGPS, game.getPlayer().getLocation()) > 2)
        {
            double angle = game.getPlayer().angelToMove(inGPS);
            boolean exit = HandleServer.play(angle);
            if (exit)
            {
                break;
            }
            try
            {
                simulationBoard.repaint();
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get best target selected by the AutoMode
     *
     * @return Closest fruit
     */
    private Fruit getBestTarget()
    {
        Vector<GameElement> targets = game.CopyFruits();
        double minDistance = Double.MAX_VALUE;
        Fruit targetFruit = null;
        for (int i = 0; i < targets.size(); i++)
        {
            String name = "fruit_" + targets.get(i).getID();
            Node target = game.getGraph().getNodeByName(name);
            double currentDistance = target.getDist();
            if (currentDistance < minDistance)
            {
                minDistance = currentDistance;
                targetFruit = (Fruit) targets.get(i);

            }
        }
        return targetFruit;
    }
}
