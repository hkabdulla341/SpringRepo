package Practise.WalletApp;

import java.math.BigDecimal;

public class Wallet
{
    private int id;
    private BigDecimal balance;

    public Wallet(int id, BigDecimal balance)
    {
	super();
	this.id = id;
	this.balance = balance;
    }

    public int getId()
    {
	return id;
    }

    public void setId(int id)
    {
	this.id = id;
    }

    public BigDecimal getBalance()
    {
	return balance;
    }

    public void setBalance(BigDecimal balance)
    {
	this.balance = balance;
    }

}
