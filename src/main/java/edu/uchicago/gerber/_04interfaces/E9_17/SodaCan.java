package edu.uchicago.gerber._04interfaces.E9_17;

public class SodaCan implements Measurable{
    private double radius;
    private double height;

    public SodaCan(double r, double h){
        this.radius = r;
        this.height = h;
    }

    @Override
    public double getMeasure() {
        return 2 * Math.PI * this.radius * this.radius + 2 * Math.PI * this.radius * this.height;
    }


    public double getVolume(){
        return Math.PI*this.radius*this.radius * this.height;
    }
}
