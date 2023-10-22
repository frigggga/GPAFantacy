package edu.uchicago.gerber._04interfaces.P9_1;

public class Clock {
    private int hours;
    private int minutes;
    public Clock(){
        hours = java.time.LocalTime.now().getHour();
        minutes = java.time.LocalTime.now().getMinute();
    }

    public int getHours(){
        return this.hours;
    }

    public int getMinutes(){
        return this.minutes;
    }

    public String getTime(){
        return "The current time is: " + this.getHours() + " : " + this.getMinutes();
    }
}
