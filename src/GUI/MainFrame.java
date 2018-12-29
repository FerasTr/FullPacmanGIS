package GUI;

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
    private JMenuBar optionsBar = new JMenuBar();
    // Game options \\
    private JMenu gameOptions = new JMenu("Options");
    private JMenuItem gameRun = new JMenuItem("Run");
    private JMenuItem gameOpen = new JMenuItem("Open");
    private JMenuItem gameClear = new JMenuItem("Clear");

    /**
     * JFrame constructor, builds game menu bad and adds the playground panel to the frame
     */
    public MainFrame()
    {
        // Add the menu bar and its elements to the frame \\
        gameOptions.add(gameRun);
        gameOptions.add(gameOpen);
        gameOptions.add(gameClear);
        optionsBar.add(gameOptions);

        // ActionListeners for menu buttons \\
        gameOpen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OpenFile();
            }
        });

        getContentPane().add(optionsBar, BorderLayout.NORTH);
        // JFrame settings \\
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(300, 100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Open and initialize game from a CSV file (send game to server).
     */
    private void OpenFile()
    {
        File selectedFile;
        JFileChooser fileChooser = new JFileChooser("./data");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getPath();
            System.out.println("Selected file: " + fileName);
            System.out.println("Sending to server...");
            HandleServer.initGame(fileName);
        }
    }
}
