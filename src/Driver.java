import Coordinates.MyCoords;
import Coordinates.Point3D;
import GUI.MainFrame;
import GameMap.MapInit;

public class Driver
{
    // TODO IMPLEMENT STATS BETWEEN PLAYERS
    public static void main(String[] args)
    {
        MainFrame gui = new MainFrame(MapInit.ArielMap());

       /* Point3D p = new Point3D(32.103869531873436, 35.20774851040772);
        Point3D p2 = new Point3D(32.103875580891916, 35.20776245011847);
        System.out.println(MyCoords.distance3d(p,p2));*/

    }
}
