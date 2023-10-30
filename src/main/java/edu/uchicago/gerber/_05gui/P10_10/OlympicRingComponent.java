package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.*;
import java.awt.*;

public class OlympicRingComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawRing(g, Color.BLUE, 100, 100);
        drawRing(g, Color.BLACK, 150, 100);
        drawRing(g, Color.RED, 200, 100);
        drawRing(g, Color.YELLOW, 130, 120);
        drawRing(g, Color.GREEN, 180, 120);
    }

    private void drawRing(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.drawOval(x, y, 50, 50);
    }
}
