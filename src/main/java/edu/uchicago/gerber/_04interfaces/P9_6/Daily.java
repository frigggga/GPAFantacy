package edu.uchicago.gerber._04interfaces.P9_6;

public class Daily extends Appointment{
    public Daily(String des, int y, int m, int d){
        super(des, y, m, d);
    }

    @Override
    public boolean occursOn(int y, int m, int d){
        return (y * 10000 + m * 100 + d) > (getYear()*10000 + getMonth() * 100 + getDay());
    }
}
