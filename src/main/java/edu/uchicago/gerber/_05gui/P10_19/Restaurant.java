package edu.uchicago.gerber._05gui.P10_19;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Restaurant {
    private static final double TAX_RATE = 0.08;
    private static final double TIP_RATES[] = {0.10, 0.12, 0.15};

    private JFrame frame;
    private JPanel menuPanel;
    private JPanel billPanel;
    private JTextArea billTextArea;
    private ArrayList<String> dishes;

    private double totalBill;

    public Restaurant() {
        frame = new JFrame("Restaurant Bill");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 2));
        dishes = new ArrayList<>();

        createMenuItem("Beijing Duck", 20.0);
        createMenuItem("Gongpao Chicken", 22.0);
        createMenuItem("Chinese Noodle", 16.0);
        createMenuItem("Fried Rice", 15.0);
        createMenuItem("Today's Special #1", 9.0);
        createMenuItem("Today's Special #2", 13.0);
        createMenuItem("ice cream", 9.0);
        createMenuItem("Dumplings", 13.0);
        createMenuItem("Bubbled Milk Tea", 5);
        createMenuItem("Mango Fruit Tea", 7);

        createCustomItem();

        billPanel = new JPanel();
        billPanel.setLayout(new BorderLayout());

        billTextArea = new JTextArea();
        billTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billTextArea);

        menuPanel.setPreferredSize(new Dimension(400, 300));
        billPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(billPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void createMenuItem(String name, double price) {
        JButton button = new JButton(name + " ($" + price + ")");
        menuPanel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                totalBill += price;
                dishes.add(name);
                updateBillTextArea();
            }
        });
    }

    private void createCustomItem() {
//        JTextField itemName = new JTextField("Custom Item");
//        JTextField itemPrice = new JTextField("0.0");
        JButton addButton = new JButton("Add Custom Item");
//
//        menuPanel.add(itemName);
//        menuPanel.add(itemPrice);
        menuPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
                    JButton button = new JButton("custom item" + " ($" + price + ")");
                    menuPanel.add(button);
                    totalBill += price;
                    dishes.add("custom item");
                    updateBillTextArea();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid price for the custom item.");
                }
            }
        });
    }

    private void updateBillTextArea() {
        StringBuilder billText = new StringBuilder();
        billText.append("Restaurant Bill\n");
        for(int i = 0; i < dishes.size(); i++){
            billText.append("Dish: " + dishes.get(i) + '\n');
        }

        billText.append("Total Bill Without Tax: $" + String.format("%.2f", totalBill) + "\n");
        billText.append("Tax rate: 8%" + "\n");
        totalBill += TAX_RATE * totalBill;
        billText.append("Total Bill With Tax: $" + String.format("%.2f", totalBill)  + "\n");
        for (double tipRate : TIP_RATES) {
            double tip = totalBill * tipRate;
            double totalWithTip = totalBill + tip;
            double totalWithTax = totalWithTip * (1 + TAX_RATE);

            billText.append("Tip (" + (int) (tipRate * 100) + "%): $" + String.format("%.2f", tip) + "\n");
            billText.append("Total Bill (with " + (int) (tipRate * 100) + "% tip and tax): $" + String.format("%.2f", totalWithTax) + "\n");
        }

        billTextArea.setText(billText.toString());
    }
}
