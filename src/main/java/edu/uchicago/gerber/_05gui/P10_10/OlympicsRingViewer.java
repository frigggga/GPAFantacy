package edu.uchicago.gerber._05gui.P10_10;

import javax.swing.*;

public class OlympicsRingViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Olympic Ring Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        OlympicRingComponent ringsComponent = new OlympicRingComponent();
        frame.add(ringsComponent);

        frame.setVisible(true);
    }
}
