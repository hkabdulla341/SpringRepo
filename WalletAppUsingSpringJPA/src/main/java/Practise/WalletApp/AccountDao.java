package Practise.WalletApp;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountDao
{
    private String accountid;
    private String name;
    private String mobile;
    private WalletDao aWallet;

    public AccountDao(String accountid, String name, String mobile, WalletDao aWallet)
    {
	super();
	this.accountid = accountid;
	this.name = name;
	this.mobile = mobile;
	this.aWallet = aWallet;
    }

    public AccountDao(Account acc)
    {
	this.accountid = acc.getAccountid();
	this.name = acc.getName();
	this.mobile = acc.getMobile();
	this.aWallet = new WalletDao(acc.getaWallet());
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
	return "Account Id : " + this.accountid + " Mobile : " + this.mobile + " "+ this.aWallet.toString();
    }
}
