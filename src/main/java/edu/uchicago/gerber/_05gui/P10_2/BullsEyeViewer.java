package edu.uchicago.gerber._05gui.P10_2;

import javax.swing.*;

// Driver for Bulls Eye Component
public class BullsEyeViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setTitle("Bull's Eye Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BullsEyeComponent component = new BullsEyeComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
