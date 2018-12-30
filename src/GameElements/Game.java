package GameElements;

import java.util.ArrayList;

public class Game
{
    private Player player;
    ArrayList<Pacman> pacmanBots;
    ArrayList<Ghost> ghostBots;
    ArrayList<Fruit> fruits;
    ArrayList<Box> obstecales;

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

    public ArrayList<Pacman> getPacmanBots()
    {
        return pacmanBots;
    }

    public void setPacmanBots(ArrayList<Pacman> pacmanBots)
    {
        this.pacmanBots = pacmanBots;
    }

    public ArrayList<Ghost> getGhostBots()
    {
        return ghostBots;
    }

    public void setGhostBots(ArrayList<Ghost> ghostBots)
    {
        this.ghostBots = ghostBots;
    }

    public ArrayList<Fruit> getFruits()
    {
        return fruits;
    }

    public void setFruits(ArrayList<Fruit> fruits)
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
}
