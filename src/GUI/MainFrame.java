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
    // Game control \\
    private JMenu gameCommand = new JMenu("Commands");
    private JMenuItem commandMan = new JMenuItem("Manual");
    private JMenuItem commandAuto = new JMenuItem("Auto");
    private JMenuItem commandReset = new JMenuItem("Reset");
    // Game options \\
    private JMenu gameOptions = new JMenu("Options");
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
        gameCommand.add(commandMan);
        gameCommand.add(commandAuto);
        gameCommand.add(commandReset);
        gameBar.add(gameCommand);
        gameOptions.add(optionsOpen);
        gameOptions.add(optionsClear);
        gameBar.add(gameOptions);
        gameInsert.add(insertPlayer);
        gameBar.add(gameInsert);
        // Add the panel to the JFrame
        add(battleground);
        // Button options
        commandMan.setEnabled(false);
        commandAuto.setEnabled(false);
        commandReset.setEnabled(false);
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
        commandReset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetGame();
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

    private void runAuto()
    {
        HandleServer.startServer();
        commandMan.setEnabled(false);
        commandAuto.setEnabled(false);
        commandReset.setEnabled(true);
        battleground.autoGame();
    }

    private void resetGame()
    {
        game.clearGame();
        battleground.stopMouseListen();
        HandleServer.stopServer();
        game = HandleServer.initGame(game.getFileName());
        battleground.updateGame(game);
        commandMan.setEnabled(false);
        commandAuto.setEnabled(false);
        commandReset.setEnabled(false);
        insertPlayer.setEnabled(true);
    }

    private void runManual()
    {
        HandleServer.startServer();
        commandMan.setEnabled(false);
        commandAuto.setEnabled(false);
        commandReset.setEnabled(true);
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

    private void ClearGame()
    {
        if (game != null)
        {
            game.clearGame();
            battleground.updateGame(game);
            commandMan.setEnabled(false);
            commandAuto.setEnabled(false);
            commandReset.setEnabled(false);
            insertPlayer.setEnabled(false);
        }
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
            game = HandleServer.initGame(fileName);
            battleground.updateGame(game);
            insertPlayer.setEnabled(true);
            commandAuto.setEnabled(true);
        }
    }
}
