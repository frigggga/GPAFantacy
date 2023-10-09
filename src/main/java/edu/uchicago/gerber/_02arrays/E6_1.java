package edu.uchicago.gerber._02arrays;

public class E6_1 {
    public static void main(String[] args){
        int[] randomInt = new int[10];
        System.out.println("Initial integers: ");
        for(int i = 0; i < 10; i++){
            randomInt[i] = (int) (Math.random() * 100) + 1;
            System.out.print(randomInt[i] + " ");
        }

        System.out.println();

        System.out.println("Every element at an even index: ");
        for(int i = 0; i < 10; i += 2){
            System.out.print(randomInt[i] + " ");
        }

        System.out.println();

        System.out.println("Every even element: ");
        for(int i = 0; i < 10; i ++){
            if(randomInt[i] % 2 == 0) {
                System.out.print(randomInt[i] + " ");
            }
        }

        System.out.println();

        System.out.println("All elements in reverse order: ");
        for(int i = 9; i >= 0; i --){
            System.out.print(randomInt[i] + " ");
        }

        System.out.println();

        System.out.println("Only the first and last element: ");
        System.out.print(randomInt[0] + " ");
        System.out.print(randomInt[9] + " ");
        System.out.println();
    }
}
