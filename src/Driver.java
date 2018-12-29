import GUI.MainFrame;
import Robot.Play;

import java.util.ArrayList;

public class Driver
{
    public static void main(String[] args)
    {
        // 1) Create a "play" from a file (attached to Ex4)
        String file_name = "data/Ex4_OOP_example2.csv";
        Play play = new Play(file_name);
        // 2) Set your ID
        play.setIDs(133713376);
        // 3) Get the GPS coordinates of the "arena"
        String map_data = play.getBoundingBox();
        System.out.println("Bounding Box info: " + map_data);

        // 4) get the game-board data
        ArrayList<String> board_data = play.getBoard();
        for (int i = 0; i < board_data.size(); i++)
        {
            System.out.println(board_data.get(i));
        }
        System.out.println();
        System.out.println("Init Player Location should be set using the bounding box info");

        // 5) Set the "player" init location - should be a valid location
        play.setInitLocation(32.1040, 35.2061);

        // 6) Start the "server"
        play.start(); // default max time is 100 seconds (1000*100 ms).

        // 7) "Play" as long as there are "fruits" and time
        int i = 0;
        while (play.isRuning())
        {
            i++;
            // 7.1) this is the main command to the player (on the server side)
            play.rotate(36 * i);
            System.out.println("***** " + i + "******");

            // 7.2) get the current score of the game
            String info = play.getStatistics();
            System.out.println(info);
            // 7.3) get the game-board current state
            board_data = play.getBoard();
            for (int a = 0; a < board_data.size(); a++)
            {
                System.out.println(board_data.get(a));
            }
            System.out.println();
        }
        // 8) stop the server - not needed in the real implementation.
        //play1.stop();
        System.out.println("**** Done Game (user stop) ****");

        // 9) print the data & save to the course DB
        String info = play.getStatistics();
        System.out.println(info);


        MainFrame gui = new MainFrame();
    }
}
