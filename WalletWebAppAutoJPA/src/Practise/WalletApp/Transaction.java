package Practise.WalletApp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Wall_Acct_Txn")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id")
    private int id;

    @Temporal(TemporalType.DATE)
    private Calendar dateOfTransaction;
    private String performedAction;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;
    private String balance;
    private String amount;

    public Transaction()
    {
    }

    public Transaction(String performedAction, Account account, String amount)
    {
	super();
	this.dateOfTransaction = new GregorianCalendar();
	this.balance = account.getaWallet().getBalance().toString();
	this.performedAction = performedAction;
	this.account = account;
	this.amount = amount;
    }

    public Date getDateOfTransaction()
    {
	return dateOfTransaction.getTime();
    }

    public String getPerformedAction()
    {
	return performedAction;
    }

    public String getAmount()
    {
	return amount;
    }

    public String getBalance()
    {
	return balance;
    }

    public void setPerformedAction(String performedAction)
    {
	this.performedAction = performedAction;
    }

    public void setBalance(String balance)
    {
	this.balance = balance;
    }

    public void setAmount(String amount)
    {
	this.amount = amount;
    }

    @Override
    public String toString()
    {
	return "Date : " + dateOfTransaction.getTime() + " | Account : " + account.getMobile() + " | Action : "
		+ performedAction + " | amount : $" + amount + " | balance : $" + balance;
    }
}
