package edu.uchicago.gerber._05gui.P10_19;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Restaurant();
            }
        });
    }
}

