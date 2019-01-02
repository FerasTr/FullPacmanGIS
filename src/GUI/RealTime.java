package GUI;

import Algorithm.AutoMode;
import Coordinates.MyCoords;
import Coordinates.Point3D;
import GameElements.*;
import ToServer.HandleServer;
import graph.Graph;
import graph.Node;

import java.util.ArrayList;

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

        while (!targets.isEmpty())
        {
            // Calc min distance between player and fruit
            double minDistance = Double.MAX_VALUE;
            Node bestTarget = null;
            Fruit targetFruit = null;
            for (int i = 0; i < targets.size(); i++)
            {
                String name = "fruit_" + targets.get(i).getID();
                Node target = G.getNodeByName(name);
                double currentDistance = target.getDist();
                if (currentDistance < minDistance)
                {
                    bestTarget = target;
                    minDistance = currentDistance;
                    targetFruit = (Fruit) targets.get(i);

                }
            }


            ArrayList<String> shortestPath = bestTarget.getPath();
            for (int i = 1; i < shortestPath.size(); i++)
            {
                Point3D inGPS = getPoint(bestTarget, targets);
                while (MyCoords.distance3d(inGPS, game.getPlayer().getLocation()) > 2)
                {
                    double angle = game.getPlayer().angelToMove(inGPS);
                    game = HandleServer.play(angle);
                    try
                    {
                        simulationBoard.repaint();
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            // Remove fruit
            targets.remove(targetFruit);
            // Update graph again
            AutoMode.Algorithm(game);
            G = game.getGraph();
        }

    }

    private Point3D getPoint(Node bestTarget, ArrayList<GameElement> targetsCopy)
    {
        String actualName = bestTarget.get_name();
        for (int i = 0; i < targetsCopy.size(); i++)
        {
            String name = "fruit_" + targetsCopy.get(i).getID();
            if (name.equals(actualName))
            {
                return targetsCopy.get(i).getLocation();
            }
        }
        return null;
    }
}
