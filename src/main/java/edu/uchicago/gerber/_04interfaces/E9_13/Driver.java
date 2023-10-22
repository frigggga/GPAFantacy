package edu.uchicago.gerber._04interfaces.E9_13;

public class Driver {
    public static void main(String[] args) {
        BetterRectangle betterRectangle1 = new BetterRectangle(3, 3, 10, 12);
        System.out.println("Created a better rectangle with width 10 and height 12");

        double perimeter1 = betterRectangle1.getPerimeter();
        double area1 = betterRectangle1.getArea();
        System.out.println("BetterRectangle1 Perimeter: " + perimeter1);
        System.out.println("BetterRectangle1 Area: " + area1);

        BetterRectangle betterRectangle2 = new BetterRectangle(6, 6, 9, 8);
        System.out.println("\nCreated a better rectangle with width 9 and height 9");

        double perimeter2 = betterRectangle2.getPerimeter();
        double area2 = betterRectangle2.getArea();
        System.out.println("BetterRectangle2 Perimeter: " + perimeter2);
        System.out.println("BetterRectangle2 Area: " + area2);

        BetterRectangle betterRectangle3 = new BetterRectangle(10, 10, 22, 33);
        System.out.println("\nCreated a better rectangle with width 22 and height 33");

        double perimeter3 = betterRectangle3.getPerimeter();
        double area3 = betterRectangle3.getArea();
        System.out.println("BetterRectangle3 Perimeter: " + perimeter3);
        System.out.println("BetterRectangle3 Area: " + area3);
    }
}
