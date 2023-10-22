package edu.uchicago.gerber._04interfaces.E9_8;

public class BasicAccount extends BankAccount{
    public BasicAccount(){}

    public void withdraw(double amount)
    {
        double balance = getBalance();
        if(amount <= balance){
            super.withdraw(amount);
        }else{
            System.out.println("You are trying to withdraw an amount that is greater than your current account balance, please do not withdraw more money than is currently in the account.");
        }
    }
}
