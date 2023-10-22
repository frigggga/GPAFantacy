package edu.uchicago.gerber._04interfaces.P9_6;

public class Appointment {
    private String description;
    private int year;
    private int month;
    private int day;

    public Appointment(String des, int y, int m, int d){
        this.description = des;
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public boolean occursOn(int y, int m, int d){
        return this.year == y && this.month == m && this.day == d;
    }

    public String getDescription() {
        return this.description;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "description='" + description + '\'' +
                ", on " + year +
                " - " + month +
                " - " + day +
                '}';
    }
}
