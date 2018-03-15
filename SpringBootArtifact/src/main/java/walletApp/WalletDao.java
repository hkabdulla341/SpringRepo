package walletApp;

import java.math.BigDecimal;

public class WalletDao
{
    private int id;
    private BigDecimal balance;

    public WalletDao(int id, BigDecimal balance)
    {
	super();
	this.id = id;
	this.balance = balance;
    }

    public WalletDao(Wallet wallet)
    {
	this.id = wallet.getId();
	this.balance = wallet.getBalance();
    }

    public BigDecimal getBalance()
    {
	return balance;
    }

    public int getId()
    {
	return id;
    }

    @Override
    public String toString()
    {
	return "Wallet id : " + this.id + ", Wallet Balance: " + getBalance();
    }
}
