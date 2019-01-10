package ToServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 * End game dialog calculations are done in this class using the provided MySQL server
 */
public final class EndDialog
{
    private static String jdbcUrl = "jdbc:mysql://ariel-oop.xyz:3306/oop";
    private static String jdbcUser = "student";
    private static String jdbcPassword = "student";

    private static ResultSet resultSet;
    private static Statement statement;
    private static Connection connection;

    private static final double MAP1 = 2128259830;
    private static final double MAP2 = 1149748017;
    private static final double MAP3 = -683317070;
    private static final double MAP4 = 1193961129;
    private static final double MAP5 = 1577914705;
    private static final double MAP6 = -1315066918;
    private static final double MAP7 = -1377331871;
    private static final double MAP8 = 306711633;
    private static final double MAP9 = 919248096;

    private static DecimalFormat df2 = new DecimalFormat(".##");

    /**
     * Get the score for the current map
     *
     * @param map
     * @return
     */
    public static double[] GetAvg(double map)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            System.out.println("***MAP " + map + "***");
            // Get latest score
            double score = GetScore(map);
            System.out.println("***RECENT SCORE: " + score + "***");
            // Get your average point for current map
            double myAvg = MyAvg(map);
            System.out.println("***MY AVG: " + myAvg + "***");
            // Get everyone's average for current map
            double allAvg = AllAvg(map);
            System.out.println("***ALL AVG: " + allAvg + "***");
            // Print the database
            PrintDatabase();
            // Close the connections
            CloseConnections();
            // Return the average
            return new double[]{Double.parseDouble(df2.format(score)), Double.parseDouble(df2.format(myAvg)),
                    Double.parseDouble(df2.format(allAvg))};
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

    /**
     * Get the score for this game using the ID and map hash code.
     *
     * @param map hashcode representing the game map.
     * @return Score
     * @throws SQLException
     */
    private static double GetScore(double map) throws SQLException
    {
        String score =
                "SELECT Point FROM logs WHERE (FirstID = 133713376) AND (SomeDouble = " + map + ")ORDER BY " +
                        "LogTime DESC LIMIT 1";
        resultSet = statement.executeQuery(score);
        if (resultSet.next())
        {
            return resultSet.getFloat(1);
        }
        // Never reach this
        return -68;
    }

    /**
     * Get the average for this game using the ID and map hash code.
     *
     * @param map hashcode representing the game map.
     * @return My average
     * @throws SQLException
     */
    private static double MyAvg(double map) throws SQLException
    {
        String getAvg = "SELECT AVG(Point) FROM logs WHERE " + "(FirstID = " + "133713376) AND " + "(SomeDouble " +
                "=" + " " + map + ")";
        resultSet = statement.executeQuery(getAvg);
        if (resultSet.next())
        {
            return resultSet.getFloat(1);
        }
        // Never reach this
        return -68;
    }

    /**
     * Get the average (everyone) for this game using the ID and map hash code.
     *
     * @param map hashcode representing the game map.
     * @return All average
     * @throws SQLException
     */
    private static float AllAvg(double map) throws SQLException
    {
        String getAvg = "SELECT AVG(Point) FROM logs WHERE (SomeDouble=" + map + ")";

        resultSet = statement.executeQuery(getAvg);
        if (resultSet.next())
        {
            return resultSet.getFloat(1);
        }
        // Never reach this
        return -68;
    }

    /**
     * Print the last 20 entry in the database.
     *
     * @throws SQLException
     */
    private static void PrintDatabase() throws SQLException
    {
        String getDB = "SELECT * FROM logs ORDER BY LogTime DESC LIMIT 10";
        resultSet = statement.executeQuery(getDB);
        System.out.println("FirstID\t\t\t\tSecondID\t\t\t\tThirdID\t\t\t\tLogTime\t\t\t\tPoint\t\t\t\tSomeDouble");
        while (resultSet.next())
        {
            System.out.println(resultSet.getInt("FirstID") + "\t\t\t\t" + resultSet.getInt("SecondID") + "\t\t\t\t" + resultSet.getInt("ThirdID") + "\t\t\t\t" + resultSet.getTimestamp("LogTime") + "\t\t\t\t" + resultSet.getDouble("Point") + "\t\t\t\t" + resultSet.getDouble("SomeDouble"));
        }
    }

    /**
     * Retrun the Game map hashcode
     *
     * @param name Name of the map
     * @return hashcode
     */
    public static double GetMap(String name)
    {
        if (name.contains("example1"))
        {
            return MAP1;
        }
        else if (name.contains("example2"))
        {
            return MAP2;
        }
        else if (name.contains("example3"))
        {
            return MAP3;
        }
        else if (name.contains("example4"))
        {
            return MAP4;
        }
        else if (name.contains("example5"))
        {
            return MAP5;
        }
        else if (name.contains("example6"))
        {
            return MAP6;
        }
        else if (name.contains("example7"))
        {
            return MAP7;
        }
        else if (name.contains("example8"))
        {
            return MAP8;
        }
        else if (name.contains("example9"))
        {
            return MAP9;
        }
        return -1;
    }

    /**
     * Close SQL connections
     *
     * @throws SQLException
     */
    private static void CloseConnections() throws SQLException
    {
        resultSet.close();
        statement.close();
        connection.close();
    }


}