package Practise.WalletApp;

//import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository(value = "walletServiceDao")
public class WalletServiceDao implements WalletRepo
{
    @Resource(name = "allAccounts")
    private Map<String, Account> allAccounts;

    /*
     * public WalletServiceDao() { this.allAccounts = new HashMap<String,
     * Account>(); }
     */

    public boolean save(Account acc)
    {
	if (allAccounts.containsKey(acc.getMobile()))
	{
	    return false;
	}
	else
	{
	    allAccounts.put(acc.getMobile(), acc);
	    return true;
	}
    }

    public Account find(String mobile)
    {
	if (allAccounts.containsKey(mobile))
	{
	    return allAccounts.get(mobile);
	}
	else
	{
	    return new Account("null", "null", null);
	}
    }

}
