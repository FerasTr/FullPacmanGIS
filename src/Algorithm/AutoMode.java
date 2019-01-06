package Algorithm;

import Coordinates.*;
import GameElements.*;
import GameMap.*;
import ToServer.*;
import graph.*;

import java.util.ArrayList;
import java.util.Vector;

// TODO Improve player picker

/**
 * AutoMode is a class to play the game alone, built using Dijkstra algorithm.
 * The algorithm calculates the best possible move for the current position.
 */
final public class AutoMode
{
    ///////////////
    // Variables \\
    ///////////////

    public static Game toRun;
    private static Map m = MapInit.ArielMap();

    //////////////////
    // Constructors \\
    //////////////////
    private AutoMode()
    {
    }

    /////////////
    // Methods \\
    /////////////

    /**
     * Main method for automating the move set.
     *
     * @param game The game to run.
     */
    public static void Algorithm(Game game)
    {
        toRun = game;
        Graph graph = new Graph();
        graph.ResetGraph();
        toRun.setGraph(graph);
        PrepareGraph();
        Graph_Algo.dijkstra(graph, "player");
    }

    /**
     * Build all the nodes and add the required edges.
     */
    private static void PrepareGraph()
    {
        if (toRun.getObstacles().size() == 0)
        {
            GraphNoBox();
        }
        else
        {
            GraphWithBox();
        }
    }

    /////////////
    // WithBox \\
    /////////////

    /**
     * Prepare a graph that has obstacles.
     */
    private static void GraphWithBox()
    {
        Vector<GameElement> targets = toRun.getFruits();
        // Add player to the server.
        if (toRun.getPlayer().getLocation() == null)
        {
            System.out.println("Adding player to the game...");
            Point3D p = toRun.getFruits().get(0).getLocation();
            p = MyCoords.add(p, new Point3D(2, 2, 0));
            AddPlayerToServer(p);
        }
        // Add player node.
        toRun.getGraph().add(new Node("player"));
        // Add fruits nodes.
        AddFruitEdgesBox();
        // Add box nodes to graph
        AddBoxNodes();
        // Connect player node to fruit nodes
        for (int i = 0; i < targets.size(); i++)
        {
            String name = "fruit_" + targets.get(i).getID();
            if (!Obstructed(m.gpsToPixle(toRun.getPlayer().getLocation()), m.gpsToPixle(targets.get(i).getLocation())))
            {
                double distance = MyCoords.distance3d(toRun.getPlayer().getLocation(), targets.get(i).getLocation());
                toRun.getGraph().addEdge("player", name, distance);
            }
        }
    }

    /**
     * Add fruit nodes and edges.
     */
    private static void AddFruitEdgesBox()
    {
        Vector<GameElement> targets = toRun.getFruits();
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
                    if (!Obstructed(m.gpsToPixle(location1), m.gpsToPixle(location2)))
                    {
                        double distance = MyCoords.distance3d(location1, location2);
                        toRun.getGraph().addEdge(name1, name2, distance);
                    }
                }
            }
        }
    }

    /**
     * Add box nodes with an offset of 3 pixels
     */
    private static void AddBoxNodes()
    {
        Vector<Box> obst = toRun.getObstacles();
        ArrayList<Point3D> points = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();
        for (Box box : obst)
        {
            Point3D max = box.getMax();
            Point3D min = box.getMin();

            max = m.gpsToPixle(max);
            min = m.gpsToPixle(min);

            Point3D topRight = new Point3D(max.x() + 3, max.y() - 3, 0);
            if (!NotValidPoint(topRight))
            {
                Node n = new Node("box_topRight_" + box.getID());
                toRun.getGraph().add(n);
                nodes.add(n);
                points.add(m.pixleToGPS(topRight));
            }
            Point3D botLeft = new Point3D(min.x() - 3, min.y() + 3, 0);
            if (!NotValidPoint(botLeft))
            {
                Node n = new Node("box_botLeft_" + box.getID());
                toRun.getGraph().add(n);
                nodes.add(n);
                points.add(m.pixleToGPS(botLeft));
            }
            Point3D topLeft = new Point3D(min.x() - 3, max.y() - 3, 0);
            if (!NotValidPoint(topLeft))
            {
                Node n = new Node("box_topLeft_" + box.getID());
                toRun.getGraph().add(n);
                nodes.add(n);
                points.add(m.pixleToGPS(topLeft));
            }
            Point3D botRight = new Point3D(max.x() + 3, min.y() + 3, 0);
            if (!NotValidPoint(botRight))
            {
                Node n = new Node("box_botRight_" + box.getID());
                toRun.getGraph().add(n);
                nodes.add(n);
                points.add(m.pixleToGPS(botRight));
            }
        }
        AddBoxEdges(points, nodes);
    }

    /**
     * Add the edges between fruits and box nodes.
     *
     * @param points ArrayList of points representing the position of each node
     * @param nodes  ArrayList of the nodes
     */
    private static void AddBoxEdges(ArrayList<Point3D> points, ArrayList<Node> nodes)
    {
        Vector<GameElement> targets = toRun.getFruits();
        Player p = toRun.getPlayer();
        for (int i = 0; i < points.size(); i++)
        {
            Point3D nodePoint1 = points.get(i);
            Node toAdd1 = nodes.get(i);
            String name1 = toAdd1.get_name();

            // Connect to box nodes
            for (int j = 0; j < points.size(); j++)
            {
                Point3D nodePoint2 = points.get(j);
                Node toAdd2 = nodes.get(j);
                String name2 = toAdd2.get_name();
                if (!name1.equals(name2))
                {
                    if (!Obstructed(m.gpsToPixle(nodePoint1), m.gpsToPixle(nodePoint2)))
                    {
                        double distance = MyCoords.distance3d(nodePoint1, nodePoint2);
                        toRun.getGraph().addEdge(name1, name2, distance);
                    }
                }
            }

            // Connect to fruit nodes
            for (int h = 0; h < targets.size(); h++)
            {
                Point3D targetLocation = targets.get(h).getLocation();
                if (!Obstructed(m.gpsToPixle(nodePoint1), m.gpsToPixle(targetLocation)))
                {
                    double distance = MyCoords.distance3d(nodePoint1, targetLocation);
                    String name = "fruit_" + targets.get(h).getID();
                    toRun.getGraph().addEdge(name1, name, distance);
                }
            }

            // Connect to player
            if (!Obstructed(m.gpsToPixle(p.getLocation()), m.gpsToPixle(nodePoint1)))
            {
                double distance = MyCoords.distance3d(p.getLocation(), nodePoint1);
                toRun.getGraph().addEdge("player", name1, distance);
            }
        }
    }

    /**
     * Check if the path is obstructed between two points using the Bresenham algorithm.
     *
     * @param point1 pixel 1
     * @param point2 pixel 2
     * @return true if blocked, false if clear.
     */
    public static boolean Obstructed(Point3D point1, Point3D point2)
    {
        int x = point1.ix();
        int y = point1.iy();
        int x2 = point2.ix();
        int y2 = point2.iy();
        int w = x2 - x;
        int h = y2 - y;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w < 0) dx1 = -1;
        else if (w > 0) dx1 = 1;
        if (h < 0) dy1 = -1;
        else if (h > 0) dy1 = 1;
        if (w < 0) dx2 = -1;
        else if (w > 0) dx2 = 1;
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest > shortest))
        {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h < 0) dy2 = -1;
            else if (h > 0) dy2 = 1;
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++)
        {
            if (NotValidPoint(new Point3D(x, y, 0)))
            {
                return true;
            }
            numerator += shortest;
            if (!(numerator < longest))
            {
                numerator -= longest;
                x += dx1;
                y += dy1;
            }
            else
            {
                x += dx2;
                y += dy2;
            }
        }
        return false;
    }

    /**
     * Check if the point has a valid position.
     *
     * @param p Point to check
     * @return true if the point is not valid, false otherwise.
     */
    private static boolean NotValidPoint(Point3D p)
    {
        for (Box b : toRun.getObstacles())
        {
            if (b.IsInside(p))
            {
                return true;
            }
        }
        return false;
    }

    ///////////
    // NoBox \\
    ///////////

    /**
     * Prepare a graph that has no obstacles.
     */
    private static void GraphNoBox()
    {
        Vector<GameElement> targets = toRun.getFruits();
        // Add player to the server.
        if (toRun.getPlayer().getLocation() == null)
        {
            System.out.println("Adding player to the game...");
            Point3D p = toRun.getPacmanBots().get(0).getLocation();
            AddPlayerToServer(p);
        }
        toRun.getGraph().add(new Node("player"));
        // Add fruit nodes and edges
        AddFruitEdgesNoBox();
        // Add between player and fruits
        for (int i = 0; i < targets.size(); i++)
        {
            double distance = MyCoords.distance3d(toRun.getPlayer().getLocation(), targets.get(i).getLocation());
            String name = "fruit_" + targets.get(i).getID();
            toRun.getGraph().addEdge("player", name, distance);
        }
    }

    /**
     * Add fruit nodes and edges.
     */
    private static void AddFruitEdgesNoBox()
    {
        Vector<GameElement> targets = toRun.getFruits();

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
    }

    ////////////
    // Helper \\
    ////////////

    /**
     * Set player location.
     *
     * @param p player location
     */
    private static void AddPlayerToServer(Point3D p)
    {
        System.out.println();
        System.out.println("PLAYER ADDED " + " ==> GIS: [" + p.x() + "," + p.y() + "]");
        System.out.println();
        HandleServer.setLocation(p);
        Player toAdd = new Player(p, 20, 1);
        toRun.setPlayer(toAdd);
        HandleServer.startServer();
    }
}
