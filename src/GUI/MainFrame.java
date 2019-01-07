package GUI;

import GameElements.Game;
import GameMap.Map;
import ToServer.EndDialog;
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
    // Game control \\
    private JMenu gameCommand = new JMenu("Commands");
    private JMenuItem commandMan = new JMenuItem("Manual");
    private JMenuItem commandAuto = new JMenuItem("Auto");
    // Game options \\
    private JMenu gameOptions = new JMenu("Options");
    private JMenuItem optionsOpen = new JMenuItem("Open");
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
        gameCommand.add(commandMan);
        gameCommand.add(commandAuto);
        gameBar.add(gameCommand);
        gameOptions.add(optionsOpen);
        gameBar.add(gameOptions);
        gameInsert.add(insertPlayer);
        gameBar.add(gameInsert);
        // Add the panel to the JFrame
        add(battleground);
        // Button options
        commandMan.setEnabled(false);
        commandAuto.setEnabled(false);
        insertPlayer.setEnabled(false);

        commandMan.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                runManual();
            }
        });

        commandAuto.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                runAuto();
            }
        });
        optionsOpen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OpenFile();
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

    private void runAuto()
    {
        commandMan.setEnabled(false);
        commandAuto.setEnabled(false);
        battleground.autoGame();
    }

    private void runManual()
    {
        HandleServer.startServer();
        commandMan.setEnabled(false);
        commandAuto.setEnabled(false);
        battleground.manualGame();
    }

    private void AddPlayer()
    {
        System.out.println("Adding player to the game...");
        battleground.addPlayer();
        insertPlayer.setEnabled(false);
        commandMan.setEnabled(true);
        commandAuto.setEnabled(false);
    }

    /**
     * Open and initialize game from a CSV file (send game to server).
     */
    private void OpenFile()
    {
        if (game != null)
        {
            game.clearGame();
            game.getObstacles().clear();
        }
        File selectedFile;
        JFileChooser fileChooser = new JFileChooser("./data");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getPath();
            System.out.println("Selected file: " + fileName);
            game = HandleServer.initGame(fileName);
            battleground.updateGame(this,game);
            insertPlayer.setEnabled(true);
            commandAuto.setEnabled(true);
        }
    }
}
