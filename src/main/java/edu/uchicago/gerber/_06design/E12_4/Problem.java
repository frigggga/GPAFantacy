package edu.uchicago.gerber._06design.E12_4;

public class Problem {
    public final int num1;
    public final int num2;
    public final String operation;
    public final int answer;

    public Problem(int num1, int num2, String operation, int answer) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "What is " + num1 + " " + operation + " " + num2 + "?";
    }
}
