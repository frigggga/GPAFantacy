package edu.uchicago.gerber._04interfaces.E9_11;

class Instructor extends Person {
    private double salary;

    public Instructor(String name, int birthYear, double salary) {
        super(name, birthYear);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Instructor Name: " + this.getName() + ", Birth Year: " + this.getBirthYear() + ", Salary: " + this.salary;

    }
}
