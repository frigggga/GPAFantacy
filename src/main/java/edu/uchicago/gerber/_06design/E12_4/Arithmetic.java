package edu.uchicago.gerber._06design.E12_4;

import java.util.Random;


public class Arithmetic {
    private final Random random;
    public enum Level {
        LEVEL_1,
        LEVEL_2,
        LEVEL_3
    }
    public Arithmetic() {
        random = new Random();
    }

    public Problem generateProblem(Level level) {
        int num1, num2;
        String operation = null;
        int answer;

        switch (level) {
            case LEVEL_1:
                num1 = random.nextInt(5) + 1;
                num2 = random.nextInt(5) + 1;
                answer = num1 + num2;
                break;
            case LEVEL_2:
                num1 = random.nextInt(10);
                num2 = random.nextInt(10);
                operation = "+";
                answer = num1 + num2;
                break;
            case LEVEL_3:
                num1 = random.nextInt(10);
                num2 = random.nextInt(num1 + 1);
                operation = "-";
                answer = num1 - num2;
                break;
            default:
                throw new IllegalArgumentException("Unsupported level");
        }
        return new Problem(num1, num2, operation, answer);
    }
}



