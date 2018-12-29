package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * JPanel for displaying the game
 */
public class PlaygroundPanel extends JPanel
{
    // Variables \\
    private BufferedImage myImage = null;
    private int imgW = myImage.getWidth(), imgH = myImage.getHeight();

    public PlaygroundPanel()
    {
        try
        {
            myImage = ImageIO.read(new File("./data/Ariel1.png"));
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
