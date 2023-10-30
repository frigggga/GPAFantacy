package edu.uchicago.gerber._05gui.P10_2;

import javax.swing.*;
import java.awt.*;

public class BullsEyeComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int x = 0;
        int y = 0;

        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }

            int diameter = Math.min(width, height) - 2 * i * 30;
            x = (width - diameter) / 2;
            y = (height - diameter) / 2;

            g.fillOval(x, y, diameter, diameter);
        }
    }
}