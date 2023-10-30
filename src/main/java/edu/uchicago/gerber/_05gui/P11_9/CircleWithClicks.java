package edu.uchicago.gerber._05gui.P11_9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CircleWithClicks extends JFrame {
    private Point center;
    private Point pointOnPeriphery;

    public CircleWithClicks() {
        setTitle("Circle Viewer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MouseListener listener = new MouseListener();
        addMouseListener(listener);

        center = null;
        pointOnPeriphery = null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (center != null && pointOnPeriphery != null) {
            int radius = (int) center.distance(pointOnPeriphery);
            int diameter = radius * 2;
            int x = center.x - radius;
            int y = center.y - radius;

            g.drawOval(x, y, diameter, diameter);
        }
    }

    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (center == null) {
                center = e.getPoint();
            } else if (pointOnPeriphery == null) {
                pointOnPeriphery = e.getPoint();
                repaint();
            }
        }
    }
}
