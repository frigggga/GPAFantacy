package edu.uchicago.gerber._02arrays;

import java.io.*;
import java.util.Scanner;

public class E7_4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("I have already created one input file called 'E7_4.txt' inside the project root directory, feel free to use it");
        System.out.print("Enter the input file name: ");
        String inputFile = scan.nextLine();

        System.out.print("Enter the output file name: ");
        String outputFile = scan.nextLine();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            int lineNumber = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                String numberedLine = "/* " + lineNumber + " */ " + line;
                writer.write(numberedLine);
                writer.newLine();
                lineNumber++;
            }

            reader.close();
            writer.close();

            System.out.println("Line numbering completed. Output written to " + outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }
}

