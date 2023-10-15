package edu.uchicago.gerber._03objects.P8_7;

public class Driver {
    public static void main(String[] args) {
        ComboLock lock = new ComboLock(10, 20, 30);

        lock.turnRight(10);
        lock.turnLeft(20);
        lock.turnRight(30);

        if (lock.open()) {
            System.out.println("Lock is open.");
        } else {
            System.out.println("Incorrect combination. Lock is still closed.");
        }
    }
}
