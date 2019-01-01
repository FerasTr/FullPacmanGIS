package GameElements;

import java.util.ArrayList;

public class Game
{
    private Player player;
    private ArrayList<GameElement> pacmanBots;
    private ArrayList<GameElement> ghostBots;
    private ArrayList<GameElement> fruits;
    private ArrayList<Box> obstecales;
    // TODO implement a completed check in the gui the displays the stats for the current "map"
    private boolean completed = false;
    private String fileName;

    public Game()
    {
        player = new Player();
        pacmanBots = new ArrayList<>();
        ghostBots = new ArrayList<>();
        fruits = new ArrayList<>();
        obstecales = new ArrayList<>();
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

    public ArrayList<GameElement> getPacmanBots()
    {
        return pacmanBots;
    }

    public void setPacmanBots(ArrayList<GameElement> pacmanBots)
    {
        this.pacmanBots = pacmanBots;
    }

    public ArrayList<GameElement> getGhostBots()
    {
        return ghostBots;
    }

    public void setGhostBots(ArrayList<GameElement> ghostBots)
    {
        this.ghostBots = ghostBots;
    }

    public ArrayList<GameElement> getFruits()
    {
        return fruits;
    }

    public void setFruits(ArrayList<GameElement> fruits)
    {
        this.fruits = fruits;
    }

    public ArrayList<Box> getObstecales()
    {
        return obstecales;
    }

    public void setObstecales(ArrayList<Box> obstecales)
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

    public void clearGame()
    {
        player = new Player();
        pacmanBots.clear();
        ghostBots.clear();
        fruits.clear();
        obstecales.clear();
    }

    public ArrayList<GameElement> CopyFruits()
    {
        ArrayList<GameElement> temp = new ArrayList<>();
        for (GameElement fruit : fruits)
        {
            temp.add(new Fruit((Fruit) fruit));
        }
        return temp;
    }

    public ArrayList<GameElement> CopyPacmans()
    {
        ArrayList<GameElement> temp = new ArrayList<>();
        for (GameElement pac : pacmanBots)
        {
            temp.add(new Pacman((Pacman) pac));
        }
        return temp;
    }
}
