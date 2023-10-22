package edu.uchicago.gerber._04interfaces.E9_11;

class Student extends Person {
    private String major;

    public Student(String name, int birthYear, String major) {
        super(name, birthYear);
        this.major = major;
    }

    public String getMajor() {
        return this.major;
    }

    @Override
    public String toString() {
        return "Student Name: " + this.getName() + ", Birth Year: " + this.getBirthYear() + ", Major: " + this.major;
    }
}