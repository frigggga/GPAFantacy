package edu.uchicago.gerber._05gui.P10_9;

import javax.swing.*;

public class FlagViewer {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent component = new FlagComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
