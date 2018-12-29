package ToServer;

import Robot.Play;

import java.util.ArrayList;

/**
 * Static class responsible for handling the communication between the client and the server
 */
final public class HandleServer
{
    // Variables
    private static Play play = null;

    // Constructors
    private HandleServer()
    {
    }

    // Main methods
    public static void initGame(String fileName)
    {
        // 1) Create a "play" from a file.
        play = new Play(fileName);
        // 2) Set your ID
        play.setIDs(133713376);
    }

    public static void playgroundInfo()
    {
        // 3) Get the GPS coordinates of the "arena"
        String map_data = play.getBoundingBox();
        System.out.println("Bounding Box info: " + map_data);
    }

    public static void gameData()
    {
        // 4) get the game-board data
        GetGameData(play);
        System.out.println("Init Player Location should be set using the bounding box info");
    }

    public static void setLocation()
    {
        // 5) Set the "player" init location - should be a valid location
        // TODO should be in the GUI with pixel click
        play.setInitLocation(32.1040, 35.2061);
    }

    public static void startServer()
    {
        // 6) Start the "server" (default max time is 100 seconds (1000*100 ms))
        play.start();
    }

    public static void play()
    {
        // 7) "Play" as long as there are "fruits" and time
        int i = 0;
        while (play.isRuning())
        {
            i++;

            // 7.1) this is the main command to the player (on the server side)+
            // TODO calculate the azimuth of the mouse click and the current position, should be in GUI
            play.rotate(36 * i);
            System.out.println("***** " + 36 * i + "******");

            // 7.2) get the current score of the game
            String info = play.getStatistics();
            System.out.println(info);

            // 7.3) get the game-board current state
            GetGameData(play);
        }
    }

    public static void stop()
    {
        // 8) stop the server - not needed in the real implementation.
        play.stop();
        System.out.println("**** Done Game (user stop) ****");
    }

    public static void gameStatistics()
    {
        // 9) print the data & save to the course DB
        String info = play.getStatistics();
        System.out.println(info);
    }

    // Helper methods
    private static void GetGameData(Play play)
    {
        ArrayList<String> board_data;
        board_data = play.getBoard();
        for (int a = 0; a < board_data.size(); a++)
        {
            System.out.println(board_data.get(a));
        }
        System.out.println();
    }
}
