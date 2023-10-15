package edu.uchicago.gerber._03objects.P8_1;

// A microwave control panel has four buttons: one for increasing the time by 30 seconds,
// one for switching between power levels 1 and 2, a reset button, and a start button.
// Implement a class that simulates the microwave, with a method for each button.
// The method for the start button should print a message “Cooking for ... seconds at level ...”.

public class Microwave {
    private int powerLevel;
    private int time;

    public Microwave(){
        this.powerLevel = 1; //set default power level to 1;
        this.time = 0;
    }

    //increasing the time by 30 seconds,
    public void increaseTime(){
        this.time += 30;
        System.out.println("Time increased by 30 seconds.");
    }

//    switching between power levels 1 and 2
    public void switchPowerLevel(){
        if(powerLevel == 1){
            this.powerLevel = 2;
        }else{
            this.powerLevel = 1;
        }
        System.out.println("Power level switched to " + powerLevel);
    }

    //reset time = 0 and power level = 1;
    public void reset(){
        this.time = 0;
        this.powerLevel = 1;
        System.out.println("time and power level reset to default settings." );
    }

    public void start(){
        if(this.time > 0){
            System.out.println("Cooking for " + this.time + " seconds at level " + this.powerLevel);
            this.time = 0;
            System.out.println("Cooking completed, time has been automatically set to 0. ");
        }else{
            System.out.println("Please set cooking time first. ");
        }

    }
}
