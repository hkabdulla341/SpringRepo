package Practise.WalletApp;

import java.math.BigDecimal;
import java.util.UUID;

public class Account
{
    private String accountid;
    private String name;
    private String mobile;
    private Wallet aWallet;

    public Account(String name, String mobile, BigDecimal amt)
    {
	super();
	this.accountid = UUID.randomUUID().toString();
	this.name = name;
	this.mobile = mobile;

	if (!mobile.equals("null"))
	{
	    this.aWallet = new Wallet(Integer.parseInt(mobile), amt);
	}
    }

    public String getAccountid()
    {
	return accountid;
    }

    public void setAccountid(String accountid2)
    {
	this.accountid = accountid2;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getMobile()
    {
	return mobile;
    }

    public void setMobile(String mobile)
    {
	this.mobile = mobile;
    }

    public Wallet getaWallet()
    {
	return aWallet;
    }

    public void setaWallet(Wallet aWallet)
    {
	this.aWallet = aWallet;
    }

}
