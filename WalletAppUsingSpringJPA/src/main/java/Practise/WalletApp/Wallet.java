package Practise.WalletApp;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "Wall_wallet")
public class Wallet
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "wall_id")
    private int id;
    private int walletId;
    private BigDecimal balance;

    public Wallet()
    {
    }

    public Wallet(int id, BigDecimal balance)
    {
	super();
	this.walletId = id;
	this.balance = balance;
    }

    public int getId()
    {
	return walletId;
    }

    public void setId(int id)
    {
	this.walletId = id;
    }

    public BigDecimal getBalance()
    {
	return balance;
    }

    public void setBalance(BigDecimal balance)
    {
	this.balance = balance;
    }

    @Override
    public String toString()
    {
	return "Wallet id : " + this.walletId + ", Balance: " + getBalance();
    }

}
