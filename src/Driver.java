import Algorithm.AutoMode;
import Coordinates.MyCoords;
import Coordinates.Point3D;
import GUI.MainFrame;
import GameElements.Game;
import GameMap.Map;
import GameMap.MapInit;
import ToServer.HandleServer;

import java.util.ArrayList;

public class Driver
{
    // TODO IMPLEMENT STATS BETWEEN PLAYERS
    public static void main(String[] args)
    {
        MainFrame gui = new MainFrame(MapInit.ArielMap());

        Map m = MapInit.ArielMap();

        /*Point3D p38 = new Point3D(32.1045535405543,35.2092014005389);
        Point3D p42 = new Point3D(32.1042145607231,35.2078578501463);

        p38 = m.gpsToPixle(p38); // 975 193
        p42 = m.gpsToPixle(p42); // 782 251

        Game game = HandleServer.initGame("C:\\MyProjects\\GitRepo\\FullPacmanGIS\\data\\Ex4_OOP_example3.csv");
        AutoMode.toRun = game;
        boolean pp = AutoMode.Obstructed(p38, p42);

        System.out.println(pp);*/

    }


}
