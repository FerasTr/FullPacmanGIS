package ToServer;

import Coordinates.Point3D;
import GameElements.*;
import Robot.Play;

import java.util.ArrayList;

/**
 * Static class responsible for handling the communication between the client and the server
 */
final public class HandleServer
{
    // Variables \\
    public static Play play = null;
    private static Game game = null;

    // Constructors \\
    private HandleServer()
    {
    }

    // Main methods \\
    public static Game initGame(String fileName)
    {
        System.out.println("*****************************");
        System.out.println("...SENDING TO SERVER...");
        // 1) Create the game variables
        play = new Play(fileName);
        System.out.println("Using game: " + fileName);
        game = new Game();
        game.setFileName(fileName);
        // 2) Set your ID
        long id = 133713376;
        play.setIDs(id);
        System.out.println("Using ID: " + id);
        // Return game from play object
        System.out.println("*****************************");
        System.out.println();
        GetGameDataWithBox();
        System.out.println("...INSERT A PLAYER TO PLAY...");
        System.out.println();
        return game;
    }

    public static void playgroundInfo()
    {
        // 3) Get the GPS coordinates of the "arena"
        //System.out.println("*****************************");
        //System.out.println("...GETTING MAP INFO...");
        String map_data = play.getBoundingBox();
        //System.out.println("Bounding Box info: " + map_data);
        //System.out.println("*****************************");
        System.out.println();
    }

    public static void gameData()
    {
        // 4) get the game-board data
        // System.out.println("*****************************");
        //System.out.println("...PARSING GAME DATA...");
        GetGameDataNoBox();
        //System.out.println("*****************************");
        //System.out.println();
    }

    public static void setLocation(Point3D gps)
    {
        // 5) Set the "player" init location - should be a valid location
        play.setInitLocation(gps.x(), gps.y());
    }

    public static void startServer()
    {
        // 6) Start the "server" (default max time is 100 seconds (1000*100 ms))
        System.out.println("*****************************");
        System.out.println("...STARTING SERVER...");
        play.start();
        System.out.println("*****************************");
        System.out.println();
    }

    public static boolean play(Double angle)
    {
        // 7) "Play" as long as there are "fruits" and time
        // 7.1) this is the main command to the player (on the server side)
        //System.out.println("*****************************");
        //System.out.println("...MOVING THE GAME...");
        boolean done;
        if (play.isRuning())
        {
            done = false;
            // System.out.println("Moved by:" + angle + " degrees");
            play.rotate(angle);
            //   System.out.println("*****************************");
            // System.out.println();
            gameData();
        }
        else
        {
            //  System.out.println("Game is no longer running");
            // System.out.println("*****************************");
            //  System.out.println();
            done = true;
        }
        // 7.2) get the current score of the game
        gameStatistics();
        // 7.3) get the game-board current state
        return done;
    }

    public static void stopServer()
    {
        // 8) stopServer the server - not needed in the real implementation.
        System.out.println("*****************************");
        System.out.println("... STOPPING SERVER...");
        System.out.println("*****************************");
        System.out.println();
        play.stop();
    }

    public static void gameStatistics()
    {
        // 9) print the data & save to the course DB
        //System.out.println("*****************************");
        //System.out.println("...GETTING GAME STATISTICS...");
        String info = play.getStatistics();
        // System.out.println(info);
        //System.out.println("*****************************");
        // System.out.println();
    }

    // Helper methods \\
    private static void GetGameDataWithBox()
    {
        ArrayList<String> board_data;
        board_data = play.getBoard();
        game.clearGame();
        for (int i = 0; i < board_data.size(); i++)
        {
            String line = board_data.get(i);
            //System.out.println(line);
            String[] lineElements = line.split(",");
            if (lineElements[0].equals("P"))
            {
                game.addPacman(new Pacman(lineElements));
            }
            else if (lineElements[0].equals("F"))
            {
                game.addFruit(new Fruit(lineElements));
            }
            else if (lineElements[0].equals("G"))
            {
                game.addGhost(new Ghost(lineElements));
            }
            else if (lineElements[0].equals("B"))
            {
                game.addBox(new Box(lineElements));
            }
            else if (lineElements[0].equals("M"))
            {
                game.setPlayer(new Player(lineElements));
            }
        }
    }

    private static void GetGameDataNoBox()
    {
        ArrayList<String> board_data;
        board_data = play.getBoard();
        game.clearGame();
        for (int i = 0; i < board_data.size(); i++)
        {
            String line = board_data.get(i);
            //ystem.out.println(line);
            String[] lineElements = line.split(",");
            if (lineElements[0].equals("P"))
            {
                game.addPacman(new Pacman(lineElements));
            }
            else if (lineElements[0].equals("F"))
            {
                game.addFruit(new Fruit(lineElements));
            }
            else if (lineElements[0].equals("G"))
            {
                game.addGhost(new Ghost(lineElements));
            }
            else if (lineElements[0].equals("M"))
            {
                game.setPlayer(new Player(lineElements));
            }
        }
    }
}
