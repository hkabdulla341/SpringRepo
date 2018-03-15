package walletApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wall_account")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "acct_id")
    private int id;
    private String accountid;
    private String name;

    @Column(unique = true)
    private String mobile;

    @Temporal(TemporalType.DATE)
    private Calendar dateOfCreation;

    @OneToOne(cascade = { CascadeType.ALL })
    private Wallet aWallet;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<Transaction> allAccTxn;

    public Account()
    {
    }

    public Account(String name, String mobile, BigDecimal amt)
    {
	super();
	this.accountid = UUID.randomUUID().toString();
	this.name = name;
	this.mobile = mobile;
	this.dateOfCreation = new GregorianCalendar();
	this.allAccTxn = new ArrayList<Transaction>();

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

    public List<Transaction> getAllAccTxn()
    {
	return allAccTxn;
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

    public void addNewTransaction(String action, String amount)
    {
	this.allAccTxn.add(new Transaction(action, this, amount));
    }
}
