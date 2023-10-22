package edu.uchicago.gerber._04interfaces.E9_8;

public class BankAccount
{
    private double balance;

    public BankAccount()
    {
        this.balance = 0;
    }

    public void deposit(double amount)
    {
        this.balance += amount;
    }

    public void withdraw(double amount)
    {
        this.balance -= amount;
    }

    public void monthEnd()
    {
        return;
    }

    public double getBalance()
    {
        return this.balance;
    }
}
