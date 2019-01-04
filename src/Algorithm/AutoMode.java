package Algorithm;

import Coordinates.MyCoords;
import Coordinates.Point3D;
import GameElements.*;
import ToServer.HandleServer;
import graph.*;

import java.util.Vector;


//TODO implement the auto mode
final public class AutoMode
{
    private static Game toRun;

    private AutoMode()
    {
    }

    public static void Algorithm(Game game)
    {
        toRun = game;
        Graph graph = new Graph();
        graph.ResetGraph();
        toRun.setGraph(graph);
        PrepareGraph();
        Graph_Algo.dijkstra(graph, "player");
        //System.out.println(toRun.getGraph().toString());
    }

    private static void PrepareGraph()
    {
        if (toRun.getObstecales().size() == 0)
        {
            GraphNoBox();
        }
        else
        {
            GraphWithBox();
        }
    }

    private static void GraphWithBox()
    {
    }

    private static void GraphNoBox()
    {
        // Place player
        Vector<GameElement> targets = toRun.getFruits();
        // TODO implement a better location picker
        if (toRun.getPlayer().getLocation() == null)
        {
            System.out.println("Adding player to the game...");
            Point3D p = toRun.getPacmanBots().get(0).getLocation();
            System.out.println();
            System.out.println("PLAYER ADDED " + " ==> GIS: [" + p.x() + "," + p.y() + "]");
            System.out.println();
            HandleServer.setLocation(p);
            Player toAdd = new Player(p, 20, 1);
            toRun.setPlayer(toAdd);
            HandleServer.startServer();
        }
        toRun.getGraph().add(new Node("player"));

        // ADD NODES AND EDGES

        for (int i = 0; i < targets.size(); i++)
        {
            String name1 = "fruit_" + targets.get(i).getID();
            Node node1 = new Node(name1);
            if (!toRun.getGraph().exist(node1))
            {
                toRun.getGraph().add(node1);
            }
            Point3D location1 = targets.get(i).getLocation();
            for (int j = 0; j < targets.size(); j++)
            {
                String name2 = "fruit_" + targets.get(j).getID();
                Point3D location2 = targets.get(j).getLocation();
                if (!name1.equals(name2))
                {
                    Node node2 = new Node(name2);
                    if (!toRun.getGraph().exist(node2))
                    {
                        toRun.getGraph().add(node2);
                    }
                    double distance = MyCoords.distance3d(location1, location2);
                    toRun.getGraph().addEdge(name1, name2, distance);
                }
            }
        }

        // ADD EDGES TO SOURCE
        for (int i = 0; i < targets.size(); i++)
        {
            double distance = MyCoords.distance3d(toRun.getPlayer().getLocation(), targets.get(i).getLocation());
            String name = "fruit_" + targets.get(i).getID();
            toRun.getGraph().addEdge("player", name, distance);
        }
    }
}
