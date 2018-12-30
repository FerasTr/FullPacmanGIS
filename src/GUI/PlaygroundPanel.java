package GUI;

import GameElements.Game;
import GameElements.Pacman;
import GameMap.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * JPanel for displaying the game
 */
public class PlaygroundPanel extends JPanel
{
    // Variables \\
    private BufferedImage myImage;
    private int imgW, imgH;
    private Map map;
    private Game game = null;
    // Icons


    public PlaygroundPanel(Map battlegroundMap)
    {
        map = battlegroundMap;
        myImage = map.getMapPath();
        imgW = myImage.getWidth();
        imgH = myImage.getHeight();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
        drawGame();
    }

    private void drawGame()
    {
        if (game != null)
        {
            // Display pacman bots
            ArrayList<Pacman> pacBots = game.getPacmanBots();
            for (Pacman pacBot : pacBots)
            {

            }
        }
    }

    public void updateGame(Game gameSettings)
    {
        this.game = gameSettings;
        repaint();
    }
}
