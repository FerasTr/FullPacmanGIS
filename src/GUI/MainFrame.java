package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame window that contains the playground panel and menu bar to control the game.
 */
public class MainFrame extends JFrame
{
    // Final variables
    private final int WIDTH = 1433;
    private final int HEIGHT = 642;
    // Menu Bar
    private JMenuBar optionsBar = new JMenuBar();
    // Game options
    private JMenu gameOptions = new JMenu("Options");
    private JMenuItem gameOpen = new JMenuItem("Open");
    private JMenuItem gameSave = new JMenuItem("Save");
    private JMenuItem gameClear = new JMenuItem("Clear");
    // Insert options
    private JMenu gameInsert = new JMenu("Insert");
    private JMenuItem insertPacman = new JMenuItem("PACMAN");
    private JMenuItem insertFruit = new JMenuItem("Fruit");
    private JMenuItem gameRun = new JMenuItem("Run");

    /**
     * JFrame constructor, builds game menu bad and adds the playground panel to the frame
     */
    public MainFrame()
    {
        // Add the menu bar and its elements to the frame
        gameOptions.add(gameRun);
        gameOptions.add(gameOpen);
        gameOptions.add(gameSave);
        gameOptions.add(gameClear);
        optionsBar.add(gameOptions);
        gameInsert.add(insertPacman);
        gameInsert.add(insertFruit);
        optionsBar.add(gameInsert);
        getContentPane().add(optionsBar, BorderLayout.NORTH);
        // JFrame settings
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(300, 100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
