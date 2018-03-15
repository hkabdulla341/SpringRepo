package walletApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountDao
{
    private String accountid;
    private String name;
    private String mobile;
    private WalletDao aWallet;
    private List<TransactionDao> allAccTxn;

    public AccountDao(String accountid, String name, String mobile, WalletDao aWallet, List<Transaction> allAccTxn)
    {
	super();
	this.accountid = accountid;
	this.name = name;
	this.mobile = mobile;
	this.aWallet = aWallet;
	this.allAccTxn = getAsTxnDao(allAccTxn);
    }

    public AccountDao(Account acc)
    {
	this.accountid = acc.getAccountid();
	this.name = acc.getName();
	this.mobile = acc.getMobile();
	this.aWallet = new WalletDao(acc.getaWallet());
	this.allAccTxn = getAsTxnDao(acc.getAllAccTxn());
    }

    public List<TransactionDao> getAsTxnDao(List<Transaction> allTxn)
    {
	List<TransactionDao> newTxnListDao = new ArrayList<TransactionDao>();

	for (Transaction aTxn : allTxn)
	{
	    newTxnListDao.add(new TransactionDao(aTxn));
	}

	return newTxnListDao;
    }

    public String getAccountid()
    {
	return accountid;
    }

    public String getName()
    {
	return name;
    }

    public String getMobile()
    {
	return mobile;
    }

    public WalletDao getaWallet()
    {
	return aWallet;
    }

    public List<TransactionDao> getAllAccTxn()
    {
	return allAccTxn;
    }

    public String display()
    {
	return "Balance of account " + mobile + " is " + aWallet.getBalance();
    }

    public BigDecimal getWalletBalance()
    {
	return aWallet.getBalance();
    }

    @Override
    public String toString()
    {
	String str = "Account Id : " + this.accountid + " Account Mobile : " + this.mobile + System.lineSeparator();
	str += aWallet.toString() + System.lineSeparator();

	for (TransactionDao txn : allAccTxn)
	{
	    str += txn.toString() + System.lineSeparator();
	}

	return str;
    }
}
