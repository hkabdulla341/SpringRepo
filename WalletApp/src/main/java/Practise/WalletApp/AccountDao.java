package Practise.WalletApp;

import java.util.UUID;

public class AccountDao
{
    private UUID accountid;
    private String name;
    private String mobile;
    private WalletDao aWallet;

    public AccountDao(UUID accountid, String name, String mobile, WalletDao aWallet)
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

    public UUID getAccountid()
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

    public String getbalance()
    {
	return "Balance of account "+ mobile + " is " + aWallet.getBalance();
    }
}
