package edu.uchicago.gerber._04interfaces.E9_8;

public class SavingsAccount extends BankAccount
{
    private double interestRate;
    private double minBalance;

    public SavingsAccount()
    {
        interestRate = 0;
        minBalance = 0;
    }

    public void setInterestRate(double rate)
    {
        interestRate = rate;
    }

    public void withdraw(double amount)
    {
        super.withdraw(amount);
        double balance = getBalance();
        if (balance < minBalance)
        {
            minBalance = balance;
        }
    }

    public void monthEnd()
    {
        double interest = minBalance * interestRate / 100;
        deposit(interest);
        minBalance = getBalance();
    }
}


