package GUI;

import Algorithm.AutoMode;
import Coordinates.MyCoords;
import Coordinates.Point3D;
import GameElements.*;
import ToServer.HandleServer;
import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.Iterator;

public class RealTime implements Runnable
{
    PlaygroundPanel simulationBoard;
    Game game;

    public RealTime(PlaygroundPanel pg)
    {
        this.simulationBoard = pg;
        this.game = pg.getGameSettings();
    }

    @Override
    public void run()
    {
        ArrayList<GameElement> targets = game.getFruits();
        Graph G = game.getGraph();
        Iterator<GameElement> itr;
        while (!targets.isEmpty())
        {
            // Calc min distance between player and fruit
            double minDistance = Double.MAX_VALUE;
            Node bestTarget = null;
            itr = targets.iterator();
            Fruit currentFruit = null;
            while (itr.hasNext())
            {
                currentFruit = (Fruit) itr.next();
                String name = "fruit_" + currentFruit.getID();
                Node target = G.getNodeByName(name);
                double currentDistance = target.getDist();
                if (currentDistance < minDistance)
                {
                    bestTarget = target;
                    minDistance = currentDistance;
                }
            }
            System.out.println(bestTarget.get_name());
            ArrayList<String> shortestPath = bestTarget.getPath();
            System.out.println("DEBUG" + game.getPlayer().getLocation().toFile());
            // Direct line
            if (shortestPath.size() == 1)
            {
                Point3D inGPS = currentFruit.getLocation();
                System.out.println(inGPS.toFile());
                while (MyCoords.distance3d(inGPS, game.getPlayer().getLocation()) > 2)
                {
                    double angle = game.getPlayer().angelToMove(inGPS);
                    HandleServer.play(angle);
                    System.out.println("MOVED IN " + angle);
                    try
                    {
                        simulationBoard.repaint();
                        Thread.sleep(16);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            // Path
            else
            {
                for (int i = 1; i < shortestPath.size(); i++)
                {

                }
            }
            // Remove fruit
            itr.remove();
            // Update graph again
            AutoMode.Algorithm(game);
            G = game.getGraph();
        }

    }
}
