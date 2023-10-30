package edu.uchicago.gerber._05gui.P10_5;

import javax.swing.*;

public class HouseViewer {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HouseComponent().setVisible(true);
        });
    }

}

