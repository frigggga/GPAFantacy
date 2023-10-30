package edu.uchicago.gerber._05gui.P10_5;//package edu.uchicago.gerber._05gui.P10_5;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HouseComponent extends JFrame {
    private JPanel housePanel;

    public HouseComponent() {
        setTitle("House Viewer");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        housePanel = new JPanel();
        housePanel.setLayout(new FlowLayout());

        createHouse();

        add(housePanel);

    }

    private void createHouse() {
//
//        component.drawHouse(housePanel.getGraphics(), x, y, width, height, color);
////                frame.add(component);
////                frame.setVisible(true);

        JButton houseButton = new JButton("draw house");
        houseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose House Color", Color.RED);
                if (color == null) {
                    color = Color.RED;
                }

                int x = Integer.parseInt(JOptionPane.showInputDialog("Enter X Position:"));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Enter Y Position:"));
                int width = Integer.parseInt(JOptionPane.showInputDialog("Enter Width:"));
                int height = Integer.parseInt(JOptionPane.showInputDialog("Enter height:"));
//                HouseComponent component = new HouseComponent();
                drawHouse(housePanel.getGraphics(), x, y, width, height, color);
            }
        });
        housePanel.add(houseButton);
    }

    public void drawHouse(Graphics g, int x, int y, int width, int height, Color color) {
        // Draw the house body (rectangle)
        if(color == null){
            g.setColor(Color.BLACK);
        }else{
            g.setColor(color);
        }
        g.fillRect(x, y, width, height);

        // Draw the roof (triangle)
        int[] xPoints = {x, x + width, x + width / 2};
        int[] yPoints = {y, y, y - height / 2};
        if(color == null){
            g.setColor(Color.BLACK);
        }else{
            g.setColor(color);
        }
        g.fillPolygon(xPoints, yPoints, 3);

        // Draw the door (rectangle)
        g.setColor(Color.WHITE);  // Door color
        int doorWidth = width / 4;
        int doorHeight = height / 2;
        g.fillRect(x + width / 2 - doorWidth / 2, y + height / 2, doorWidth, doorHeight);

        // Draw the window (rectangle)
        g.setColor(Color.WHITE);  // Window color
        int windowSize = doorWidth / 2;
        g.fillRect(x + width - windowSize - 10, y + 10, windowSize, windowSize);
    }


}
