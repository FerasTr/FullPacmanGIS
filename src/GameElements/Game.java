package GameElements;

import graph.Graph;

import java.util.Vector;

public class Game
{
    private Player player;
    private Vector<GameElement> pacmanBots;
    private Vector<GameElement> ghostBots;
    private Vector<GameElement> fruits;
    private Vector<Box> obstecales;
    // TODO implement a completed check in the gui the displays the stats for the current "map"
    private boolean completed = false;
    private String fileName;

    Graph graph = new Graph();

    public Game()
    {
        player = new Player();
        pacmanBots = new Vector<>();
        ghostBots = new Vector<>();
        fruits = new Vector<>();
        obstecales = new Vector<>();
    }

    public Game(Game game)
    {
        this.player = new Player(game.getPlayer());
        this.fruits = game.CopyFruits();
        this.pacmanBots = game.CopyPacmans();
        this.obstecales = game.getObstecales();
    }

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

    public void setPacmanBots(Vector<GameElement> pacmanBots)
    {
        this.pacmanBots = pacmanBots;
    }

    public Vector<GameElement> getGhostBots()
    {
        return ghostBots;
    }

    public void setGhostBots(Vector<GameElement> ghostBots)
    {
        this.ghostBots = ghostBots;
    }

    public Vector<GameElement> getFruits()
    {
        return fruits;
    }

    public void setFruits(Vector<GameElement> fruits)
    {
        this.fruits = fruits;
    }

    public Vector<Box> getObstecales()
    {
        return obstecales;
    }

    public void setObstecales(Vector<Box> obstecales)
    {
        this.obstecales = obstecales;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted()
    {
        this.completed = true;
    }

    public void setNotCompleted()
    {
        this.completed = false;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
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
        obstecales.add(box);
    }

    public Graph getGraph()
    {
        return graph;
    }

    public void setGraph(Graph graph)
    {
        this.graph = graph;
    }

    public void clearGame()
    {
        player = new Player();
        pacmanBots.clear();
        ghostBots.clear();
        fruits.clear();
        obstecales.clear();
    }

    public Vector<GameElement> CopyFruits()
    {
        Vector<GameElement> temp = new Vector<>();
        for (GameElement fruit : fruits)
        {
            temp.add(new Fruit((Fruit) fruit));
        }
        return temp;
    }

    public Vector<GameElement> CopyPacmans()
    {
        Vector<GameElement> temp = new Vector<>();
        for (GameElement pac : pacmanBots)
        {
            temp.add(new Pacman((Pacman) pac));
        }
        return temp;
    }
}
