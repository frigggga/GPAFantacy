package edu.uchicago.gerber._04interfaces.P9_6;

public class Monthly extends Appointment{
    public Monthly(String des, int y, int m, int d){
        super(des, y, m, d);
    }

    @Override
    public boolean occursOn(int y, int m, int d){
        return this.getDay() == d;
    }
}
