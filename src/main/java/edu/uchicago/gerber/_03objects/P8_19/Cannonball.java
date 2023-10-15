package edu.uchicago.gerber._03objects.P8_19;

import java.text.DecimalFormat;

public class Cannonball {
    DecimalFormat df = new DecimalFormat("####0.00");
    private double x;
    private double y;
    private double vx;
    private double vy;

    public Cannonball(double p) {
        this.x = p;
        this.y = 0.0;
        this.vx = 0.0;
        this.vy = 0.0;
    }

    public void move(double sec) {
        this.x += this.vx * sec;
        this.y += this.vy * sec - 0.5 * 9.81 * sec * sec;
        vy -= 9.81 * sec;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void shoot(double a, double v) {
        double angle = Math.toRadians(a);
        this.vx = v * Math.cos(angle);
        this.vy = v * Math.sin(angle);

        while (this.y >= 0) {
            move(0.1);
            System.out.println("Position of current x and y: " + df.format(this.getX()) + ", " + df.format(this.getY()));
        }
    }


}
