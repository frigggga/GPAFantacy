package edu.uchicago.gerber._02arrays;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Source: https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
// I learnt how to use stringbuilder through this link

public class P7_5 {

    private static List<List<String>> data = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("I have already created one input csv file called 'P7_5.csv' inside the project root directory, feel free to use it");
        System.out.print("Enter the input file name: ");
        String inputFile = scan.nextLine();
        scan.close();
        readFile(inputFile);

        int rowCount = numberOfRows();
        int fieldCount = numberOfFields(2);
        String fieldValue = field(2, 1);

        System.out.println("Number of Rows: " + rowCount);
        System.out.println("Number of Fields in Row 3: " + fieldCount);
        System.out.println("Field Value in Row 3, Column 2: " + fieldValue);
    }


    private static void readFile(String inputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> row = new ArrayList<>();
                StringBuilder field = new StringBuilder();
                boolean insideQuotes = false;

                for (char c : line.toCharArray()) {
                    if (c == '"') {
                        insideQuotes = !insideQuotes;
                    } else if (c == ',' && !insideQuotes) {
                        row.add(field.toString());
                        field = new StringBuilder();
                    } else {
                        field.append(c);
                    }
                }
                row.add(field.toString());
                data.add(row);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int numberOfRows() {
        return data.size();
    }

    public static int numberOfFields(int row) {
        if (row >= 0 && row < data.size()) {
            return data.get(row).size();
        } else {
            return 0;
        }
    }

    public static String field(int row, int column) {
        if (row >= 0 && row < data.size() && column >= 0) {
            List<String> rowData = data.get(row);
            if (column < rowData.size()) {
                return rowData.get(column);
            }
        }
        return null;
    }
}