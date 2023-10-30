package edu.uchicago.gerber._05gui.P10_9;

import javax.swing.*;
import java.awt.*;

public class FlagComponent extends JComponent {
    public void paintComponent(Graphics g)
    {
        drawFlag(g, 10, 10, 150, 100, Color.BLACK, Color.RED, Color.YELLOW);
        drawFlag(g, 10, 300, 150, 100, Color.RED, Color.WHITE, Color.GREEN);
    }


    public void drawFlag(Graphics g, int xLeft, int yTop, int width, int height, Color c1, Color c2, Color c3){
        g.setColor(c1);
        g.fillRect(xLeft, yTop, width, height / 3);

        g.setColor(c2);
        g.fillRect(xLeft, yTop + height / 3, width, height / 3);

        g.setColor(c3);
        g.fillRect(xLeft, yTop + 2 * height / 3, width, height / 3);
    }

}
