package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class EndDialog
{
    private static String jdbcUrl = "jdbc:mysql://ariel-oop.xyz:3306/oop";
    private static String jdbcUser = "student";
    private static String jdbcPassword = "student";

    private static ResultSet resultSet;
    private static Statement statement;
    private static Connection connection;

    public static final double MAP1 = 545731857;
    public static final double MAP2 = 1149748017;
    public static final double MAP3 = -683317070;
    public static final double MAP4 = 1193961129;
    public static final double MAP5 = 1577914705;
    public static final double MAP6 = -1315066918;
    public static final double MAP7 = -1377331871;
    public static final double MAP8 = 552196504;
    public static final double MAP9 = 919248096;

    public static double[] GetAvg(double map)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            // Get latest score
            double score = GetScore(map);
            System.out.println("RECENT SCORE " + score);
            // Get your average point for current map
            double myAvg = MyAvg(map);
            System.out.println("MY AVG " + myAvg);
            // Get everyone's average for current map
            double allAvg = AllAvg(map);
            System.out.println("ALL AVG " + allAvg);
            // Print the database
            GetDatabase();
            CloseConnections();
            // Return the average
            return new double[]{score, myAvg, allAvg};
        }

        catch (SQLException sqle)

        {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        }

        catch (ClassNotFoundException e)

        {
            e.printStackTrace();
        }
        return null;
    }

    private static double GetScore(double map) throws SQLException
    {
        String score = "SELECT Point FROM logs ORDER BY LogTime DESC LIMIT 1 WHERE FirstID = 133713376 AND " +
                "SomeDouble" + " = " + map;
        resultSet = statement.executeQuery(score);
        if (resultSet.next())
        {
            return resultSet.getFloat(1);
        }
        return 0;
    }


    private static double MyAvg(double map) throws SQLException
    {
        String getAvg = "SELECT AVG(Point) FROM logs WHERE " + "(FirstID = " + "133713376) AND " + "(SomeDouble " +
                "=" + " " + map + ")";
        float avg = 0;
        resultSet = statement.executeQuery(getAvg);
        if (resultSet.next())
        {
            avg = resultSet.getFloat(1);
        }
        return avg;
    }

    private static float AllAvg(double map) throws SQLException
    {
        String getAvg = "SELECT AVG(Point) FROM logs WHERE (SomeDouble=" + map + ")";

        resultSet = statement.executeQuery(getAvg);
        if (resultSet.next())
        {
            return resultSet.getFloat(1);
        }
        return -1;
    }

    private static void GetDatabase() throws SQLException
    {
        String getDB = "SELECT * FROM logs;";
        resultSet = statement.executeQuery(getDB);
        System.out.println("FirstID\t\tSecondID\tThirdID\t\tLogTime\t\t\tPoint\t\tSomeDouble");
        while (resultSet.next())
        {
            System.out.println(resultSet.getInt("FirstID") + "\t\t" + resultSet.getInt("SecondID") + "\t\t" + resultSet.getInt("ThirdID") + "\t\t" + resultSet.getTimestamp("LogTime") + "\t\t\t\t" + resultSet.getDouble("Point") + "\t\t" + resultSet.getDouble("SomeDouble"));
        }
    }

    private static void CloseConnections() throws SQLException
    {
        resultSet.close();
        statement.close();
        connection.close();
    }


}