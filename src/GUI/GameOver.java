package GUI;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JDialog
{
    private JLabel yourScore = new JLabel();
    private JLabel yourAvg = new JLabel();
    private JLabel allAvg = new JLabel();
    private JPanel jp = new JPanel();

    public GameOver(JFrame parent, double score, double yavg, double aavg)
    {
        super(parent, "Score", true);

        yourScore.setText("<html>Score: <font color='red'>" + score + "</font></html>");
        yourAvg.setText("<html>Your Average: <font color='red'>" + yavg + "</font></html>");
        allAvg.setText("<html>Map Average: <font color='red'>" + aavg + "</font></html>");

        jp.add(yourScore);
        jp.add(yourAvg);
        jp.add(allAvg);
        getContentPane().add(jp);
        setSize(400, 60);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
