package edu.uchicago.gerber._06design.E12_4;

import java.util.Scanner;

public class Driver {
    private final Arithmetic problemGenerator;
    private final Scanner scanner;
    private Arithmetic.Level currentLevel;
    private int score;

    public Driver() {
        problemGenerator = new Arithmetic();
        scanner = new Scanner(System.in);
        currentLevel = Arithmetic.Level.LEVEL_1;
        score = 0;
    }

    public void startGame() {
        System.out.println("Welcome to the Arithmetic Game!");

        while (currentLevel != null) {
            System.out.println("Level " + (currentLevel.ordinal() + 1));
            Problem problem = problemGenerator.generateProblem(currentLevel);

            int attempts = 0;
            while (attempts < 2) {
                System.out.println(problem);
                int playerAnswer = scanner.nextInt();
                if (playerAnswer == problem.answer) {
                    System.out.println("Correct!");
                    score++;
                    break;
                } else {
                    System.out.println("That's not quite right. Try again.");
                }
                attempts++;
            }

            if (score >= 5) {
                advanceLevel();
            }
        }

        System.out.println("Congratulations! You have completed all levels of the Arithmetic Game!");
        scanner.close();
    }

    private void advanceLevel() {
        if (currentLevel == Arithmetic.Level.LEVEL_3) {
            currentLevel = null;
        } else {
            currentLevel = Arithmetic.Level.values()[currentLevel.ordinal() + 1];
            score = 0;
            System.out.println("Great job! Advancing to the next level.");
        }
    }

    public static void main(String[] args) {
        new Driver().startGame();
    }
}
