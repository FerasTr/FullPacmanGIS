package GameElements;

import graph.Graph;

import java.util.Vector;

public class Game
{
    private Player player;
    private Vector<GameElement> pacmanBots;
    private Vector<GameElement> ghostBots;
    private Vector<GameElement> fruits;
    private Vector<Box> obstacles;
    private String fileName;
    private Graph graph = new Graph();

    // Constructors \\

    /**
     * Default constructor
     */
    public Game()
    {
        player = new Player();
        pacmanBots = new Vector<>();
        ghostBots = new Vector<>();
        fruits = new Vector<>();
        obstacles = new Vector<>();
    }

    /**
     * Copy constructor
     *
     * @param game Game to copy
     */
    public Game(Game game)
    {
        if (game.getPlayer().getLocation() == null)
        {
            this.player = new Player();
        }
        else
        {
            this.player = new Player(game.getPlayer());
        }
        this.fruits = game.CopyFruits();
        this.pacmanBots = game.CopyPacmans();
        this.obstacles = game.getObstacles();
        this.ghostBots = game.CopyGhostBots();
        this.fileName = game.getFileName();
    }

    // Getters & Setters \\

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Vector<GameElement> getPacmanBots()
    {
        return pacmanBots;
    }

    public Vector<GameElement> getGhostBots()
    {
        return ghostBots;
    }

    public Vector<GameElement> getFruits()
    {
        return fruits;
    }

    public Vector<Box> getObstacles()
    {
        return obstacles;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Graph getGraph()
    {
        return graph;
    }

    public void setGraph(Graph graph)
    {
        this.graph = graph;
    }

    // Methods \\
    public void addPacman(Pacman pac)
    {
        pacmanBots.add(pac);
    }

    public void addFruit(Fruit fruit)
    {
        fruits.add(fruit);
    }

    public void addGhost(Ghost ghost)
    {
        ghostBots.add(ghost);
    }

    public void addBox(Box box)
    {
        obstacles.add(box);
    }

    /**
     * Clear the game
     */
    public void clearGame()
    {
        player = new Player();
        pacmanBots.clear();
        ghostBots.clear();
        fruits.clear();
    }

    /**
     * Copy fruits in game
     *
     * @return Collection of fruits.
     */
    public Vector<GameElement> CopyFruits()
    {
        Vector<GameElement> temp = new Vector<>();
        for (GameElement fruit : fruits)
        {
            temp.add(new Fruit((Fruit) fruit));
        }
        return temp;
    }

    /**
     * Copy bots in game
     *
     * @return Collection of bots.
     */
    public Vector<GameElement> CopyPacmans()
    {
        Vector<GameElement> temp = new Vector<>();
        for (GameElement pac : pacmanBots)
        {
            temp.add(new Pacman((Pacman) pac));
        }
        return temp;
    }


    public Vector<GameElement> CopyGhostBots()
    {
        Vector<GameElement> temp = new Vector<>();
        for (GameElement ghost : ghostBots)
        {
            temp.add(new Ghost((Ghost) ghost));
        }
        return temp;
    }
}
