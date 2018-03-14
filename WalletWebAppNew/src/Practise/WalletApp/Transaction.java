package Practise.WalletApp;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.*;

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

    @ManyToOne
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

    @Override
    public String toString()
    {
	return "Date : " + dateOfTransaction + " | Account : " + account.getMobile() + " | Action : " + performedAction
		+ " | amount : $" + amount + " | balance : $" + account.getaWallet().getBalance();
    }
}
