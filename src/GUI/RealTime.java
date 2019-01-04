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
import java.util.Vector;

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
        Vector<GameElement> targets = game.getFruits();
        Graph G = game.getGraph();
        Iterator<GameElement> itr = targets.iterator();
        while(itr.hasNext())
        {
            Fruit currentFruit = getBestTarget();
        }
    }

    private Fruit getBestTarget()
    {
        double minDistance = Double.MAX_VALUE;
        Fruit targetFruit = null;
        for (int i = 0; i < game.getFruits().size(); i++)
        {
            String name = "fruit_" + game.getFruits().get(i).getID();
            Node target = game.getGraph().getNodeByName(name);
            double currentDistance = target.getDist();
            if (currentDistance < minDistance)
            {
                minDistance = currentDistance;
                targetFruit = (Fruit) game.getFruits().get(i);

            }
        }
        return targetFruit;
    }
}
