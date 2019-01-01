import Algorithm.AutoMode;
import Coordinates.Point3D;
import GUI.MainFrame;
import GameElements.Fruit;
import GameElements.Player;
import GameMap.MapInit;
import GameMap.Path;

public class Driver
{
    // TODO IMPLEMENT STATS BETWEEN PLAYERS
    public static void main(String[] args)
    {
        Player p = new Player(new Point3D(32.102478, 35.207439, 0), 20, 1);
        Fruit f = new Fruit(new Point3D(32.104672, 35.207905, 0), 1);
        Path path = AutoMode.GetPath(f, p);
        String s = path.toString();
        System.out.println(s);
        //MainFrame gui = new MainFrame(MapInit.ArielMap());
    }
}
