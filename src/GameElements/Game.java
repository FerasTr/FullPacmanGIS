package GameElements;

import java.util.ArrayList;

public class Game
{
    private Player player;
    private ArrayList<GameElement> pacmanBots;
    private ArrayList<GameElement> ghostBots;
    private ArrayList<GameElement> fruits;
    private ArrayList<Box> obstecales;

    public Game()
    {
        player = new Player();
        pacmanBots = new ArrayList<>();
        ghostBots = new ArrayList<>();
        fruits = new ArrayList<>();
        obstecales = new ArrayList<>();
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
}
