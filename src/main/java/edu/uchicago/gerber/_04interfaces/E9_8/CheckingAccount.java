package edu.uchicago.gerber._04interfaces.E9_8;

public class CheckingAccount extends BankAccount
{
    private int withdrawal;

    public CheckingAccount()
    {
        this.withdrawal = 0;
    }

    public void withdraw(double amount)
    {
        final int FREE_WITHDRAWALS = 3;
        final int WITHDRAWAL_FEE = 1;

        super.withdraw(amount);
        this.withdrawal++;
        if (this.withdrawal > FREE_WITHDRAWALS)
        {
            super.withdraw(WITHDRAWAL_FEE);
        }else{
           System.out.println("You have exceeded the free withdrawal times, a fee of $1 is applied");
        }
    }

    public void monthEnd()
    {
        this.withdrawal = 0;
    }
}
