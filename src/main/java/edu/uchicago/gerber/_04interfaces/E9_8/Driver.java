package edu.uchicago.gerber._04interfaces.E9_8;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args)
    {
        BankAccount[] accounts = new BankAccount[10];
        System.out.println("Created 10 new bank accounts, with 2 checking accounts,  3 savings accounts, and 5 basic accounts ");
        System.out.println("Two checking accounts have account number 0 and 1");
        for (int i = 0; i < 2; i++)
        {
            accounts[i] = new CheckingAccount();
        }
        System.out.println("Savings accounts have account number 2, 3 and 4");
        for (int i = 2; i < 5; i++)
        {
            SavingsAccount account = new SavingsAccount();
            account.setInterestRate(0.75);
            accounts[i] = account;
        }
        System.out.println("The 5 Basic accounts have account number 5-9");
        for (int i = 5; i < accounts.length; i++)
        {
            accounts[i] = new BasicAccount();
        }


        Scanner in = new Scanner(System.in);
        boolean done = false;
        while (!done)
        {
            System.out.print("D)eposit  W)ithdraw  M)onth end  Q)uit: ");
            String input = in.next();
            if (input.equals("D") || input.equals("W")) // Deposit or withdrawal
            {
                System.out.print("Enter account number and amount(account number is any number between 0-9): ");
                int num = in.nextInt();
                double amount = in.nextDouble();

                if (input.equals("D")) { accounts[num].deposit(amount); }
                else { accounts[num].withdraw(amount); }

                System.out.println("Balance: " + accounts[num].getBalance());
            }
            else if (input.equals("M")) // Month end processing
            {
                for (int n = 0; n < accounts.length; n++)
                {
                    accounts[n].monthEnd();
                    System.out.println(n + " " + accounts[n].getBalance());
                }
            }
            else if (input.equals("Q"))
            {
                done = true;
            }
        }
    }
}
