package edu.uchicago.gerber._05gui.P11_9;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CircleWithClicks().setVisible(true);
        });
    }
}
