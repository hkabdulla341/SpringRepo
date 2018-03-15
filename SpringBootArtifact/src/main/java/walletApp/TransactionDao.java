package walletApp;

import java.util.Date;

public class TransactionDao
{
    private Date dateOfTransaction;
    private String performedAction;
    private String balance;
    private String amount;

    public TransactionDao(Transaction txn)
    {
	this.dateOfTransaction = txn.getDateOfTransaction();
	this.performedAction = txn.getPerformedAction();
	this.balance = txn.getBalance();
	this.amount = txn.getAmount();
    }

    public Date getDateOfTransaction()
    {
	return dateOfTransaction;
    }

    public String getPerformedAction()
    {
	return performedAction;
    }

    public String getBalance()
    {
	return balance;
    }

    public String getAmount()
    {
	return amount;
    }

    @Override
    public String toString()
    {
	return "Date : " + dateOfTransaction.getTime() + " | Action : " + performedAction + " | amount : $" + amount
		+ " | balance : $" + balance;
    }
}
