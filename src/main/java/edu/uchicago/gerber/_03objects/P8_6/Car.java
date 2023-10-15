package edu.uchicago.gerber._03objects.P8_6;

public class Car {
    private double fuelEfficiency;
    private double gasLevel;

    public Car(double efficiency){
        this.fuelEfficiency = efficiency;
        this.gasLevel = 0;
    }

    public void addGas(double gas){
        this.gasLevel += gas;
        System.out.println(gas + " level of gas has been added to the tank, The current remaining gas level is " + this.gasLevel);
    }

    public void drive(double mile){
        double fuelAmountNeeded = mile / this.fuelEfficiency;
        double diff = this.gasLevel - fuelAmountNeeded;
        if(diff < 0){
            this.gasLevel = 0;
            System.out.println("The current gas level is 0. You need to add " + Math.abs(diff) + " more gas to drive the remaining miles. ");
        }else{
            this.gasLevel -= fuelAmountNeeded;
            System.out.println("The current remaining gas level is " + diff);
        }
    }

    public double getGasLevel(){
        return this.gasLevel;
    }
}
