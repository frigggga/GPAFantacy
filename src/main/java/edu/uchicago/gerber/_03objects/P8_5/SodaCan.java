package edu.uchicago.gerber._03objects.P8_5;

public class SodaCan {
    private double radius;
    private double height;

    public SodaCan(double r, double h){
        this.radius = r;
        this.height = h;
    }

    public double getSurfaceArea(){
        return 2*Math.PI*this.radius*this.radius + 2*Math.PI*radius*this.height;
    }

    public double getVolume(){
        return Math.PI*this.radius*this.radius * this.height;
    }
}
