package GUI;

import GameElements.Game;
import GameMap.Map;
import ToServer.HandleServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * JFrame window that contains the playground panel and menu bar to control the game.
 */
public class MainFrame extends JFrame
{
    // Final variables \\
    private final int WIDTH = 1433;
    private final int HEIGHT = 642;
    // Menu Bar \\
    private JMenuBar gameBar = new JMenuBar();
    // Game options \\
    private JMenu gameOptions = new JMenu("Options");
    private JMenuItem optionsRun = new JMenuItem("Run");
    private JMenuItem optionsOpen = new JMenuItem("Open");
    private JMenuItem optionsClear = new JMenuItem("Clear");
    // Game insert \\
    private JMenu gameInsert = new JMenu("Insert");
    private JMenuItem insertPlayer = new JMenuItem("Player");

    private Game game = null;
    private PlaygroundPanel battleground;

    /**
     * JFrame constructor, builds game menu bad and adds the playground panel to the frame
     */
    public MainFrame(Map battlegroundMap)
    {
        // Battleground world settings
        battleground = new PlaygroundPanel(battlegroundMap);
        // Add the menu bar and its elements to the frame
        gameOptions.add(optionsRun);
        gameOptions.add(optionsOpen);
        gameOptions.add(optionsClear);
        gameBar.add(gameOptions);
        gameInsert.add(insertPlayer);
        gameBar.add(gameInsert);
        // Add the panel to the JFrame
        add(battleground);
        // Button options
        optionsRun.setEnabled(false);
        insertPlayer.setEnabled(false);

        optionsOpen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OpenFile();
            }
        });

        optionsClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClearGame();
            }
        });

        insertPlayer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AddPlayer();
            }
        });

        getContentPane().add(gameBar, BorderLayout.NORTH);
        // JFrame settings
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(300, 100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void AddPlayer()
    {
        System.out.println("Adding player to the game...");
        battleground.addPlayer(game);
        insertPlayer.setEnabled(false);
        optionsRun.setEnabled(true);
    }

    private void ClearGame()
    {
        game.clearGame();
        battleground.updateGame(game);
        optionsRun.setEnabled(false);
        insertPlayer.setEnabled(false);
    }

    /**
     * Open and initialize game from a CSV file (send game to server).
     */
    private void OpenFile()
    {
        if (game != null)
        {
            game.clearGame();
        }
        File selectedFile;
        JFileChooser fileChooser = new JFileChooser("./data");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getPath();
            System.out.println("Selected file: " + fileName);
            System.out.println("Sending to server...");
            game = HandleServer.initGame(fileName);
            battleground.updateGame(game);
            insertPlayer.setEnabled(true);
        }
        System.out.println("Please insert a player to run the game...");
    }
}
